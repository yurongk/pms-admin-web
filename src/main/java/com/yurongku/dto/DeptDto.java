package com.yurongku.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class DeptDto {

    private Long id;

    @NotBlank(message = "部门名称不能为空")
    @Size(max = 100, message = "部门名称长度不能超过100个字符")
    private String deptName;

    @Size(max = 50, message = "部门编码长度不能超过50个字符")
    private String deptCode;

    private Long parentId;

    private String ancestors;

    @Size(max = 50, message = "负责人长度不能超过50个字符")
    private String leader;

    @Size(max = 20, message = "电话号码长度不能超过20个字符")
    private String phone;

    @Size(max = 100, message = "邮箱长度不能超过100个字符")
    private String email;

    private Integer status = 1;

    private Integer sortOrder = 0;

    @Size(max = 200, message = "描述长度不能超过200个字符")
    private String description;
}
