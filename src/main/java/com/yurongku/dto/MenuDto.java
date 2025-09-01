package com.yurongku.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class MenuDto {

    private Long id;

    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 100, message = "菜单名称长度不能超过100个字符")
    private String menuName;

    @Size(max = 100, message = "菜单编码长度不能超过100个字符")
    private String menuCode;

    private Long parentId;

    @NotBlank(message = "菜单类型不能为空")
    @Size(max = 20, message = "菜单类型长度不能超过20个字符")
    private String menuType;

    @Size(max = 200, message = "路由地址长度不能超过200个字符")
    private String path;

    @Size(max = 200, message = "组件路径长度不能超过200个字符")
    private String component;

    @Size(max = 100, message = "权限标识长度不能超过100个字符")
    private String perms;

    @Size(max = 50, message = "图标长度不能超过50个字符")
    private String icon;

    private Integer visible = 1;

    private Integer status = 1;

    private Integer sortOrder = 0;

    private Integer isFrame = 0;

    private Integer isCache = 0;

    @Size(max = 200, message = "描述长度不能超过200个字符")
    private String description;
}
