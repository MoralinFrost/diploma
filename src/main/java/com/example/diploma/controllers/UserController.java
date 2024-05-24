package com.example.diploma.controllers;

import com.example.diploma.Entity.AuthRequest;
import com.example.diploma.Entity.User;
import com.example.diploma.service.JwtService;
import com.example.diploma.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Value("/home/moralin/Documents/files")
    private String filesPath;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/profile")
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<User> getProfile() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Optional<User> user = userInfoService.getUserByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<User> updateProfile(@RequestParam(value = "photo", required = false) MultipartFile photo) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Optional<User> user = userInfoService.getUserByUsername(username);
        if (user.isPresent()) {
            try {
                User existingUser = user.get();

                if (photo != null && !photo.isEmpty()) {
                    String photoUrl = savePhoto(photo);
                    existingUser.setPhoto(photoUrl);
                }

                User updated = userInfoService.updateUser(existingUser);
                return ResponseEntity.ok(updated);
            } catch (IOException e) {
                return ResponseEntity.status(500).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String savePhoto(MultipartFile photo) throws IOException {
        String fileName = photo.getOriginalFilename();
        Path filePath = Path.of(filesPath, fileName);
        Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }


    @PostMapping("/register")

    public AuthRequest registerNewUser(@RequestBody User registerRequest)
            throws NoSuchAlgorithmException {
        return userInfoService.registerNewUser(registerRequest);

    }

    @PostMapping("/authenticate")
    public AuthRequest authenticateUser(@RequestBody AuthRequest authenticationRequest) {
        return userInfoService.authentication(authenticationRequest);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userInfoService.getAllUsers();
    }






}
