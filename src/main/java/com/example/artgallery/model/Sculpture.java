package com.example.artgallery.model;

public class Sculpture extends Artwork {

    public Sculpture(int id, String title, double price, Artist artist) {
        super(id, title, price, artist);
    }

    @Override
    public String getType() {
        return "Sculpture";
    }
}
