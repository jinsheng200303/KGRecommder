package cn.hunnu.recommender.examination.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-18
 */
@Getter
@Setter
@TableName("question_knowledge")
@ApiModel(value = "QuestionKnowledge对象", description = "")
public class QuestionKnowledge implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试题ID")
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty("知识点ID")
    @TableField("knowledge_id")
    private Integer knowledgeId;

    @ApiModelProperty("试题知识点关联ID")
    @TableId(value = "question_knowledge_id", type = IdType.AUTO)
    private Integer questionKnowledgeId;


}
