package com.example.artgallery.controller;

import com.example.artgallery.model.Artwork;
import com.example.artgallery.service.ArtworkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtworkController {

    private final ArtworkService service;

    public ArtworkController(ArtworkService service) {
        this.service = service;
    }

    @GetMapping("/api/artworks")
    public List<Artwork> getAllArtworks() {
        return service.getAllArtworks();
    }
}
