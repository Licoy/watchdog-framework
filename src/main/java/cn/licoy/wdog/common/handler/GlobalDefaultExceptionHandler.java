package cn.licoy.wdog.common.handler;


import cn.licoy.wdog.common.bean.RequestResult;
import cn.licoy.wdog.common.bean.StatusEnum;
import cn.licoy.wdog.common.exception.RequestException;
import lombok.extern.log4j.Log4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licoy.cn
 * @version 2017/11/18
 */
@ControllerAdvice(basePackages = {"cn.licoy.wdog"})
@Log4j
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = RequestException.class)
    @ResponseBody
    public RequestResult requestExceptionHandler(RequestException e){
        return RequestResult.builder().msg(e.getMsg()).status(e.getStatus()).build();
    }


    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public RequestResult requestExceptionHandler(DataIntegrityViolationException e){
        return RequestResult.builder().msg("数据操作格式异常").status(StatusEnum.FAIL.code).build();
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

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RequestResult requestExceptionHandler(Exception e){
        e.printStackTrace();
        return RequestResult.builder().msg("服务器飘了，管理员去拿刀修理了~").status(StatusEnum.FAIL.code).build();
    }






}
