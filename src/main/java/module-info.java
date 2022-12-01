module com.example.foxtrotdatabases {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.foxtrotdatabases to javafx.fxml;
    exports com.example.foxtrotdatabases;
}