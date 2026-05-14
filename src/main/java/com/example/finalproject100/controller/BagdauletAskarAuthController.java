package com.example.finalproject100.controller;

import com.example.finalproject100.dto.request.BagdauletAskarLoginRequest;
import com.example.finalproject100.dto.request.BagdauletAskarRegisterRequest;
import com.example.finalproject100.dto.response.BagdauletAskarAuthResponse;
import com.example.finalproject100.service.BagdauletAskarAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Register and login endpoints")
public class BagdauletAskarAuthController {

    private final BagdauletAskarAuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Creates a new user account and returns JWT token")
    @ApiResponse(responseCode = "200", description = "User registered successfully")
    @ApiResponse(responseCode = "400", description = "Username or email already exists")
    public ResponseEntity<BagdauletAskarAuthResponse> register(
            @Valid @RequestBody BagdauletAskarRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticates user and returns JWT token")
    @ApiResponse(responseCode = "200", description = "Login successful")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    public ResponseEntity<BagdauletAskarAuthResponse> login(
            @Valid @RequestBody BagdauletAskarLoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
