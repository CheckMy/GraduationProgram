package com.graduation.design.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Set;

/**
 * @author xuweizhi
 * @date 2018/11/14 9:36
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    private Duration timeToLive = Duration.ZERO;

    public void setTimeToLive(Duration timeToLive) {
        this.timeToLive = timeToLive;
    }

    //RedisCacheManager的基本属性
    //@SuppressWarnings("rawtypes") //
    //配置redisTemplate 通过构造函数
    //private final RedisOperations redisOperations;
    ////是否使用前缀
    //private boolean usePrefix = false;
    ////默认前缀 为":"。使用前缀可以对缓存进行分组，避免缓存的冲突
    //private RedisCachePrefix cachePrefix = new DefaultRedisCachePrefix();
    ////远程加载缓存
    //private boolean loadRemoteCachesOnStartup = false;
    ////是否动态生成缓存。默认为true。这个就是上面如果缓存不存在，则创建
    ////是通过这个属性进行配置。配置为false则不会去创建缓存
    //private boolean dynamic = true;
    //
    //// 过期时间 0为永不过期
    //private long defaultExpiration = 0;
    ////可以配置指定key的过期时间 可以通过定制化配置过期时间
    //private Map<String, Long> expires = null;
    ////配置缓存名称集合
    //private Set<String> configuredCacheNames;
    ////缓存是否可以为null
    //private final boolean cacheNullValues;

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //Redis加锁的写入器
        //RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        //启动Redis默认设置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(this.timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                .disableCachingNullValues();

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
        return redisCacheManager;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }


    /**
     * 自定义CacheManager
     */
   /* @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        //全局redis缓存过期时间
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1));
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }*/

    /**
     * 自定义redis key值生成策略
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
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

    /**
     * 设置RedisTemplate序列化器
     *
     * @param redisConnectionFactory 注入Redis工厂
     * @return 返回Redis模板
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 配置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public ValueOperations<String, String> opsForValue(RedisTemplate redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, Object> opsForList(RedisTemplate redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public HashOperations opsForHash(RedisTemplate redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public SetOperations opsForSet(RedisTemplate redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations opsForZSet(RedisTemplate redisTemplate) {
        return redisTemplate.opsForZSet();
    }


}
