package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClassHomeworkQuery extends PageInfo {

    @ApiModelProperty("作业ID")
    private Integer homeworkId;

    @ApiModelProperty("课堂ID")
    private Integer classId;
}
