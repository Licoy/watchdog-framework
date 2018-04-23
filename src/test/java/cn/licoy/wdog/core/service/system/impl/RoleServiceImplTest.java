package cn.licoy.wdog.core.service.system.impl;

import cn.licoy.wdog.core.entity.system.SysRole;
import cn.licoy.wdog.core.service.system.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @author Licoy
 * @version 2018/4/20/16:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Resource
    private SysRoleService roleService;

    @Test
    public void test(){
        SysRole role = SysRole.builder().name("1").build();
        roleService.insert(role);
    }

}