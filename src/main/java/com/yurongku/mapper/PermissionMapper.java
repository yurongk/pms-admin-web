package com.yurongku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    IPage<Permission> selectPagePermissions(Page<Permission> page,
                                            @Param("keyword") String keyword,
                                            @Param("status") Integer status,
                                            @Param("permissionType") String permissionType,
                                            @Param("parentId") Long parentId);

    List<Permission> selectAllPermissions(@Param("status") Integer status);
}
