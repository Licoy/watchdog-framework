package cn.licoy.sbm.core.service;

import cn.licoy.sbm.core.entity.Role;
import com.baomidou.mybatisplus.service.IService;

public interface RoleService extends IService<Role> {
    Role findRoleById(Integer id);
}
