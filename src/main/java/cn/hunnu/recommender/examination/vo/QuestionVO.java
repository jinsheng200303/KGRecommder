package cn.hunnu.recommender.examination.vo;

import cn.hunnu.recommender.examination.entity.Options;
import cn.hunnu.recommender.user.dto.PageInfo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class QuestionVO  extends PageInfo {
    @ApiModelProperty("试题ID号")
    private Integer questionId;

    @ApiModelProperty("题库ID号;外键")
    private Integer bankId;

    @ApiModelProperty("题库名")
    private String bankName;

    @ApiModelProperty("试题类型:1选择题;2填空题;3问答题")
    private Integer questionTypeId;

    @ApiModelProperty("试题类型")
    private String questionType;

    @ApiModelProperty("试题知识点分类ID")
    private Integer knowledgeId;

    @ApiModelProperty("试题知识点分类名称")
    private String knowledgeName;

    @ApiModelProperty("试题描述")
    private String questionStatement;

    @ApiModelProperty("试题答案")
    private String answer;

    @ApiModelProperty("试题难度")
    private String difficultyLevel;

    @ApiModelProperty("试题得分")
    private String score;

    @TableField(exist = false)
    List<Options> options;
}
