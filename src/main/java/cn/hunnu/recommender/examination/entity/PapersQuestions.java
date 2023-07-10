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
 * @since 2023-07-10
 */
@Getter
@Setter
@TableName("papers_questions")
@ApiModel(value = "PapersQuestions对象", description = "")
public class PapersQuestions implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试卷试题关联id")
    @TableId(value = "paper_question_id", type = IdType.AUTO)
    private Integer paperQuestionId;

    @ApiModelProperty("试卷id")
    @TableField("paper_id")
    private Integer paperId;

    @ApiModelProperty("试题id")
    @TableField("question_id")
    private Integer questionId;


}
