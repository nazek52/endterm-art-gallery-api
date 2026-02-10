package com.example.artgallery.utils;

import com.example.artgallery.model.Artwork;

import java.util.Comparator;
import java.util.List;

public class SortingUtils {

    public static void sortByTitle(List<Artwork> list) {
        list.sort(Comparator.comparing(Artwork::getTitle));
    }
}
