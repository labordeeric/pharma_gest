package com.pharma.app.pharmaproto.model;

public class Forme {

    private int id_forme;
    private String nom_forme;
    private boolean active = true;

    private String date_ajout;

    public Forme(int id_forme, String nom_forme) {
        this.id_forme = id_forme;
        this.nom_forme = nom_forme;
    }

    public Forme() {
    }

    public int getId_forme() {
        return id_forme;
    }

    public void setId_forme(int id_forme) {
        this.id_forme = id_forme;
    }

    public String getNom_forme() {
        return nom_forme;
    }

    public void setNom_forme(String nom_forme) {
        this.nom_forme = nom_forme;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }


}
