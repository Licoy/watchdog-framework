package cn.licoy.sbm.core.service.impl;

import cn.licoy.sbm.core.entity.Permission;
import cn.licoy.sbm.core.entity.Role;
import cn.licoy.sbm.core.entity.UserRole;
import cn.licoy.sbm.core.mapper.RoleMapper;
import cn.licoy.sbm.core.service.RolePermissionService;
import cn.licoy.sbm.core.service.RoleService;
import cn.licoy.sbm.core.service.UserRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public List<Role> findAllRoleByUserId(Integer uid) {
        List<UserRole> userRoles = userRoleService.selectList(new EntityWrapper<UserRole>().eq("uid", uid));
        List<Role> roles = new ArrayList<>();
        userRoles.forEach(v->{
            Role role = this.selectById(v.getRid());
            if(role!=null){
                List<Permission> permissions = rolePermissionService.findAllPermissionByRoleId(role.getId());
                role.setPermissions(permissions);
                roles.add(role);
            }
        });
        return roles;
    }
}
