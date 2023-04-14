package com.pharma.app.pharmaproto.model;

public class Role {
    private int role_id;
    private String titre;

//    public Roles(String titre, String description) {
//        this.titre = titre;
//        this.description = description;
//    }
//
//    public Roles(int role_id, String titre, String description) {
//        this.role_id = role_id;
//        this.titre = titre;
//        this.description = description;
//    }

    private String description;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
