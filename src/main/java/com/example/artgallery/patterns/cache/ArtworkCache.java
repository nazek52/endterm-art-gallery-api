package com.example.artgallery.patterns.cache;

import com.example.artgallery.model.Artwork;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ArtworkCache {

    private static volatile ArtworkCache instance;

    private static final long TTL_SECONDS = 60;

    private static class CacheEntry {
        List<Artwork> data;
        Instant timestamp;

        CacheEntry(List<Artwork> data) {
            this.data = data;
            this.timestamp = Instant.now();
        }
    }

    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

    private final AtomicLong hitCount = new AtomicLong(0);
    private final AtomicLong missCount = new AtomicLong(0);

    private ArtworkCache() {}

    // Double-Checked Locking
    public static ArtworkCache getInstance() {
        if (instance == null) {
            synchronized (ArtworkCache.class) {
                if (instance == null) {
                    instance = new ArtworkCache();
                }
            }
        }
        return instance;
    }

    public void put(String key, List<Artwork> artworks) {
        cache.put(key, new CacheEntry(artworks));
    }

    public List<Artwork> get(String key) {
        CacheEntry entry = cache.get(key);

        if (entry == null) {
            missCount.incrementAndGet();
            return null;
        }

        if (isExpired(entry)) {
            cache.remove(key);
            missCount.incrementAndGet();
            return null;
        }

        hitCount.incrementAndGet();
        return entry.data;
    }

    private boolean isExpired(CacheEntry entry) {
        return Instant.now().isAfter(entry.timestamp.plusSeconds(TTL_SECONDS));
    }

    public void invalidate(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    public long getHitCount() {
        return hitCount.get();
    }

    public long getMissCount() {
        return missCount.get();
    }
}

