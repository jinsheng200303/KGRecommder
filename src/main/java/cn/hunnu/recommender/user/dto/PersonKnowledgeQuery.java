package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PersonKnowledgeQuery extends PageInfo {
    @ApiModelProperty("用户ID")
    private Integer userId;

}