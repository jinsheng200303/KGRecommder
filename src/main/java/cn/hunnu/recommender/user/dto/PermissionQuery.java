package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PermissionQuery extends PageInfo{
    @ApiModelProperty("权限名")
    private String permissionName;
}
