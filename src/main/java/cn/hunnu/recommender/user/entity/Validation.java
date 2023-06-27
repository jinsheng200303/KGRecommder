package cn.hunnu.recommender.user.entity;

import cn.hunnu.recommender.user.entity.userBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 邮箱验证表
 * </p>
 *
 * @author czj
 * @since 2023-06-27
 */
@Getter
@Setter
@TableName("validation")
@ApiModel(value = "Validation对象", description = "邮箱验证表")
public class Validation extends userBaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("验证码")
    @TableField("code")
    private String code;

    @ApiModelProperty("过期时间")
    @TableField("time")
    private Date time;


}
