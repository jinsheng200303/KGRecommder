package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClassesQuery extends PageInfo {
    @ApiModelProperty("课堂名")
    private String className;
}
