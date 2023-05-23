package cn.hunnu.recommender.examination.dto;

import lombok.Data;

@Data
public class PageInfo {
    //页码
    private Integer pageNum;
    //每页显示条数
    private Integer pageSize;
}
