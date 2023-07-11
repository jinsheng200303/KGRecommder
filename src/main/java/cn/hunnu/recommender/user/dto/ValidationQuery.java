package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ValidationQuery extends PageInfo {
    @ApiModelProperty("邮箱ID")
    private Integer id;
}
