package com.example.artgallery.patterns.singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static Connection connection;

    private DatabaseConnection() {}

    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/gallery.db",
                        "postgres",
                        "Ilovemath8"
                );
            } catch (Exception e) {
                throw new RuntimeException("DB connection failed", e);
            }
        }
        return connection;
    }
}
