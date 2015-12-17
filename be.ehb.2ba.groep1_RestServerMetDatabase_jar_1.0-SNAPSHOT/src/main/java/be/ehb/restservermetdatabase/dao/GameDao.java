package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Game;

public class GameDao {

    public static ArrayList<Game> getGames() {
        ArrayList<Game> result = new ArrayList<Game>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from games");
            if (results != null) {
                while (results.next()) {
                    Game current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static Game getGameById(int id) {
        Game result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from games where game_id = ?", new Object[]{id});
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
    
    public static Game getGameByName(String name) {
        Game result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from games WHERE game_name = ?", new Object[]{name});
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

    public static int addGame(Game g) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO games ( game_name, game_description ) VALUES (?,?)", new Object[]{g.getName(), g.getDescription()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int updateGame(Game g) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("UPDATE games SET game_name = ?, game_description = ? WHERE game_id = ?", new Object[]{g.getName(), g.getDescription(), g.getId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int deleteGame(int id) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM games WHERE game_id = ?", new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    private static Game convertRowToObject(ResultSet row) throws SQLException {
        return new Game(row.getInt("game_id"), row.getString("game_name"),  row.getString("game_description"));
    }

}
