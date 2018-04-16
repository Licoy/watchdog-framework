package cn.licoy.sbm.core.service.impl;

import cn.licoy.sbm.core.entity.Permission;
import cn.licoy.sbm.core.entity.Role;
import cn.licoy.sbm.core.mapper.RoleMapper;
import cn.licoy.sbm.core.service.RolePermissionService;
import cn.licoy.sbm.core.service.RoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Resource
    private RolePermissionService rolePermissionService;

    @Override
    public Role findRoleById(Integer id) {
        Role role = this.selectById(id);
        if (role == null){
            return null;
        }
        List<Permission> permissions = rolePermissionService.findAllPermissionByRoleId(role.getId());
        role.setPermissions(permissions);
        return role;
    }
}
