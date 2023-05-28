package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LessonQuery extends PageInfo {
    @ApiModelProperty("课程名称")
    private String lessonName;
}
