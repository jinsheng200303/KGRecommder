package cn.hunnu.recommender.course.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@TableName("class_user")
@Data
public class ClassVO {
    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("课堂ID")
    @TableField("class_id")
    private String classId;

}
