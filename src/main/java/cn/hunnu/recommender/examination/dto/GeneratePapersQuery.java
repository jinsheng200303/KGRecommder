package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GeneratePapersQuery {

    @ApiModelProperty("选择题数量")
    private Integer typeChoice;

    @ApiModelProperty("填空题数量")
    private Integer typeGap;

    @ApiModelProperty("问答题数量")
    private Integer typeEssay;

    @ApiModelProperty("试卷ID")
    private Integer paperId;

    @ApiModelProperty("题库ID")
    private Integer bankId;

}
