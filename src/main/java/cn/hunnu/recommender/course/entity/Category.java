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
 * 
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-08
 */
@Getter
@Setter
@TableName("category")
@ApiModel(value = "Category对象", description = "")
public class Category {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课堂类别ID")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty("课堂类别")
    @TableField("category_name")
    private String categoryName;


}
