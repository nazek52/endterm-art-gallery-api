package com.example.artgallery.controller;

import com.example.artgallery.dto.ArtworkRequest;
import com.example.artgallery.model.Artwork;
import com.example.artgallery.service.ArtworkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkController {

    private final ArtworkService artworkService;

    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping
    public List<Artwork> getAllArtworks() {
        return artworkService.getAllArtworks();
    }

    @PostMapping
    public Artwork createArtwork(@RequestBody ArtworkRequest request) {
        return artworkService.createArtwork(
                request.title,
                request.price,
                request.type
        );
    }

    @DeleteMapping("/{id}")
    public String deleteArtwork(@PathVariable Long id) {
        artworkService.deleteArtwork(id);
        return "Artwork deleted successfully";
    }

    @DeleteMapping("/cache")
    public String clearCache() {
        artworkService.clearCache();
        return "Cache cleared successfully";
    }

    @GetMapping("/stats")
    public String getCacheStats() {
        return artworkService.getCacheStats();
    }
}
