package com.yurongku.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_dept")
@TableName("sys_dept")
public class Dept extends BaseEntity {

    @Column(name = "dept_name", nullable = false, length = 100)
    private String deptName;

    @Column(name = "dept_code", unique = true, length = 50)
    private String deptCode;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "ancestors", length = 500)
    private String ancestors; // 祖级列表，用逗号分隔

    @Column(name = "leader", length = 50)
    private String leader; // 负责人

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "status", nullable = false)
    private Integer status = 1; // 1: 启用, 0: 禁用

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "description", length = 200)
    private String description;
}
