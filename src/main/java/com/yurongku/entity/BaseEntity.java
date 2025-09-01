package com.yurongku.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @CreatedDate
    @Column(name = "created_time", nullable = false, updatable = false)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @LastModifiedDate
    @Column(name = "updated_time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @CreatedBy
    @Column(name = "created_by", length = 100, updatable = false)
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by", length = 100)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @Column(name = "is_deleted")
    @TableLogic
    private Integer isDeleted = 0;
}
