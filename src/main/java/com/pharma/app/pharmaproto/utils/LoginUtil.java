package com.pharma.app.pharmaproto.utils;

import com.pharma.app.pharmaproto.model.Role;
import com.pharma.app.pharmaproto.model.UserDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginUtil {
    DbContext db = new DbContext();
    Connection connection = db.getConnection();
    Statement statement = null;
    ResultSet resultSet = null;
    String message = db.message;
    public int id_user;


    public Boolean login(String username, String password) {

        try {
            String query = String
                    .format("SELECT * FROM public.%s WHERE user_username = '%s' AND user_password = '%s'", "user", username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                id_user = resultSet.getInt("user_id");
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Boolean logout() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Permissions
    public Boolean hasAdminPermission() {
        if (id_user != 0){
            UserDao userDao = new UserDao();
            try {
                for (Role role: userDao.getUserById(id_user).getRoles()) {
                    return role.getTitre().equals("admin");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public Boolean hasVendeurPermission() {
        if (id_user != 0){
            UserDao userDao = new UserDao();
            try {
                for (Role role: userDao.getUserById(id_user).getRoles()) {
                    return role.getTitre().equals("vendeur");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public Boolean hasCaissePermission() {
        if (id_user != 0){
            UserDao userDao = new UserDao();
            try {
                for (Role role: userDao.getUserById(id_user).getRoles()) {
                    return role.getTitre().equals("caisse");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
