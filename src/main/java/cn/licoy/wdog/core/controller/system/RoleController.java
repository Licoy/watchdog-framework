package cn.licoy.wdog.core.controller.system;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Licoy
 * @version 2018/4/19/9:41
 */
@RestController
@RequestMapping(value = {"/system/role"})
@Api(tags = {"角色管理"})
public class RoleController {

    @Resource
    private SysRoleService roleService;

    @Resource
    private SysResourceService resourceService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页获取所有角色列表")
    public RequestResult get(@RequestBody @ApiParam(value = "权限查找过滤条件") FindRoleDTO findRoleDTO){
        return RequestResult.e(StatusEnum.OK,roleService.getList(findRoleDTO));
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "删除指定ID的角色")
    public RequestResult remove(@PathVariable("id") @ApiParam(value = "角色标识ID") String id){
        roleService.removeById(id);
        return RequestResult.e(StatusEnum.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加角色")
    public RequestResult add(@RequestBody @Validated @ApiParam(value = "角色添加信息") RoleAddDTO addDTO){
        roleService.add(addDTO);
        return RequestResult.e(StatusEnum.OK);
    }

    @RequestMapping(value = "/add/all-resource", method = RequestMethod.POST)
    @ApiOperation(value = "获取添加角色的时可用角色列表")
    public RequestResult getAddAllResource(){
        return RequestResult.e(StatusEnum.OK,
                resourceService.list());
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "更新指定ID的角色信息")
    public RequestResult update(@PathVariable("id") @ApiParam(value = "角色标识ID") String id,
                                @RequestBody @Validated @ApiParam(value = "角色更新信息") RoleUpdateDTO updateDTO){
        roleService.update(id, updateDTO);
        return RequestResult.e(StatusEnum.OK);
    }

}
