package cn.licoy.sbm.controller;

import cn.licoy.sbm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author Licoy
 * @version 2018/4/13/14:02
 */
@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = {"/{id}"})
    public String findUser(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "user";
    }

}
