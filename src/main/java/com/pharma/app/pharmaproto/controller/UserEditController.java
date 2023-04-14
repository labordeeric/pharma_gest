package com.pharma.app.pharmaproto.controller;

import com.jfoenix.controls.JFXListView;
import com.pharma.app.pharmaproto.model.Role;
import com.pharma.app.pharmaproto.model.User;
import com.pharma.app.pharmaproto.model.UserDao;
import com.pharma.app.pharmaproto.utils.DbContext;
import com.pharma.app.pharmaproto.utils.ModelBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserEditController implements Initializable {

    @FXML
    private TextField adresse;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnAnnuler;

    @FXML
    private TextField email;
    //Email Regex
    String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";

    @FXML
    private Label lblMessage;

    @FXML
    private JFXListView<Role> listViewRole;
    
    @FXML
    private JFXListView<Role> listViewRoleUser;

    @FXML
    private TextField nom;

    @FXML
    private TextField password;

    @FXML
    private TextField prenom;

    @FXML
    private TextField refUser;

    @FXML
    private TextField rptPassword;

    @FXML
    private TextField tel_no;

    @FXML
    private TextField username;

    private ObservableList<Role> roles;
    public User userEdit;
    private ObservableList<Role> selectedRoles = FXCollections.observableArrayList();
    private ObservableList<Role> selectedRoleUser = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void initializeEdit(User user){
        userEdit = user;
        initializeUserListRole();
        initializeListRole();
        refUser.setText(String.valueOf(user.getUser_id()));
        nom.setText(user.getNom());
        prenom.setText(user.getPrenom());
        tel_no.setText(String.valueOf(user.getTel_no()));
        adresse.setText(user.getAdresse());
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        password.setText(user.getPassword());
        rptPassword.setText(user.getPassword());
        //selectedRoles = FXCollections.observableArrayList(user.getRoles());
        selectedRoles.clear();
    }

    void initializeUserListRole(){
        // Récupérer les rôles de l'utilisateur
        List<Role> userRoles = userEdit.getRoles();

        Connection conn = DbContext.getConnection();
        Statement statement;
        ResultSet rs = null;
        //roles.clear();
        //listViewRole.getItems().clear();
        try {
            String query = "SELECT * from role right join user_role on role.role_id = user_role.role_id " +
                    "where user_role.user_id = "+userEdit.getUser_id()+"";
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
            listViewRoleUser.setItems(FXCollections.observableList(roleList));
            listViewRoleUser.setCellFactory(param -> new ListCell<>(){
                private final HBox content;
                private final Label idLabel;
                private final Label nameLabel;
                private final Button btnDelete;
                private final Tooltip tooltip;

                {
                    content = new HBox(10);
                    idLabel = new Label();
                    nameLabel = new Label();
                    btnDelete = new Button("❌");
                    tooltip = new Tooltip();
                    content.getChildren().addAll(idLabel, nameLabel, btnDelete);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }

                @Override
                protected void updateItem(Role item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        idLabel.setText(String.valueOf(item.getRole_id()));
                        nameLabel.setText(item.getTitre());
                        btnDelete.setOnAction(event -> {
                            Role role = listViewRoleUser.getItems().get(getIndex());
                            UserDao userDao = new UserDao();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation");
                            alert.setHeaderText("Suppression de rôle");
                            alert.setContentText("Voulez-vous vraiment supprimer ce rôle ?");
                            alert.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    try {
                                        if(listViewRoleUser.getItems().size()<2) {
                                            lblMessage.setText("L'utilisateur doit avoir au moins un rôle");
                                            lblMessage.setTextFill(Color.RED);
                                        }else{
                                            if (userDao.deleteUserRole(userEdit.getUser_id(), role.getRole_id())) {
                                                initializeUserListRole();
                                                initializeListRole();
                                            }
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                        });
                        tooltip.setText(item.getDescription());
                        setTooltip(tooltip);
                        setGraphic(content);
                    }
                }
            });
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void initializeListRole(){
        // Récupérer les rôles de l'utilisateur
        List<Role> userRoles = userEdit.getRoles();

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

            // Afficher les rôles dans la liste
            listViewRole.setItems(FXCollections.observableList(roleList));
            listViewRole.setCellFactory(param -> new ListCell<>(){
                private final HBox content;
                private final Label idLabel;
                private final Label nameLabel;
                private final CheckBox checkBox;
                private final Tooltip tooltip;

                {
                    content = new HBox(10);
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
    void btnModifierAction(ActionEvent event) {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setNom(nom.getText());
        user.setPrenom(prenom.getText());
        user.setEmail(email.getText());
        user.setTel_no(Integer.parseInt(tel_no.getText()));
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        user.setAdresse(adresse.getText());
        user.setRoles(selectedRoles);
        user.setUser_id(userEdit.getUser_id());
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Modification d'utilisateur");
            alert.setContentText("Voulez-vous vraiment modifier cet utilisateur ?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        if (userDao.editUser(user)>0){
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Information");
                            alert1.setHeaderText("Modification d'utilisateur");
                            alert1.setContentText("L'utilisateur a été modifié avec succès");
                            alert1.showAndWait().ifPresent(response1 -> {
                                if (response1 == ButtonType.OK) {
                                    Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
                                    stage.close();
                                }
                            });
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAnnulerAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation d'Annulation");
        alert.setContentText("Êtes-vous sûr d'annuler les modifications ?");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
                stage.close();
            }
        });

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
    void rptPasswordAction(KeyEvent event) {
        passwordCheck();
    }

    @FXML
    void rptPasswordReleased(KeyEvent event) {
        passwordCheck();
    }

    void passwordCheck(){
        if (!password.getText().isEmpty()){
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
    void usernameTyped(KeyEvent event) {

    }

    boolean isValidUsername(String username){
        Connection conn = DbContext.getConnection();
        PreparedStatement statement;
        ResultSet rs = null;
        String query = "SELECT * FROM public.user WHERE user_username = ? " +
                "AND user_id != ?";
        try{
            statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setInt(2, Integer.parseInt(refUser.getText().trim()));
            rs = statement.executeQuery();
            if (rs.next()){
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }


    boolean isValidPassword(){
        if(password.getText().length() > 0
                && password.getText().equals(rptPassword.getText())
                && rptPassword.getText().length() > 0){
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

}
