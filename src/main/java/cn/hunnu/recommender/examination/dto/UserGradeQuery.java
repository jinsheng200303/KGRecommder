package cn.hunnu.recommender.examination.dto;

import cn.hunnu.recommender.user.dto.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserGradeQuery extends PageInfo {
    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("考试ID")
    private String examsId;
}
