package cn.hunnu.recommender.user.vo;

import cn.hunnu.recommender.user.entity.Permission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
public class RolePermissionVO {
    @ApiModelProperty("角色ID")
    private Integer roleId;

    @ApiModelProperty("权限名")
    private String permissionName;
}
