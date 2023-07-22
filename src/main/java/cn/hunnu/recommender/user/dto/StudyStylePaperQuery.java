package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StudyStylePaperQuery extends PageInfo {
    @ApiModelProperty("题目ID")
    private Integer id;
}
