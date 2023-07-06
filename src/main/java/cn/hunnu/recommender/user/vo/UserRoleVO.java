package cn.hunnu.recommender.user.vo;

import cn.hunnu.recommender.user.dto.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRoleVO extends PageInfo {
    @ApiModelProperty("用户角色ID")
    private Integer personRoleId;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("角色名")
    private String roleName;
}
