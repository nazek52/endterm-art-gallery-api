package com.example.artgallery.patterns.factory;

import com.example.artgallery.model.*;

public class ArtworkFactory {

    private ArtworkFactory() {
    }

    public static Artwork createArtwork(
            String type,
            int id,
            String title,
            double price,
            Artist artist
    ) {
        if (type == null) {
            throw new IllegalArgumentException("Artwork type cannot be null");
        }

        return switch (type.toLowerCase()) {
            case "painting" -> new Painting(id, title, price, artist);
            case "sculpture" -> new Sculpture(id, title, price, artist);
            default -> throw new IllegalArgumentException(
                    "Unknown artwork type: " + type
            );
        };
    }
}
