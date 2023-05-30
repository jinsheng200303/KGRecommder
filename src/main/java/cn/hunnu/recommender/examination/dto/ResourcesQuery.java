package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResourcesQuery extends PageInfo{
    @ApiModelProperty("学习资源名称")
    private String resourcesName;
}
