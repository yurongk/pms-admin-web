package com.yurongku.dto;

import com.yurongku.utils.TreeUtil;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PermissionTreeDto implements TreeUtil.TreeNode<PermissionTreeDto> {

	private Long id;

	@NotNull
	private Long parentId;

	private String permissionName;

	private String permissionCode;

	private String permissionType;

	private String path;

	private Integer status;

	private Integer sortOrder;

	private List<PermissionTreeDto> children;

	@Override
	public Long getParentId() {
		return parentId;
	}

	@Override
	public void setChildren(List<PermissionTreeDto> children) {
		this.children = children;
	}
}


