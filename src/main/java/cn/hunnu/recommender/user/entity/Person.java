package cn.hunnu.recommender.user.entity;

import cn.hunnu.recommender.user.entity.userBaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 个人信息
 * </p>
 *
 * @author czj
 * @since 2023-05-23
 */
@Getter
@Setter
@TableName("person")
@ApiModel(value = "Person对象", description = "个人信息")
public class Person extends userBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("身份证;18位身份证")
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty("真实姓名")
    @TableField("name")
    private String name;

    @Length(min = 6,max = 100, message = "密码长度不能小于6")
    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("手机号;11位手机号")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty("性别;性别说明,M为男，F为女，U为未知")
    @TableField("gender")
    private String gender;

    @ApiModelProperty("出生日期")
    @TableField("birth")
    private LocalDateTime birth;

    @ApiModelProperty("政治面貌，10为共青团员，20为中共党员，30为民主党派，40为群众")
    @TableField("politics")
    private String politics;

    @ApiModelProperty("职业")
    @TableField("profession")
    private String profession;

    @ApiModelProperty("学校")
    @TableField("school")
    private String school;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("简介")
    @TableField("synopsis")
    private String synopsis;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

}
