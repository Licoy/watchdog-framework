package cn.licoy.wdog.core.service.system;

import cn.licoy.wdog.core.dto.system.resource.ResourceDTO;
import cn.licoy.wdog.core.entity.system.SysResource;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author Licoy
 * @version 2018/4/20/16:35
 */
public interface SysResourceService extends IService<SysResource> {

    /**
     * 获取资源列表
     * @return
     */
    List<SysResource> list();

    /**
     * 添加资源
     * @param dto
     */
    void add(ResourceDTO dto);

    /**
     * 更新资源
     * @param id
     * @param dto
     */
    void update(String id,ResourceDTO dto);

    /**
     * 删除资源
     * @param id
     */
    void remove(String id);

    /**
     * 递归查找所有的子集
     * @param resource
     */
    void findAllChild(SysResource resource);



    /**
     * 获取资源所有的父级
     * @param resource 资源
     * @param cacheMap 缓存对象
     * @param cacheMap2 缓存对象
     * @return 资源
     */
    SysResource getResourceAllParent(SysResource resource,Map<String,SysResource> cacheMap,
                                     Map<String,SysResource> cacheMap2);


}
