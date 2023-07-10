package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RolePermissionQuery extends PageInfo {
    @ApiModelProperty("权限ID")
    private String permissionId;

    @ApiModelProperty("角色ID")
    private String roleId;
}
