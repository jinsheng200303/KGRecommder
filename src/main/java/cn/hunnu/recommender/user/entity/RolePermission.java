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
 * 角色权限关联表
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@Getter
@Setter
@TableName("role_permission")
@ApiModel(value = "RolePermission对象", description = "角色权限关联表")
public class RolePermission extends userBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色权限关联ID")
    @TableId(value = "role_permission_id", type = IdType.AUTO)
    private Integer rolePermissionId;

    @ApiModelProperty("角色ID")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty("权限ID")
    @TableField("permission_id")
    private String permissionId;


}
