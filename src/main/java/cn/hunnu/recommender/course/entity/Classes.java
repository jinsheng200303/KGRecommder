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
 * 课堂
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@Getter
@Setter
@TableName("classes")
@ApiModel(value = "Classes对象", description = "课堂")
public class Classes extends CourseBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课堂ID")
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    @ApiModelProperty("课堂名称")
    @TableField("class_name")
    private String className;

    @ApiModelProperty("课堂图片")
    @TableField("class_picture")
    private String classPicture;

    @ApiModelProperty("课堂创建人ID")
    @TableField("create_user_id")
    private Integer createUserId;

    @ApiModelProperty("课堂类别ID")
    @TableField("class_category_id")
    private String classCategoryId;

    @ApiModelProperty("课堂描述")
    @TableField("description")
    private String description;
}
