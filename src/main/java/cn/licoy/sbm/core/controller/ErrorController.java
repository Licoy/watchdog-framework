package cn.licoy.sbm.core.controller;

import cn.licoy.sbm.common.bean.RequestResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Licoy
 * @version 2018/4/16/13:39
 */
@RestController
@RequestMapping(value = {"/error"})
public class ErrorController {

    @RequestMapping(value = {"/forbidden"})
    public RequestResult Forbidden(){
        return RequestResult.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .msg("未经授权无权限访问")
                .build();
    }

    @RequestMapping(value = {"/unauthorized"})
    public RequestResult unauthorized(){
        return RequestResult.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .msg("请先登录再进行操作")
                .build();
    }

}
