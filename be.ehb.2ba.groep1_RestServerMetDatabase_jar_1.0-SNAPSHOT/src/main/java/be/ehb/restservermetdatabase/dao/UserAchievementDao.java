package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.UsersAchievement;

public class UserAchievementDao {

    public static ArrayList<UsersAchievement> getUsersAchievements() {
        ArrayList<UsersAchievement> resultaat = new ArrayList<UsersAchievement>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from usersachievements");
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    UsersAchievement huidigeUserAvatar = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeUserAvatar);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static ArrayList<UsersAchievement> getUsersAchievementsById(int id) {
        ArrayList<UsersAchievement> resultaat = new ArrayList<UsersAchievement>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from usersachievements where user_id = ?", new Object[]{id});
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    UsersAchievement huidigeUserAvatar = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeUserAvatar);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static int voegUsersAchievementtoe(UsersAchievement nieuweUsersAchievement) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO usersachievements ( user_id, achievement_id ) VALUES (?,?)", new Object[]{nieuweUsersAchievement.getUser_id(), nieuweUsersAchievement.getAchievement_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderUsersAchievements(int userId, int achievementId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM usersachievements WHERE user_id = ? AND achievement_id = ?", new Object[]{userId,achievementId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static UsersAchievement converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new UsersAchievement(mijnResultset.getInt("user_id"), mijnResultset.getInt("achievement_id"));
    }

}