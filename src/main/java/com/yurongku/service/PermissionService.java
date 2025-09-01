package com.yurongku.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.PermissionDto;
import com.yurongku.dto.PermissionTreeDto;
import com.yurongku.entity.Permission;

import java.util.List;

public interface PermissionService {

	Permission createPermission(PermissionDto request);

	Permission updatePermission(Long id, PermissionDto request);

	void deletePermission(Long id);

	Permission getPermission(Long id);

	IPage<Permission> pagePermissions(Page<Permission> page, String keyword, Integer status, String permissionType, Long parentId);

	List<Permission> listAllPermissions(Integer status, String permissionType, Long parentId);

	List<PermissionTreeDto> listPermissionTree(Integer status);

	void updatePermissionStatus(Long id, Integer status);

}


