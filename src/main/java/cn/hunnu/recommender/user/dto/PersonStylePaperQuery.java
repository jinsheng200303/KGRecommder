package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PersonStylePaperQuery extends PageInfo {
    @ApiModelProperty("用户ID")
    private Integer userId;
}
