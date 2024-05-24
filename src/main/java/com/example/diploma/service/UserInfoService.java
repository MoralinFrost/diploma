package com.example.diploma.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.diploma.Entity.AuthRequest;
import com.example.diploma.Entity.Role;
import com.example.diploma.Entity.User;
import com.example.diploma.repos.UserRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    @Autowired
    private UserRepos userRepos;




    @Autowired
    private PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    @Autowired
    private MailSenderService mailSender;

    public Optional<User> getUserByUsername(String email) {
        return userRepos.findByEmail(email);
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userDetail=userRepos.findByEmail(username);
//
//        return userDetail.map(UserInfoDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
//    }


    public AuthRequest registerNewUser(User registerRequest) {

        var user = User.builder()
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .password(encoder.encode(registerRequest.getPassword()))
                .roles(Role.USER)
                .build();
        userRepos.save(user);

        var jwtToken = jwtService.generateToken(UserInfoDetails.build(user));
        return AuthRequest.builder()
                .token(jwtToken)
                .email(user.getName()).build();

    }

    @Transactional
    public AuthRequest authentication(AuthRequest authenticationDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDTO.getEmail(), authenticationDTO.getPassword())
        );
        var user = userRepos.findByEmail(authenticationDTO.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(UserInfoDetails.build(user));
        return AuthRequest.builder()
                .token(jwtToken)
                .email(user.getEmail()).build();
    }

    public List<User> getAllUsers() {
        return userRepos.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepos.findById(id);
    }

    public User updateUser(User user) {
        return userRepos.save(user);
    }





}
