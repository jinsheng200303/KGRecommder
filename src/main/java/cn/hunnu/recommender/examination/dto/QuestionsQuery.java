package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuestionsQuery extends PageInfo{
    @ApiModelProperty("试题名称")
    private String questionStatement;

    @ApiModelProperty("试题库名称")
    private Integer bankId;

    @ApiModelProperty("试题所属课堂ID")
    private Integer classId;

    @ApiModelProperty("试题类型")
    private String type;
}
