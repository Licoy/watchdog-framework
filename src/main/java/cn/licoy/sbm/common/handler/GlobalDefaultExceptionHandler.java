package cn.licoy.sbm.common.handler;


import lombok.extern.log4j.Log4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import java.util.Arrays;
import java.util.List;

/**
 * @author licoy.cn
 * @version 2017/11/18
 */
@ControllerAdvice
@Log4j
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(NestedServletException.class)
    public String pageNotFoundExceptionHandler(NestedServletException e){
        return "/error/404";
    }


    @ExceptionHandler(UnauthenticatedException.class)
    public String unAuthorizationExceptionHandler(UnauthenticatedException e){
        return "/error/401";
    }

    @ExceptionHandler(AuthorizationException.class)
    public String authorizationExceptionHandler(AuthorizationException e){
        return "/error/403";
    }






}
