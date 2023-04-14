package com.pharma.app.pharmaproto.model;

public class FamilleMedicament {
    private int famille_id;
    private String nom_famille;
    private boolean active;
    public FamilleMedicament(int famille_id, String nom_famille, boolean active) {
        this.famille_id = famille_id;
        this.nom_famille = nom_famille;
        this.active = active;
    }

    public FamilleMedicament(String nom_famille, boolean active) {
        this.nom_famille = nom_famille;
        this.active = active;
    }

    public FamilleMedicament(int famille_id, String nom_famille) {
        this.famille_id = famille_id;
        this.nom_famille = nom_famille;
    }

    public int getFamille_id() {
        return famille_id;
    }

    public void setFamille_id(int famille_id) {
        this.famille_id = famille_id;
    }

    public String getNom_famille() {
        return nom_famille;
    }

    public void setNom_famille(String nom_famille) {
        this.nom_famille = nom_famille;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
