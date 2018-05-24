package cn.licoy.wdog.core.controller.system;

import cn.licoy.wdog.common.bean.ResponseResult;
import cn.licoy.wdog.common.bean.ResponseCode;
import cn.licoy.wdog.common.annotation.SysLogs;
import cn.licoy.wdog.core.dto.system.user.FindUserDTO;
import cn.licoy.wdog.core.dto.system.user.ResetPasswordDTO;
import cn.licoy.wdog.core.dto.system.user.UserAddDTO;
import cn.licoy.wdog.core.dto.system.user.UserUpdateDTO;
import cn.licoy.wdog.core.service.system.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author Licoy
 * @version 2018/4/18/14:15
 */
@RestController
@RequestMapping(value = {"/system/user"})
@Api(tags = {"用户管理"})
public class UserController {

    @Autowired
    private SysUserService userService;

    @PostMapping(value = {"/list"})
    @ApiOperation(value = "分页获取用户数据")
    @SysLogs("分页获取用户数据")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "身份认证Token")
    public ResponseResult get(@RequestBody @Validated @ApiParam(value = "用户获取过滤条件") FindUserDTO findUserDTO){
        return ResponseResult.e(ResponseCode.OK,userService.getAllUserBySplitPage(findUserDTO));
    }

    @PostMapping(value = {"/get/id/{id}"})
    @ApiOperation(value = "根据ID获取用户信息")
    @SysLogs("根据ID获取用户信息")
    public ResponseResult getById(@PathVariable("id") @ApiParam(value = "用户ID") String id){
        return ResponseResult.e(ResponseCode.OK,userService.findUserById(id,true));
    }

    @PostMapping(value = {"/lock/{id}"})
    @ApiOperation(value = "锁定用户")
    @SysLogs("锁定用户")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "身份认证Token")
    public ResponseResult lock(@PathVariable("id") @ApiParam(value = "用户标识ID") String id){
        userService.statusChange(id, 0);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/unlock/{id}"})
    @ApiOperation(value = "解锁用户")
    @SysLogs("解锁用户")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "身份认证Token")
    public ResponseResult unlock(@PathVariable("id") @ApiParam(value = "用户标识ID") String id){
        userService.statusChange(id, 1);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除用户")
    @SysLogs("删除用户")
    public ResponseResult remove(@PathVariable("id") @ApiParam(value = "用户标识ID") String id){
        userService.removeUser(id);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加用户")
    @SysLogs("添加用户")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "身份认证Token")
    public ResponseResult add(@RequestBody @Validated @ApiParam(value = "用户数据") UserAddDTO addDTO){
        userService.add(addDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "更新用户")
    @SysLogs("更新用户")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "身份认证Token")
    public ResponseResult update(@PathVariable("id") @ApiParam(value = "用户标识ID") String id,
                                 @RequestBody @Validated @ApiParam(value = "用户数据") UserUpdateDTO updateDTO){
        userService.update(id,updateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/reset-password"})
    @ApiOperation(value = "重置密码")
    @SysLogs("重置密码")
    @ApiImplicitParam(paramType = "header",name = "Authorization",value = "身份认证Token")
    public ResponseResult resetPassword(@RequestBody
                                           @Validated @ApiParam(value = "用户及密码数据") ResetPasswordDTO dto){
        userService.resetPassword(dto);
        return ResponseResult.e(ResponseCode.OK);
    }

}
