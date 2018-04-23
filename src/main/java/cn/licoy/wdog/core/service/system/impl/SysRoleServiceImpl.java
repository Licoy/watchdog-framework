package cn.licoy.wdog.core.service.system.impl;

import cn.licoy.wdog.common.bean.StatusEnum;
import cn.licoy.wdog.common.exception.RequestException;
import cn.licoy.wdog.core.dto.role.FindRoleDTO;
import cn.licoy.wdog.core.dto.role.RoleUpdateDTO;
import cn.licoy.wdog.core.entity.system.SysResource;
import cn.licoy.wdog.core.entity.system.SysRole;
import cn.licoy.wdog.core.entity.system.SysUserRole;
import cn.licoy.wdog.core.mapper.system.SysRoleMapper;
import cn.licoy.wdog.core.service.system.SysRoleResourceService;
import cn.licoy.wdog.core.service.system.SysRoleService;
import cn.licoy.wdog.core.service.system.SysUserRoleService;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {

    @Resource
    private SysRoleResourceService roleResourceService;

    @Resource
    private SysUserRoleService userRoleService;

    @Override
    public List<SysRole> findAllRoleByUserId(String uid) {
        List<SysUserRole> userRoles = userRoleService.selectList(new EntityWrapper<SysUserRole>().eq("uid", uid));
        List<SysRole> roles = new ArrayList<>();
        userRoles.forEach(v->{
            SysRole role = this.selectById(v.getRid());
            if(role!=null){
                List<SysResource> permissions = roleResourceService.findAllResourceByRoleId(role.getId());
                role.setResources(permissions);
                roles.add(role);
            }
        });
        return roles;
    }

    @Override
    public Page<SysRole> getList(FindRoleDTO findRoleDTO) {
        EntityWrapper<SysRole> wrapper = new EntityWrapper<>();
        Page<SysRole> rolePage = this.selectPage(new Page<>(findRoleDTO.getPage(),
                findRoleDTO.getPageSize()), wrapper);
        if(rolePage.getRecords()!=null){
            rolePage.getRecords().forEach(v->
                    v.setResources(roleResourceService.findAllResourceByRoleId(v.getId())));
        }
        return rolePage;
    }

    @Override
    public void removeById(String rid) {
        SysRole role = this.selectById(rid);
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
    public void update(String rid, RoleUpdateDTO roleUpdateDTO) {
        SysRole role = this.selectById(rid);
        if(role==null) throw new RequestException(StatusEnum.FAIL.code,"角色不存在！");
        BeanUtils.copyProperties(roleUpdateDTO,role);
        try {
            this.updateById(role);
        }catch (Exception e){
            throw new RequestException(StatusEnum.FAIL.code,"角色更新失败！",e);
        }

    }
}
