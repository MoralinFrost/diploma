package com.example.diploma.service;

import com.example.diploma.dto.JwtAuthenticationResponse;
import com.example.diploma.dto.SignInRequest;
import com.example.diploma.dto.SignUpRequest;
import com.example.diploma.entity.User;
import com.example.diploma.repository.RoleRepository;
import com.example.diploma.repository.UserRepository;
import com.example.diploma.security.JwtService;
import com.example.diploma.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = new User();
        user.setFirstname(request.firstname());
        user.setSurname(request.lastname());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRoles(Set.of(roleRepository.findByName("USER").orElseThrow()));

        User savedUser = userRepository.save(user);
        String jwt = jwtService.generateToken(PrincipalUser.of(savedUser));
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String jwt = jwtService.generateToken(PrincipalUser.of(user));
        return new JwtAuthenticationResponse(jwt);
    }

}
