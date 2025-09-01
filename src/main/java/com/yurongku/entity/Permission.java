package com.yurongku.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_permission")
@TableName("sys_permission")
public class Permission extends BaseEntity {

    @Column(name = "permission_name", nullable = false, length = 100)
    private String permissionName;

    @Column(name = "permission_code", unique = true, nullable = false, length = 100)
    private String permissionCode;

    @Column(name = "permission_type", nullable = false, length = 20)
    private String permissionType; // MENU: 菜单, BUTTON: 按钮, API: 接口

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "path", length = 200)
    private String path;

    @Column(name = "component", length = 200)
    private String component;

    @Column(name = "icon", length = 50)
    private String icon;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "status", nullable = false)
    private Integer status = 1; // 1: 启用, 0: 禁用

    @Column(name = "sort_order")
    private Integer sortOrder = 0;
}
