package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClassUserQuery extends PageInfo {

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("课堂ID")
    private Integer classId;
}
