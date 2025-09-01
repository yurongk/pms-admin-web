package com.yurongku.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yurongku.dto.ApiResponse;
import com.yurongku.dto.CreateUserRequest;
import com.yurongku.dto.DeptDto;
import com.yurongku.dto.DeptTreeDto;
import com.yurongku.entity.Dept;
import com.yurongku.entity.User;
import com.yurongku.service.DeptService;
import com.yurongku.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/depts")
public class DeptController {

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @PostMapping
    public ApiResponse<Dept> createDept(@Valid @RequestBody DeptDto dto) {
        Dept dept = deptService.createDept(dto);
        return ApiResponse.success(dept);
    }

    @PutMapping("/{id}")
    public ApiResponse<Dept> updateDept(@PathVariable Long id, @Valid @RequestBody DeptDto dto) {
        Dept dept = deptService.updateDept(id, dto);
        return ApiResponse.success(dept);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDept(@PathVariable Long id) {
        deptService.deleteDept(id);
        return ApiResponse.success(null);
    }

    @GetMapping("/{id}")
    public ApiResponse<Dept> getDept(@PathVariable Long id) {
        Dept dept = deptService.getDept(id);
        return ApiResponse.success(dept);
    }

    @GetMapping("/page")
    public ApiResponse<IPage<Dept>> pageDepts(@RequestParam(defaultValue = "1") long page,
                                              @RequestParam(defaultValue = "10") long size,
                                              @RequestParam(required = false) String keyword,
                                              @RequestParam(required = false) Integer status) {
        IPage<Dept> result = deptService.pageDepts(new Page<>(page, size), keyword, status);
        return ApiResponse.success(result);
    }

    @GetMapping("/tree")
    public ApiResponse<List<DeptTreeDto>> getDeptTree() {
        List<DeptTreeDto> tree = deptService.getDeptTree();
        return ApiResponse.success(tree);
    }
}