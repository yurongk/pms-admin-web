package com.yurongku.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.CreateUserRequest;
import com.yurongku.dto.DeptDto;
import com.yurongku.dto.DeptTreeDto;
import com.yurongku.entity.Dept;
import com.yurongku.entity.User;
import com.yurongku.entity.UserDept;
import com.yurongku.mapper.DeptMapper;
import com.yurongku.mapper.UserDeptMapper;
import com.yurongku.mapper.UserMapper;
import com.yurongku.service.DeptService;
import com.yurongku.service.UserService;
import com.yurongku.utils.PasswordUtil;
import com.yurongku.utils.TreeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Dept createDept(DeptDto dto) {
        Dept dept = new Dept();
        dept.setDeptName(dto.getDeptName());
        dept.setDeptCode(dto.getDeptCode());
        dept.setParentId(dto.getParentId());
        dept.setAncestors(dto.getAncestors());
        dept.setLeader(dto.getLeader());
        dept.setPhone(dto.getPhone());
        dept.setEmail(dto.getEmail());
        dept.setStatus(dto.getStatus());
        dept.setSortOrder(dto.getSortOrder());
        dept.setDescription(dto.getDescription());
        deptMapper.insert(dept);
        return dept;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Dept updateDept(Long id, DeptDto dto) {
        Dept dept = deptMapper.selectById(id);
        if (dept == null) {
            throw new RuntimeException("部门不存在");
        }
        dept.setDeptName(dto.getDeptName());
        dept.setDeptCode(dto.getDeptCode());
        dept.setParentId(dto.getParentId());
        dept.setAncestors(dto.getAncestors());
        dept.setLeader(dto.getLeader());
        dept.setPhone(dto.getPhone());
        dept.setEmail(dto.getEmail());
        dept.setStatus(dto.getStatus());
        dept.setSortOrder(dto.getSortOrder());
        dept.setDescription(dto.getDescription());
        deptMapper.updateById(dept);
        return dept;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDept(Long id) {
        deptMapper.deleteById(id);
    }

    @Override
    public Dept getDept(Long id) {
        return deptMapper.selectById(id);
    }

    @Override
    public IPage<Dept> pageDepts(Page<Dept> page, String keyword, Integer status) {
        return deptMapper.selectPageDepts(page, keyword, status);
    }

    @Override
    public List<Dept> listAllDepts(Integer status) {
        return deptMapper.selectAllDepts(status);
    }

    @Override
    public List<DeptTreeDto> getDeptTree() {
        List<Dept> list = deptMapper.selectList(null);
        List<DeptTreeDto> dtoList = list.stream().map(d -> {
            DeptTreeDto dto = new DeptTreeDto();
            dto.setId(d.getId());
            dto.setDeptName(d.getDeptName());
            dto.setDeptCode(d.getDeptCode());
            dto.setParentId(d.getParentId());
            dto.setAncestors(d.getAncestors());
            dto.setLeader(d.getLeader());
            dto.setPhone(d.getPhone());
            dto.setEmail(d.getEmail());
            dto.setStatus(d.getStatus());
            dto.setSortOrder(d.getSortOrder());
            dto.setDescription(d.getDescription());
            dto.setCreatedTime(d.getCreatedTime());
            dto.setCreatedBy(d.getCreatedBy());
            return dto;
        }).collect(Collectors.toList());

        return TreeUtil.buildTree(dtoList);
    }
}
