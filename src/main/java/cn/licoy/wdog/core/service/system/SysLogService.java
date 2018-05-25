package cn.licoy.wdog.core.service.system;

import cn.licoy.wdog.common.service.QueryService;
import cn.licoy.wdog.core.dto.system.log.FindLogDTO;
import cn.licoy.wdog.core.entity.system.SysLog;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/28/9:56
 */
public interface SysLogService extends IService<SysLog>,QueryService<SysLog,FindLogDTO>
{

    Page<SysLog> list(FindLogDTO findLogDTO);

    void remove(List<String> idList);

}
