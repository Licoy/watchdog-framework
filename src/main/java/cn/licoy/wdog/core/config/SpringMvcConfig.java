package cn.licoy.wdog.core.config;

import cn.licoy.wdog.common.resolver.JwtTokenArgumentResolver;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author licoy.cn
 * @version 2018/9/13
 */
@SpringBootConfiguration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JwtTokenArgumentResolver());
    }
}
