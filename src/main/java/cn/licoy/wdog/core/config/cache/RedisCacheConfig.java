package cn.licoy.wdog.core.config.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @author Licoy
 * @version 2018/5/3/9:45
 */
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Bean
    @ConfigurationProperties("spring.redis")
    public JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator(){
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };

    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

}
