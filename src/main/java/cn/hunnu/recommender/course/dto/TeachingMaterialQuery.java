package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeachingMaterialQuery extends PageInfo {
    @ApiModelProperty("标题")
    private String title;
}
