package com.yurongku.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PermissionDto {

    private Long id;

    @NotBlank(message = "权限名称不能为空")
    @Size(max = 100)
    private String permissionName;

    @NotBlank(message = "权限编码不能为空")
    @Size(max = 100)
    private String permissionCode;

    @NotBlank(message = "权限类型不能为空")
    @Size(max = 20)
    private String permissionType; // API/BUTTON

    private Long parentId;

    @Size(max = 200)
    private String path;

    @Size(max = 200)
    private String description;

    private Integer status = 1;

    private Integer sortOrder = 0;
}
