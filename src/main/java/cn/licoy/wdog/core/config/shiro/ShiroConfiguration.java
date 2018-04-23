package cn.licoy.wdog.core.config.shiro;

import cn.licoy.wdog.core.entity.system.SysResource;
import cn.licoy.wdog.core.filter.PermissionAuthorizationFilter;
import cn.licoy.wdog.core.service.system.SysResourceService;
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
import javax.servlet.Filter;
import java.util.*;

/**
 * @author licoy.cn
 * @version 2017/9/22
 */
@Configuration
@Log4j
public class ShiroConfiguration {

    @Resource
    private SysResourceService resourceService;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        log.info("Shiro Configuration initialized");
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<String[]> permsList = new ArrayList<>();
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->

        List<SysResource> resources = resourceService.list();

        if(resources!=null){
            for (SysResource resource : resources) {
                if(!StringUtils.isEmpty(resource.getUrl()) && !StringUtils.isEmpty(resource.getPermission())){
                    permsList.add(0,new String[]{resource.getUrl(),"perms["+resource.getPermission()+"]"});
                }
                iterationAllResourceInToFilter(resource,permsList);
            }
        }

        for (String[] strings : permsList) {
            filterChainDefinitionMap.put(strings[0],strings[1]);
        }

        filterChainDefinitionMap.put("/**", "anon");
        //过滤器
        Map<String,Filter> filters = new HashMap<>();
        filters.put("perms",new PermissionAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    private void iterationAllResourceInToFilter(SysResource resource,
                                                List<String[]> permsList){
        if(resource.getChildren()!=null && resource.getChildren().size()>0){
            for (SysResource v : resource.getChildren()) {
                if(!StringUtils.isEmpty(v.getUrl()) && !StringUtils.isEmpty(v.getPermission())){
                    permsList.add(0,new String[]{v.getUrl(),"perms["+v.getPermission()+"]"});
                    iterationAllResourceInToFilter(v,permsList);
                }
            }
        }
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
