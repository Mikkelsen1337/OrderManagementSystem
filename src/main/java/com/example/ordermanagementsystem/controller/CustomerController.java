package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.DatabaseConnection;
import com.example.ordermanagementsystem.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.*;

public class CustomerController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    private static final String URL = "jdbc:mysql://localhost:3306/ordermanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "Marchholdt3M";

    @FXML
    private void findCustomerByEmail() {
        String email = emailField.getText().trim();

        if (email.isEmpty()) {
            System.out.println("Indtast venligst en email");
            return;
        }

        String sql = "SELECT * FROM customers WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");

                System.out.println("Kunde fundet:");
                System.out.println("ID: " + id);
                System.out.println("Navn: " + name);
                System.out.println("Email: " + email);
                System.out.println("Telefon: " + phone);
            } else {
                System.out.println("Ingen kunde fundet med den email.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl ved databaseopslag.");
        }
    }


    @FXML
    private void saveCustomer() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            System.out.println("Udfyld alle felter. ");
        return;
        }

        Customer customer = new Customer(0, name, email, phone);
        saveToDatabase(customer);
    }
    private void saveToDatabase(Customer customer) {
        String sql = "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)";


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, customer.getName());
                statement.setString(2, customer.getEmail());
                statement.setString(3, customer.getPhone());
                statement.executeUpdate();
                System.out.println("Kunde gemt! ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

