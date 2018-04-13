package cn.licoy.sbm.core.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author licoy.cn
 * @version 2018/3/13
 */
@Component
public class ErrorPageConfig {

    /*@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return configurableEmbeddedServletContainer -> {
            configurableEmbeddedServletContainer
                    .addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/error/404"));
            configurableEmbeddedServletContainer
                    .addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
            configurableEmbeddedServletContainer
                    .addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
            configurableEmbeddedServletContainer
                    .addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
            configurableEmbeddedServletContainer
                    .addErrorPages(new ErrorPage(Throwable.class, "/error/500"));
        };
    }*/

}
