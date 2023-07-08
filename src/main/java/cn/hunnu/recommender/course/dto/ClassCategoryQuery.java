package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClassCategoryQuery extends PageInfo {
    @ApiModelProperty("课堂类别ID")
    private Integer CategoryID;

    @ApiModelProperty("课堂ID")
    private Integer classID;
}
