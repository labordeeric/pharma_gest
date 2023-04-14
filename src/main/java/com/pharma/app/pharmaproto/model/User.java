package com.pharma.app.pharmaproto.model;

import java.util.Date;
import java.util.List;
//@Warning pas d'initiallisation avec utils.ModelBuilder
public class User {
    private int user_id;
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private int tel_no;
    private Date date_creation;
    private List<Role> roles;

    private String role;

    public User() {
    }

    public User(int user_id, String username, String nom, String prenom, String adresse, String email, int tel_no, String role) {
        this.user_id = user_id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.tel_no = tel_no;
        this.role = role;
    }

    public User(int user_id, String username, String password, String nom, String prenom, String adresse, String email, int tel_no, Date date_creation, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.tel_no = tel_no;
        this.date_creation = date_creation;
        this.role = role;
    }

    //Constructeur sans password
    public User(int user_id, String username,
                String nom, String prenom, String adresse,
                String email,
                int tel_no, Date date_creation, List<Role> roles) {
        this.user_id = user_id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.tel_no = tel_no;
        this.date_creation = date_creation;
        this.roles = roles;
    }

    public User(String username,
                String password,
                String nom,
                String prenom,
                String adresse,
                String email,
                int tel_no,
                List<Role> roles) {
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.tel_no = tel_no;
        this.roles = roles;
    }

    //Constructor avec password
    public User(int user_id, String username, String password,
                String nom, String prenom,
                String adresse, String email,
                int tel_no, Date date_creation,
                List<Role> roles) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.tel_no = tel_no;
        this.date_creation = date_creation;
        this.roles = roles;
    }
    //Constructeur sans id
    public User(String username,
                String password,
                String nom,
                String prenom,
                String adresse,
                String email,
                int tel_no,
                Date date_creation,
                List<Role> roles) {
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.tel_no = tel_no;
        this.date_creation = date_creation;
        this.roles = roles;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel_no() {
        return tel_no;
    }

    public void setTel_no(int tel_no) {
        this.tel_no = tel_no;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
