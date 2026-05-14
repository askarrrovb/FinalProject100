package com.example.finalproject100.controller;

import com.example.finalproject100.dto.request.BagdauletAskarLoginRequest;
import com.example.finalproject100.dto.request.BagdauletAskarRegisterRequest;
import com.example.finalproject100.dto.response.BagdauletAskarAuthResponse;
import com.example.finalproject100.service.BagdauletAskarAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class BagdauletAskarAuthController {

    private final BagdauletAskarAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<BagdauletAskarAuthResponse> register(
            @Valid @RequestBody BagdauletAskarRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<BagdauletAskarAuthResponse> login(
            @Valid @RequestBody BagdauletAskarLoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
