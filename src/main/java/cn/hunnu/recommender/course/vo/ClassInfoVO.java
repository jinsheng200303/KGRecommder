package cn.hunnu.recommender.course.vo;

import cn.hunnu.recommender.course.dto.PageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ClassInfoVO extends PageInfo {
    @ApiModelProperty("课堂创建人ID")
    private Integer createUserId;

    @ApiModelProperty("课堂ID")
    private Integer classId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("课堂名")
    private String className;

    @ApiModelProperty("课堂类别名")
    private String categoryName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;
}
