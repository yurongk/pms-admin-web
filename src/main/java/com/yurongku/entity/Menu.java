package com.yurongku.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_menu")
@TableName("sys_menu")
public class Menu extends BaseEntity {

    @Column(name = "menu_name", nullable = false, length = 100)
    private String menuName;

    @Column(name = "menu_code", unique = true, length = 100)
    private String menuCode;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "menu_type", nullable = false, length = 20)
    private String menuType; // MENU: 菜单, BUTTON: 按钮

    @Column(name = "path", length = 200)
    private String path; // 路由地址

    @Column(name = "component", length = 200)
    private String component; // 组件路径

    @Column(name = "perms", length = 100)
    private String perms; // 权限标识

    @Column(name = "icon", length = 50)
    private String icon; // 图标

    @Column(name = "visible", nullable = false)
    private Integer visible = 1; // 1: 显示, 0: 隐藏

    @Column(name = "status", nullable = false)
    private Integer status = 1; // 1: 启用, 0: 禁用

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "is_frame", nullable = false)
    private Integer isFrame = 0; // 1: 外链, 0: 内链

    @Column(name = "is_cache", nullable = false)
    private Integer isCache = 0; // 1: 缓存, 0: 不缓存

    @Column(name = "description", length = 200)
    private String description;
}
