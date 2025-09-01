package com.yurongku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    IPage<Role> selectPageRoles(Page<Role> page, @Param("keyword") String keyword, @Param("status") Integer status);

    List<Role> selectAllRoles(@Param("status") Integer status);
}
