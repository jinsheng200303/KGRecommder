package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.entity.BrowseRecords;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 浏览记录表 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-07-23
 */
@Mapper
public interface BrowseRecordsMapper extends BaseMapper<BrowseRecords> {

}
