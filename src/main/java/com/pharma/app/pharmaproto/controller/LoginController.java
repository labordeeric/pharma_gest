package com.pharma.app.pharmaproto.controller;

import com.pharma.app.pharmaproto.model.Anarana;

import com.pharma.app.pharmaproto.utils.LoginUtil;
import com.pharma.app.pharmaproto.utils.ModelBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class LoginController {
    @FXML
    private PasswordField txtPassword;

    private Stage stage;
    @FXML
    private TextField txtUsername;




    @FXML
    void afficheAide(MouseEvent event) {

    }

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException {

        LoginUtil login = new LoginUtil();
        if (login.login(txtUsername.getText(), txtPassword.getText())){
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/pharma/app/pharmaproto/AppView.fxml"));
            FXMLLoader fxmlLoaderNew = new FXMLLoader(getClass().getResource("/com/pharma/app/pharmaproto/view-main.fxml"));

//            Parent root = fxmlLoader.load();
            Parent rootNew = fxmlLoaderNew.load();

//            AppController appController = fxmlLoader.getController();
            MainController appMainController = fxmlLoaderNew.getController();

//            appController.initializeApp(login, login.id_user);
            appMainController.initializeApp(login, login.id_user);

//            Scene scene = new Scene(root);
            Scene sceneMain = new Scene(rootNew);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
            Image logo = new Image(getClass().getResourceAsStream("/com/pharma/app/pharmaproto/assets/logoP.png"));
            stage = new Stage();
            stage.getIcons().add(logo);
            stage.setTitle("Pharmagest App");
//            stage.setScene(scene);
            stage.setScene(sceneMain);
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur d'authentification");
            alert.setContentText("Veuillez vérifier vos informations de connexion");
            alert.showAndWait();
        }

    }

    @FXML
    void btnLomAction(ActionEvent event) {
        try{
            Anarana anarana = new ModelBuilder<Anarana>(Anarana.class)
                    .set("id", 1)
                    .set("anarana", "Rakotoarisoa")
                    .set("taona", 40)
                    .build();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lombok Alert");
            alert.setHeaderText("message");
            alert.setContentText("id : " + anarana.getId() + " anarana : " + anarana.getAnarana()  + "taona : " + anarana.getTaona());
            alert.showAndWait();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    @FXML
    void btnTestAction(ActionEvent event) throws URISyntaxException {
        Path configPath = Paths.get(getClass().getResource("/com/pharma/app/pharmaproto/conf/conf.json").toURI());
        String content = null;
        try {
            content = Files.readString(configPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // parse the JSON content into a JSON object
        JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();

        // extract the login information from the JSON object
        String username = jsonObject.getAsJsonObject("login").get("username").getAsString();
        String password = jsonObject.getAsJsonObject("login").get("password").getAsString();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Test Alert");
        alert.setHeaderText("message");
        alert.setContentText("username : " + username + "password : " + password);
        alert.showAndWait();

    }

    @FXML
    void exitAction(MouseEvent event) {
    System.exit(0);
    }

    public void setPrimaryStage(Stage stage){
        this.stage = stage;
    }
}