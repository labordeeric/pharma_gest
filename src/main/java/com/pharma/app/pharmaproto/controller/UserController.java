package com.pharma.app.pharmaproto.controller;

import com.jfoenix.controls.*;
import com.pharma.app.pharmaproto.MainApplication;
import com.pharma.app.pharmaproto.model.Medicament;
import com.pharma.app.pharmaproto.model.Role;
import com.pharma.app.pharmaproto.model.UserDao;
import com.pharma.app.pharmaproto.model.User;
import com.pharma.app.pharmaproto.utils.DbContext;
import com.pharma.app.pharmaproto.utils.ModelBuilder;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UserController implements Initializable {

    @FXML
    private TableColumn<User, String> colAdresse;

    @FXML
    private TableColumn<User, Date> colCreation;

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<User, String> colNom;

    @FXML
    private TableColumn<User, Integer> colPhone;

    @FXML
    private TableColumn<User, String> colPrenom;

    @FXML
    private TableColumn<User, Integer> colRef;

    @FXML
    private TableColumn<User, String> colRole;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableView<User> tblUser;
    @FXML
    private JFXListView<Role> listViewRole;

    @FXML
    private TextField email;
    //Email Regex
    String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";

    @FXML
    private Label lblMessage;

    @FXML
    private TextField password;

    @FXML
    private TextField rptPassword;

    @FXML
    private TextField tel_no;

    @FXML
    private Tooltip tipDescription;

    @FXML
    private TextField username;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField adresse;

    private ObservableList<Role> roles;
    private ObservableList<Role> selectedRoles = FXCollections.observableArrayList();
    private ObservableList<User> userObList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeListUser();
        addButtonToTable();
        initializeListRole();
    }

    @FXML
    void rptPasswordAction(KeyEvent event) {
        passwordCheck();
    }

    @FXML
    void rptPasswordReleased(KeyEvent event) {
        passwordCheck();
    }

    void passwordCheck(){
        if (!password.getText().trim().isEmpty()){
            if (!password.getText().equals(rptPassword.getText())) {
                lblMessage.setText("Mot de passe pas pareil");
                rptPassword.setStyle("-fx-focus-color: firebrick");
                lblMessage.setTextFill(Color.RED);

            } else {
                lblMessage.setText("");
                rptPassword.setStyle("-fx-focus-color: #00FF00;");
            }
        }
    }

    @FXML
    void emailTyped(KeyEvent event) {
        //text.matches(regex) , this is the use of regex
        if (email.getText().trim().matches(emailRegex) == false) {
            lblMessage.setText("Email non valide");
            lblMessage.setTextFill(Color.RED);
            email.setStyle("-fx-focus-color: firebrick");
        } else{
            lblMessage.setText("");
            email.setStyle("-fx-focus-color: #00FF00;");
        }
    }

    @FXML
    void usernameTyped(KeyEvent event) {
        String usernameText = username.getText().trim();
        if (usernameText.isEmpty()) {
            lblMessage.setText("Le nom d'utilisateur ne peut pas être vide");
            lblMessage.setTextFill(Color.RED);
            username.setStyle("-fx-focus-color: firebrick");
        } else if (usernameText.length() < 4) {
            lblMessage.setText("Le nom d'utilisateur ne peut pas être moins de 4 caractères");
            lblMessage.setTextFill(Color.RED);
            username.setStyle("-fx-focus-color: firebrick");
        } else if (!isValidUsername(username.getText().trim())) {
            lblMessage.setText("Le nom d'utilisateur est déjà pris");
            lblMessage.setTextFill(Color.RED);
            username.setStyle("-fx-focus-color: firebrick");
        } else {
            lblMessage.setText("");
            username.setStyle("-fx-focus-color: #00FF00;");
        }
    }

    void initializeListUser(){
        UserDao userDao = new UserDao();
        userObList.clear();
        try {

            userObList.addAll(userDao.getAllUsers());
            tblUser.setItems(userObList);
            colRef.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUser_id()).asObject());
            colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
            colPrenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
            colUsername.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
            colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
            colAdresse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse()));
            colPhone.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTel_no()).asObject());
            colCreation.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Date) cellData.getValue().getDate_creation()));
            colRole.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addButtonToTable() {
        UserDao userDao = new UserDao();
        TableColumn<User, Button> colAction = new TableColumn<>("Action");
        Callback<TableColumn<User, Button>, TableCell<User, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<User, Button> call(final TableColumn<User, Button> param) {
                return new TableCell<User, Button>() {

                    private final Button btnModifier = new Button("Modifier");

                    {
                        btnModifier.setOnAction((ActionEvent event) -> {

                            User data = null;
                            try {
                                int id = getTableView().getItems().get(getIndex()).getUser_id();
                                data = userDao.getUserById(id);
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/pharma/app/pharmaproto/UserEditView.fxml"));
                                Parent root = null;
                                try {
                                    root = fxmlLoader.load();
                                    UserEditController userEditController = fxmlLoader.getController();
                                    userEditController.initializeEdit(data);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnModifier);
                        }
                    }
                };
            }
        };
        colAction.setCellFactory(cellFactory);

        tblUser.getColumns().add(colAction);
    }

    void initializeListRole(){
        Connection conn = DbContext.getConnection();
        Statement statement;
        ResultSet rs = null;
        //roles.clear();
        //listViewRole.getItems().clear();
        try {
            String query = "SELECT * from role";
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            Role roleMod = null;
            List<Role> roleList = new ArrayList<>();
            while (rs.next()) {
                roleMod = new ModelBuilder<Role>(Role.class)
                                .set("role_id", rs.getInt("role_id"))
                                .set("titre", rs.getString("role_nom"))
                                .set("description", rs.getString("description"))
                                .build();
                roleList.add(roleMod);
            }
            roles = FXCollections.observableArrayList(roleList);
            listViewRole.setItems(roles);
            listViewRole.setCellFactory(param -> new ListCell<>(){
                private final HBox content;
                private final Label idLabel;
                private final Label nameLabel;
                private final CheckBox checkBox;
                private final Tooltip tooltip;

                {
                    content = new HBox(30);
                    idLabel = new Label();
                    nameLabel = new Label();
                    checkBox = new CheckBox();
                    tooltip = new Tooltip();
                    content.getChildren().addAll(idLabel, nameLabel, checkBox);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }

                @Override
                protected void updateItem(Role role, boolean empty) {
                    super.updateItem(role, empty);
                    if (empty || role == null) {
                        setGraphic(null);
                    } else {
                        idLabel.setText(String.valueOf(role.getRole_id()));
                        nameLabel.setText(role.getTitre());
                        checkBox.setSelected(selectedRoles.contains(role));
                        checkBox.setOnAction(event -> {
                            if (checkBox.isSelected()) {
                                selectedRoles.add(role);
                            } else {
                                selectedRoles.remove(role);
                            }
                        });
                        tooltip.setText(role.getDescription());
                        Tooltip.install(content, tooltip);
                        setGraphic(content);
                    }
                }
            });

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    @FXML
    void btnAjouterAction(ActionEvent event) {
        if(listViewRole.getItems().size()>0){
            if (isValidUsername(username.getText())
            && isValidEmail()
            && isValidPassword()
            && isValidAdresse()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation d'Ajout");
                alert.setContentText("Êtes-vous sur de vouloir ajouter cet utilisateur?");
                alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    UserDao userDao = new UserDao();
                    try {
                        //userDao.addUser retourne l'id de l'utilisateur ajouté
                        //si c'est à 0 c'est qu'il y a eu une erreur
                        int id = userDao.addUser(new User(username.getText(),
                                password.getText(),
                                nom.getText(),
                                prenom.getText(),
                                adresse.getText(),
                                email.getText(),
                                Integer.parseInt(tel_no.getText()),
                                selectedRoles));
                        if(id > 0){
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Information");
                            alert1.setContentText("Utilisateur ajouté avec succès");
                            alert1.showAndWait();
                            initializeListUser();
                            lblMessage.setTextFill(Color.GREEN);
                            lblMessage.setText("✔");
                        } else {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("Erreur");
                            alert1.setContentText("Une erreur est survenue lors de l'ajout de l'utilisateur");
                            alert1.showAndWait();
                        }

                    } catch (SQLException e) {
                        //throw new RuntimeException(e);
                        System.out.println(e);
                    }
                }
        });
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Un champ a n'est pas valide ou vide");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez selectionner au moins un role");
            alert.showAndWait();
        }


//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation d'Ajout");
//        alert.setContentText("Êtes-vous sur de vouloir ajouter cet utilisateur?");
//        alert.showAndWait().ifPresent(rs -> {
//            if (rs == ButtonType.OK) {
//                System.out.println("Pressed OK.");
//
//            }
//        });
    }


    boolean isValidUsername(String username){
        Connection conn = DbContext.getConnection();
        Statement statement;
        ResultSet rs = null;
        String query = "SELECT * FROM public.user WHERE user_username = '"+username+"'";
        try{
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                return false;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return true;
    }


    boolean isValidPassword(){
        if(password.getText().trim().length() > 0
                && password.getText().trim().equals(rptPassword.getText())
                && rptPassword.getText().trim().length() > 0){
            return true;
        }
        return false;
    }

    boolean isValidEmail(){
        if(email.getText().trim().length() > 0
                && email.getText().trim().matches(emailRegex)){
            return true;
        }
        return false;
    }

    boolean isValidAdresse(){
        if(adresse.getText().trim().length() > 0){
            return true;
        }
        return false;
    }

//    @FXML
//    void tglActiveAction(ActionEvent event) {
//
//        tglActive.setOnAction(e -> {
//            JFXToggleButton toggle = (JFXToggleButton) e.getSource();
//            if (toggle.isSelected()) {
//                toggle.setText("Actif");
//            } else {
//                toggle.setText("Inactif");
//            }
//        });
//    }


}
