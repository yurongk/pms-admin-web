package com.yurongku.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_role")
@TableName("sys_role")
public class Role extends BaseEntity {

    @Column(name = "role_name", unique = true, nullable = false, length = 50)
    private String roleName;

    @Column(name = "role_code", unique = true, nullable = false, length = 50)
    private String roleCode;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "status", nullable = false)
    private Integer status = 1; // 1: 启用, 0: 禁用

    @Column(name = "sort_order")
    private Integer sortOrder = 0;
}
