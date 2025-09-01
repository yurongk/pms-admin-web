package com.yurongku.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.ApiResponse;
import com.yurongku.dto.AssignRolePermissionsRequest;
import com.yurongku.dto.RoleDto;
import com.yurongku.entity.Permission;
import com.yurongku.entity.Role;
import com.yurongku.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Tag(name = "Role APIs", description = "角色管理：分页、查询、增删改、分配权限")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping
	@Operation(summary = "创建角色", description = "创建一个新角色，roleCode 需唯一")
	public ApiResponse<Role> create(@Valid @RequestBody RoleDto request) {
		Role role = roleService.createRole(request);
		return ApiResponse.success(role);
	}

	@PutMapping("/{id}")
	@Operation(summary = "更新角色", description = "根据 ID 更新角色")
	public ApiResponse<Role> update(@Parameter(description = "角色ID") @PathVariable Long id, @Valid @RequestBody RoleDto request) {
		Role role = roleService.updateRole(id, request);
		return ApiResponse.success(role);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "删除角色", description = "根据 ID 逻辑删除角色")
	public ApiResponse<Void> delete(@Parameter(description = "角色ID") @PathVariable Long id) {
		roleService.deleteRole(id);
		return ApiResponse.success(null);
	}

	@DeleteMapping
	@Operation(summary = "批量删除角色", description = "根据 ID 列表逻辑删除角色")
	public ApiResponse<Void> deleteBatch(@Parameter(description = "角色ID 列表") @RequestParam List<Long> ids) {
		roleService.deleteRoles(ids);
		return ApiResponse.success(null);
	}

	@GetMapping("/{id}")
	@Operation(summary = "角色详情", description = "根据 ID 获取角色详情")
	public ApiResponse<Role> detail(@Parameter(description = "角色ID") @PathVariable Long id) {
		Role role = roleService.getRole(id);
		return ApiResponse.success(role);
	}

	@GetMapping("/page")
	@Operation(summary = "分页查询角色", description = "支持关键字、状态筛选")
	public ApiResponse<IPage<Role>> page(@Parameter(description = "页码，从1开始") @RequestParam(defaultValue = "1") long page,
	                                    @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") long size,
	                                    @Parameter(description = "关键字：名称/编码") @RequestParam(required = false) String keyword,
	                                    @Parameter(description = "状态：1启用 0禁用") @RequestParam(required = false) Integer status) {
		IPage<Role> result = roleService.pageRoles(new Page<>(page, size), keyword, status);
		return ApiResponse.success(result);
	}

	@GetMapping("/all")
	@Operation(summary = "查询全部角色", description = "支持状态筛选")
	public ApiResponse<List<Role>> listAll(@Parameter(description = "状态：1启用 0禁用") @RequestParam(required = false) Integer status) {
		List<Role> list = roleService.listAllRoles(status);
		return ApiResponse.success(list);
	}

	@GetMapping("/permissions")
	@Operation(summary = "查询全部权限(用于分配)", description = "返回启用状态权限列表可供分配")
	public ApiResponse<List<Permission>> listPermissionsForAssign() {
		List<Permission> list = roleService.listAssignablePermissions();
		return ApiResponse.success(list);
	}

	@PostMapping("/assign-permissions")
	@Operation(summary = "分配权限给角色", description = "替换角色的权限集合")
	public ApiResponse<Void> assignPermissions(@Valid @RequestBody AssignRolePermissionsRequest request) {
		roleService.assignPermissions(request);
		return ApiResponse.success(null);
	}

	@GetMapping("/{roleId}/permission-ids")
	@Operation(summary = "查询角色已分配的权限ID列表", description = "用于回显勾选状态")
	public ApiResponse<List<Long>> listPermissionIds(@Parameter(description = "角色ID") @PathVariable Long roleId) {
		List<Long> ids = roleService.listPermissionIdsByRole(roleId);
		return ApiResponse.success(ids);
	}
}


