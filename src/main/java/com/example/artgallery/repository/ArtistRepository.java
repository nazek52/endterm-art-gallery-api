package com.example.artgallery.repository;

import com.example.artgallery.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    private final List<Artist> storage = new ArrayList<>();

    public void save(Artist artist) {
        storage.add(artist);
    }

    public List<Artist> findAll() {
        return storage;
    }

    public Optional<Artist> findById(int id) {
        return storage.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }
}
