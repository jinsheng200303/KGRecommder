package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PersonRoleQuery extends PageInfo {

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("角色ID")
    private Integer roleId;

    //利用用户ID以及角色ID查询用户名时使用
    @ApiModelProperty("用户名")
    private String userName;
}
