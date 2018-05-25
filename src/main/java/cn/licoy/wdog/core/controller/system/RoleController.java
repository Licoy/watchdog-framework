package cn.licoy.wdog.core.controller.system;

import cn.licoy.wdog.common.controller.CrudController;
import cn.licoy.wdog.core.dto.system.role.FindRoleDTO;
import cn.licoy.wdog.core.dto.system.role.RoleAddDTO;
import cn.licoy.wdog.core.dto.system.role.RoleUpdateDTO;
import cn.licoy.wdog.core.entity.system.SysRole;
import cn.licoy.wdog.core.service.system.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @author Licoy
 * @version 2018/4/19/9:41
 */
@RestController
@RequestMapping(value = {"/system/role"})
@Api(tags = {"角色管理"})
public class RoleController implements CrudController<SysRole,RoleAddDTO,RoleUpdateDTO,String,FindRoleDTO,SysRoleService>{

    private final SysRoleService sysRoleService;

    @Autowired
    public RoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @Override
    public SysRoleService getService() {
        return sysRoleService;
    }
}
