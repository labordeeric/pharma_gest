package com.pharma.app.pharmaproto.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import jfxtras.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardApproController implements Initializable {
    @FXML
    private Button btnAccesAppro;

    @FXML
    private Button btnFermer;

    @FXML
    private ListView<?> listViewAppro;

    public MainController mController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void initializeApp(MainController mainController){
        mController = mainController;
    }

    @FXML
    void btnAccesApproOnAction(ActionEvent event) {
        Parent view;
        try {
            view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/pharma/app/pharmaproto/ApproView.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mController.borderPane.setCenter(view);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnFermerOnAction(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();

    }
}
