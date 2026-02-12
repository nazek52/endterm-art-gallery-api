package com.example.artgallery.repository;

import com.example.artgallery.model.Artwork;
import com.example.artgallery.patterns.builder.ArtworkBuilder;
import com.example.artgallery.patterns.singleton.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtworkRepository {

    public List<Artwork> findAll() {

        System.out.println("Query executed: SELECT * FROM artworks");

        List<Artwork> artworks = new ArrayList<>();
        String sql = "SELECT * FROM artworks ORDER BY id";

        try (
                Connection conn = DatabaseConnection.getInstance();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                artworks.add(
                        new ArtworkBuilder()
                                .id(rs.getLong("id"))
                                .title(rs.getString("title"))
                                .price(rs.getDouble("price"))
                                .type(rs.getString("type"))
                                .build()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return artworks;
    }

    public Artwork save(String title, double price, String type) {

        String sql = "INSERT INTO artworks(title, price, type) VALUES (?, ?, ?) RETURNING id";

        try (
                Connection conn = DatabaseConnection.getInstance();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, title);
            stmt.setDouble(2, price);
            stmt.setString(3, type);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            Long id = rs.getLong("id");

            return new ArtworkBuilder()
                    .id(id)
                    .title(title)
                    .price(price)
                    .type(type)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Long id) {

        String sql = "DELETE FROM artworks WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getInstance();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
