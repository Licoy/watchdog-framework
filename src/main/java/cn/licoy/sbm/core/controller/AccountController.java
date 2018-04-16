package cn.licoy.sbm.core.controller;

import cn.licoy.sbm.common.bean.RequestResult;
import cn.licoy.sbm.core.dto.SignInDTO;
import cn.licoy.sbm.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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
public class AccountController {

    @Resource
    private UserService userService;

    @RequestMapping(value = {"/sign-in"},method = RequestMethod.POST)
    public RequestResult findUser(SignInDTO signInDTO, Model model){
        Boolean res = userService.signIn(signInDTO,model);
        if(res){
            return RequestResult.builder()
                    .status(HttpStatus.OK.value())
                    .msg("登录成功")
                    .build();
        }
        return RequestResult.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .msg("登录失败")
                .build();
    }

    @RequestMapping(value = "/home")
    public String home(){
        return "account/home";
    }

    @RequestMapping(value = "/logout")
    public RequestResult logout(){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        }catch (Exception e){
            return RequestResult.error("注销登录失败");
        }
        return RequestResult.okHasDataAndMsg(null, "注销登录成功");
    }

}
