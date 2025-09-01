package com.yurongku.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String phone;

    private String realName;

    private String avatar;

    private Integer status;

    private Long deptId;

    private LocalDateTime createdTime;
}
