package com.pharma.app.pharmaproto.controller;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import com.pharma.app.pharmaproto.model.Forme;
import com.pharma.app.pharmaproto.model.Famille;
import com.pharma.app.pharmaproto.model.Fournisseur;
import com.pharma.app.pharmaproto.model.Medicament;
import com.pharma.app.pharmaproto.utils.DbContext;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class MedCon implements Initializable {

    //region Initialisation
    public Button btnAddDB;
    DbContext dbContext = new DbContext();
    Connection connection = dbContext.getConnection();

    @FXML
    public TableColumn<Medicament, String> colDose;

    @FXML
    public TableColumn<Medicament, String> colFamille;

    @FXML
    public TableColumn<Medicament, Fournisseur> colFournisseur;

    @FXML
    public TableColumn<Medicament, String> colNom;

    @FXML
    public TableColumn<Medicament, Float> colPrix;

    @FXML
    public TableColumn<Medicament, Integer> colQuantite;

    @FXML
    public TableColumn<Medicament, Integer> colRef;
    @FXML
    public TableColumn<Medicament, Date> colDateFabrication;
    @FXML
    public TableColumn<Medicament, Date> colDateExpiration;
    @FXML
    public TableColumn<Medicament, String> colDescription;

    @FXML
    public TableColumn<Medicament, String> colForme;

    @FXML
    public TableView<Medicament> tblMedicament;

    @FXML
    public static TableView<Medicament> tblMedicament_app;


    @FXML
    public TextField tfNom;
    @FXML
    public TextField tfDose;
    @FXML
    public TextField tfQuantite;
    @FXML
    public TextField tfPrix;
    @FXML
    public DatePicker dpDateFabrication;
    @FXML
    public DatePicker dpDateExpiration;
    @FXML
    public TextArea taDescription;


    @FXML
    public ComboBox<Famille> cbFamille;
    @FXML
    public ComboBox<Fournisseur> cbFournisseur;
    @FXML
    public ComboBox<Forme> cbForme;


    ObservableList<Medicament> objMedicament = FXCollections.observableArrayList();
    ObservableList<Fournisseur> objFournisseur = FXCollections.observableArrayList();
    ObservableList<Famille> objFamille = FXCollections.observableArrayList();
    ObservableList<Forme> objForme = FXCollections.observableArrayList();


    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblMedicament_app = tblMedicament;
        tblMedicament.setItems(lireMedicament(connection));
        addButtonToTable();
        initializeComboBox();

    }


    //region Méthodes

    //region CRUD Medicament

    //Create Medicament
    public void ajouterMedicament(Connection conn, String name, String dose, Integer quantite,
                                         float prix, String description,Integer idFamille, Integer idForme, Integer idFournisseur,
                                  LocalDate dateFabricationValue, LocalDate dateExpirationValue) {
        Statement statement;
        try {


            String isoDate = dateFabricationValue.toString();

            String isoDate2 = dateExpirationValue.toString();


            String query = String.format("insert into medicament(nom_medicament, dose, quantite, id_fournisseur, " +
                            "date_fabrication, date_expiration, description, id_forme, id_famille) " +
                            "values('%s', '%s','%d','%d','%s', '%s', '%s', '%d', '%d');", name, dose, quantite, idFournisseur,
                    isoDate, isoDate2, description, idForme, idFamille);
            String query2 = String.format("insert into fournisseur_prix(id_fournisseur, id_medicament, prix) " +
                    "values('%d', (select id_medicament from medicament where nom_medicament = '%s'), '%f');", idFournisseur, name, prix);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.executeUpdate(query2);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Read Medicament
    public ObservableList<Medicament> lireMedicament(Connection connection) {
        clearObjects();
        Statement statement;
        ResultSet rs = null;
        try {
            String query = "SELECT * from medicament m " +
                    "left join forme f on f.forme_id = m.id_forme " +
                    "left join fournisseur fn on fn.fournisseur_id = m.id_fournisseur " +
                    "left join famille fm on fm.famille_id = m.id_famille " +
                    "left join fournisseur_prix FP on m.medicament_id = FP.medicament_id " +
                    "WHERE m.active = true ORDER BY m.medicament_id";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Medicament medicament = new Medicament();
                medicament.setId(rs.getInt("medicament_id"));
                medicament.setNom(rs.getString("medicament_nom"));
                medicament.setDose(rs.getString("dose"));
                medicament.setQuantite(rs.getInt("medicament_quantite"));
                medicament.setPrix(rs.getFloat("fournisseur_prix_prix"));
                medicament.setDate_fabrication(rs.getDate("date_fabrication"));
                medicament.setDate_expirations(rs.getDate("date_expiration"));
                medicament.setDescription(rs.getString("description"));

                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setIdFournisseur(rs.getInt("fournisseur_id"));
                fournisseur.setNom(rs.getString("fournisseur_nom"));
                fournisseur.setAddresse(rs.getString("fournisseur_adresse"));
                fournisseur.setTelephone(rs.getInt("fournisseur_telephone"));
                fournisseur.setEmail(rs.getString("fournisseur_email"));
                medicament.setFournisseur(fournisseur);

                Famille famille = new Famille();
                famille.setIdFamille(rs.getInt("famille_id"));
                famille.setNom_famille(rs.getString("famille_nom"));
                medicament.setFamille(famille);


                Forme forme = new Forme();
                forme.setId_forme(rs.getInt("forme_id"));
                forme.setNom_forme(rs.getString("forme_nom"));
                medicament.setForme(forme);



                colRef.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
                colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
                colDose.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDose()));
                colQuantite.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantite()).asObject());
                colPrix.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrix()).asObject());
                colDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
                colForme.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getForme().getNom_forme()));
                colFamille.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFamille().getNom_famille()));
//                colFournisseur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));

                objMedicament.add(medicament);
            }
            return FXCollections.observableArrayList(objMedicament);
        } catch (Exception e) {
            System.out.println(e);
        }
        return objMedicament;
    }

    //Update Medicament

//     private void editTable(){
//        colNom.setCellFactory(TextFieldTableCell.forTableColumn());
//        colNom.setOnEditCommit(e -> {
//            Statement statement;
//            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
//            String queryNom = String.format("UPDATE medicament SET nom_medicament = '%s' WHERE id_medicament = '%d'",
//                    e.getNewValue(), e.getTableView().getItems().get(e.getTablePosition().getRow()).getId());
//            try {
//                statement = connection.createStatement();
//                statement.executeUpdate(queryNom);
//
//            } catch (SQLException ex) {
//                System.out.println(ex);
//            }
//        });
//        colFamille.setCellFactory(ComboBoxTableCell.forTableColumn());
//    }

    //Delete Medicament
    private void effacerMedicament(int id) {
        Statement statement;
        try {
            String query = "UPDATE medicament SET active = false WHERE medicament_id = " + id;
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //endregion


    //region Events

    public void onAddDBclick(ActionEvent actionEvent) {
        try {
            String nom = tfNom.getText();
            String dose = tfDose.getText();
            int quantite = Integer.parseInt(tfQuantite.getText());
            float prix = Float.parseFloat(tfPrix.getText());
            String description = taDescription.getText();
            int id_famille = cbFamille.getSelectionModel().getSelectedItem().getIdFamille();
            int id_forme = cbForme.getSelectionModel().getSelectedItem().getId_forme();
            int id_fournisseur = cbFournisseur.getSelectionModel().getSelectedItem().getIdFournisseur();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate dateFabricationValue = dpDateFabrication.getValue();
            LocalDate dateExpirationValue = dpDateExpiration.getValue();
            if(isTextFieldEmpty(tfNom)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez remplir tous les champs");
                alert.showAndWait();

            }else {
                ajouterMedicament(connection, nom, dose, quantite, prix, description, id_famille,  id_forme, id_fournisseur, dateFabricationValue, dateExpirationValue);
                tblMedicament.setItems(lireMedicament(connection));
                clearAll();
                initializeComboBox();
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onRefreshClick(ActionEvent actionEvent) {
    }

    private void addButtonToTable() {
        TableColumn<Medicament, Button> colAction = new TableColumn<>("Action");
        Callback<TableColumn<Medicament, Button>, TableCell<Medicament, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Medicament, Button> call(final TableColumn<Medicament, Button> param) {
                return new TableCell<Medicament, Button>() {

                    private final Button btnEffacer = new Button("Effacer");

                    {
                        btnEffacer.setOnAction((ActionEvent event) -> {
                            Medicament data = getTableView().getItems().get(getIndex());
                            int id = data.getId();
                            objMedicament.remove(data);
                            tblMedicament.setItems(lireMedicament(connection));
                            effacerMedicament(id);
                        });
                    }

//                    private final Button btnDetail = new Button("Detail");
//
//                    {
//                        btnDetail.setOnAction((ActionEvent event) -> {
//                            Medicament data = getTableView().getItems().get(getIndex());
//                            int id = data.getId();
//                            objMedicament.remove(data);
//                            tblMedicament.setItems(FXCollections.observableArrayList(objMedicament));
//                            effacerMedicament(id);
//                        });
//                    }

                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnEffacer);
//                            setGraphic(btnDetail);
                        }
                    }
                };
            }
        };
        colAction.setCellFactory(cellFactory);

        tblMedicament.getColumns().add(colAction);
    }

    //endregion

    //region Utils

    //Function to check if the textfield is empty
    public boolean isTextFieldEmpty(TextField textField) {
        return textField.getText().isEmpty();
    }

    //Function to check if the combobox is empty
    public boolean isComboBoxFamilleEmpty(ComboBox<Famille> comboBox) {
        return comboBox.getSelectionModel().isEmpty();
    }

    public boolean isComboBoxFormeEmpty(ComboBox<Forme> comboBox) {
        return comboBox.getSelectionModel().isEmpty();
    }

    public boolean isComboBoxFournisseurEmpty(ComboBox<Fournisseur> comboBox) {
        return comboBox.getSelectionModel().isEmpty();
    }

    //Function to check if the datepicker is empty
    public boolean isDatePickerEmpty(DatePicker datePicker) {
        return datePicker.getValue() == null;
    }

    //Function to check if the textarea is empty
    public boolean isTextAreaEmpty(TextArea textArea) {
        return textArea.getText().isEmpty();
    }

    //Function to check if the textfield, combobox, datepicker and textarea are empty
    public boolean isAllEmpty() {
        return isTextFieldEmpty(tfNom) || isTextFieldEmpty(tfDose) || isTextFieldEmpty(tfQuantite) || isTextFieldEmpty(tfPrix) ||
                isComboBoxFamilleEmpty(cbFamille) || isComboBoxFormeEmpty(cbForme) || isComboBoxFournisseurEmpty(cbFournisseur) ||
                isDatePickerEmpty(dpDateFabrication) || isDatePickerEmpty(dpDateExpiration) || isTextAreaEmpty(taDescription);
    }

    //Function to clear the textfield
    public void clearTextField(TextField textField) {
        textField.clear();
    }

    //Function to clear the combobox
    public void clearFamilleComboBox(ComboBox<Famille> comboBox) {
        comboBox.getSelectionModel().clearSelection();
    }

    public void clearFormeComboBox(ComboBox<Forme> comboBox) {
        comboBox.getSelectionModel().clearSelection();
    }

    public void clearFournisseurComboBox(ComboBox<Fournisseur> comboBox) {
        comboBox.getSelectionModel().clearSelection();
    }


    //Function to clear the datepicker
    public void clearDatePicker(DatePicker datePicker) {
        datePicker.setValue(null);
    }

    //Function to clear the textarea
    public void clearTextArea(TextArea textArea) {
        textArea.clear();
    }

    //Function to clear all the textfield, combobox, datepicker and textarea
    public void clearAll() {
        clearTextField(tfNom);
        clearTextField(tfDose);
        clearTextField(tfQuantite);
        clearTextField(tfPrix);
        clearFamilleComboBox(cbFamille);
        clearFormeComboBox(cbForme);
        clearFournisseurComboBox(cbFournisseur);
        clearDatePicker(dpDateFabrication);
        clearDatePicker(dpDateExpiration);
        clearTextArea(taDescription);
    }

    public void clearObjects() {
        objMedicament.clear();
        objFamille.clear();
        objForme.clear();
        objFournisseur.clear();
    }

    //Function to get the id of the selected item in the table
    public int getSelectedId() {
        return tblMedicament.getSelectionModel().getSelectedItem().getId();
    }

    //Function to get the name of the selected item in the table
    public String getSelectedName() {
        return tblMedicament.getSelectionModel().getSelectedItem().getNom();
    }

    //Function to get the dose of the selected item in the table
    public String getSelectedDose() {
        return tblMedicament.getSelectionModel().getSelectedItem().getDose();
    }

    //Function to get the quantity of the selected item in the table
    public int getSelectedQuantity() {
        return tblMedicament.getSelectionModel().getSelectedItem().getQuantite();
    }

    //Function to get the price of the selected item in the table
    public float getSelectedPrice() {
        return tblMedicament.getSelectionModel().getSelectedItem().getPrix();
    }

    //Function to get the description of the selected item in the table
    public String getSelectedDescription() {
        return tblMedicament.getSelectionModel().getSelectedItem().getDescription();
    }

    //Function to get the date of fabrication of the selected item in the table
    public Date getSelectedDateFabrication() {
        return tblMedicament.getSelectionModel().getSelectedItem().getDate_fabrication();
    }

    //Function to get the date of expiration of the selected item in the table
    public Date getSelectedDateExpiration() {
        return tblMedicament.getSelectionModel().getSelectedItem().getDate_expirations();
    }

    //Function to get the id of the selected item in the combobox
    public int getSelectedIdFamille() {
        return cbFamille.getSelectionModel().getSelectedItem().getIdFamille();
    }

    //Function to get the id of the selected item in the combobox
    public int getSelectedIdForme() {
        return cbForme.getSelectionModel().getSelectedItem().getId_forme();
    }

    //Function to get the id of the selected item in the combobox
    public int getSelectedIdFournisseur() {
        return cbFournisseur.getSelectionModel().getSelectedItem().getIdFournisseur();
    }

    //Function to get the name of the selected item in the combobox
    public String getSelectedNameFamille() {
        return cbFamille.getSelectionModel().getSelectedItem().getNom_famille();
    }

    //Function to get the name of the selected item in the combobox
    public String getSelectedNameForme() {
        return cbForme.getSelectionModel().getSelectedItem().getNom_forme();
    }

    //Function to get the name of the selected item in the combobox
    public String getSelectedNameFournisseur() {
        return cbFournisseur.getSelectionModel().getSelectedItem().getNom();
    }

    //Function to get the selected item in the table
    public Medicament getSelectedMedicament() {
        return tblMedicament.getSelectionModel().getSelectedItem();
    }

    //Function to alert the user on the empty textfield
    public void alertTextField(TextField textField) {
        isTextFieldEmpty(textField);
        textField.setStyle("-fx-border-color: red");
        textField.setPromptText("Veuillez remplir ce champ");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champ vide");
        alert.setContentText("Veuillez remplir le champ " + textField.getPromptText());
        alert.showAndWait();
    }

    //Function to alert the user on the empty combobox
    public void alertComboBox(ComboBox comboBox) {
        isComboBoxFamilleEmpty(comboBox);
        comboBox.setStyle("-fx-border-color: red");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champ vide");
        alert.setContentText("Veuillez remplir le champ " + comboBox.getPromptText());
        alert.showAndWait();
    }

    //Function to alert the user on the empty datepicker
    public void alertDatePicker(DatePicker datePicker) {
        isDatePickerEmpty(datePicker);
        datePicker.setStyle("-fx-border-color: red");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champ vide");
        alert.setContentText("Veuillez remplir le champ " + datePicker.getPromptText());
        alert.showAndWait();
    }

    //Function to alert the user on the empty textarea
    public void alertTextArea(TextArea textArea) {
        isTextAreaEmpty(textArea);
        textArea.setStyle("-fx-border-color: red");
        textArea.setPromptText("Veuillez remplir ce champ");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Champ vide");
        alert.setContentText("Veuillez remplir le champ " + textArea.getPromptText());
        alert.showAndWait();
    }

    //Function to alert on empty Nom
    public void alertNom() {
        if (isTextFieldEmpty(tfNom)) {
            alertTextField(tfNom);
        }
    }

    //Function to alert on empty Dose
    public void alertDose() {
        if (isTextFieldEmpty(tfDose)) {
            alertTextField(tfDose);
        }
    }

    //Function to alert on empty Quantite
    public void alertQuantite() {
        if (isTextFieldEmpty(tfQuantite)) {
            alertTextField(tfQuantite);
        }
    }

    //Function to alert on empty Prix
    public void alertPrix() {
        if (isTextFieldEmpty(tfPrix)) {
            alertTextField(tfPrix);
        }
    }

    //Function to alert on empty Famille
    public void alertFamille() {
        if (isComboBoxFamilleEmpty(cbFamille)) {
            alertComboBox(cbFamille);
        }
    }

    //Function to alert on empty Forme
    public void alertForme() {
        if (isComboBoxFormeEmpty(cbForme)) {
            alertComboBox(cbForme);
        }
    }

    //Function to alert on empty Fournisseur
    public void alertFournisseur() {
        if (isComboBoxFournisseurEmpty(cbFournisseur)) {
            alertComboBox(cbFournisseur);
        }
    }

    //Function to alert on empty Date Fabrication
    public void alertDateFabrication() {
        if (isDatePickerEmpty(dpDateFabrication)) {
            alertDatePicker(dpDateFabrication);
        }
    }

    //Function to alert on empty Date Expiration
    public void alertDateExpiration() {
        if (isDatePickerEmpty(dpDateExpiration)) {
            alertDatePicker(dpDateExpiration);
        }
    }

    //Function to alert on empty Description
    public void alertDescription() {
        if (isTextAreaEmpty(taDescription)) {
            alertTextArea(taDescription);
        }
    }

    //Function to fill ComboBox Fournisseur
    private void initializeCbFournisseur() {
        cbFournisseur.getItems().clear();
        Statement statement;
        ResultSet rs = null;
        try {
            String query = "SELECT * from fournisseur where active = true ORDER BY fournisseur_id";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                objFournisseur.add(new Fournisseur(rs.getInt("fournisseur_id"),
                        rs.getString("fournisseur_nom")));
            }
            cbFournisseur.setItems(objFournisseur);
            cbFournisseur.setConverter(new StringConverter<Fournisseur>() {
                @Override
                public String toString(Fournisseur fournisseur) {
                    return fournisseur.getNom();
                }

                @Override
                public Fournisseur fromString(String string) {
                    return cbFournisseur.getItems().stream().filter(ap ->
                            ap.getNom().equals(string)).findFirst().orElse(null);
                }
            });
            cbFournisseur.setPromptText("Fournisseur");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    //Function to fill ComboBox Famille
    private void initializeCbFamille() {
        cbFamille.getItems().clear();
        Statement statement;
        ResultSet rs = null;
        try {
            String query = "SELECT * from famille where active = true ORDER BY famille_id";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                objFamille.add(new Famille(rs.getInt("famille_id"),
                        rs.getString("famille_id")));
            }
            cbFamille.setItems(objFamille);
            cbFamille.setConverter(new StringConverter<Famille>() {
                @Override
                public String toString(Famille famille) {
                    return famille.getNom_famille();
                }

                @Override
                public Famille fromString(String string) {
                    return cbFamille.getItems().stream().filter(ap ->
                            ap.getNom_famille().equals(string)).findFirst().orElse(null);
                }
            });
            cbFamille.setPromptText("Famille");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    //Function to fill ComboBox Forme
    private void initializeCbForme() {
        cbForme.getItems().clear();
        Statement statement;
        ResultSet rs = null;
        try {
            String query = "SELECT * from forme where active = true ORDER BY forme_id";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                objForme.add(new Forme(rs.getInt("forme_id"),
                        rs.getString("forme_nom")));
            }
            cbForme.setItems(objForme);
            cbForme.setConverter(new StringConverter<Forme>() {
                @Override
                public String toString(Forme forme) {
                    return forme.getNom_forme();
                }

                @Override
                public Forme fromString(String string) {
                    return cbForme.getItems().stream().filter(ap ->
                            ap.getNom_forme().equals(string)).findFirst().orElse(null);
                }
            });
            cbForme.setPromptText("Forme");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    //Function to initialize all ComboBox
    private void initializeComboBox() {
        initializeCbFamille();
        initializeCbForme();
        initializeCbFournisseur();
    }

    //endregion

}
