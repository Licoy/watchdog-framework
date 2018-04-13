package cn.licoy.sbm.core.controller;

import cn.licoy.sbm.core.dto.SignInDTO;
import cn.licoy.sbm.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @author Licoy
 * @version 2018/4/13/14:02
 */
@Controller
@RequestMapping(value = {"/account"})
public class AccountController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/sign-in")
    public String signInPage(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            return "redirect:/account/home";
        }
        return "account/sign-in";
    }

    @RequestMapping(value = {"/sign-in"},method = RequestMethod.POST)
    public String findUser(SignInDTO signInDTO, Model model){
        Boolean res = userService.signIn(signInDTO,model);
        if(res){
            return "redirect:/account/home";
        }
        return "account/sign-in";
    }

    @RequestMapping(value = "/home")
    @RequiresAuthentication
    public String home(){
        return "account/home";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/account/sign-in";
    }

}
