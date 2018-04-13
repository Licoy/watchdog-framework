package cn.licoy.sbm.core.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Licoy
 * @version 2018/4/13/16:53
 */
@Controller
@RequestMapping(value = {"/page"})
public class PageController {

    @RequiresPermissions("page:select")
    @RequestMapping(value = {"/select"})
    public String goSelect(){
        return "page/select";
    }

    @RequiresPermissions("page:remove")
    @RequestMapping(value = {"/remove"})
    public String goRemove(){
        return "page/remove";
    }

}
