package cn.hunnu.recommender.user.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class UserVO {
    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("真实姓名")
    private String name;

    @ApiModelProperty("性别;性别说明,M为男，F为女，U为未知")
    private String gender;

    @ApiModelProperty("出生日期")
    private LocalDateTime birth;

    @ApiModelProperty("政治面貌，10为共青团员，20为中共党员，30为民主党派，40为群众")
    private String politics;

    @ApiModelProperty("职业")
    private String profession;

    @ApiModelProperty("学校")
    private String school;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("简介")
    private String synopsis;

}
