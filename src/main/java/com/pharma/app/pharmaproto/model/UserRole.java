package com.pharma.app.pharmaproto.model;

import java.util.Date;
import java.util.List;

public class UserRole {
    private int id;
    private int user_id;
    private int role_id;
    private Date date_attrib;
    private List<User> users;
    private List<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public Date getDate_attrib() {
        return date_attrib;
    }

    public void setDate_attrib(Date date_attrib) {
        this.date_attrib = date_attrib;
    }
}
