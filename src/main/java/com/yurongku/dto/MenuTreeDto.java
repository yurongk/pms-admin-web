package com.yurongku.dto;

import com.yurongku.utils.TreeUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MenuTreeDto implements TreeUtil.TreeNode<MenuTreeDto> {

    private Long id;
    private String menuName;
    private String menuCode;
    private Long parentId;
    private String menuType;
    private String path;
    private String component;
    private String perms;
    private String icon;
    private Integer visible;
    private Integer status;
    private Integer sortOrder;
    private Integer isFrame;
    private Integer isCache;
    private String description;
    private LocalDateTime createdTime;
    private String createdBy;
    private List<MenuTreeDto> children;
}
