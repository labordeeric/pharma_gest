package com.pharma.app.pharmaproto.model;

import java.util.Date;

public class Fournisseur {
    private String Nom;
    private int IdFournisseur;
    private String Email;
    private String Addresse;
    private int Telephone;
    private boolean active = true;
    private Date date_ajout;

public Fournisseur(String nom, int idFournisseur, String email, String addresse, int telephone) {
    this.Nom = nom;
    this.IdFournisseur = idFournisseur;
    this.Email = email;
    this.Addresse = addresse;
    this.Telephone = telephone;
    }

    public Fournisseur() {

    }

    public Fournisseur(int idFournisseur, String nomFournisseur) {
        this.IdFournisseur = idFournisseur;
        this.Nom = nomFournisseur;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public int getIdFournisseur() {
        return IdFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        IdFournisseur = idFournisseur;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddresse() {
        return Addresse;
    }

    public void setAddresse(String addresse) {
        Addresse = addresse;
    }

    public int getTelephone() {
        return Telephone;
    }

    public void setTelephone(int telephone) {
        Telephone = telephone;
    }

    public void setDate_ajout(){
    date_ajout = new Date();
    }
}
