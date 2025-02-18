package com.example.ordermanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection_GitUpload {
    private static final String URL = "jdbc:mysql://localhost:3306/ordermanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "PLACEHOLDER_TIL_GITHUB";


    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Forbindelse oprettet! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


