package com.example.artgallery.service;

import com.example.artgallery.model.Artist;

import java.util.List;

public interface ArtistService {

    void create(Artist artist);

    List<Artist> getAll();

    Artist getById(int id);
}
