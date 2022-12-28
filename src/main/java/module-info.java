module com.example.foxtrotdatabases {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;

    opens com.example.foxtrotdatabases to javafx.fxml;
    opens com.example.foxtrotdatabases.Games to javafx.fxml, org.hibernate.orm.core;
    exports com.example.foxtrotdatabases.Games;
}