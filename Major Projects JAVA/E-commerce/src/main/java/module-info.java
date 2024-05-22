module com.example.ecommerce {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ecommerce to javafx.fxml;
    exports com.example.ecommerce;
}