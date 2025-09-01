package com.yurongku.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.CreateUserRequest;
import com.yurongku.dto.DeptDto;
import com.yurongku.dto.DeptTreeDto;
import com.yurongku.entity.Dept;
import com.yurongku.entity.User;

import java.util.List;

public interface DeptService {

    Dept createDept(DeptDto dto);

    Dept updateDept(Long id, DeptDto dto);

    void deleteDept(Long id);

    Dept getDept(Long id);

    IPage<Dept> pageDepts(Page<Dept> page, String keyword, Integer status);

    List<Dept> listAllDepts(Integer status);

    List<DeptTreeDto> getDeptTree();
}
