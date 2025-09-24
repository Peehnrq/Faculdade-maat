module main.maat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens main.maat to javafx.fxml;
    exports main.maat;
    exports main.maat.controller;
    opens main.maat.controller to javafx.fxml;
    opens main.maat.model to javafx.base;


    exports main.maat.model;
    exports main.maat.DAO;
    exports main.maat.service;
    exports main.maat.util;
}
