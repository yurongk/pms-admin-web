package com.yurongku.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.ApiResponse;
import com.yurongku.dto.CreateUserRequest;
import com.yurongku.entity.User;
import com.yurongku.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);
        return ApiResponse.success(user);
    }

    @GetMapping("/page")
    public ApiResponse<IPage<User>> pageUsers(@RequestParam(defaultValue = "1") long page,
                                              @RequestParam(defaultValue = "10") long size,
                                              @RequestParam(required = false) String keyword,
                                              @RequestParam(required = false) Integer status) {
        IPage<User> result = userService.pageUsers(new Page<>(page, size), keyword, status);
        return ApiResponse.success(result);
    }

    @GetMapping("/all")
    public ApiResponse<List<User>> listAllUsers(@RequestParam(required = false) Integer status) {
        List<User> list = userService.listAllUsers(status);
        return ApiResponse.success(list);
    }
}
