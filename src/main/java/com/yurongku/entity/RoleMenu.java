package com.yurongku.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_role_menu")
@TableName("sys_role_menu")
public class RoleMenu extends BaseEntity {

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "menu_id", nullable = false)
    private Long menuId;
}
