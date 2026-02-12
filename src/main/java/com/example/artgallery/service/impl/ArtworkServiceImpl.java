package com.example.artgallery.service.impl;

import com.example.artgallery.model.Artwork;
import com.example.artgallery.patterns.cache.ArtworkCache;
import com.example.artgallery.repository.ArtworkRepository;
import com.example.artgallery.service.ArtworkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    private static final String CACHE_KEY = "all_artworks";

    private final ArtworkRepository repository = new ArtworkRepository();
    private final ArtworkCache cache = ArtworkCache.getInstance();

    @Override
    public List<Artwork> getAllArtworks() {

        List<Artwork> cached = cache.get(CACHE_KEY);

        if (cached != null) {
            System.out.println("Returned from CACHE");
            return cached;
        }

        System.out.println("Fetching from DATABASE");
        List<Artwork> artworks = repository.findAll();
        cache.put(CACHE_KEY, artworks);

        return artworks;
    }

    @Override
    public Artwork createArtwork(String title, double price, String type) {

        Artwork artwork = repository.save(title, price, type);

        cache.invalidate(CACHE_KEY);

        return artwork;
    }

    @Override
    public void deleteArtwork(Long id) {

        repository.delete(id);

        cache.invalidate(CACHE_KEY);
    }

    @Override
    public void clearCache() {
        cache.clear();
    }

    @Override
    public String getCacheStats() {
        return "Cache Hits: " + cache.getHitCount() +
                ", Cache Misses: " + cache.getMissCount();
    }
}

}

