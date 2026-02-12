package com.example.artgallery.service;

import com.example.artgallery.model.Artwork;
import java.util.List;

public interface ArtworkService {

    List<Artwork> getAllArtworks();

    Artwork createArtwork(String title, double price, String type);

    void deleteArtwork(Long id);

    void clearCache();

    String getCacheStats();
}
