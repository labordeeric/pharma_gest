package com.pharma.app.pharmaproto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarPicker;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button btnSeuil;

    @FXML
    private CalendarPicker calendrier;

    @FXML
    private Label lblBeneficeTotal;

    @FXML
    private Label lblNombreSeuil;

    @FXML
    private Label lblVenteTotal;

    @FXML
    private Label lblDate;

    public MainController mController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Gestion d'evenement sur le calendrier à chaque clique sur une date
        calendrier.calendarProperty().addListener((observable, oldValue, newValue) -> {
            Date selectedDate = newValue.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String selectedDateString = dateFormat.format(selectedDate);
            // Mise à jour du label avec la nouvelle date selectionnée
            lblDate.setText(selectedDateString);
        });
    }

    void initializeApp(MainController mainController){
        mController = mainController;
    }

    @FXML
    void btnSeuilOnAction(ActionEvent event) {
        //TODO
        Parent view;
        FXMLLoader newFxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/pharma/app/pharmaproto/DashboardApproView.fxml")));
        try {
            view = newFxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DashboardApproController dashboardApproController = newFxmlLoader.getController();
        dashboardApproController.initializeApp(mController);
        Stage stage = new Stage();
        stage.setScene(new Scene(view));
        stage.setTitle("Approvisionnement Seuil");
        stage.show();
    }

}
