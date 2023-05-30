package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HomeworkQuery extends PageInfo {
    @ApiModelProperty("作业标题")
    private String homeworkTitle;
}
