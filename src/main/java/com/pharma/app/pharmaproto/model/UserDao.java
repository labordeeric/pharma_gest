package com.pharma.app.pharmaproto.model;

import com.pharma.app.pharmaproto.utils.DbContext;
import com.pharma.app.pharmaproto.utils.ModelBuilder;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    DbContext db = new DbContext();
    Connection connection = db.getConnection();
    String message = db.message;

    public List<User> getAllUsers() throws SQLException {
        //string_agg(r.role_nom, ', ') as roles : pour concatener les roles
        String sql = "SELECT u.user_id, u.user_username, u.user_password," +
                " u.user_email, u.user_nom, u.user_prenom, u.user_adresse, u.user_telephone, u.date_creation, "
                + "string_agg(r.role_nom, ', ') as roles "
                + "FROM public.user u "
                + "LEFT JOIN public.user_role ur ON u.user_id = ur.user_id "
                + "LEFT JOIN public.role r ON ur.role_id = r.role_id"
                + " GROUP BY u.user_id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                User user = null;
                while (resultSet.next()) {
                    if (user == null || user.getUser_id() != resultSet.getInt("user_id")) {

                        user = new User(resultSet.getInt("user_id"),
                                resultSet.getString("user_username"),
                                resultSet.getString("user_password"),
                                resultSet.getString("user_nom"),
                                resultSet.getString("user_prenom"),
                                resultSet.getString("user_adresse"),
                                resultSet.getString("user_email"),
                                resultSet.getInt("user_telephone"),
                                resultSet.getDate("date_creation"),
                                resultSet.getString("roles"));
                        users.add(user);

                    }
                }
                return users;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT u.user_id, u.user_username, u.user_password," +
                " u.user_email, u.user_nom, u.user_prenom, u.user_adresse, u.user_telephone, u.date_creation, " +
                " r.role_id as role_id, r.role_nom as titre, r.description as description, r.date_ajout as date_ajout "
                + "FROM public.user u "
                + "LEFT JOIN public.user_role ur ON u.user_id = ur.user_id "
                + "LEFT JOIN public.role r ON ur.role_id = r.role_id "
                + "WHERE u.user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                User user = null;
                Role role;
                List<Role> roles = new ArrayList<>();
                while (resultSet.next()) {
                    if(resultSet.getInt("role_id") > 0){

                        role = new ModelBuilder<Role>(Role.class)
                                .set("role_id", resultSet.getInt("role_id"))
                                .set("titre", resultSet.getString("titre"))
                                .set("description", resultSet.getString("description"))
                                .build();
                        roles.add(role);
                    }
                    if (user == null) {
                        user = new User(resultSet.getInt("user_id"),
                                resultSet.getString("user_username"),
                                resultSet.getString("user_password"),
                                resultSet.getString("user_nom"),
                                resultSet.getString("user_prenom"),
                                resultSet.getString("user_adresse"),
                                resultSet.getString("user_email"),
                                resultSet.getInt("user_telephone"),
                                resultSet.getDate("date_creation"),
                                roles.toArray().length > 0 ? roles : null);
//
                    }
                }
                return user;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public String removeUserById(int id) throws SQLException {
//        String sql = "DELETE FROM users WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, id);
//            statement.executeUpdate();
//            return "User deleted successfully";
//        } catch(SQLException e) {
//            e.printStackTrace();
//            return "Error deleting user";
//        }
//    }

    public int addUser(User user) throws SQLException {
        String sql = "INSERT INTO public.user (user_username, user_password, user_email," +
                " user_nom, user_prenom, user_adresse, user_telephone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO public.user_role (user_id, role_id) VALUES (?, ?)" ;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getNom());
            statement.setString(5, user.getPrenom());
            statement.setString(6, user.getAdresse());
            statement.setInt(7, user.getTel_no());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    PreparedStatement statement2 = connection.prepareStatement(sql2);
                    List<Role> roles = user.getRoles();
                    for (Role role : roles
                    ) {
                        statement2.setInt(1, generatedKeys.getInt(1));
                        statement2.setInt(2, role.getRole_id());
                        statement2.executeUpdate();
                    }
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Erreur en Créant l'utilisateur, Pas d'ID obtenu.");
                }
            }

        } catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int editUser(User user) throws SQLException {
        String sql = "UPDATE public.user SET user_username = ?, user_password = ?, user_email = ?, user_nom = ?, user_prenom = ?, user_adresse = ?, user_telephone = ? WHERE user_id = ?";
        String sql2 = "INSERT INTO public.user_role (user_id, role_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getNom());
            statement.setString(5, user.getPrenom());
            statement.setString(6, user.getAdresse());
            statement.setInt(7, user.getTel_no());
            statement.setInt(8, user.getUser_id());
            statement.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            List<Role> roles = user.getRoles();
            for (Role role : roles
            ) {
                statement2.setInt(1, user.getUser_id());
                statement2.setInt(2, role.getRole_id());
                statement2.executeUpdate();
            }
            return user.getUser_id();
        } catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean deleteUserRole(int user_id, int role_id) throws SQLException {
        String sql = "DELETE FROM public.user_role WHERE user_id = ? AND role_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setInt(2, role_id);
            statement.executeUpdate();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //TODO: edit users
//    public int editUser(Users user) throws SQLException {
//        String sql = "UPDATE users SET username = ?, password = ?, email = ?, nom = ?, prenom = ?, tel_no = ?, derniere_conn = ?, present = ?, statut = ? WHERE id_user = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, user.getUsername());
//            statement.setString(2, user.getPassword());
//            statement.setString(3, user.getEmail());
//            statement.setString(4, user.getNom());
//            statement.setString(5, user.getPrenom());
//            statement.setString(6, user.getTel_no());
//            statement.setDate(7, (Date) user.getDerniere_conn());
//            statement.setBoolean(8, user.isPresent());
//            statement.setBoolean(9, user.isStatut());
//            statement.setInt(10, user.getUser_id());
//            statement.executeUpdate();
//            return user.getUser_id();
//        } catch(SQLException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
}
