package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.entity.PersonStylePaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户学习风格测量答卷存储表 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-07-21
 */
@Mapper
public interface PersonStylePaperMapper extends BaseMapper<PersonStylePaper> {

    @Select("select * from person_style_paper where user_id = #{userId}")
    PersonStylePaper findUserInfo(Integer userId);

    List<Integer> findUsersId(List<Integer> studyStyleId1,List<Integer> studyStyleId2,
                              List<Integer> studyStyleId3,List<Integer> studyStyleId4);
}
