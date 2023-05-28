package cn.hunnu.recommender.course.entity;

import cn.hunnu.recommender.course.entity.CourseBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@Getter
@Setter
@TableName("lesson")
@ApiModel(value = "Lesson对象", description = "课程")
public class Lesson {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID")
    @TableId(value = "lesson_id", type = IdType.AUTO)
    private Integer lessonId;

    @ApiModelProperty("课程名")
    @TableField("lesson_name")
    private String lessonName;

    @ApiModelProperty("课程图片")
    @TableField("lesson_picture")
    private String lessonPicture;

    @ApiModelProperty("课程说明")
    @TableField("intro")
    private String intro;

    @ApiModelProperty("课程评分")
    @TableField("lesson_mark")
    private BigDecimal lessonMark;

    @ApiModelProperty("课程类别")
    @TableField("category")
    private String category;

    @ApiModelProperty("知识点ID")
    @TableField("knowledge_id")
    private String knowledgeId;

    @ApiModelProperty("课程链接")
    @TableField("lesson_link")
    private String lessonLink;


}
