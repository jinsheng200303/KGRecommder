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
 * 权限
 * </p>
 *
 * @author czj
 * @since 2023-05-19
 */
@Getter
@Setter
@TableName("permission")
@ApiModel(value = "Permission对象", description = "权限")
public class Permission extends userBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限名")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty("权限ID")
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;


}
