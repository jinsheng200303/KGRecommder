package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data

public class RoleQuery extends PageInfo {
    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("唯一标识")
    private String flag;
}
