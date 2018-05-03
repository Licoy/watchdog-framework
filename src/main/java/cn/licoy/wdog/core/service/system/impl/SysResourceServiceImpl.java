package cn.licoy.wdog.core.service.system.impl;

import cn.licoy.wdog.common.bean.StatusEnum;
import cn.licoy.wdog.common.exception.RequestException;
import cn.licoy.wdog.core.dto.system.resource.ResourceDTO;
import cn.licoy.wdog.core.entity.system.SysResource;
import cn.licoy.wdog.core.mapper.system.SysResourceMapper;
import cn.licoy.wdog.core.service.global.ShiroService;
import cn.licoy.wdog.core.service.system.SysResourceService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/20/16:36
 */
@Service
@Transactional
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper,SysResource>
        implements SysResourceService {

    @Autowired
    private ShiroService shiroService;

    @Override
    public List<SysResource> list() {
        EntityWrapper<SysResource> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id",0)
                .or()
                .isNull("parent_id")
                .orderBy("sort");
        List<SysResource> resources = this.selectList(wrapper);
        if(resources!=null && resources.size()>0){
            resources.forEach(this::findAllChild);
        }
        return resources;
    }

    @Override
    public void add(ResourceDTO dto) {
        SysResource resource = new SysResource();
        BeanUtils.copyProperties(dto,resource);
        resource.setCreateDate(new Date());
        this.insert(resource);
        shiroService.reloadPerms();
    }

    @Override
    public void update(String id, ResourceDTO dto) {
        SysResource resource = this.selectById(id);
        if(resource==null)
            throw new RequestException(StatusEnum.FAIL.code,"更新失败，不存在ID为"+id+"的资源");
        BeanUtils.copyProperties(dto,resource);
        this.updateById(resource);
        shiroService.reloadPerms();
    }

    @Override
    public void remove(String id) {
        SysResource resource = this.selectOne(new EntityWrapper<SysResource>()
                .eq("id",id).setSqlSelect("id"));
        if(resource==null)
            throw new RequestException(StatusEnum.FAIL.code,"删除失败，不存在ID为"+id+"的资源");
        this.deleteById(id);
        shiroService.reloadPerms();
    }

    public void findAllChild(SysResource resource){
        EntityWrapper<SysResource> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id",resource.getId()).orderBy("sort");
        List<SysResource> resources = this.selectList(wrapper);
        resource.setChildren(resources);
        if(resources!=null && resources.size()>0){
            resources.forEach(this::findAllChild);
        }
    }
}
