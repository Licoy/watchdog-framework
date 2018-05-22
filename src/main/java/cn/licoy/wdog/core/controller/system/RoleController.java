package cn.licoy.wdog.core.controller.system;

import cn.licoy.wdog.common.annotation.SysLogs;
import cn.licoy.wdog.common.bean.RequestResult;
import cn.licoy.wdog.common.bean.StatusEnum;
import cn.licoy.wdog.core.dto.system.role.FindRoleDTO;
import cn.licoy.wdog.core.dto.system.role.RoleAddDTO;
import cn.licoy.wdog.core.dto.system.role.RoleUpdateDTO;
import cn.licoy.wdog.core.service.system.SysResourceService;
import cn.licoy.wdog.core.service.system.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



/**
 * @author Licoy
 * @version 2018/4/19/9:41
 */
@RestController
@RequestMapping(value = {"/system/role"})
@Api(tags = {"角色管理"})
public class RoleController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysResourceService resourceService;

    @PostMapping(value = {"/list"})
    @ApiOperation(value = "分页获取所有角色列表")
    @SysLogs("分页获取所有角色列表")
    public RequestResult get(@RequestBody @ApiParam(value = "权限查找过滤条件") FindRoleDTO findRoleDTO){
        return RequestResult.e(StatusEnum.OK,roleService.getList(findRoleDTO));
    }

    @PostMapping(value = "/remove/{id}")
    @ApiOperation(value = "删除指定ID的角色")
    @SysLogs("删除指定ID的角色")
    public RequestResult remove(@PathVariable("id") @ApiParam(value = "角色标识ID") String id){
        roleService.removeById(id);
        return RequestResult.e(StatusEnum.OK);
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "添加角色")
    @SysLogs("添加角色")
    public RequestResult add(@RequestBody @Validated @ApiParam(value = "角色添加信息") RoleAddDTO addDTO){
        roleService.add(addDTO);
        return RequestResult.e(StatusEnum.OK);
    }

    @PostMapping(value = "/update/{id}")
    @ApiOperation(value = "更新指定ID的角色信息")
    @SysLogs("更新指定ID的角色信息")
    public RequestResult update(@PathVariable("id") @ApiParam(value = "角色标识ID") String id,
                                @RequestBody @Validated @ApiParam(value = "角色更新信息") RoleUpdateDTO updateDTO){
        roleService.update(id, updateDTO);
        return RequestResult.e(StatusEnum.OK);
    }

}
