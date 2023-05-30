package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.RecordDiag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 记录与诊断关联表 Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Mapper
public interface RecordDiagMapper extends BaseMapper<RecordDiag> {

}
