package com.yurongku.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "用户名不能为空")
    @Size(max = 50)
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Email(message = "邮箱格式不正确")
    @Size(max = 100)
    private String email;

    @Size(max = 20)
    private String phone;

    @Size(max = 50)
    private String realName;

    @Size(max = 255)
    private String avatar;

    private Integer status = 1;

    private Long deptId;
}
