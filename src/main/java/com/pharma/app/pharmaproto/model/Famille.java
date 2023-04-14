package com.pharma.app.pharmaproto.model;

import java.time.LocalDate;

public class Famille {
    private int id_Famille;
    private String nom_famille;
    private boolean active = true;
    private String description;
    private LocalDate date_ajout;

    public Famille(int idFamille, String nom_famille) {
        this.id_Famille = idFamille;
        this.nom_famille = nom_famille;

    }

    public Famille() {

    }

    public int getIdFamille() {
        return id_Famille;
    }

    public void setIdFamille(int id_Famille) {
        this.id_Famille = id_Famille;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_ajout() {
        return date_ajout;
    }


}
