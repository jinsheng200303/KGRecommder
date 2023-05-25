package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PersonRoleQuery extends PageInfo {

    @ApiModelProperty("用户ID")
    private String userID;

    @ApiModelProperty("角色ID")
    private String roleID;
}
