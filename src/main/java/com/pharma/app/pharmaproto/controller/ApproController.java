package com.pharma.app.pharmaproto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ApproController implements Initializable {

    @FXML
    private Button btnAjout;

    @FXML
    private Button btnAjoutTotal;

    @FXML
    private Button btnLancer;

    @FXML
    private ComboBox<?> cbFournisseur;

    @FXML
    private ComboBox<?> cbMedicament;

    @FXML
    private ComboBox<?> cbStatut;

    @FXML
    private DatePicker dateRecu;

    @FXML
    private Label lblMessagePlacement;

    @FXML
    private ListView<?> listViewMedicament;

    @FXML
    private TextField prixFournisseur;

    @FXML
    private TextField prixVente;

    @FXML
    private TextField qteCommande;

    @FXML
    private TextField qteRecu;

    @FXML
    private TableView<?> tblAppro;

    @FXML
    private TableView<?> tblViewApproCumul;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    @FXML
    void btnAjoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnAjoutTotalOnAction(ActionEvent event) {

    }

    @FXML
    void btnLancerOnAction(ActionEvent event) {

    }

    @FXML
    void onListViewMouseClicked(MouseEvent event) {

    }

}
