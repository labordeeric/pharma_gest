package com.pharma.app.pharmaproto.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import com.pharma.app.pharmaproto.model.Famille;
import com.pharma.app.pharmaproto.model.Forme;
import com.pharma.app.pharmaproto.utils.DbContext;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FamilleController implements Initializable {
    public TableColumn<Famille, String> colNom;
    public TableColumn<Famille, Integer> colRef;

    DbContext dbContext = new DbContext();
    Connection connection = dbContext.getConnection();

    public TableView<Famille> tblFamille;
    @FXML
    public TextField tfNom;


    ObservableList<Famille> objFamille = FXCollections.observableArrayList();

    public static void ajouterFamille(Connection conn,String nom) {
        Statement statement;
        try {

            String query = String.format("insert into famille(famille_nom) " +
                    "values('%s');", nom);
            statement = conn.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addButtonToTable() {
        TableColumn<Famille, Button> colAction = new TableColumn<>("Action");
        Callback<TableColumn<Famille, Button>, TableCell<Famille, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Famille, Button> call(final TableColumn<Famille, Button> param) {
                return new TableCell<Famille, Button>() {

                    private final Button btn = new Button("Effacer");


                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Famille data = getTableView().getItems().get(getIndex());
                            int id = data.getIdFamille();
                            objFamille.remove(data);
                            tblFamille.setItems(FXCollections.observableArrayList(objFamille));
                            effacerFamille(id);
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

        tblFamille.getColumns().add(colAction);
    }

    private void effacerFamille(int id) {
        Statement statement;
        try {
            String query = "UPDATE famille SET active = false WHERE famille_id = " + id;

            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObservableList<Famille> lireFamille(Connection conn) {
        objFamille.clear();
        Statement statement;
        ResultSet rs = null;
        try {
            String query = "select * from famille  WHERE active = true ORDER BY famille_id";
            statement = conn.createStatement();
            statement.executeQuery(query);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Famille famille = new Famille();
                famille.setIdFamille(rs.getInt("famille_id"));
                famille.setNom_famille(rs.getString("famille_nom"));

                colRef.setCellValueFactory(cellData ->  new SimpleIntegerProperty(cellData.getValue().getIdFamille()).asObject());
                colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom_famille()));
                objFamille.add(famille);

            }
            return FXCollections.observableArrayList(objFamille);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblFamille.setItems(FXCollections.observableArrayList(lireFamille(connection)));
        addButtonToTable();
    }

    public void onAddDBclick(ActionEvent actionEvent) {
        String nom = tfNom.getText();
        ajouterFamille(connection, nom);
        tblFamille.getItems().clear();
        tblFamille.setItems(FXCollections.observableArrayList(lireFamille(connection)));
        tfNom.clear();
    }
}
