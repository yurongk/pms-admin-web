package com.yurongku.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleDto {

    private Long id;

    @NotBlank(message = "角色名称不能为空")
    @Size(max = 50)
    private String roleName;

    @NotBlank(message = "角色编码不能为空")
    @Size(max = 50)
    private String roleCode;

    @Size(max = 200)
    private String description;

    private Integer status = 1;

    private Integer sortOrder = 0;
}
