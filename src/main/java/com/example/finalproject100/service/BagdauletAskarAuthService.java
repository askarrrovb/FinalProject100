package com.example.finalproject100.service;

import com.example.finalproject100.dto.request.BagdauletAskarLoginRequest;
import com.example.finalproject100.dto.request.BagdauletAskarRegisterRequest;
import com.example.finalproject100.dto.response.BagdauletAskarAuthResponse;
import com.example.finalproject100.entity.BagdauletAskarUser;
import com.example.finalproject100.repository.BagdauletAskarUserRepository;
import com.example.finalproject100.security.BagdauletAskarJwtUtil;
import com.example.finalproject100.security.BagdauletAskarUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BagdauletAskarAuthService {

    private final BagdauletAskarUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BagdauletAskarJwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final BagdauletAskarUserDetailsService userDetailsService;

    public BagdauletAskarAuthResponse register(BagdauletAskarRegisterRequest request) {
        log.info("Registering new user: {}", request.getUsername());
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already taken: " + request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use: " + request.getEmail());
        }
        BagdauletAskarUser user = BagdauletAskarUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        userRepository.save(user);
        log.info("User registered successfully: {}", user.getUsername());
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return new BagdauletAskarAuthResponse(token, user.getUsername(), user.getRole().name());
    }

    public BagdauletAskarAuthResponse login(BagdauletAskarLoginRequest request) {
        log.info("Login attempt for user: {}", request.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        BagdauletAskarUser user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        log.info("User logged in successfully: {}", request.getUsername());
        return new BagdauletAskarAuthResponse(token, user.getUsername(), user.getRole().name());
    }
}
