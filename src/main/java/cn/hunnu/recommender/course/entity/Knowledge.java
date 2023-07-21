package cn.hunnu.recommender.course.entity;

import cn.hunnu.recommender.course.entity.CourseBaseEntity;
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
 * 知识点表
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@Getter
@Setter
@TableName("knowledge")
@ApiModel(value = "Knowledge对象", description = "知识点表")
public class Knowledge {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("知识点ID")
    @TableId(value = "knowledge_id", type = IdType.AUTO)
    private Integer knowledgeId;

    @ApiModelProperty("知识点名称")
    @TableField("knowledge_name")
    private String knowledgeName;

    @ApiModelProperty("知识点描述")
    @TableField("knowledge_description")
    private String knowledgeDescription;

    @TableField(exist = false)
    private double comprehension;

}
