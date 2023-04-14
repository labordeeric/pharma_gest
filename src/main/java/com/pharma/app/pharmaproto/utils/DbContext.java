package com.pharma.app.pharmaproto.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbContext {
    //Attributs
    public static String message = "";
    public static boolean connected = false;

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/pharmaproto";
//    private static final String DB_URL = "jdbc:postgresql://6.tcp.eu.ngrok.io:16322/pharmagest";

    private static final String USER = "postgres";
    private static final String PASS = "1234";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                connected = true;
                message = "Connected";
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                connected = false;
                message = "Class not found : " + e;
            } catch (SQLException e) {
                e.printStackTrace();
                connected = false;
                message = "SQL Exception : " + e;
            }
        }
        return connection;
    }
}
