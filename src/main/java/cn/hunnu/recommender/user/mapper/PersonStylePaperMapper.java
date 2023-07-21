package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.entity.PersonStylePaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
