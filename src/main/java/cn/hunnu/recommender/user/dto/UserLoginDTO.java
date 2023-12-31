package cn.hunnu.recommender.user.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

//专门用于登陆的实体类
@Data
public class UserLoginDTO {
    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("密码")
    private String password;
}
