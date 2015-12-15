package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Achievement;

public class AchievementDao {

    public static ArrayList<Achievement> getAchievements() {
        ArrayList<Achievement> resultaat = new ArrayList<Achievement>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from achievements");
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    Achievement huidigeAchievement = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeAchievement);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static Achievement getAchievementById(int id) {
        Achievement resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from achievements where achievement_id = ?", new Object[]{id});
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

    public static int voegAchievementToe(Achievement nieuweAchievement) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO achievements ( achievement_google_id, achievement_name, achievement_description, achievement_icon, achievement_list_order ) VALUES (?,?,?,?,?)", new Object[]{nieuweAchievement.getAchievement_google_id(), nieuweAchievement.getAchievement_name(), nieuweAchievement.getAchievement_description(), nieuweAchievement.getAchievement_icon(), nieuweAchievement.getAchievement_list_order()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updateAchievement(Achievement nieuweAchievement) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE achievements SET achievement_google_id = ?, achievement_name = ?, achievement_description = ?, achievement_icon = ? , achievement_list_order = ? WHERE achievement_id = ?", new Object[]{nieuweAchievement.getAchievement_google_id(), nieuweAchievement.getAchievement_name(), nieuweAchievement.getAchievement_description(), nieuweAchievement.getAchievement_icon(), nieuweAchievement.getAchievement_list_order(), nieuweAchievement.getAchievement_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderFriends(int achievementId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM achievements WHERE achievement_id = ?", new Object[]{achievementId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static Achievement converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new Achievement(mijnResultset.getInt("achievement_id"), mijnResultset.getInt("achievement_list_order"), mijnResultset.getString("achievement_google_id"), mijnResultset.getString("achievement_name"), mijnResultset.getString("achievement_description"), mijnResultset.getString("achievement_icon"));
    }

}
