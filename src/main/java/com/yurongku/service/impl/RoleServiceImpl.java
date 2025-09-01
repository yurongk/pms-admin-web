package com.yurongku.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.AssignRolePermissionsRequest;
import com.yurongku.dto.RoleDto;
import com.yurongku.entity.Permission;
import com.yurongku.entity.Role;
import com.yurongku.mapper.PermissionMapper;
import com.yurongku.mapper.RoleMapper;
import com.yurongku.mapper.RolePermissionMapper;
import com.yurongku.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleMapper roleMapper;
	private final PermissionMapper permissionMapper;
	private final RolePermissionMapper rolePermissionMapper;

	public RoleServiceImpl(RoleMapper roleMapper, PermissionMapper permissionMapper, RolePermissionMapper rolePermissionMapper) {
		this.roleMapper = roleMapper;
		this.permissionMapper = permissionMapper;
		this.rolePermissionMapper = rolePermissionMapper;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Role createRole(RoleDto request) {
		Role role = new Role();
		role.setRoleName(request.getRoleName());
		role.setRoleCode(request.getRoleCode());
		role.setDescription(request.getDescription());
		role.setStatus(request.getStatus());
		role.setSortOrder(0);
		roleMapper.insert(role);
		return role;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Role updateRole(Long id, RoleDto request) {
		Role role = roleMapper.selectById(id);
		if (role == null) {
			return null;
		}
		role.setRoleName(request.getRoleName());
		role.setRoleCode(request.getRoleCode());
		role.setDescription(request.getDescription());
		role.setStatus(request.getStatus());
		roleMapper.updateById(role);
		return role;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteRole(Long id) {
		roleMapper.deleteById(id);
		rolePermissionMapper.deleteByRoleId(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteRoles(List<Long> ids) {
		ids.forEach(this::deleteRole);
	}

	@Override
	public Role getRole(Long id) {
		return roleMapper.selectById(id);
	}

	@Override
	public IPage<Role> pageRoles(Page<Role> page, String keyword, Integer status) {
		return roleMapper.selectPageRoles(page, keyword, status);
	}

	@Override
	public List<Role> listAllRoles(Integer status) {
		return roleMapper.selectAllRoles(status);
	}

	@Override
	public List<Permission> listAssignablePermissions() {
		return permissionMapper.selectAllPermissions(1);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void assignPermissions(AssignRolePermissionsRequest request) {
		rolePermissionMapper.deleteByRoleId(request.getRoleId());
		if (request.getPermissionIds() != null && !request.getPermissionIds().isEmpty()) {
			rolePermissionMapper.insertBatch(request.getRoleId(), request.getPermissionIds());
		}
	}

	@Override
	public List<Long> listPermissionIdsByRole(Long roleId) {
		return rolePermissionMapper.selectPermissionIdsByRoleId(roleId);
	}
}


