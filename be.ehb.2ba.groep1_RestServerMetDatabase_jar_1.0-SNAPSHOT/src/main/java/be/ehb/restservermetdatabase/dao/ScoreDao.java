package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Score;

public class ScoreDao {

    public static ArrayList<Score> getScore() {
        ArrayList<Score> result = new ArrayList<Score>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from scores");
            if (results != null) {
                while (results.next()) {
                    Score current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static Score getScoreById(int id) {
        Score result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from scores where score_id = ?", new Object[]{id});
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

    public static int addScore(Score s) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO scores ( game_id, user_id, score_value, score_date ) VALUES (?,?,?,?)", new Object[]{s.getGameId(), s.getUserId(), s.getScore(), s.getDate()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int updateScore(Score s) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("UPDATE scores SET game_id = ?, user_id = ?, score_value = ?, score_date = ? where score_id = ?", new Object[]{s.getGameId(), s.getUserId(), s.getScore(), s.getDate(), s.getId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int deleteScore(int id) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM scores WHERE score_id = ?", new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    private static Score convertRowToObject(ResultSet row) throws SQLException {
        return new Score(row.getInt("score_id"), row.getInt("game_id"), row.getInt("user_id"),row.getInt("score_value"), row.getDate("score_date"));
    }

}
