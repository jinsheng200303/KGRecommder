package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class HandPapersQuery {

    @ApiModelProperty("手动选择的试题ID列表")
    private List<Integer> handleQuestionIds;

    @ApiModelProperty("试卷ID")
    private Integer paperId;


}
