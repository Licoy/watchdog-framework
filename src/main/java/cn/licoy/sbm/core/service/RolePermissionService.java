package cn.licoy.sbm.core.service;

import cn.licoy.sbm.core.entity.Permission;
import cn.licoy.sbm.core.entity.RolePermission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/16/9:01
 */
public interface RolePermissionService extends IService<RolePermission> {

    List<Permission> findAllPermissionByRoleId(Integer rid);

}
