package cn.licoy.wdog.core.service.global;

import cn.licoy.wdog.core.entity.system.SysResource;

import java.util.List;
import java.util.Map;

/**
 * @author Licoy
 * @version 2018/4/23/13:59
 */
public interface ShiroService {

    /**
     * 获取拦截器数据
     * @return
     */
    Map<String,String> getFilterChainDefinitionMap();

    /**
     * 迭代所有的资源子集
     * @param resource
     * @param permsList
     */
    void iterationAllResourceInToFilter(SysResource resource,List<String[]> permsList);

    /**
     * 重新加载权限
     */
    void reloadPerms();

}
