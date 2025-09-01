package com.yurongku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    IPage<Menu> selectPageMenus(Page<Menu> page, @Param("keyword") String keyword, @Param("status") Integer status);

    List<Menu> selectAllMenus(@Param("status") Integer status);

    List<Menu> selectByRoleId(@Param("roleId") Long roleId);
}
