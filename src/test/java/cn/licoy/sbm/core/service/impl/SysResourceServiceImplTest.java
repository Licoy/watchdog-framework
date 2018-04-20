package cn.licoy.sbm.core.service.impl;

import cn.licoy.sbm.core.entity.SysResource;
import cn.licoy.sbm.core.service.SysResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author Licoy
 * @version 2018/4/20/16:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysResourceServiceImplTest {

    @Resource
    private SysResourceService service;

    @Test
    public void test(){
        for (int i = 0; i < 100; i++) {
            SysResource sysResource = SysResource.builder().name("test"+i).build();
            service.insert(sysResource);
        }
    }

}