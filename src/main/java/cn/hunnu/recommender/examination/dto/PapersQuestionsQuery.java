package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PapersQuestionsQuery extends PageInfo{
    @ApiModelProperty("试卷与试题关联ID")
    private Integer PapersQuestionsId;
}
