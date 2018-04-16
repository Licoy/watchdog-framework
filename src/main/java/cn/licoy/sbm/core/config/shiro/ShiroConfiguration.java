package cn.licoy.sbm.core.config.shiro;

import cn.licoy.sbm.core.entity.Permission;
import cn.licoy.sbm.core.service.PermissionService;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author licoy.cn
 * @version 2017/9/22
 */
@Configuration
@Log4j
public class ShiroConfiguration {

    @Resource
    private PermissionService permissionService;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        log.info("Shiro Configuration initialized");
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //登出 resultAPI不能使用logout，不然会自动跳转至登录页面
        filterChainDefinitionMap.put("/account/logout", "anon");
        filterChainDefinitionMap.put("/account/sign-in", "anon");
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->

        List<Permission> permissions = permissionService.selectList(null);

        if(permissions!=null){
            permissions.forEach(v-> {
                if(!StringUtils.isEmpty(v.getUrl()) && !StringUtils.isEmpty(v.getPermission())){
                    filterChainDefinitionMap.put(v.getUrl(),"perms["+v.getPermission()+"]");
                }
            });
        }

        filterChainDefinitionMap.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/error/unauthorized"); //将页面定向到未登录错误页
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/account/home");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/forbidden");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public SecurityManager securityManager(MyRealm myRealm){
        DefaultWebSecurityManager manager =  new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    //开启shiro aop注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager){
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(securityManager);
        return bean;
    }
}
