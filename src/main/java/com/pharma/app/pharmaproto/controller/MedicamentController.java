//package com.pharma.app.pharmaproto.controller;
//
//import com.pharma.app.pharmaproto.model.FamilleMedicament;
//import com.pharma.app.pharmaproto.model.Fournisseur;
//import com.pharma.app.pharmaproto.model.Medicament;
//import com.pharma.app.pharmaproto.utils.DbContext;
//import javafx.application.Platform;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.TextFieldTableCell;
//import javafx.util.Callback;
//import javafx.util.StringConverter;
//
//import java.io.Console;
//import java.net.URL;
//import java.sql.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.ResourceBundle;
//
//public class MedicamentController implements Initializable {
//
//    //region Initialisation
//    public Button btnAddDB;
//    DbContext dbContext = new DbContext();
//    Connection connection = dbContext.getConnection();
//
//    //@FXML
//    //public TableColumn<Medicament, Button> colAction;
//
//    @FXML
//    public TableColumn<Medicament, String> colDose;
//
//    @FXML
//    public TableColumn<Medicament, String> colFamille;
//
//    @FXML
//    public TableColumn<Medicament, String> colFournisseur;
//
//    @FXML
//    public TableColumn<Medicament, String> colNom;
//
//    @FXML
//    public TableColumn<Medicament, String> colPrix;
//
//    @FXML
//    public TableColumn<Medicament, String> colQuantite;
//
//    @FXML
//    public TableColumn<Medicament, String> colRef;
//    public TableColumn<Medicament, String> colDateFabrication;
//    public TableColumn<Medicament, String> colDateExpiration;
//    public TableColumn<Medicament, String> colDescription;
//
//    @FXML
//    public TableView<Medicament> tblMedicament;
//
//    public static TableView<Medicament> tblMedicament_app;
//
//
//    @FXML
//    public TextField tfNom;
//    @FXML
//    public TextField tfDose;
//    @FXML
//    public TextField tfQuantite;
//    @FXML
//    public TextField tfPrix;
//    @FXML
//    public DatePicker dpDateFabrication;
//    @FXML
//    public DatePicker dpDateExpiration;
//    @FXML
//    public TextArea taDescription;
//
//
//    @FXML
//    public ComboBox<FamilleMedicament> cbFamille;
//    @FXML
//    public ComboBox<Fournisseur> cbFournisseur;
//
//
//
//    ObservableList<Medicament> objMedicament = FXCollections.observableArrayList();
//    ObservableList<Fournisseur> objFournisseur = FXCollections.observableArrayList();
//    ObservableList<FamilleMedicament> objFamilleMedicament = FXCollections.observableArrayList();
//
//    //endregion
//
//    //Fonction qui initialise le tableau des Medicaments
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        tblMedicament_app = tblMedicament;
//        lireMedicament(connection);
//        initializeCbFournisseur();
//        initializeCbFamille();
//        //Code temporaire
//        addButtonToTable();
//    }
//
//    //Fonction afin d'ajouter des Medicaments à la base de données
//    public void ajouterMedicament(Connection conn, String name, String dose, Integer famille, Integer quantite,
//                                         Integer idFournisseur, float prix, Date dateFabrication, Date dateExpiration, String description) {
//        Statement statement;
//        try {
//
//            String query = String.format("insert into medicament(nom_medicament, dose, famille_id, quantite, fournisseur_id, " +
//                            "prix, date_fabrication, date_expiration, description) " +
//                            "values('%s', '%s','%d','%d','%d','%.02f','%tD', '%tD', '%s');", name, dose, famille, quantite, idFournisseur,
//                    prix, dateFabrication, dateExpiration, description);
//            statement = conn.createStatement();
//            statement.executeUpdate(query);
//
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        //changeQuantityColor();
//    }
//
//    //Fonction qui lit les données des text fields afin de les ajouter à la base de données
//    public void onAddDBclick(ActionEvent actionEvent) {
//        String nom = tfNom.getText();
//        String dose = tfDose.getText();
//        float prix = Float.parseFloat(tfPrix.getText());
//        int quantite = Integer.parseInt(tfQuantite.getText());
//        int id_Famille = cbFamille.getValue().getFamille_id();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateFabrication = formatter.parse(dpDateFabrication.getValue().toString(), new java.text.ParsePosition(0)); //Date.valueOf(dpDateFabrication.getValue().toString());
//        Date dateExpiration = formatter.parse(dpDateExpiration.getValue().toString(), new java.text.ParsePosition(0));
//        String description = taDescription.getText();
//        int id_fournisseur = cbFournisseur.getValue().getIdFournisseur();
//        lireMedicament(connection);
//        ajouterMedicament(connection, nom, dose, id_Famille, quantite, id_fournisseur, prix, dateFabrication, dateExpiration, description);
//        tfNom.clear();
//        tfDose.clear();
//        tfPrix.clear();
//        tfQuantite.clear();
//        cbFamille.setValue(null);
//        cbFamille.setPromptText("Famille");
//        cbFournisseur.setValue(null);
//        cbFournisseur.setPromptText("Fournisseur");
//        dpDateFabrication.setValue(null);
//        dpDateExpiration.setValue(null);
//        taDescription.clear();
//        //changeQuantityColor();
//    }
//
//    //Fonction utilitaire afin de changer les données du model en String
//    private String toString(int id) {
//        return Integer.toString(id);
//    }
//
//    private String toString(double id) {
//        return Double.toString(id);
//    }
//
//    private String toString(Date date) {
//        return date.toString();
//    }
//
//
//    //Fonction afin d'ajouter des Medicaments à la table Medicament
//    public void lireMedicament(Connection conn) {
//        Statement statement;
//        ResultSet rs = null;
//        try {
//            String query = "SELECT * from medicament  WHERE actif = true ORDER BY id_medicament";
//            statement = conn.createStatement();
//            rs = statement.executeQuery(query);
//            while (rs.next()) {
//                objMedicament.add(new Medicament(
//                        rs.getString("nom_medicament"), rs.getString("dose"),
//                        rs.getInt("id_medicament"), rs.getInt("quantite"), rs.getDouble("prix"),
//                        rs.getInt("famille_id"), rs.getInt("fournisseur_id"), rs.getDate("date_ajout"),
//                        rs.getBoolean("actif"), rs.getDate("date_fabrication"),
//                        rs.getDate("date_expiration"), rs.getString("description")));
//
//                //Set the value to the table
//                colNom.setCellValueFactory(paramNom -> new SimpleStringProperty(paramNom.getValue().getNom()));
//                colDose.setCellValueFactory(paramDose -> new SimpleStringProperty(paramDose.getValue().getDose()));
//                colDescription.setCellValueFactory(paramDescription -> new SimpleStringProperty(paramDescription.getValue().getDescription()));
//
//                //convert int to String to add to the table
//                colRef.setCellValueFactory(paramId -> new SimpleStringProperty(toString(paramId.getValue().getId())));
//                colQuantite.setCellValueFactory(paramQuantite -> new SimpleStringProperty(toString(paramQuantite.getValue().getQuantite())));
//                colPrix.setCellValueFactory(paramPrix -> new SimpleStringProperty(toString(paramPrix.getValue().getPrix())));
//                colFamille.setCellValueFactory(paramFamille -> new SimpleStringProperty(toString(paramFamille.getValue().getId_famille())));
//                colFournisseur.setCellValueFactory(paramFournisseur -> new SimpleStringProperty(toString(paramFournisseur.getValue().getId_fournisseur())));
//
//                //convert date to String to add to the table
//                colDateFabrication.setCellValueFactory(paramDateFabrication -> new SimpleStringProperty(toString(paramDateFabrication.getValue().getDate_fabrication())));
//                colDateExpiration.setCellValueFactory(paramDateExpiration -> new SimpleStringProperty(toString(paramDateExpiration.getValue().getDate_expirations())));
//
//
//                editableCols();
//                tblMedicament.setItems(FXCollections.observableArrayList(objMedicament));
//            }
//        } catch (Exception e) {
//
//            System.out.println(e);
//        }
//    }
//
//    //Fonction afin d'ajouter un bouton 'Effacer' à chaque ligne de la table Medicament
//    private void addButtonToTable() {
//        TableColumn<Medicament, Button> colAction = new TableColumn<>("Action");
//
//        Callback<TableColumn<Medicament, Button>, TableCell<Medicament, Button>> buttonCellFactory = new Callback<>() {
//            @Override
//            public TableCell<Medicament, Button> call(final TableColumn<Medicament, Button> param) {
//                final TableCell<Medicament, Button> cell = new TableCell<>() {
//                    final Button btn = new Button("Effacer");
//
//                    {
//                        btn.setOnAction(new EventHandler<ActionEvent>() {
//                            @Override
//                            public void handle(ActionEvent event) {
//                                Medicament medicament = getTableView().getItems().get(getIndex());
//                                objMedicament.remove(medicament);
//                                tblMedicament.setItems(FXCollections.observableArrayList(objMedicament));
//                                effacerMedicament(medicament.getId());
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Button item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btn);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//
//        colAction.setCellFactory(buttonCellFactory);
//        tblMedicament.getColumns().add(colAction);
//    }
//
//    private void effacerMedicament(int id) {
//        Statement statement;
//        try {
//            String query = "UPDATE medicament SET actif = false WHERE id_medicament = " + id;
//            //System.out.println(query);
//
//            statement = connection.createStatement();
//            statement.executeUpdate(query);
//            statement.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        //changeQuantityColor();
//    }
//
//    //Fonction afin de permettre la modification des champs de la table Medicament
//    //et envoyer les données modifiées à la base de données
//    private void editableCols() {
//
//        //set editable for the Nom column
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
//        //set editable for the Dose column
//        colDose.setCellFactory(TextFieldTableCell.forTableColumn());
//        colDose.setOnEditCommit(e -> {
//            Statement statement;
//            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDose(e.getNewValue());
//            String queryDose = String.format("UPDATE medicament SET dose = '%s' WHERE id_medicament = '%d'",
//                    e.getNewValue(), e.getTableView().getItems().get(e.getTablePosition().getRow()).getId());
//            try {
//                statement = connection.createStatement();
//                statement.executeUpdate(queryDose);
//
//            } catch (SQLException ex) {
//                System.out.println(ex);
//            }
//        });
//        //set editable for the Famille column
//        colFamille.setCellFactory(TextFieldTableCell.forTableColumn());
//        colFamille.setOnEditCommit(e -> {
//            Statement statement;
//            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId_famille(Integer.parseInt(e.getNewValue()));
//            String queryFamille = String.format("UPDATE medicament SET famille_id = '%s' WHERE id_medicament = '%d'",
//                    e.getNewValue(), e.getTableView().getItems().get(e.getTablePosition().getRow()).getId());
//            try {
//                statement = connection.createStatement();
//                statement.executeUpdate(queryFamille);
//
//            } catch (SQLException ex) {
//                System.out.println(ex);
//            }
//        });
//        //set editable for the Quantity column
//        colQuantite.setCellFactory(column -> new TableCell<Medicament, String>() {
//            private TextField textField = new TextField();
//
//            @Override
//            public void startEdit() {
//                super.startEdit();
//                textField.setText(String.valueOf(getItem()));
//                setGraphic(textField);
//                textField.requestFocus();
//                textField.setOnAction(e -> {
//                    commitEdit(textField.getText());
//                    Statement statement;
//                    String queryQuantite = String.format("UPDATE medicament SET quantite = '%s' WHERE id_medicament = '%d'",
//                            textField.getText(), getTableView().getItems().get(getIndex()).getId());
//                    try {
//                        statement = connection.createStatement();
//                        statement.executeUpdate(queryQuantite);
//                    } catch (SQLException ex) {
//                        System.out.println(ex);
//                    }
//                });
//            }
//
//            @Override
//            public void cancelEdit() {
//                super.cancelEdit();
//                setText(String.valueOf(getItem()));
//                setGraphic(null);
//            }
//
//            @Override
//            public void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setText(null);
//                    setGraphic(null);
//                } else {
//                    if (isEditing()) {
//                        setText(null);
//                        setGraphic(textField);
//                    } else {
//                        setText(item);
//                        int nb = Integer.parseInt(item.trim());
//                        if (nb < 10) {
//                            setStyle("-fx-background-color: yellow;");
//                        } else {
//                            setStyle("");
//                        }
//                        setGraphic(null);
//                    }
//                }
//            }
//
//            @Override
//            public void commitEdit(String newValue) {
//                super.commitEdit(newValue);
//                setText(newValue);
//                setGraphic(null);
//                updateItem(newValue, false);
//            }
//        });
//
//        //set editable for the Prix column
//        colPrix.setCellFactory(TextFieldTableCell.forTableColumn());
//        colPrix.setOnEditCommit(e -> {
//            Statement statement;
//            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrix(Double.parseDouble(e.getNewValue()));
//            String queryPrix = String.format("UPDATE medicament SET prix = '%s' WHERE id_medicament = '%d'",
//                    e.getNewValue(), e.getTableView().getItems().get(e.getTablePosition().getRow()).getId());
//            try {
//                statement = connection.createStatement();
//                statement.executeUpdate(queryPrix);
//
//            } catch (SQLException ex) {
//                System.out.println(ex);
//            }
//        });
//        //set editable for the Description column
//        colDescription.setCellFactory(TextFieldTableCell.forTableColumn());
//        colDescription.setOnEditCommit(e -> {
//            Statement statement;
//            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDescription(e.getNewValue());
//            String queryDescription = String.format("UPDATE medicament SET description = '%s' WHERE id_medicament = '%d'",
//                    e.getNewValue(), e.getTableView().getItems().get(e.getTablePosition().getRow()).getId());
//            try {
//                statement = connection.createStatement();
//                statement.executeUpdate(queryDescription);
//
//            } catch (SQLException ex) {
//                System.out.println(ex);
//            }
//        });
//        tblMedicament.setEditable(true);
//
//
//    }
//
//    /*
//    private void changeQuantityColor() {
//        colQuantite.setCellFactory(column -> new TableCell<Medicament, String>() {
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (item == null || empty) {
//                    setText(null);
//                    setStyle("");
//                } else {
//                    setText(item);
//                    int nb = Integer.parseInt(item.trim());
//                    if (nb < 10) {
//                        setStyle("-fx-background-color: yellow;");
//                    } else {
//                        setStyle("");
//                    }
//                }
//            }
//        });
//    }
//*/
//    private void initializeCbFournisseur(){
//        cbFournisseur.getItems().clear();
//        Statement statement;
//        ResultSet rs = null;
//        try {
//            String query = "SELECT * from fournisseur where active = true ORDER BY fournisseur_id";
//            statement = connection.createStatement();
//            rs = statement.executeQuery(query);
//            while (rs.next()) {
//                objFournisseur.add(new Fournisseur(rs.getInt("fournisseur_id"),
//                        rs.getString("nom_fournisseur")));
//            }
//            cbFournisseur.setItems(objFournisseur);
//            cbFournisseur.setConverter(new StringConverter<Fournisseur>() {
//                @Override
//                public String toString(Fournisseur fournisseur) {
//                    return fournisseur.getNom();
//                }
//
//                @Override
//                public Fournisseur fromString(String string) {
//                    return cbFournisseur.getItems().stream().filter(ap ->
//                            ap.getNom().equals(string)).findFirst().orElse(null);
//                }
//            });
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//    }
//    private void initializeCbFamille(){
//        cbFamille.getItems().clear();
//        Statement statement;
//        ResultSet rs = null;
//        try {
//            String query = "SELECT * from famille_medicament where active = true ORDER BY famille_id";
//            statement = connection.createStatement();
//            rs = statement.executeQuery(query);
//            while (rs.next()) {
//                objFamilleMedicament.add(new FamilleMedicament(rs.getInt("famille_id"),
//                        rs.getString("nom_famille")));
//            }
//            cbFamille.setItems(objFamilleMedicament);
//            cbFamille.setConverter(new StringConverter<FamilleMedicament>() {
//                @Override
//                public String toString(FamilleMedicament familleMedicament) {
//                    return familleMedicament.getNom_famille();
//                }
//
//                @Override
//                public FamilleMedicament fromString(String string) {
//                    return cbFamille.getItems().stream().filter(ap ->
//                            ap.getNom_famille().equals(string)).findFirst().orElse(null);
//                }
//            });
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//    }
//
//    private void converterFournisseur(){
//
//    }
//
///*
//    public void lireMedicamentFournisseur(Connection conn) {
//        Statement statement;
//        ResultSet rs = null;
//        try {
//            String query = "SELECT * from medicament  WHERE actif = true ORDER BY id_medicament";
//            statement = conn.createStatement();
//            rs = statement.executeQuery(query);
//            while (rs.next()) {
//                objMedicament.add(new Medicament(
//                        rs.getString("nom_medicament"), rs.getString("dose"),
//                        rs.getInt("id_medicament"), rs.getInt("quantite"), rs.getDouble("prix"),
//                        rs.getInt("famille"), rs.getInt("id_fournisseur"), rs.getDate("date_ajout"),
//                        rs.getBoolean("actif"), rs.getDate("date_fabrication"),
//                        rs.getDate("date_expiration"), rs.getString("description")));
//
//                //Set the value to the table
//                colNom.setCellValueFactory(paramNom -> new SimpleStringProperty(paramNom.getValue().getNom()));
//                colDose.setCellValueFactory(paramDose -> new SimpleStringProperty(paramDose.getValue().getDose()));
//                colDescription.setCellValueFactory(paramDescription -> new SimpleStringProperty(paramDescription.getValue().getDescription()));
//
//                //convert int to String to add to the table
//                colRef.setCellValueFactory(paramId -> new SimpleStringProperty(toString(paramId.getValue().getId())));
//                colQuantite.setCellValueFactory(paramQuantite -> new SimpleStringProperty(toString(paramQuantite.getValue().getQuantite())));
//                colPrix.setCellValueFactory(paramPrix -> new SimpleStringProperty(toString(paramPrix.getValue().getPrix())));
//                colFamille.setCellValueFactory(paramFamille -> new SimpleStringProperty(toString(paramFamille.getValue().getId_famille())));
//                colFournisseur.setCellValueFactory(paramFournisseur -> new SimpleStringProperty(toString(paramFournisseur.getValue().getId_fournisseur())));
//
//                //convert date to String to add to the table
//                colDateFabrication.setCellValueFactory(paramDateFabrication -> new SimpleStringProperty(toString(paramDateFabrication.getValue().getDate_fabrication())));
//                colDateExpiration.setCellValueFactory(paramDateExpiration -> new SimpleStringProperty(toString(paramDateExpiration.getValue().getDate_expirations())));
//
//
//                colQuantite.setCellFactory(column -> new TableCell<Medicament, String>() {
//                    @Override
//                    protected void updateItem(String item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (item == null || empty) {
//                            setText(null);
//                            setStyle("");
//                        } else {
//                            setText(item);
//                            int nb = Integer.parseInt(item.trim());
//                            if (nb < 10) {
//                                setStyle("-fx-background-color: yellow;");
//                            } else {
//                                setStyle("");
//                            }
//                        }
//                    }
//                });
//                editableCols();
//                tblMedicament.setItems(FXCollections.observableArrayList(objMedicament));
//            }
//        } catch (Exception e) {
//
//            System.out.println(e);
//        }
//    }
//    */
//}
