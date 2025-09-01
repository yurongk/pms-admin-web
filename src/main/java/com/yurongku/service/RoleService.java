package com.yurongku.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.AssignRolePermissionsRequest;
import com.yurongku.dto.RoleDto;
import com.yurongku.entity.Permission;
import com.yurongku.entity.Role;

import java.util.List;

public interface RoleService {

	Role createRole(RoleDto request);

	Role updateRole(Long id, RoleDto request);

	void deleteRole(Long id);

	void deleteRoles(List<Long> ids);

	Role getRole(Long id);

	IPage<Role> pageRoles(Page<Role> page, String keyword, Integer status);

	List<Role> listAllRoles(Integer status);

	List<Permission> listAssignablePermissions();

	void assignPermissions(AssignRolePermissionsRequest request);

	List<Long> listPermissionIdsByRole(Long roleId);
}


