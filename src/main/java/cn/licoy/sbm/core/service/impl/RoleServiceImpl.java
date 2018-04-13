package cn.licoy.sbm.core.service.impl;

import cn.licoy.sbm.common.exception.RequestException;
import cn.licoy.sbm.core.entity.Permission;
import cn.licoy.sbm.core.entity.Role;
import cn.licoy.sbm.core.mapper.RoleMapper;
import cn.licoy.sbm.core.service.PermissionService;
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
    private PermissionService permissionService;

    @Override
    public Role findRoleById(Integer id) {
        Role role = this.selectById(id);
        if (role == null){
            return null;
        }
        List<Permission> permissions = permissionService
                .selectList(new EntityWrapper<Permission>()
                        .eq("role_id",role.getId()));
        role.setPermissions(permissions);
        return role;
    }
}
