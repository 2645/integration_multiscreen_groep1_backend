package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.User;

public class UserDao {

    public static ArrayList<User> getUsers() {
        ArrayList<User> result = new ArrayList<User>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from users");
            while (results.next()) {
                User current = convertRowToObject(results);
                result.add(current);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static User getUserById(int id) {
        User result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from users where user_id = ?", new Object[]{id});
            if (results != null) {
                results.first();
                result = convertRowToObject(results);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static User getUserByEmail(String mail) {
        User result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from users where user_mail = ?", new Object[]{mail});
            if (results != null) {
                results.first();
                result = convertRowToObject(results);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static ArrayList<User> getUsersByFirstname(String fname) {
        ArrayList<User> result = new ArrayList<User>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from users where user_firstname = ?", new Object[]{fname});
            if (results != null) {
                while (results.next()) {
                    User current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static ArrayList<User> getUsersByLastname(String lname) {
        ArrayList<User> result = new ArrayList<User>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from users where user_lastname = ?", new Object[]{lname});
            if (results != null) {
                while (results.next()) {
                    User current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static ArrayList<User> getUsersByFirstAndLastname(String fname, String lname) {
        ArrayList<User> result = new ArrayList<User>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from users where user_firstname = ? AND user_lastname = ?", new Object[]{fname, lname});
            if (results != null) {
                while (results.next()) {
                    User current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static int addUser(User u) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO users ( user_firstname, user_lastname, user_mail, user_password, user_balance, user_currentavatar_id) VALUES (?,?,?,?,?,?)", new Object[]{u.getFname(), u.getLname(), u.getMail(), u.getPw(), u.getBalance(), u.getAvatarId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int updateUser(User u) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("UPDATE users SET user_firstname = ?, user_lastname = ?, user_mail = ?, user_password = ?, user_balance = ?, user_currentavatar_id = ? WHERE user_id = ?", new Object[]{u.getFname(), u.getLname(), u.getMail(), u.getPw(), u.getBalance(), u.getAvatarId(), u.getId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int deleteUser(int id) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM users WHERE user_id = ?", new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    private static User convertRowToObject(ResultSet row) throws SQLException {
        return new User(row.getInt("user_id"), row.getInt("user_balance"), row.getInt("user_currentavatar_id"), row.getString("user_firstname"), row.getString("user_lastname"), row.getString("user_mail"), row.getString("user_password"));
    }

}
