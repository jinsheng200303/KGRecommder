package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BrowseRecordsQuery extends PageInfo {
    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("资源ID")
    private Integer resourceId;
}