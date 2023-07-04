package cn.hunnu.recommender.user.entity;

import cn.hunnu.recommender.user.entity.userBaseEntity;
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
 * 个人角色关联表
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@Getter
@Setter
@TableName("person_role")
@ApiModel(value = "PersonRole对象", description = "个人角色关联表")
public class PersonRole extends userBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("个人角色关联ID")
    @TableId(value = "person_role_id", type = IdType.AUTO)
    private Integer personRoleId;

    @ApiModelProperty("角色ID")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;


}
