package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClassStudentGraphQuery extends PageInfo {

    @ApiModelProperty("用户ID")
    private Integer userID;

    @ApiModelProperty("课堂ID")
    private Integer classID;

    @ApiModelProperty("图谱ID")
    private Integer graphID;
}
