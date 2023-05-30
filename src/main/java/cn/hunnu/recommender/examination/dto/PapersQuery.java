package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PapersQuery extends PageInfo{
    @ApiModelProperty("试卷名称")
    private String paperTitle;
}
