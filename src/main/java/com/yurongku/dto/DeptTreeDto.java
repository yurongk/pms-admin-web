package com.yurongku.dto;

import com.yurongku.utils.TreeUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeptTreeDto implements TreeUtil.TreeNode<DeptTreeDto> {

    private Long id;
    private String deptName;
    private String deptCode;
    private Long parentId;
    private String ancestors;
    private String leader;
    private String phone;
    private String email;
    private Integer status;
    private Integer sortOrder;
    private String description;
    private LocalDateTime createdTime;
    private String createdBy;
    private List<DeptTreeDto> children;
}
