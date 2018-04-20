package cn.licoy.sbm.core.mapper;

import cn.licoy.sbm.core.entity.SysResource;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Licoy
 * @version 2018/4/20/16:35
 */
@Mapper
@Repository
public interface SysResourceMapper extends BaseMapper<SysResource> {
}
