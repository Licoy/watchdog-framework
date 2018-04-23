package cn.licoy.wdog.core.config.redis;

import org.crazycake.shiro.RedisManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Licoy
 * @version 2018/4/23/12:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisConfigTest {

    @Resource
    private RedisManager redisManager;

    @Test
    public void get() {
        System.out.println();
    }
}