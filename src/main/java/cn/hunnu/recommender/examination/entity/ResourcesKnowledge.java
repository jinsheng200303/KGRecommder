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
 * 学习资源与知识点关联表
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("resources_knowledge")
@ApiModel(value = "ResourcesKnowledge对象", description = "学习资源与知识点关联表")
public class ResourcesKnowledge implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学习资源与知识点关联id")
    @TableId(value = "resources_knowledge_id", type = IdType.AUTO)
    private Integer resourcesKnowledgeId;

    @ApiModelProperty("知识点ID")
    @TableField("knowledge_id")
    private Integer knowledgeId;

    @ApiModelProperty("资源ID")
    @TableField("resource_id")
    private Integer resourceId;


}
