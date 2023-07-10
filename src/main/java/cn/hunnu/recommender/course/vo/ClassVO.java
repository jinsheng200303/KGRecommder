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
    private Integer classId;

    @ApiModelProperty("课堂名")
    private String className;

    @ApiModelProperty("课堂类别ID")
    private Integer categoryId;

    @ApiModelProperty("课堂图片")
    private MultipartFile file;
}
