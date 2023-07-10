package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ExamsQuery extends PageInfo{
    @ApiModelProperty("考试名称")
    private String examTitle;

    @ApiModelProperty("课堂ID")
    private Integer classId;
}
