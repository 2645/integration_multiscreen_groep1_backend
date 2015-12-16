package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Friendship;
import be.ehb.restservermetdatabase.model.User;

public class FriendshipDao {

    public static ArrayList<User> getFriends(int id) {
        ArrayList<User> resultaat = new ArrayList<>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from friends WHERE friend_user_id1 = ? OR friend_user_id2 = ?", new Object[]{id, id});
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    Friendship huidigeFriendship = converteerHuidigeRijNaarObject(mijnResultset);
                    int friendID = 0;
                    
                    if(huidigeFriendship.getFrom_id() == id) {
                        friendID = huidigeFriendship.getTo_id();
                        
                    } else if(huidigeFriendship.getFrom_id() == id) {
                        friendID = huidigeFriendship.getTo_id();
                    }
                    resultaat.add(UserDao.getUserById(friendID));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static Friendship getFriendshipById(int id) {
        Friendship resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from friends WHERE friend_id = ?", new Object[]{id});
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

    public static int getFriendshipId(int from_id, int to_id) {
        Friendship resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from friends WHERE friend_user_id1 = ? AND friend_user_id2 = ?", new Object[]{from_id, to_id});
            if (mijnResultset != null) {
                mijnResultset.first();
                resultaat = converteerHuidigeRijNaarObject(mijnResultset);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat.getFriendship_id();
    }

    public static int voegFriendshipToe(Friendship nieuweFriendship) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO friends ( user_id1, user_id2 ) VALUES (?,?)", new Object[]{nieuweFriendship.getFrom_id(), nieuweFriendship.getTo_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updateFriendship(Friendship nieuweFriendship) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE friends SET user_id1 = ?, user_id2 = ? WHERE friend_id = ?", new Object[]{nieuweFriendship.getFrom_id(), nieuweFriendship.getTo_id(), nieuweFriendship.getFriendship_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderFriendship(int friendsId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM friends WHERE friend_id = ?", new Object[]{friendsId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderFriendship(int from_id, int to_id) {
        int aantalAangepasteRijen = 0;
        int friendsID = 0;
        
        try {
            friendsID = FriendshipDao.getFriendshipId(from_id, to_id);
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM friends WHERE friend_id = ?", new Object[]{friendsID});
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static Friendship converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new Friendship(mijnResultset.getInt("friend_id"), mijnResultset.getInt("user_id1"),  mijnResultset.getInt("user_id2"));
    }

}
