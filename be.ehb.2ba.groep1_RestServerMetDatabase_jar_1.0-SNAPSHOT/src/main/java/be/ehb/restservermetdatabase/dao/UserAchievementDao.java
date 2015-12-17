package be.ehb.restservermetdatabase.dao;

import be.ehb.restservermetdatabase.model.Achievement;
import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.UserAchievement;

public class UserAchievementDao {

    public static ArrayList<UserAchievement> getUsersAchievements() {
        ArrayList<UserAchievement> result = new ArrayList<UserAchievement>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from usersachievements");
            if (results != null) {
                while (results.next()) {
                    UserAchievement current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static ArrayList<UserAchievement> getUsersAchievementsById(int id) {
        ArrayList<UserAchievement> result = new ArrayList<UserAchievement>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from usersachievements where user_id = ?", new Object[]{id});
            if (results != null) {
                while (results.next()) {
                    UserAchievement current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static int AddUserAchievement(UserAchievement u) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO usersachievements ( user_id, achievement_id ) VALUES (?,?)", new Object[]{u.getUserId(), u.getAchievementId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int deleteUserAchievement(int userId, int achievementId) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM usersachievements WHERE user_id = ? AND achievement_id = ?", new Object[]{userId, achievementId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static ArrayList<Achievement> getPersonalAchievements(int userId) {
        ArrayList<Achievement> result = new ArrayList<Achievement>();
        String sqlQuerry = "SELECT * from achievements a join usersachievements u on a.achievement_id = u.achievement_id where user_id = ?";
        try {
            ResultSet results = Database.execSqlAndReturn(sqlQuerry, new Object[]{userId});
            if (results != null) {
                while (results.next()) {
                    Achievement current = AchievementDao.convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    private static UserAchievement convertRowToObject(ResultSet row) throws SQLException {
        return new UserAchievement(row.getInt("user_id"), row.getInt("achievement_id"));
    }

}
