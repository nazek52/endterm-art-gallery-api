package com.example.artgallery.patterns.builder;

import com.example.artgallery.model.Artwork;
import com.example.artgallery.patterns.factory.ArtworkFactory;

public class ArtworkBuilder {

    private Long id;
    private String title;
    private double price;
    private String type;

    public ArtworkBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ArtworkBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ArtworkBuilder price(double price) {
        this.price = price;
        return this;
    }

    public ArtworkBuilder type(String type) {
        this.type = type;
        return this;
    }

    public Artwork build() {
        return ArtworkFactory.create(id, title, price, type);
    }
}

