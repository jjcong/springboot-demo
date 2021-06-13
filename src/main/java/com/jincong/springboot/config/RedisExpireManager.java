package com.jincong.springboot.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * RedisExpireManager
 * 基于自定义注解@ReidsCacheDuration实现自定义缓存管理器
 * 通过Spring相关方法拿到所有的自定义注解，将过期时间设置到对应的key中
 * 1) 新建类RedisExpireManager继承RedisCacheManager，重写loadCaches方法
 * 2) 实现ApplicationContextAware用于通过application拿到所有的自定义注解
 * 3) 实现InitializingBean 用于执行afterPropertiesSet方法
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/6/13
 */
@Slf4j
public class RedisExpireManager extends RedisCacheManager implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    /**
     * Cache缓存值结构
     */
    private Map<String, RedisCacheConfiguration> initCacheConfiguration = new LinkedHashMap<>();

    /**
     * key 字符序列化器
     */
    private static final StringRedisSerializer STRING_REDIS_SERIALIZER = new StringRedisSerializer();

    /**
     * value son序列化器
     */
    private static final GenericFastJsonRedisSerializer FAST_JSON_REDIS_SERIALIZER = new GenericFastJsonRedisSerializer();

    /**
     * key serializer pair
     */
    private static final RedisSerializationContext.SerializationPair<String> STRING_PAIR = RedisSerializationContext
            .SerializationPair.fromSerializer(STRING_REDIS_SERIALIZER);
    /**
     * value serializer pair
     */
    private static final RedisSerializationContext.SerializationPair<Object> FASTJSON_PAIR = RedisSerializationContext
            .SerializationPair.fromSerializer(FAST_JSON_REDIS_SERIALIZER);

    RedisExpireManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }



    @Override
    protected Collection<RedisCache> loadCaches() {
        return initCacheConfiguration.entrySet().stream()
                .map(entry -> super.createRedisCache(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    @Override
    public void afterPropertiesSet() {
        Stream.of(applicationContext.getBeanNamesForType(Object.class))
                .forEach(beanName -> add(applicationContext.getType(beanName)));
        super.afterPropertiesSet();
    }

    /**
     * 查询所有@RedisCacheExpire的方法 获取过期时间
     *
     * @param clazz RedisCacheExpire注解
     */
    private void add(final Class clazz) {
        ReflectionUtils.doWithMethods(clazz, method -> {
            ReflectionUtils.makeAccessible(method);
            method.getAnnotation(RedisCacheExpire.class);
            RedisCacheExpire cacheDuration = AnnotationUtils.findAnnotation(method, RedisCacheExpire.class);
            if (cacheDuration == null) {
                return;
            }
            Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);
            if (cacheable != null) {
                add(cacheable.cacheNames(), cacheDuration);
            }

        }, method -> null != AnnotationUtils.findAnnotation(method, RedisCacheExpire.class));
    }


    /**
     * 重新设置过期时间到cache缓存中
     * @param cacheNames  bean
     * @param redisCacheExpire 自定义注解
     */
    private void add(String[] cacheNames, RedisCacheExpire redisCacheExpire) {
        for (String cacheName : cacheNames) {
            if (StringUtils.isEmpty(cacheName)) {
                continue;
            }
            long expire = redisCacheExpire.expire();
            TimeType timeType = redisCacheExpire.type();
            log.info("cacheName: {}, expire: {}", cacheName, expire);
            if (expire >= 0) {
                // 缓存配置
                RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(timeType.duration.apply(expire))
                        .disableCachingNullValues()
                        // .prefixKeysWith(cacheName)
                        .serializeKeysWith(STRING_PAIR)
                        .serializeValuesWith(FASTJSON_PAIR);
                initCacheConfiguration.put(cacheName, config);
            } else {
                log.warn("{} use default expiration.", cacheName);
            }

        }
    }


}
