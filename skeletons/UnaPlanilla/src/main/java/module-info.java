module unaplanilla {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires java.base;
    requires jakarta.json;
    requires jakarta.ws.rs;
    requires jakarta.xml.bind;
    requires javafx.graphics;
    requires transitive javafx.base;
    requires com.jfoenix;

    opens cr.ac.una.unaplanilla to javafx.fxml, javafx.graphics;
    opens cr.ac.una.unaplanilla.controller to javafx.fxml, javafx.controls, com.jfoenix;

    exports cr.ac.una.unaplanilla.model;
}
