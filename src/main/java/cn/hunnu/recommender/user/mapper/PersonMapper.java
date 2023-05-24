package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.entity.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 个人信息 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-05-23
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}
