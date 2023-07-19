package cn.hunnu.recommender.user.entity;

import cn.hunnu.recommender.user.entity.userBaseEntity;
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
 * 用户知识点理解程度关联表
 * </p>
 *
 * @author czj
 * @since 2023-07-20
 */
@Getter
@Setter
@TableName("person_knowledge")
@ApiModel(value = "PersonKnowledge对象", description = "用户知识点理解程度关联表")
public class PersonKnowledge extends userBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户知识点关联ID")
    @TableId(value = "user_knowledge_id", type = IdType.AUTO)
    private Integer userKnowledgeId;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("知识点ID")
    @TableField("knowledge_id")
    private Integer knowledgeId;

    @ApiModelProperty("理解程度")
    @TableField("comprehension")
    private Float comprehension;


}
