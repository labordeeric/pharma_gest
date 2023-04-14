package com.pharma.app.pharmaproto.controller;

import com.pharma.app.pharmaproto.MainApplication;
import com.pharma.app.pharmaproto.model.User;
import com.pharma.app.pharmaproto.model.UserDao;
import com.pharma.app.pharmaproto.utils.LoginUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public Button btnLogout;
    public Button btnMedicament;
    public Button btnFournisseur;
    public Button btnFamille;
    public BorderPane borderPane;
    public Label lblUser;

    public int id_user = 0;

    public User userLogged = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDashboard();
    }

    public void initializeApp(LoginUtil loginObj, int id) {
        id_user = id;
        try {
            userLogged = new UserDao().getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblUser.setText("user : " + userLogged.getUsername() + " " );
//
//        if (loginObj.hasAdminPermission()){
//            lblTest.setText("Admin");
//        }
//        if (loginObj.hasVendeurPermission()){
//            lblTest.setText("Vendeur");
//        }
//        if(loginObj.hasCaissePermission()){
//            lblTest.setText("Caisse");
//        }

    }

    //Fonction pour déconnecter l'utilisateur
    @FXML
    public void onLogoutClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("LoginView.fxml"));


        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void loadDashboard(){
        Parent view;
        try{
            FXMLLoader newView = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/pharma/app/pharmaproto/DashboardView.fxml")));
            view = newView.load();
            ScrollPane mainScrollPane = new ScrollPane();
            DashboardController dashboardController = newView.getController();
            dashboardController.initializeApp(this);
            mainScrollPane.setContent(view);
            borderPane.setCenter(mainScrollPane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void onDashClick(ActionEvent actionEvent) throws IOException {
        loadDashboard();
    }
@FXML
    public void onMedicamentClick(ActionEvent actionEvent) throws IOException {
    try {
        Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/pharma/app/pharmaproto/view-med.fxml")));
        ScrollPane mainScrollPane = new ScrollPane();
        mainScrollPane.setContent(view);
        borderPane.setCenter(mainScrollPane);
    } catch (Exception e) {
        System.out.println(e);

    }
}
    public void onFamilleClick(ActionEvent actionEvent) throws IOException  {
        try{
            Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/pharma/app/pharmaproto/view-famille.fxml")));
            ScrollPane mainScrollPane = new ScrollPane();
            mainScrollPane.setContent(view);
            borderPane.setCenter(mainScrollPane);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void onFournisseurClick(ActionEvent actionEvent) throws IOException {
        try{
            Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/pharma/app/pharmaproto/view-fournisseur.fxml")));
            ScrollPane mainScrollPane = new ScrollPane();
            mainScrollPane.setContent(view);
            borderPane.setCenter(mainScrollPane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onFormeClick(ActionEvent actionEvent) throws IOException {
        try{
            Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/pharma/app/pharmaproto/view-forme.fxml")));
            ScrollPane mainScrollPane = new ScrollPane();
            mainScrollPane.setContent(view);
            borderPane.setCenter(mainScrollPane);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void onApproClick(ActionEvent actionEvent) throws IOException {
        try{
            Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/pharma/app/pharmaproto/ApproView.fxml")));
            ScrollPane mainScrollPane = new ScrollPane();
            mainScrollPane.setContent(view);
            borderPane.setCenter(mainScrollPane);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    public void onUtilisateurClick(ActionEvent actionEvent) throws IOException {
        try{
            Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/pharma/app/pharmaproto/UserView.fxml")));
            ScrollPane mainScrollPane = new ScrollPane();
            mainScrollPane.setContent(view);
            borderPane.setCenter(mainScrollPane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
