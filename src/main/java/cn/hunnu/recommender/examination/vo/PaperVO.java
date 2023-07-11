package cn.hunnu.recommender.examination.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PaperVO {
    @ApiModelProperty("试卷ID")
    private Integer paperId;

    @ApiModelProperty("试卷标题")
    private String paperTitle;
}
