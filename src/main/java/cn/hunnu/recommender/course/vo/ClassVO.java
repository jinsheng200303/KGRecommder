package cn.hunnu.recommender.course.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ClassVO {

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("课堂ID")
    private String classId;

    @ApiModelProperty("课堂类别")
    private String classCategory;

    @ApiModelProperty("课堂图片")
    private MultipartFile file;
}
