package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Score;

public class ScoreDao {

    public static ArrayList<Score> getScore() {
        ArrayList<Score> resultaat = new ArrayList<Score>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from scores");
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    Score huidigeScore = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeScore);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static Score getScoreById(int id) {
        Score resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from scores where score_id = ?", new Object[]{id});
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

    public static int voegScoreToe(Score nieuweScore) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO scores ( game_id, user_id, score_value, score_date ) VALUES (?,?,?,?)", new Object[]{nieuweScore.getGame_id(), nieuweScore.getUser_id(), nieuweScore.getScore_value(), nieuweScore.getScore_date()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updateScore(Score nieuweScore) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE scores SET game_id = ?, user_id = ?, score_value = ?, score_date = ? where score_id = ?", new Object[]{nieuweScore.getGame_id(), nieuweScore.getUser_id(), nieuweScore.getScore_value(), nieuweScore.getScore_date(), nieuweScore.getScore_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderScore(int scoreId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM scores WHERE score_id = ?", new Object[]{scoreId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static Score converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new Score(mijnResultset.getInt("score_id"), mijnResultset.getInt("game_id"), mijnResultset.getInt("user_id"),mijnResultset.getInt("score_value"), mijnResultset.getDate("score_date"));
    }

}
