package com.yurongku.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AssignRoleMenusRequest {

    @NotNull
    private Long roleId;

    @NotNull
    private List<Long> menuIds;
}
