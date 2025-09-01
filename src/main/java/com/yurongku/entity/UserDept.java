package com.yurongku.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_user_dept")
@TableName("sys_user_dept")
public class UserDept extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "dept_id", nullable = false)
    private Long deptId;

    @Column(name = "is_primary", nullable = false)
    private Integer isPrimary = 0; // 是否主部门 1: 是, 0: 否

    @Column(name = "position", length = 50)
    private String position; // 在该部门的职位

    @Column(name = "join_date")
    private LocalDateTime joinDate; // 加入该部门的时间
}
