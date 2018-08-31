package cn.licoy.wdog.common.util;

import cn.licoy.wdog.common.bean.ResponseCode;
import cn.licoy.wdog.common.exception.RequestException;
import cn.licoy.wdog.core.config.jwt.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Licoy
 * @version 2018/4/28/9:46
 */
public class Tools {

    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip==null || "".equals(ip.trim()) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if("0.0.0.0".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "localhost".equals(ip) || "127.0.0.1".equals(ip)){
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 判断请求是否是Ajax
     * @param request
     * @return
     */
    public static boolean ajax(HttpServletRequest request){
        String accept = request.getHeader("accept");
        return accept != null && accept.contains("application/json") || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"));
    }

    public static boolean executeLogin(ServletRequest request){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        if(authorization==null || "".equals(authorization.trim())){
            throw RequestException.fail("未含授权标示，禁止访问");
        }
        JwtToken token = new JwtToken(authorization,null,null);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (DisabledAccountException e){
            if(e.getMessage().equals("verifyFail")){
                throw new RequestException(ResponseCode.NOT_SING_IN.code,"身份已过期，请重新登录",e);
            }
            throw new RequestException(ResponseCode.SIGN_IN_INPUT_FAIL.code,e.getMessage(),e);
        }catch (Exception e){
            e.printStackTrace();
            throw new RequestException(ResponseCode.SIGN_IN_FAIL,e);
        }
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    public static synchronized void executeLogin(){
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        boolean b = Tools.executeLogin(request);
        if(!b){
            throw RequestException.fail("身份已过期或无效，请重新认证");
        }
    }

}
