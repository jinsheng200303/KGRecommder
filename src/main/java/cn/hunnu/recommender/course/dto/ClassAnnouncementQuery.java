package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClassAnnouncementQuery extends PageInfo {

    @ApiModelProperty("公告ID")
    private Integer announcementId;

    @ApiModelProperty("课堂ID")
    private Integer classId;
}
