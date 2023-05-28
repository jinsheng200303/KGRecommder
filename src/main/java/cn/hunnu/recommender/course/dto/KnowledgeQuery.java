package cn.hunnu.recommender.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KnowledgeQuery extends PageInfo {
    @ApiModelProperty("知识点名称")
    private String knowledgeName;
}
