module com.pharma.app.pharmaproto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires com.google.gson;
    requires com.jfoenix;
    requires jfxtras.icalendaragenda;

    opens com.pharma.app.pharmaproto to javafx.fxml;
    exports com.pharma.app.pharmaproto;
    exports com.pharma.app.pharmaproto.controller;
    exports com.pharma.app.pharmaproto.model;
    exports com.pharma.app.pharmaproto.utils;
    opens com.pharma.app.pharmaproto.controller to javafx.fxml;
}