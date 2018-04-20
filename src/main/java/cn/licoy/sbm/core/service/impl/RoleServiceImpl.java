package cn.licoy.sbm.core.service.impl;

import cn.licoy.sbm.common.bean.StatusEnum;
import cn.licoy.sbm.common.exception.RequestException;
import cn.licoy.sbm.core.dto.role.FindRoleDTO;
import cn.licoy.sbm.core.dto.role.RoleUpdateDTO;
import cn.licoy.sbm.core.entity.Permission;
import cn.licoy.sbm.core.entity.Role;
import cn.licoy.sbm.core.entity.UserRole;
import cn.licoy.sbm.core.mapper.RoleMapper;
import cn.licoy.sbm.core.service.RolePermissionService;
import cn.licoy.sbm.core.service.RoleService;
import cn.licoy.sbm.core.service.UserRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<Role> findAllRoleByUserId(Long uid) {
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

    @Override
    public Page<Role> getList(FindRoleDTO findRoleDTO) {
        EntityWrapper<Role> wrapper = new EntityWrapper<>();
        Page<Role> rolePage = this.selectPage(new Page<>(findRoleDTO.getPage(),
                findRoleDTO.getPageSize()), wrapper);
        if(rolePage.getRecords()!=null){
            rolePage.getRecords().forEach(v->
                    v.setPermissions(rolePermissionService.findAllPermissionByRoleId(v.getId())));
        }
        return rolePage;
    }

    @Override
    public void removeById(Long rid) {
        Role role = this.selectById(rid);
        if(role==null) throw new RequestException(StatusEnum.FAIL.code,"角色不存在！");
        try {
            this.deleteById(rid);
        }catch (DataIntegrityViolationException e){
            throw new RequestException(StatusEnum.FAIL.code,
                    String.format("请先解除角色为【%s】角色的全部用户！",role.getName()),e);
        }catch (Exception e){
            throw new RequestException(StatusEnum.FAIL.code,"角色删除失败！",e);
        }
    }

    @Override
    public void update(Long rid, RoleUpdateDTO roleUpdateDTO) {
        Role role = this.selectById(rid);
        if(role==null) throw new RequestException(StatusEnum.FAIL.code,"角色不存在！");
        BeanUtils.copyProperties(roleUpdateDTO,role);
        try {
            this.updateById(role);
        }catch (Exception e){
            throw new RequestException(StatusEnum.FAIL.code,"角色更新失败！",e);
        }

    }
}
