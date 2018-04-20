package cn.licoy.sbm.core.service.impl;

import cn.licoy.sbm.core.entity.Permission;
import cn.licoy.sbm.core.entity.RolePermission;
import cn.licoy.sbm.core.mapper.RolePermissionMapper;
import cn.licoy.sbm.core.service.PermissionService;
import cn.licoy.sbm.core.service.RolePermissionService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/16/9:01
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper,RolePermission>
        implements RolePermissionService {

    @Resource
    private PermissionService permissionService;

    @Override
    public List<Permission> findAllPermissionByRoleId(Long rid) {
        List<RolePermission> rps = this.selectList(new EntityWrapper<RolePermission>().eq("rid",rid));
        if(rps!=null){
            List<Long> pids = new ArrayList<>();
            rps.forEach(v->pids.add(v.getPid()));
            if(pids.size()==0){
                return null;
            }
            return permissionService.selectList(new EntityWrapper<Permission>().in("id", pids));
        }
        return null;
    }
}
