package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryQuery extends PageInfo {
    @ApiModelProperty("课堂类别名")
    private String Category;
}
