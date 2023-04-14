package com.pharma.app.pharmaproto.controller;

import com.pharma.app.pharmaproto.model.Fournisseur;
import com.pharma.app.pharmaproto.utils.DbContext;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FournisseurController implements Initializable {

    //region Initialisation
    DbContext dbContext = new DbContext();
    Connection connection = dbContext.getConnection();
    public TableColumn <Fournisseur, Integer> colRef;
    public TableColumn <Fournisseur, String> colNom;
    public TableColumn <Fournisseur, String> colEmail;
    public TableColumn <Fournisseur, Integer> colPhone;
    public TableColumn <Fournisseur, String> colAddresse;

    public TextField tfEmail;
    public TextField tfPhone;
    public TextField tfNom;
    public TextField tfAddresse;
    public Button btnAddDB;
    public TableView <Fournisseur> tblFournisseur;
    public static TableView <Fournisseur> tblFournisseur_app;

    ObservableList<Fournisseur> objFournisseur = FXCollections.observableArrayList();

    //endregion

    //Fonction qui peuple le tableau des Fournisseurs
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblFournisseur_app = tblFournisseur;
        lireFournisseur();
        updateFournisseur();
        addButtonToTable();

    }

    //region CRUD
    // Fonctions de gestion des fournisseurs (ajout, modification, suppression, lecture)

    //Fonction qui lit les fournisseurs de la base de données
    private void lireFournisseur() {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = "SELECT * from fournisseur order by fournisseur_id";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                //creation d'un objet fournisseur
                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setNom(rs.getString("fournisseur_nom"));
                fournisseur.setIdFournisseur(rs.getInt("fournisseur_id"));
                fournisseur.setEmail(rs.getString("fournisseur_email"));
                fournisseur.setAddresse(rs.getString("fournisseur_adresse"));
                fournisseur.setTelephone(rs.getInt("fournisseur_telephone"));

                //Ajout des valeurs de l'objet fournisseur dans le tableau
                colRef.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdFournisseur()).asObject());
                colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
                colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
                colPhone.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTelephone()).asObject());
                colAddresse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddresse()));
                objFournisseur.add(fournisseur);
                tblFournisseur.setItems(FXCollections.observableArrayList(objFournisseur));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Fonction qui ajoute un fournisseur à la base de données
    private void ajouterFournisseur( String nom, String email, String addresse, int numero) {
        Statement statement;
        try {
            String query = "INSERT INTO fournisseur (fournisseur_nom, fournisseur_email, fournisseur_adresse, fournisseur_telephone) VALUES ('" + nom + "', '" + email + "', '" + addresse + "', '" + numero + "')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Fonction qui delete un fournisseur de la base de données
    private void deleteFournisseur(int id) {
        Statement statement;
        try {
            String query = "UPDATE fournisseur SET active = false WHERE fournisseur_id = " + id;
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Fonction qui modifie un fournisseur de la base de données
    private void updateFournisseur(){
        //Rend les colonnes du tableau éditables et les ajoutent à la base de données
        colNom.setCellFactory(TextFieldTableCell.forTableColumn());
        colNom.setOnEditCommit(e -> {
            Statement statement;
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
            String queryNom = String.format("UPDATE fournisseur SET fournisseur_nom= '%s' WHERE id= '%d'",
                    e.getNewValue(), e.getTableView().getItems().get(e.getTablePosition().getRow()).getIdFournisseur());
            try {
                statement = connection.createStatement();
                statement.executeUpdate(queryNom);

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
        colAddresse.setCellFactory(TextFieldTableCell.forTableColumn());
        colAddresse.setOnEditCommit(e -> {
            Statement statement;
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setAddresse(e.getNewValue());
            String queryAddresse = String.format("UPDATE fournisseur SET fournisseur_adresse = '%s' WHERE id = '%d'",
                    e.getNewValue(), e.getTableView().getItems().get(e.getTablePosition().getRow()).getIdFournisseur());
            try {
                statement = connection.createStatement();
                statement.executeUpdate(queryAddresse);

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(e -> {
            Statement statement;
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
            String queryEmail = String.format("UPDATE fournisseur SET fournisseur_email = '%s' WHERE id = '%d'",
                    e.getNewValue(), e.getTableView().getItems().get(e.getTablePosition().getRow()).getIdFournisseur());
            try {
                statement = connection.createStatement();
                statement.executeUpdate(queryEmail);

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPhone.setOnEditCommit(e -> {
            Statement statement;
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTelephone(e.getNewValue());
            String queryPhone = String.format("UPDATE fournisseur SET fournisseur_telephone = '%d' WHERE id = '%d'",
                    e.getNewValue(), e.getTableView().getItems().get(e.getTablePosition().getRow()).getIdFournisseur());
            try {
                statement = connection.createStatement();
                statement.executeUpdate(queryPhone);

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
    }
    //endregion


    //region button
    private void addButtonToTable() {
        TableColumn<Fournisseur, Button> colAction = new TableColumn<>("Action");

        Callback<TableColumn<Fournisseur, Button>, TableCell<Fournisseur, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Fournisseur, Button> call(final TableColumn<Fournisseur, Button> param) {
                return new TableCell<Fournisseur, Button>() {
                    private final Button btn = new Button("Effacer");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Fournisseur data = getTableView().getItems().get(getIndex());
                            int id = data.getIdFournisseur();
                            System.out.println("selectedData: " + data);
                            objFournisseur.remove(data);
                            tblFournisseur.setItems(FXCollections.observableArrayList(objFournisseur));
                            deleteFournisseur( id);
                        });
                    }

                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        colAction.setCellFactory(cellFactory);

        tblFournisseur.getColumns().add(colAction);

    }
    public void onAddDBclick(ActionEvent actionEvent) {
        String nom = tfNom.getText();
        String email = tfEmail.getText();
        String addresse = tfAddresse.getText();
        int numero = Integer.parseInt(tfPhone.getText());
        ajouterFournisseur(nom, email, addresse, numero);
        tfNom.clear();
        tfEmail.clear();
        tfAddresse.clear();
        tfPhone.clear();
        objFournisseur.clear();
        lireFournisseur();
    }

    //endregion
    //Fonction qui lie les données des textfields aux variables et qui les ajoute à la base de données

}
