package cn.licoy.wdog.core.service.system;

import cn.licoy.wdog.core.entity.Permission;
import cn.licoy.wdog.core.entity.RolePermission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/16/9:01
 */
public interface RolePermissionService extends IService<RolePermission> {

    List<Permission> findAllPermissionByRoleId(Long rid);

}
