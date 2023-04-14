package com.pharma.app.pharmaproto.controller;

import com.pharma.app.pharmaproto.MainApplication;
import com.pharma.app.pharmaproto.model.Role;
import com.pharma.app.pharmaproto.model.UserDao;
import com.pharma.app.pharmaproto.model.User;
import com.pharma.app.pharmaproto.utils.LoginUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label lblTest;
    @FXML
    private Label lblUsername;
    @FXML
    private Button addPurchase;

    @FXML
    private Button btnAide;

    @FXML
    private Button btnLogout;

    @FXML
    private Button home;

    @FXML
    private Button btnMedicament;

    @FXML
    private Button page02;

    @FXML
    private Button btnRegister;

    @FXML
    private Button page04;

    @FXML
    private Button page05;

    @FXML
    private Button page06;

    @FXML
    private Button page07;

    @FXML
    private Button purchaseDetail;

    private Stage stage;

    public int id_user = 0;
    public User userLogged = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initializeApp(LoginUtil loginObj, int id) {
        id_user = id;
        try {
            userLogged = new UserDao().getUserById(id);
            for (Role role: userLogged.getRoles()
                 ) {
                System.out.println(role.getRole_id());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblUsername.setText("user : " + userLogged.getUsername() + " " );
        lblTest.setText("Aucune Permission");
        if (loginObj.hasAdminPermission()){
            lblTest.setText("Admin");
        }
        if (loginObj.hasVendeurPermission()){
            lblTest.setText("Vendeur");
        }
        if(loginObj.hasCaissePermission()){
            lblTest.setText("Caisse");
        }

    }

    @FXML
    void afficheAide(ActionEvent event) {

    }

//    clickedButton.setStyle("-fx-text-fill:#f0f0f0;-fx-background-color:#2b2a26;");
//    OtherButton.setStyle("-fx-text-fill:#f0f0f0;-fx-background-color:#404040;");

    @FXML
    void btnLogoutAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("LoginView.fxml"));


        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loadHomeView(ActionEvent event) {

    }

    @FXML
    void btnMedicamentAction(ActionEvent event) {
        //Déclaration des affichages
        Parent anchorMedic;


        ScrollPane medicScrollPane = new ScrollPane();
        //mise en place des fonctionnalités


        try{
            anchorMedic = FXMLLoader.load(getClass().getResource("/com/pharma/app/pharmaproto/view-medicament.fxml"));

            //Mise en place des contenu dans les scrollpanes
            medicScrollPane.setContent(anchorMedic);
            //Mise en place du tabpane dans le borderpane
            borderPane.setCenter(medicScrollPane);
        }catch(IOException ex){
            Label lblError = new Label("Erreur : "+ ex);
        }
    }

    @FXML
    void btnFournisseurAction(ActionEvent event) {
        Parent anchorFournisseur;
        ScrollPane fournisseurScrollPane = new ScrollPane();
        try{
        anchorFournisseur= FXMLLoader.load(getClass().getResource("/com/pharma/app/pharmaproto/view-fournisseur.fxml"));
        //Mise en place des contenu dans les scrollpanes
        fournisseurScrollPane.setContent(anchorFournisseur);
        //Mise en place du tabpane dans le borderpane
        borderPane.setCenter(fournisseurScrollPane);
    }catch(IOException ex){
        Label lblError = new Label("Erreur : "+ ex);
    }
    }

    @FXML
    void btnRegisterAction(ActionEvent event) {
        Parent anchorRegister;
        ScrollPane registerScrollPane = new ScrollPane();
        try {
            anchorRegister = FXMLLoader.load(getClass().getResource("/com/pharma/app/pharmaproto/UserView.fxml"));
            //Mise en place des contenu dans les scrollpanes
            registerScrollPane.setContent(anchorRegister);
            //Mise en place du tabpane dans le borderpane
            borderPane.setCenter(registerScrollPane);
        } catch (Exception ex) {
            //Label lblError = new Label("Erreur : "+ ex);
            System.out.println(ex);
        }

    }

    @FXML
    void loadPage04View(ActionEvent event) {

    }

    @FXML
    void loadPage05View(ActionEvent event) {

    }

    @FXML
    void loadPage06View(ActionEvent event) {

    }

    @FXML
    void loadPage07View(ActionEvent event) {

    }

}
