package com.example.artgallery.service.impl;

import com.example.artgallery.model.*;
import com.example.artgallery.repository.ArtworkRepository;
import com.example.artgallery.service.ArtworkService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    private final ArtworkRepository repository;

    public ArtworkServiceImpl(ArtworkRepository repository) {
        this.repository = repository;
    }

    // 🔥 ВАЖНО: данные загружаются при старте
    @PostConstruct
    public void init() {
        repository.save(new Painting(1, "Starry Night", 1_000_000,
                new Artist(1, "Vincent van Gogh")));

        repository.save(new Painting(2, "Mona Lisa", 850_000,
                new Artist(2, "Leonardo da Vinci")));

        repository.save(new Sculpture(3, "The Thinker", 300_000,
                new Artist(3, "Auguste Rodin")));

        repository.save(new Sculpture(4, "Venus de Milo", 400_000,
                new Artist(4, "Alexandros of Antioch")));

        repository.save(new Painting(5, "The Scream", 900_000,
                new Artist(5, "Edvard Munch")));

        repository.save(new Sculpture(6, "David", 500_000,
                new Artist(6, "Michelangelo")));
    }

    @Override
    public List<Artwork> getAllArtworks() {
        return repository.findAll();
    }
}
