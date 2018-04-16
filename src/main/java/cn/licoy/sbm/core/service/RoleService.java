package cn.licoy.sbm.core.service;

import cn.licoy.sbm.core.entity.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<Role> findAllRoleByUserId(Integer uid);
}
