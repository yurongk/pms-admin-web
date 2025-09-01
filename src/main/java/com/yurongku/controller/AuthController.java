package com.yurongku.controller;

import com.yurongku.dto.ApiResponse;
import com.yurongku.dto.LoginRequest;
import com.yurongku.dto.LoginResponse;
import com.yurongku.entity.User;
import com.yurongku.security.JwtTokenService;
import com.yurongku.service.UserService;
import com.yurongku.utils.PasswordUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    public AuthController(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.getByUsername(request.getUsername());
        if (user == null) {
            return ApiResponse.fail(401, "用户不存在或密码错误");
        }
        if (!PasswordUtil.matches(request.getPassword(), user.getPassword())) {
            return ApiResponse.fail(401, "用户不存在或密码错误");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("username", user.getUsername());
        String token = jwtTokenService.generateToken(user.getUsername(), claims);
        LoginResponse resp = new LoginResponse();
        resp.setToken(token);
        resp.setUserId(user.getId());
        resp.setUsername(user.getUsername());
        return ApiResponse.success(resp);
    }
}
