package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Friends;

public class FriendsDao {

    public static ArrayList<Friends> getFriends() {
        ArrayList<Friends> resultaat = new ArrayList<Friends>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from friends");
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    Friends huidigeFriends = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeFriends);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static Friends getFriendsById(int id) {
        Friends resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from friends where friend_id = ?", new Object[]{id});
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

    public static int voegFriendsToe(Friends nieuweFriends) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO friends ( user_id1, user_id2 ) VALUES (?,?)", new Object[]{nieuweFriends.getUser_id1(), nieuweFriends.getUser_id2()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updateFriends(Friends nieuweFriends) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE friends SET user_id1 = ?, user_id2 = ? WHERE friend_id = ?", new Object[]{nieuweFriends.getUser_id1(), nieuweFriends.getUser_id2(), nieuweFriends.getFriend_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderFriends(int friendsId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM friends WHERE friend_id = ?", new Object[]{friendsId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static Friends converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new Friends(mijnResultset.getInt("friend_id"), mijnResultset.getInt("user_id1"),  mijnResultset.getInt("user_id2"));
    }

}
