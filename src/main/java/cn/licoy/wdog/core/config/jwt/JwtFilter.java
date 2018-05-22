package cn.licoy.wdog.core.config.jwt;

import cn.licoy.wdog.common.bean.ResponseResult;
import cn.licoy.wdog.common.bean.ResponseCode;
import cn.licoy.wdog.common.exception.RequestException;
import cn.licoy.wdog.common.util.Tools;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response){
        return Tools.executeLogin(request);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("PermissionAuthorizationFilter执行");
        HttpServletResponse res = WebUtils.toHttp(response);
        if (!isLoginAttempt(request, response)) {
            writerResponse(res,ResponseCode.NOT_SING_IN.code,"无身份认证权限标示");
            return false;
        }
        try {
            executeLogin(request, response);
        }catch (RequestException e){
            writerResponse(res,e.getStatus(),e.getMsg());
            return false;
        }
        Subject subject = getSubject(request, response);
        if(null != mappedValue){
            String[] value = (String[])mappedValue;
            for (String permission : value) {
                if(permission==null || "".equals(permission.trim())){
                    continue;
                }
                if(subject.isPermitted(permission)){
                    return true;
                }
            }
        }
        if (null == subject.getPrincipal()) {//表示没有登录，返回登录提示
            writerResponse(res,ResponseCode.NOT_SING_IN.code,ResponseCode.NOT_SING_IN.msg);
        }else{
            writerResponse(res,ResponseCode.FAIL.code,"无权限访问");
        }
        return false;
    }

    private void writerResponse(HttpServletResponse response,Integer status,String content){
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(ResponseResult.builder()
                    .status(status)
                    .msg(content)
                    .build()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        return false;
    }

}
