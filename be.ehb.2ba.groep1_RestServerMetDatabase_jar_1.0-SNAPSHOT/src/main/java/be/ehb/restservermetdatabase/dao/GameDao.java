package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Game;

public class GameDao {

    public static ArrayList<Game> getGames() {
        ArrayList<Game> resultaat = new ArrayList<Game>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from games");
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    Game huidigeAvatar = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeAvatar);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static Game getGameById(int id) {
        Game resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from games where game_id = ?", new Object[]{id});
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

    public static int voegGameToe(Game nieuweGame) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO games ( game_name, game_description ) VALUES (?,?)", new Object[]{nieuweGame.getGame_name(), nieuweGame.getGame_description()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updateGame(Game nieuweGame) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE games SET game_name = ?, game_description = ? WHERE game_id = ?", new Object[]{nieuweGame.getGame_name(), nieuweGame.getGame_description(), nieuweGame.getGame_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderGame(int gameId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM games WHERE game_id = ?", new Object[]{gameId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static Game converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new Game(mijnResultset.getInt("game_id"), mijnResultset.getString("game_name"),  mijnResultset.getString("game_description"));
    }

}
