package net.karakaiv.app.storage;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import net.karakaiv.app.model.StorageData;

public class LocalStorage {
    
    // Cache<String: token, StorageData: data> and expire after 3 mins
    private static final Cache<String, StorageData> cache = Caffeine.newBuilder()
            .expireAfterAccess(3, TimeUnit.MINUTES)
            .build();

    public static void put(String token, StorageData data) {
        cache.put(token, data);
    }

    public static StorageData getIfPresent(String token) {
        return cache.getIfPresent(token);
    }

    public static void invalidate(String token) {
        cache.invalidate(token);
    }
}
