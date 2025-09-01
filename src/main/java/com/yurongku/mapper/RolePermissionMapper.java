package com.yurongku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yurongku.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

	int deleteByRoleId(@Param("roleId") Long roleId);

	int insertBatch(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

	List<Long> selectPermissionIdsByRoleId(@Param("roleId") Long roleId);
}


