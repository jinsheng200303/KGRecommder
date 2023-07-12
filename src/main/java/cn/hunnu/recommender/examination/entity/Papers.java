package cn.hunnu.recommender.examination.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 试卷表
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("papers")
@ApiModel(value = "Papers对象", description = "试卷表")
public class Papers extends ExaminationBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试卷ID")
    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;

    @ApiModelProperty("试卷标题")
    @TableField("paper_title")
    private String paperTitle;

    @ApiModelProperty("问题集合的字符串;使用 JSON 格式对该字段进行格式化，将不同选择题、判断题等放到question_set 字符串中，每一道题目都拥有自己的固定属性信息，比如难度系数、所属知识点等。当我们需要生成一份新的试卷时，就可以解析该 question_set 字符串，根据题型、难度、所属知识点等条件来选取相应数量的题目，并将其组成一份完整的试卷。")
    @TableField("question_set")
    private String questionSet;

    @ApiModelProperty("试卷所属课堂ID")
    @TableField("class_id")
    private Integer classId;


}
