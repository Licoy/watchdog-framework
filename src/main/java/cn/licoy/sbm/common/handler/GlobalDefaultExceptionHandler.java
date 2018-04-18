package cn.licoy.sbm.common.handler;


import cn.licoy.sbm.common.bean.RequestResult;
import cn.licoy.sbm.common.bean.StatusEnum;
import cn.licoy.sbm.common.exception.RequestException;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import java.util.Arrays;
import java.util.List;

/**
 * @author licoy.cn
 * @version 2017/11/18
 */
@ControllerAdvice(basePackages = {"cn.licoy.sbm"})
@Log4j
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = RequestException.class)
    @ResponseBody
    public RequestResult requestExceptionHandler(RequestException e){
        return RequestResult.builder().msg(e.getMsg()).status(e.getStatus()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RequestResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        String s = "参数验证失败";
        if(result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            s = errors.get(0).getDefaultMessage();
        }
        return RequestResult.builder().status(StatusEnum.FAIL.code).msg(s).build();
    }






}
