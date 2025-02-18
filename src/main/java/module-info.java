module com.example.ordermanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.ordermanagementsystem;
    exports com.example.ordermanagementsystem.controller; // Gør controller tilgængelig for andre moduler
    opens com.example.ordermanagementsystem.controller to javafx.fxml; // Tillader JavaFX at tilgå controller-klasser
}
