package com.example.test.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CashConfig {

    @Bean
    public CacheManager cacheManager() {
        List<Cache> cacheList = new ArrayList<>();
        cacheList.add(new ConcurrentMapCache("userCache"));
        cacheList.add(new ConcurrentMapCache("orderCache"));
        cacheList.add(new ConcurrentMapCache("accountCache"));
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(cacheList);
        return cacheManager;
    }
}
