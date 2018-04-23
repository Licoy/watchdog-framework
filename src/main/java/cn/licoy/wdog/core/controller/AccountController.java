package cn.licoy.wdog.core.controller;

import cn.licoy.wdog.common.bean.RequestResult;
import cn.licoy.wdog.common.bean.StatusEnum;
import cn.licoy.wdog.core.dto.SignInDTO;
import cn.licoy.wdog.core.service.system.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Licoy
 * @version 2018/4/13/14:02
 */
@RestController
@RequestMapping(value = {"/account"})
@Api(tags = {"账户相关"})
public class AccountController {

    @Resource
    private SysUserService userService;

    @RequestMapping(value = {"/sign-in"},method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    public RequestResult findUser(@RequestBody @Validated @ApiParam(value = "登录数据",required = true)
                                              SignInDTO signInDTO){
        userService.signIn(signInDTO);
        return RequestResult.e(StatusEnum.SIGN_IN_OK);
    }

    @RequestMapping(value = "/current", method = RequestMethod.POST)
    @ApiOperation(value = "获取当前用户信息")
    public RequestResult home(){
        return RequestResult.e(StatusEnum.OK, userService.getCurrentUser());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "注销登录")
    public RequestResult logout(){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        }catch (Exception e){
            return RequestResult.e(StatusEnum.LOGOUT_FAIL);
        }
        return RequestResult.e(StatusEnum.LOGOUT_OK);
    }

}
