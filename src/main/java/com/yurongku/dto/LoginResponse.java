package com.yurongku.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;

    private Long userId;

    private String username;
}
