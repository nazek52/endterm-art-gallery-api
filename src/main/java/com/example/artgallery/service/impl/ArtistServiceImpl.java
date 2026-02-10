package com.example.artgallery.service.impl;

import com.example.artgallery.exception.ResourceNotFoundException;
import com.example.artgallery.model.Artist;
import com.example.artgallery.repository.ArtistRepository;
import com.example.artgallery.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository repository;

    public ArtistServiceImpl(ArtistRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Artist artist) {
        repository.save(artist);
    }

    @Override
    public List<Artist> getAll() {
        return repository.findAll();
    }

    @Override
    public Artist getById(int id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Artist not found with id " + id));
    }
}
