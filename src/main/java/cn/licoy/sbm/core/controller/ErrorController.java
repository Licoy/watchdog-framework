package cn.licoy.sbm.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Licoy
 * @version 2018/4/16/13:39
 */
@Controller
@RequestMapping(value = {"/error"})
public class ErrorController {

    @RequestMapping(value = {"401"})
    public String errorFor401(){
        return "/error/403";
    }

}
