package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PersonQuery extends PageInfo {
    @ApiModelProperty("用户名")
    private String username;
}
