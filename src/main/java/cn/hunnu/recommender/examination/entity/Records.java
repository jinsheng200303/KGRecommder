package cn.hunnu.recommender.examination.entity;

import cn.hunnu.recommender.examination.entity.examinationBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 做题记录表
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("records")
@ApiModel(value = "Records对象", description = "做题记录表")
public class Records extends examinationBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("记录ID")
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    @ApiModelProperty("学生用户ID;外键")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("考试ID;外键")
    @TableField("exam_id")
    private Integer examId;

    @ApiModelProperty("试卷ID;外键")
    @TableField("paper_id")
    private Integer paperId;

    @ApiModelProperty("试题ID;外键")
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty("学生答案")
    @TableField("answer")
    private String answer;

    @ApiModelProperty("题目得分")
    @TableField("score")
    private String score;


}
