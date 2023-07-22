package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StudyStyleQuery extends PageInfo {
    @ApiModelProperty("学习风格ID")
    private Integer studyStyleId;
}
