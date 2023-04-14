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


import com.pharma.app.pharmaproto.model.Forme;
import com.pharma.app.pharmaproto.utils.DbContext;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FormeController implements Initializable {
    //region Initialisation

    public TableColumn<Forme, String> colNom;
    public TableColumn<Forme, Integer> colRef;

    DbContext dbContext = new DbContext();
    Connection connection = dbContext.getConnection();

    public TableView<Forme> tblForme;
    @FXML
    public TextField tfNom;


    ObservableList<Forme> objForme = FXCollections.observableArrayList();

    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblForme.setItems(lireForme(connection));
        addButtonToTable();
    }

    //region Méthodes

    public static void ajouterForme(Connection conn,String nom) {
        Statement statement;
        try {
            String query = String.format("insert into forme(forme_nom) " +
                    "values('%s');", nom);
            statement = conn.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addButtonToTable() {
        TableColumn<Forme, Button> colAction = new TableColumn<>("Action");
        Callback<TableColumn<Forme, Button>, TableCell<Forme, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Forme, Button> call(final TableColumn<Forme, Button> param) {
                return new TableCell<Forme, Button>() {

                    private final Button btn = new Button("Effacer");


                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Forme data = getTableView().getItems().get(getIndex());
                            int id = data.getId_forme();
                            objForme.remove(data);
                            tblForme.setItems(FXCollections.observableArrayList(objForme));
                            effacerForme(id);
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

        tblForme.getColumns().add(colAction);
    }

    private void effacerForme(int id) {
        Statement statement;
        try {
            String query = "UPDATE forme SET active = false WHERE forme_id = " + id;

            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public ObservableList<Forme> lireForme(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = "select * from forme WHERE active = true ORDER BY forme_id";
            statement = conn.createStatement();
            statement.executeQuery(query);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Forme forme = new Forme();
                forme.setId_forme(rs.getInt("forme_id"));
                forme.setNom_forme(rs.getString("forme_nom"));
                objForme.add(forme);
                colRef.setCellValueFactory(cellData ->  new SimpleIntegerProperty(cellData.getValue().getId_forme()).asObject());
                colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom_forme()));
            }
            return FXCollections.observableArrayList(objForme);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //endregion
    public void onAddDBclick(ActionEvent actionEvent) {
        String nom = tfNom.getText();
        ajouterForme(connection, nom);
        objForme.clear();
        tblForme.setItems(lireForme(connection));
        tfNom.clear();
    }
}
