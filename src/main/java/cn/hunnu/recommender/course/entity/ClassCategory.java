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
 * 课堂类别关联表
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-08
 */
@Getter
@Setter
@TableName("class_category")
@ApiModel(value = "ClassCategory对象", description = "课堂类别关联表")
public class ClassCategory {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课堂ID")
    @TableField("class_id")
    private Integer classId;

    @ApiModelProperty("课堂类别关联ID")
    @TableId(value = "class_category_id", type = IdType.AUTO)
    private Integer classCategoryId;

    @ApiModelProperty("课堂类别ID")
    @TableField("category_id")
    private Integer categoryId;


}
