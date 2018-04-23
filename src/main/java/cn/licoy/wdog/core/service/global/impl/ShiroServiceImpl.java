package cn.licoy.wdog.core.service.global.impl;

import cn.licoy.wdog.common.bean.StatusEnum;
import cn.licoy.wdog.common.exception.RequestException;
import cn.licoy.wdog.common.util.SpringUtils;
import cn.licoy.wdog.core.entity.system.SysResource;
import cn.licoy.wdog.core.service.global.ShiroService;
import cn.licoy.wdog.core.service.system.SysResourceService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Licoy
 * @version 2018/4/23/14:01
 */
@Service
@Transactional
public class ShiroServiceImpl implements ShiroService {

    @Resource
    private SysResourceService resourceService;

    @Resource
    private SpringUtils springUtils;

    @Override
    public Map<String, String> getFilterChainDefinitionMap() {

        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<String[]> permsList = new ArrayList<>();

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

        return filterChainDefinitionMap;
    }

    @Override
    public void iterationAllResourceInToFilter(SysResource resource,
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

    @Override
    public void reloadPerms() {

        ShiroFilterFactoryBean shiroFilterFactoryBean = SpringUtils.getBean(ShiroFilterFactoryBean.class);

        AbstractShiroFilter abstractShiroFilter = null;
        try {
            abstractShiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
        } catch (Exception e) {
            throw new RequestException(StatusEnum.FAIL.code,"重新加载权限失败",e);
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

    //TODO 根据用户ID清空缓存权限
    //TODO 根据角色ID清空缓存权限
    //TODO 清空全部缓存数据
}
