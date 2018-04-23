package cn.licoy.wdog.core.service.system;

import cn.licoy.wdog.core.entity.system.SysResource;
import cn.licoy.wdog.core.entity.system.SysRoleResource;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/16/9:01
 */
public interface SysRoleResourceService extends IService<SysRoleResource> {

    List<SysResource> findAllResourceByRoleId(String rid);

}
