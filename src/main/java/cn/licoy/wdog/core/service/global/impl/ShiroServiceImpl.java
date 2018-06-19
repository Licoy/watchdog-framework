package cn.licoy.wdog.core.service.global.impl;

import cn.licoy.wdog.common.bean.ResponseCode;
import cn.licoy.wdog.common.exception.RequestException;
import cn.licoy.wdog.common.util.SpringUtils;
import cn.licoy.wdog.core.config.shiro.MyRealm;
import cn.licoy.wdog.core.entity.system.SysResource;
import cn.licoy.wdog.core.service.global.ShiroService;
import cn.licoy.wdog.core.service.system.SysResourceService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.util.*;

/**
 * @author Licoy
 * @version 2018/4/23/14:01
 */
@Service
@Transactional
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysResourceService resourceService;

    @Override
    public Map<String, String> getFilterChainDefinitionMap() {

        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<String[]> permsList = new LinkedList<>();
        List<String[]> anonList = new LinkedList<>();

        List<SysResource> resources = resourceService.list();

        if(resources!=null){
            for (SysResource resource : resources) {
                if(!StringUtils.isEmpty(resource.getUrl()) && !StringUtils.isEmpty(resource.getPermission())){
                    if(!"".equals(resource.getPermission().trim())) {
                        //判断是否需要权限验证
                        if(resource.getVerification()){
                            permsList.add(0,new String[]{resource.getUrl()+
                                    "/**","perms["+resource.getPermission()+":*]"});
                        }else{
                            anonList.add(0,new String[]{resource.getUrl()+
                                    "/**","anon"});
                        }
                    }
                }
                iterationAllResourceInToFilter(resource,permsList,anonList);
            }
        }


        for (String[] strings : anonList) {
            filterChainDefinitionMap.put(strings[0],strings[1]);
        }

        for (String[] strings : permsList) {
            filterChainDefinitionMap.put(strings[0],strings[1]);
        }

        filterChainDefinitionMap.put("/**", "anon");

        return filterChainDefinitionMap;
    }

    @Override
    public void iterationAllResourceInToFilter(SysResource resource,
                                               List<String[]> permsList,List<String[]> anonList){
        if(resource.getChildren()!=null && resource.getChildren().size()>0){
            for (SysResource v : resource.getChildren()) {
                if(!StringUtils.isEmpty(v.getUrl()) && !StringUtils.isEmpty(v.getPermission())){
                    if(v.getVerification()){
                        permsList.add(0,new String[]{v.getUrl()+"/**","perms["+v.getPermission()+":*]"});
                    }else{
                        anonList.add(0,new String[]{v.getUrl()+"/**","anon"});
                    }
                    iterationAllResourceInToFilter(v,permsList,anonList);
                }
            }
        }
    }

    @Override
    public void reloadPerms() {

        ShiroFilterFactoryBean shiroFilterFactoryBean = SpringUtils.getBean(ShiroFilterFactoryBean.class);

        AbstractShiroFilter abstractShiroFilter;
        try {
            abstractShiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
        } catch (Exception e) {
            throw new RequestException(ResponseCode.FAIL.code,"重新加载权限失败",e);
        }
        PathMatchingFilterChainResolver filterChainResolver =
                (PathMatchingFilterChainResolver) abstractShiroFilter.getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                .getFilterChainManager();

        /*清除旧版权限*/
        manager.getFilterChains().clear();
        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

        /*更新新数据*/
        Map<String, String> filterChainDefinitionMap = getFilterChainDefinitionMap();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        filterChainDefinitionMap.forEach(manager::createChain);
    }

    @Override
    public void clearAuthByUserId(String uid,Boolean author, Boolean out){
        MyRealm myRealm = SpringUtils.getBean(MyRealm.class);
        myRealm.clearAuthByUserId(uid,author,out);
    }

    @Override
    public void clearAuthByUserIdCollection(List<String> userList, Boolean author, Boolean out) {
        MyRealm myRealm = SpringUtils.getBean(MyRealm.class);
        myRealm.clearAuthByUserIdCollection(userList,author,out);
    }
}
