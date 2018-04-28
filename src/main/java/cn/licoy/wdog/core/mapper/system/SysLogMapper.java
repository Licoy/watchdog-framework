package cn.licoy.wdog.core.mapper.system;

import cn.licoy.wdog.core.entity.system.SysLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Licoy
 * @version 2018/4/28/9:56
 */
@Mapper
@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {
}
