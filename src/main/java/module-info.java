module com.example.foxtrotdatabases {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires hibernate.entitymanager;
    requires org.hibernate.orm.core;

    opens com.example.foxtrotdatabases to javafx.fxml, org.hibernate.orm.core;
    exports com.example.foxtrotdatabases;
    exports com.example.foxtrotdatabases.Teams;
    opens com.example.foxtrotdatabases.Teams to javafx.fxml,org.hibernate.orm.core;
    exports com.example.foxtrotdatabases.Games;
    opens com.example.foxtrotdatabases.Games to javafx.fxml, org.hibernate.orm.core;
    exports com.example.foxtrotdatabases.Players;
    opens com.example.foxtrotdatabases.Players to javafx.fxml, org.hibernate.orm.core;
    exports com.example.foxtrotdatabases.Matches;
    opens com.example.foxtrotdatabases.Matches to javafx.fxml, org.hibernate.orm.core;
}