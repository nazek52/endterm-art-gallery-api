package com.example.artgallery.controller;

import com.example.artgallery.dto.ArtistRequest;
import com.example.artgallery.model.Artist;
import com.example.artgallery.service.ArtistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService service;

    public ArtistController(ArtistService service) {
        this.service = service;
    }

    @GetMapping
    public List<Artist> getAll() {
        System.out.println("Log called method");
        return service.getAll();
    }

    @PostMapping
    public void create(@RequestBody ArtistRequest request) {
        service.create(new Artist(request.getId(), request.getName()));
    }

    @GetMapping("/{id}")
    public Artist getById(@PathVariable int id) {
        System.out.println("Log called method");
        return service.getById(id);
    }
}
