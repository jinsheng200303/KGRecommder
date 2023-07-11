package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StudentPaperQuery extends PageInfo{
    @ApiModelProperty("考试ID")
    private String examId;

    @ApiModelProperty("用户ID")
    private String userId;
}
