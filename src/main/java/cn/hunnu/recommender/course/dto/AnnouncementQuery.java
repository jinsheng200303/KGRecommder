package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AnnouncementQuery extends PageInfo {
    @ApiModelProperty("标题")
    private String title;
}
