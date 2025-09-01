package com.yurongku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    // 分页查询部门
    IPage<Dept> selectPageDepts(Page<Dept> page,
                                @Param("keyword") String keyword,
                                @Param("status") Integer status);

    // 查询所有部门
    List<Dept> selectAllDepts(@Param("status") Integer status);
}
