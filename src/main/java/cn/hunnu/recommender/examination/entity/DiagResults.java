package cn.hunnu.recommender.examination.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 诊断结果表
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("diag_results")
@ApiModel(value = "DiagResults对象", description = "诊断结果表")
public class DiagResults extends ExaminationBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("结果ID")
    @TableId(value = "result_id", type = IdType.AUTO)
    private Integer resultId;

    @ApiModelProperty("用户ID;外键")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("记录ID;外键")
    @TableField("record_id")
    private Integer recordId;

    @ApiModelProperty("知识点ID;外键")
    @TableField("knowledge_id")
    private Integer knowledgeId;

    @ApiModelProperty("学习资源ID;外键")
    @TableField("resource_id")
    private Integer resourceId;

    @ApiModelProperty("课程ID;外键")
    @TableField("course_id")
    private Integer courseId;

    @ApiModelProperty("学习建议")
    @TableField("suggestion")
    private String suggestion;


}
