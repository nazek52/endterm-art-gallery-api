package com.example.artgallery.patterns.builder;

import com.example.artgallery.model.Artist;
import com.example.artgallery.model.Artwork;
import com.example.artgallery.patterns.factory.ArtworkFactory;

public class ArtworkBuilder {

    private String type;
    private int id;
    private String title;
    private double price;
    private Artist artist;

    public ArtworkBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public ArtworkBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ArtworkBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArtworkBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ArtworkBuilder setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }

    public Artwork build() {
        if (type == null || title == null || artist == null) {
            throw new IllegalStateException("Artwork fields are not fully initialized");
        }

        return ArtworkFactory.createArtwork(
                type,
                id,
                title,
                price,
                artist
        );
    }
}
