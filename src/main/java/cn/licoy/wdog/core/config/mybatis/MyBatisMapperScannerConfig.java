package cn.licoy.wdog.core.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("cn.licoy.sbm.mapper")
@AutoConfigureAfter(MybatisPlusConfig.class)
public class MyBatisMapperScannerConfig {

    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("cn.licoy.sbm.mapper");
        return mapperScannerConfigurer;
    }
}
