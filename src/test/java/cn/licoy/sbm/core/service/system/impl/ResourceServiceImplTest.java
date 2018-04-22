package cn.licoy.sbm.core.service.system.impl;

import cn.licoy.sbm.core.dto.system.resource.ResourceDTO;
import cn.licoy.sbm.core.entity.SysResource;
import cn.licoy.sbm.core.service.system.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author licoy.cn
 * @version 2018/4/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServiceImplTest {


    @Resource
    private ResourceService resourceService;

    @Test
    public void list() {
        List<SysResource> list = resourceService.list();
        System.out.println();
    }

    @Test
    public void add() {

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setName("测试");
        resourceService.add(resourceDTO);

    }

    @Test
    public void update() {

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setName("测试666");
//        resourceService.update("987948959588683778",resourceDTO);
        resourceService.update("987948959588683776",resourceDTO);

    }

    @Test
    public void remove() {

//        resourceService.remove("54545");
        resourceService.remove("987948959588683778");

    }
}