package cn.hunnu.recommender.examination.entity;

import cn.hunnu.recommender.examination.vo.OptionVO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 试题表
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("questions")
@ApiModel(value = "Questions对象", description = "试题表")
public class Questions extends ExaminationBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试题ID号")
    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;

    @ApiModelProperty("题库ID号;外键")
    @TableField("bank_id")
    private Integer bankId;

    @ApiModelProperty("试题类型;选择题或者判断题")
    @TableField("question_type")
    private String questionType;

    @ApiModelProperty("试题知识点分类")
    @TableField("knowledge_id")
    private Integer knowledgeId;

    @ApiModelProperty("试题描述")
    @TableField("question_statement")
    private String questionStatement;

    @ApiModelProperty("试题答案")
    @TableField("answer")
    private String answer;

    @ApiModelProperty("试题难度")
    @TableField("difficulty_level")
    private String difficultyLevel;

    @ApiModelProperty("试题得分")
    @TableField("score")
    private String score;

    @TableField(exist = false)
    List<Options> options;

}
