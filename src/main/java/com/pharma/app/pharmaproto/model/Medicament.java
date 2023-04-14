package com.pharma.app.pharmaproto.model;

import java.util.Date;

public class Medicament {

    //region Attributs
    private  String nom;
    private int quantite;
    private String dose;
    private int id_famille;
    private float prix;

    private int id_fournisseur;
    private int id;
    private Date date_ajout;
    private boolean active = true;

    private Date date_fabrication;
    private Date date_expirations;

    private String description;
    private Fournisseur fournisseur;

    private Famille famille;

    private Forme forme;
    //endregion

    public Medicament (String nom, String dose, int id, int quantite, float prix, int id_fournisseur,
                       Date date_ajout, boolean active, Date date_fabrication, Date date_expirations, String description,
                       Fournisseur fournisseur) {
        this.nom = nom;
        this.dose = dose;
        this.id = id;
        this.quantite = quantite;
        this.prix = prix;
        this.id_fournisseur = id_fournisseur;
        this.date_ajout = date_ajout;
        this.active = active;
        this.date_fabrication = date_fabrication;
        this.date_expirations = date_expirations;
        this.description = description;
        this.fournisseur = fournisseur;
    }

    public Medicament (String nom, String dose, int id, int quantite, float prix, int id_famille, int id_fournisseur,
                       Date date_ajout, boolean active, Date date_fabrication, Date date_expirations, String description) {
        this.nom = nom;
        this.dose = dose;
        this.id = id;
        this.quantite = quantite;
        this.prix = prix;
        this.id_famille = id_famille;
        this.id_fournisseur = id_fournisseur;
        this.date_ajout = date_ajout;
        this.active = active;
        this.date_fabrication = date_fabrication;
        this.date_expirations = date_expirations;
        this.description = description;

    }

    public Medicament (int id, String nom, String dose, int quantite, float prix, int id_famille, int id_fournisseur,
                       Date date_ajout, boolean active, Date date_fabrication, Date date_expirations, String description,
                       Famille famille, Fournisseur fournisseur, Forme forme) {
        this.nom = nom;
        this.dose = dose;
        this.id = id;
        this.quantite = quantite;
        this.prix = prix;
        this.id_famille = id_famille;
        this.id_fournisseur = id_fournisseur;
        this.date_ajout = date_ajout;
        this.active = active;
        this.date_fabrication = date_fabrication;
        this.date_expirations = date_expirations;
        this.description = description;
        this.famille = famille;
        this.fournisseur = fournisseur;
        this.forme = forme;

    }

    public Medicament() {

    }


    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getNom() {
        return nom;
    }

    public  void setNom(String nom) {
        this.nom = nom;
    }


    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getId_famille() {
        return id_famille;
    }

    public void setId_famille(int id_famille) {
        this.id_famille = id_famille;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDate_fabrication() {
        return date_fabrication;
    }

    public void setDate_fabrication(Date date_fabrication) {
        this.date_fabrication = date_fabrication;
    }

    public Date getDate_expirations() {
        return date_expirations;
    }

    public void setDate_expirations() {
        this.date_expirations = date_expirations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public void setDate_expirations(Date date_expirations) {
        this.date_expirations = date_expirations;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    public Forme getForme() {
        return forme;
    }

    public void setForme(Forme forme) {
        this.forme = forme;
    }


}
