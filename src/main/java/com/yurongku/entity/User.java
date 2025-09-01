package com.yurongku.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_user")
@TableName("sys_user")
public class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "real_name", length = 50)
    private String realName;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "status", nullable = false)
    private Integer status = 1; // 1: 启用, 0: 禁用

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Column(name = "last_login_ip", length = 50)
    private String lastLoginIp;

    @Column(name = "dept_id")
    private Long deptId; // 主部门ID（用于显示和默认权限）
}
