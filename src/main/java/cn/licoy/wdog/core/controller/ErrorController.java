package cn.licoy.wdog.core.controller;

import cn.licoy.wdog.common.bean.RequestResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Licoy
 * @version 2018/4/16/13:39
 */
@RestController
@RequestMapping(value = {"/error"})
@ApiIgnore
public class ErrorController {

    @RequestMapping(value = {"/forbidden"}, method = RequestMethod.GET)
    public RequestResult Forbidden(){
        return RequestResult.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .msg("未经授权无权限访问")
                .build();
    }

    @RequestMapping(value = {"/unauthorized"}, method = RequestMethod.GET)
    public RequestResult unauthorized(){
        return RequestResult.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .msg("请先登录再进行操作")
                .build();
    }

}
