package com.graduation.design.config;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @ClassName CacheManagerInstruction
 * @Author XuWeiZhi
 * @Description
 * @Date 2018-11-14 22:52 星期三 web-app
 * @VERSION 1.0.0
 **/
public class CacheManagerInstruction {

    //public void initializeCaches() {
    //    //首先加载缓存。loadCaches是一个模板方法，具体怎么加载子类决定。
    //    Collection<? extends Cache> caches = loadCaches();
    //
    //    synchronized (this.cacheMap) {
    //        //每次初始化都创建一个新的缓存名称Set。代码最后一行，把这个set集合变成只读
    //        this.cacheNames = Collections.emptySet();
    //        //清空缓存Map  ConcurrentHashMap
    //        this.cacheMap.clear();
    //        //初始化 cacheNames
    //        //1. 循环遍历子类加载的caches
    //        Set<String> cacheNames = new LinkedHashSet<String>(caches.size());
    //        for (Cache cache : caches) {
    //            String name = cache.getName();
    //            //加入到缓存map集合中，在加入前要进行对缓存进行装配 decorateCache(cache)
    //            //decorateCache 这个方法本身就是返回cache。但是子类
    //            //AbstractTransactionSupportingCacheManager 重写它。这个类从名字可以看出
    //            //是支持事务。它有一个属性transactionAware默认为false. 如果配置了支持事务，
    //            //就会把当前cache装配成支持事物的cache 所以后面会有支持事务的配置，配置的就是
    //            //transactionAware 这个属性为true
    //            //TransactionAwareCacheDecorator
    //            this.cacheMap.put(name, decorateCache(cache));
    //            //2. 把cache的name加入到name集合中
    //            cacheNames.add(name);
    //        }
    //        //set变成只读
    //        this.cacheNames = Collections.unmodifiableSet(cacheNames);
    //    }
    //}
    //
    ////初始化缓存之后，也实现了基本的获取缓存方法：
    //public Cache getCache(String name) {
    //    //根据缓存name直接从自己的cache map中获取
    //    Cache cache = this.cacheMap.get(name);
    //    if (cache != null) {
    //        return cache;
    //    }
    //    else {
    //        //如果没有，那么就同步创建
    //        synchronized (this.cacheMap) {
    //            //这里再一次获取，避免在同步前，有线程已经添加进去了。
    //            cache = this.cacheMap.get(name);
    //            if (cache == null) {
    //                //如果还没有，就调用getMissingCache方法获取。
    //                //这个方法可以看作一个模板方法，只不过自己实现了返回null
    //                //也就是说默认不创建。子类可以通过重写这个方法进行创建。
    //                cache = getMissingCache(name);
    //                if (cache != null) {
    //                    //对创建的cache进行装配
    //                    cache = decorateCache(cache);
    //                    //放入缓存 map中
    //                    this.cacheMap.put(name, cache);
    //                    //更新缓存名称集合。 上面我们知道缓存名称集合被修改成只读，
    //                    //所以更新方法里面是创建一个新的，然后size进行加1，再把前面的
    //                    //添加进去，然后加入新建的这个，再修改成只读
    //                    updateCacheNames(name);
    //                }
    //            }
    //            return cache;
    //        }
    //    }
    //}
    //
    ////final类型。子类不可重写
    //protected final Cache lookupCache(String name) {
    //    return this.cacheMap.get(name);
    //}
    //
    ////通过上面对缓存的一个基本加载获取有了认知之后，就看看redis具体怎么对其自己想要的功能进行扩展，首先我们应该知道，redis是一个键值对的缓存框架，
    //// 当然值由多种类型。在spring-data-redis中，我们操作redis又是通过RedisOperations（RedisTemplate间接的实现了它）对其进行操作，并且在
    //// 读取和写入的时候，都会对键值进行序列化。所以，如果要扩展，RedisOperations比不可少，redis支持事务，那么我们就可以利用上面的
    //// AbstractTransactionSupportingCacheManager属性transactionAware进行配置。其它诸如：过期时间，缓存是否为null等等进行扩展，
    //// 具体看看源码RedisCacheManager：
    //
    //protected Collection<? extends Cache> loadCaches() {
    //    //首先我们必须配置了redisOperations。因为没有redisTemplate我们就无法操作redis
    //    Assert.notNull(this.redisOperations, "A redis template is required in order to interact with data store");
    //    //首先看是否需要远程加载缓存
    //    //如果配置是，那么久执行  loadAndInitRemoteCaches（）方法进行加载
    //    //这个远程加载比较容易。就是用redisTemplate去加载后缀~keys的key.
    //    //后缀为~keys是因为在创建的时候加上了这个后缀。具体可以查看redis的静态内部类
    //    //RedisCacheMetadata 的构造方法。
    //    Set<Cache> caches = new LinkedHashSet<Cache>(
    //            loadRemoteCachesOnStartup ? loadAndInitRemoteCaches() : new ArrayList<Cache>());
    //    //加载我们配置的缓存名称集合 上面属性有介绍
    //    Set<String> cachesToLoad = new LinkedHashSet<String>(this.configuredCacheNames);
    //    //加载当前已经缓存名称集合 volatile类型。所以对于当前线程是可见的
    //    cachesToLoad.addAll(this.getCacheNames());
    //
    //    if (!CollectionUtils.isEmpty(cachesToLoad)) {
    //        //如果不为空则循环创建缓存 并加入缓存集合中
    //        //这个缓存为redisCache.创建就是根据我们配置的如过期时间，是否允许为null,序列化（默认为
    //        //jdk序列化）等属性创建一个redisCache
    //        for (String cacheName : cachesToLoad) {
    //            caches.add(createCache(cacheName));
    //        }
    //    }
    //
    //    return caches;
    //}

}

