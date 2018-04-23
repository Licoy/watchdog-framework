package cn.licoy.wdog.core.config.freemarker;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author licoy.cn
 * @version 2018/1/15
 */
@Component
public class PlatformFreeMarkerConfigurer implements InitializingBean {

    @Resource
    private Configuration configuration;

    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        configuration.setSharedVariable("shiro",new ShiroTags());
    }
}
