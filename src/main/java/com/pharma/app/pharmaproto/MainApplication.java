package com.pharma.app.pharmaproto;

import com.pharma.app.pharmaproto.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("LoginView.fxml"));
        //sendStage(stage, fxmlLoader);
        Scene scene = new Scene(fxmlLoader.load());
        //Image logo = new Image("/resources/com/pharma/app/pharmaproto/assets/logoP.png");
        Image logo = new Image(getClass().getResourceAsStream("assets/logoP.png"));
        stage.getIcons().add(logo);
        stage.setTitle("Pharmagest Login");
        stage.setScene(scene);
        stage.show();
    }

    public void sendStage(Stage primaryStage, FXMLLoader fxmlLoader){

        LoginController loginController = fxmlLoader.getController();
        loginController.setPrimaryStage(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}