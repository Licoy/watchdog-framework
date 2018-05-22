package cn.licoy.wdog.core.filter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Licoy
 * @version 2018/4/18/16:15
 */
@Slf4j
public class PermissionAuthorizationFilter/* extends AccessControlFilter*/ {

    /*@Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        log.info("PermissionAuthorizationFilter执行");
        Subject subject = getSubject(servletRequest, servletResponse);
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
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        saveRequest(request);
        HttpServletResponse res = WebUtils.toHttp(response);
        res.setHeader("Content-Type", "application/json;charset=utf-8");
        if (null == subject.getPrincipal()) {//表示没有登录，返回登录提示
            res.getWriter().write(JSON.toJSONString(RequestResult.e(StatusEnum.NOT_SING_IN)));
        }else{
            res.getWriter().write(JSON.toJSONString(RequestResult.builder()
                    .status(StatusEnum.FAIL.code)
                    .msg("无权限访问")
                    .build()));
        }
        return false;
    }*/
}
