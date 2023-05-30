package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuestionsQuery extends PageInfo{
    @ApiModelProperty("试题名称")
    private String questionStatement;
}
