package com.yurongku.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AssignRolePermissionsRequest {

    @NotNull
    private Long roleId;

    @NotNull
    private List<Long> permissionIds;
}
