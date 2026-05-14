package com.example.finalproject100.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BagdauletAskarAuthResponse {

    private String token;
    private String username;
    private String role;
}
