package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.User;

public class UserDao {

    public static ArrayList<User> getUsers() {
        ArrayList<User> resultaat = new ArrayList<User>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from users");
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    User huidigeUser = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeUser);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static User getUserById(int id) {
        User resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from users where user_id = ?", new Object[]{id});
            if (mijnResultset != null) {
                mijnResultset.first();
                resultaat = converteerHuidigeRijNaarObject(mijnResultset);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static int voegUserToe(User nieuweUser) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO users ( user_firstname, user_lastname, user_mail, user_password, user_balance, user_currentavatar_id) VALUES (?,?,?,?,?,?)", new Object[]{nieuweUser.getUser_firstname(), nieuweUser.getUser_lastname(), nieuweUser.getUser_mail(), nieuweUser.getUser_password(), nieuweUser.getUser_balance(), nieuweUser.getUser_currentavatar_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updateUser(User nieuweUser) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE users SET user_firstname = ?, user_lastname = ?, user_mail = ?, user_password = ?, user_balance = ?, user_currentavatar_id = ? WHERE user_id = ?", new Object[]{nieuweUser.getUser_firstname(), nieuweUser.getUser_lastname(), nieuweUser.getUser_mail(), nieuweUser.getUser_password(), nieuweUser.getUser_balance(), nieuweUser.getUser_currentavatar_id(), nieuweUser.getUser_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderUser(int userId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM users WHERE user_id = ?", new Object[]{userId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static User converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new User(mijnResultset.getInt("user_id"), mijnResultset.getInt("user_balance"),  mijnResultset.getInt("user_currentavatar_id"), mijnResultset.getString("user_firstname"), mijnResultset.getString("user_lastname"), mijnResultset.getString("user_mail"), mijnResultset.getString("user_password") );
    }

}
