package cn.licoy.wdog.core.controller.system;

import cn.licoy.wdog.common.bean.RequestResult;
import cn.licoy.wdog.core.dto.user.FindUserDTO;
import cn.licoy.wdog.core.service.system.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Licoy
 * @version 2018/4/18/14:15
 */
@RestController
@RequestMapping(value = {"/system/user"})
@Api(tags = {"用户管理"})
public class UserController {

    @Resource
    private SysUserService userService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.POST)
    @ApiOperation(value = "分页获取用户数据")
    public RequestResult get(@RequestBody @ApiParam(value = "用户获取过滤条件") FindUserDTO findUserDTO){
        return userService.getAllUserBySplitPage(findUserDTO);
    }

    @RequestMapping(value = {"/lock/{id}"}, method = RequestMethod.POST)
    @ApiOperation(value = "锁定用户")
    public RequestResult lock(@PathVariable("id") @ApiParam(value = "用户标识ID") Long id){
        return userService.statusChange(id, 0);
    }

    @RequestMapping(value = {"/unlock/{id}"}, method = RequestMethod.POST)
    @ApiOperation(value = "解锁用户")
    public RequestResult unlock(@PathVariable("id") @ApiParam(value = "用户标识ID") Long id){
        return userService.statusChange(id, 1);
    }

    @RequestMapping(value = {"/remove/{id}"}, method = RequestMethod.POST)
    @ApiOperation(value = "删除用户")
    public RequestResult remove(@PathVariable("id") @ApiParam(value = "用户标识ID") Long id){
        return userService.removeUser(id);
    }

}
