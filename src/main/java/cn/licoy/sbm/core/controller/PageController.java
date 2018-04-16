package cn.licoy.sbm.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Licoy
 * @version 2018/4/13/16:53
 */
@Controller
@RequestMapping(value = {"/page"})
public class PageController {

    @RequestMapping(value = {"/select"})
    public String goSelect(){
        return "page/select";
    }

    @RequestMapping(value = {"/remove"})
    public String goRemove(){
        return "page/remove";
    }

}
