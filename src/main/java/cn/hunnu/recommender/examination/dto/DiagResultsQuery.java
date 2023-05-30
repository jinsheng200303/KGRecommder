package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DiagResultsQuery extends PageInfo{
    @ApiModelProperty("认知诊断结果建议")
    private String diagResultsSuggestion;
}
