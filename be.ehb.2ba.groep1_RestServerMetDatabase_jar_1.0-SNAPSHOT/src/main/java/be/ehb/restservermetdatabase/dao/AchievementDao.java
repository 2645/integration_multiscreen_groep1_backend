package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Achievement;

public class AchievementDao {

    public static ArrayList<Achievement> getAchievements() {
        ArrayList<Achievement> result = new ArrayList<Achievement>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from achievements");
            if (results != null) {
                while (results.next()) {
                    Achievement current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static Achievement getAchievementById(int id) {
        Achievement result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from achievements where achievement_id = ?", new Object[]{id});
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

    public static int addAchievement(Achievement a) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO achievements ( achievement_google_id, achievement_name, achievement_description, achievement_icon, achievement_list_order ) VALUES (?,?,?,?,?)", new Object[]{a.getGoogleId(), a.getName(), a.getDescription(), a.getIcon(), a.getListOrder()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int updateAchievement(Achievement a) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("UPDATE achievements SET achievement_google_id = ?, achievement_name = ?, achievement_description = ?, achievement_icon = ? , achievement_list_order = ? WHERE achievement_id = ?", new Object[]{a.getGoogleId(), a.getName(), a.getDescription(), a.getIcon(), a.getListOrder(), a.getId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int deleteAchievement(int id) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM achievements WHERE achievement_id = ?", new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    static Achievement convertRowToObject(ResultSet row) throws SQLException {
        return new Achievement(row.getInt("achievement_id"), row.getInt("achievement_list_order"), row.getString("achievement_google_id"), row.getString("achievement_name"), row.getString("achievement_description"), row.getString("achievement_icon"));
    }

}
