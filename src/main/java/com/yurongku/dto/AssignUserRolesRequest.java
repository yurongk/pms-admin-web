package com.yurongku.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AssignUserRolesRequest {

    @NotNull
    private Long userId;

    @NotNull
    private List<Long> roleIds;
}
