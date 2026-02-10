package com.example.artgallery.repository;

import com.example.artgallery.model.Artwork;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArtworkRepository {

    private final List<Artwork> storage = new ArrayList<>();

    public void save(Artwork artwork) {
        storage.add(artwork);
    }

    public List<Artwork> findAll() {
        return storage;
    }
}
