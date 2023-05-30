package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuestionBankQuery extends PageInfo{
    @ApiModelProperty("题库名称")
    private String questionBankName;
}
