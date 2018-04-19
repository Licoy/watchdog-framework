package cn.licoy.sbm.core.controller;

import cn.licoy.sbm.common.bean.RequestResult;
import cn.licoy.sbm.common.bean.StatusEnum;
import cn.licoy.sbm.core.dto.role.FindRoleDTO;
import cn.licoy.sbm.core.dto.role.RoleUpdateDTO;
import cn.licoy.sbm.core.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Licoy
 * @version 2018/4/19/9:41
 */
@RestController
@RequestMapping(value = {"/role"})
@Api(tags = {"角色管理"})
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页获取所有角色列表")
    public RequestResult get(@RequestBody @ApiParam(value = "权限查找过滤条件") FindRoleDTO findRoleDTO){
        return RequestResult.e(StatusEnum.OK,roleService.getList(findRoleDTO));
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "删除指定ID的角色")
    public RequestResult remove(@PathVariable("id") @ApiParam(value = "角色标识ID") Long id){
        roleService.removeById(id);
        return RequestResult.e(StatusEnum.OK);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "更新指定ID的角色信息")
    public RequestResult update(@PathVariable("id") @ApiParam(value = "角色标识ID") Long id,
                                @RequestBody @ApiParam(value = "角色更新信息") RoleUpdateDTO updateDTO){
        roleService.update(id, updateDTO);
        return RequestResult.e(StatusEnum.OK);
    }

}
