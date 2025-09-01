package com.yurongku.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.ApiResponse;
import com.yurongku.dto.PermissionDto;
import com.yurongku.dto.PermissionTreeDto;
import com.yurongku.entity.Permission;
import com.yurongku.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@Tag(name = "Permission APIs", description = "权限管理：增删改查、分页、树形、状态、校验、批量操作")
public class PermissionController {

	private final PermissionService permissionService;

	public PermissionController(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@PostMapping
	@Operation(summary = "创建权限", description = "创建一个新的权限，permissionCode 需唯一")
	public ApiResponse<Permission> create(@Valid @RequestBody PermissionDto request) {
		Permission permission = permissionService.createPermission(request);
		return ApiResponse.success(permission);
	}

	@PutMapping("/{id}")
	@Operation(summary = "更新权限", description = "根据 ID 更新权限基础信息")
	public ApiResponse<Permission> update(@Parameter(description = "权限ID") @PathVariable Long id, @Valid @RequestBody PermissionDto request) {
		Permission permission = permissionService.updatePermission(id, request);
		return ApiResponse.success(permission);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除权限", description = "根据 ID 逻辑删除权限")
	public ApiResponse<Void> delete(@Parameter(description = "权限ID") @PathVariable Long id) {
		permissionService.deletePermission(id);
		return ApiResponse.success(null);
	}

	@DeleteMapping
	@Operation(summary = "批量删除权限", description = "根据 ID 列表逻辑删除权限")
	public ApiResponse<Void> deleteBatch(@Parameter(description = "权限ID 列表") @RequestParam List<Long> ids) {
		ids.forEach(permissionService::deletePermission);
		return ApiResponse.success(null);
	}

	@GetMapping("/{id}")
	@Operation(summary = "权限详情", description = "根据 ID 获取权限详情")
	public ApiResponse<Permission> detail(@Parameter(description = "权限ID") @PathVariable Long id) {
		Permission permission = permissionService.getPermission(id);
		return ApiResponse.success(permission);
	}

	@GetMapping("/page")
	@Operation(summary = "分页查询权限", description = "支持关键字、状态、类型、父ID筛选")
	public ApiResponse<IPage<Permission>> page(@Parameter(description = "页码，从1开始") @RequestParam(defaultValue = "1") long page,
	                                          @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") long size,
	                                          @Parameter(description = "关键字：名称/编码/描述") @RequestParam(required = false) String keyword,
	                                          @Parameter(description = "状态：1启用 0禁用") @RequestParam(required = false) Integer status,
	                                          @Parameter(description = "类型：API/BUTTON/MENU 等") @RequestParam(required = false) String permissionType,
	                                          @Parameter(description = "父级ID") @RequestParam(required = false) Long parentId) {
		IPage<Permission> result = permissionService.pagePermissions(new Page<>(page, size), keyword, status, permissionType, parentId);
		return ApiResponse.success(result);
	}

	@GetMapping("/all")
	@Operation(summary = "查询全部权限", description = "支持状态、类型、父ID筛选")
	public ApiResponse<List<Permission>> listAll(@Parameter(description = "状态：1启用 0禁用") @RequestParam(required = false) Integer status,
	                                            @Parameter(description = "类型：API/BUTTON/MENU 等") @RequestParam(required = false) String permissionType,
	                                            @Parameter(description = "父级ID") @RequestParam(required = false) Long parentId) {
		List<Permission> list = permissionService.listAllPermissions(status, permissionType, parentId);
		return ApiResponse.success(list);
	}

	@GetMapping("/tree")
	@Operation(summary = "权限树", description = "按父子关系返回树形结构，可按状态筛选")
	public ApiResponse<List<PermissionTreeDto>> tree(@Parameter(description = "状态：1启用 0禁用") @RequestParam(required = false) Integer status) {
		List<PermissionTreeDto> list = permissionService.listPermissionTree(status);
		return ApiResponse.success(list);
	}

	@PatchMapping("/{id}/status")
	@Operation(summary = "更新权限状态", description = "根据 ID 启用/禁用权限")
	public ApiResponse<Void> updateStatus(@Parameter(description = "权限ID") @PathVariable Long id, @Parameter(description = "状态：1启用 0禁用") @RequestParam Integer status) {
		permissionService.updatePermissionStatus(id, status);
		return ApiResponse.success(null);
	}

	@PatchMapping("/status")
	@Operation(summary = "批量更新权限状态", description = "根据 ID 列表批量启用/禁用权限")
	public ApiResponse<Void> updateStatusBatch(@Parameter(description = "权限ID 列表") @RequestParam List<Long> ids,
	                                          @Parameter(description = "状态：1启用 0禁用") @RequestParam Integer status) {
		ids.forEach(id -> permissionService.updatePermissionStatus(id, status));
		return ApiResponse.success(null);
	}

	@GetMapping("/check-code")
	@Operation(summary = "校验权限编码唯一性", description = "返回 true 表示可用，false 表示已存在")
	public ApiResponse<Boolean> checkCode(@Parameter(description = "权限编码") @RequestParam String permissionCode) {
		List<Permission> list = permissionService.listAllPermissions(null, null, null);
		boolean available = list.stream().noneMatch(p -> permissionCode.equalsIgnoreCase(p.getPermissionCode()));
		return ApiResponse.success(available);
	}
}


