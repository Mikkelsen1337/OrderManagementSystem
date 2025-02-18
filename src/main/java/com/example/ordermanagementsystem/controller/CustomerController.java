package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

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

