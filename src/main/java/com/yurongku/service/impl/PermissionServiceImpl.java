package com.yurongku.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.PermissionDto;
import com.yurongku.dto.PermissionTreeDto;
import com.yurongku.entity.Permission;
import com.yurongku.mapper.PermissionMapper;
import com.yurongku.service.PermissionService;
import com.yurongku.utils.TreeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

	private final PermissionMapper permissionMapper;

	public PermissionServiceImpl(PermissionMapper permissionMapper) {
		this.permissionMapper = permissionMapper;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Permission createPermission(PermissionDto request) {
		Permission permission = new Permission();
		permission.setPermissionName(request.getPermissionName());
		permission.setPermissionCode(request.getPermissionCode());
		permission.setPermissionType(request.getPermissionType());
		permission.setParentId(request.getParentId());
		permission.setPath(request.getPath());
		permission.setComponent(null);
		permission.setIcon(null);
		permission.setDescription(request.getDescription());
		permission.setStatus(request.getStatus());
		permission.setSortOrder(request.getSortOrder());
		permissionMapper.insert(permission);
		return permission;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Permission updatePermission(Long id, PermissionDto request) {
		Permission permission = permissionMapper.selectById(id);
		if (permission == null) {
			return null;
		}
		permission.setPermissionName(request.getPermissionName());
		permission.setPermissionCode(request.getPermissionCode());
		permission.setPermissionType(request.getPermissionType());
		permission.setParentId(request.getParentId());
		permission.setPath(request.getPath());
		permission.setDescription(request.getDescription());
		permission.setStatus(request.getStatus());
		permission.setSortOrder(request.getSortOrder());
		permissionMapper.updateById(permission);
		return permission;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deletePermission(Long id) {
		permissionMapper.deleteById(id);
	}

	@Override
	public Permission getPermission(Long id) {
		return permissionMapper.selectById(id);
	}

	@Override
	public IPage<Permission> pagePermissions(Page<Permission> page, String keyword, Integer status, String permissionType, Long parentId) {
		return permissionMapper.selectPagePermissions(page, keyword, status, permissionType, parentId);
	}

	@Override
	public List<Permission> listAllPermissions(Integer status, String permissionType, Long parentId) {
		QueryWrapper<Permission> qw = new QueryWrapper<>();
		qw.eq("is_deleted", 0);
		if (status != null) {
			qw.eq("status", status);
		}
		if (permissionType != null && !permissionType.isEmpty()) {
			qw.eq("permission_type", permissionType);
		}
		if (parentId != null) {
			qw.eq("parent_id", parentId);
		}
		qw.orderByAsc("sort_order").orderByDesc("id");
		return permissionMapper.selectList(qw);
	}

	@Override
	public List<PermissionTreeDto> listPermissionTree(Integer status) {
		List<Permission> permissions = listAllPermissions(status, null, null);
		List<PermissionTreeDto> nodes = permissions.stream().map(p -> {
			PermissionTreeDto node = new PermissionTreeDto();
			node.setId(p.getId());
			node.setParentId(p.getParentId());
			node.setPermissionName(p.getPermissionName());
			node.setPermissionCode(p.getPermissionCode());
			node.setPermissionType(p.getPermissionType());
			node.setPath(p.getPath());
			node.setSortOrder(p.getSortOrder());
			node.setStatus(p.getStatus());
			return node;
		}).collect(Collectors.toList());
		return TreeUtil.buildTree(nodes);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePermissionStatus(Long id, Integer status) {
		Permission permission = permissionMapper.selectById(id);
		if (permission == null) {
			return;
		}
		permission.setStatus(status);
		permissionMapper.updateById(permission);
	}
}


