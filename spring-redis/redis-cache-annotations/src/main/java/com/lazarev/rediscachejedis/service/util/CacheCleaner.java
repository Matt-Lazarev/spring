package com.lazarev.rediscachejedis.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@SuppressWarnings("ConstantConditions")
public class CacheCleaner {
    private final CacheManager cacheManager;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        cacheManager.getCacheNames()
                .parallelStream()
                .forEach(n -> cacheManager.getCache(n).clear());
    }
}
