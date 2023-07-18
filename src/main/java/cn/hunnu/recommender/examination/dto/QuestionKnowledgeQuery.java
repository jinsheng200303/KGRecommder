package cn.hunnu.recommender.examination.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuestionKnowledgeQuery extends PageInfo {
    @ApiModelProperty("试题知识点关联ID")
    private Integer QuestionKnowledgeId;
}
