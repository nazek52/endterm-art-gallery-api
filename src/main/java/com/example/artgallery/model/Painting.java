package com.example.artgallery.model;

public class Painting extends Artwork {

    public Painting(int id, String title, double price, Artist artist) {
        super(id, title, price, artist);
    }

    @Override
    public String getType() {
        return "Painting";
    }
}
