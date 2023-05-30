package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResourcesKnowledgeQuery extends PageInfo{
    @ApiModelProperty("学习资源与知识点关联ID")
    private Integer resourcesKnowledgeId;
}
