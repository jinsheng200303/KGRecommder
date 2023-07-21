package cn.hunnu.recommender.user.entity;

import cn.hunnu.recommender.user.entity.userBaseEntity;
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
 * 用户学习风格测量答卷存储表
 * </p>
 *
 * @author czj
 * @since 2023-07-21
 */
@Getter
@Setter
@TableName("person_style_paper")
@ApiModel(value = "PersonStylePaper对象", description = "用户学习风格测量答卷存储表")
public class PersonStylePaper extends userBaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("用户学习风格测量答卷内容(使用JSON数据格式保存)")
    @TableField("answers")
    private String answers;


}
