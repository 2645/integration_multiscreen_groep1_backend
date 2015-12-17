package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Friendship;
import be.ehb.restservermetdatabase.model.User;

public class FriendshipDao {

    public static ArrayList<User> getFriends(int id) {
        ArrayList<User> result = new ArrayList<>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from friends WHERE friend_user_id1 = ? OR friend_user_id2 = ?", new Object[]{id, id});
            if (results != null) {
                while (results.next()) {
                    Friendship current = convertRowToObject(results);
                    int friendID = 0;
                    
                    if(current.getFromId() == id) {
                        friendID = current.getToId();
                        
                    } else if(current.getToId() == id) {
                        friendID = current.getFromId();
                    }                    
                    result.add(UserDao.getUserById(friendID));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static Friendship getFriendshipById(int id) {
        Friendship result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from friends WHERE friend_id = ?", new Object[]{id});
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

    public static int getFriendshipId(int fromId, int toId) {
        Friendship result = null;        
        try {
            ResultSet check1 = Database.execSqlAndReturn("SELECT * from friends where friend_user_id1 = ? AND friend_user_id2 = ?", 
                new Object[] {fromId, toId});
            ResultSet check2 = Database.execSqlAndReturn("SELECT * from friends where friend_user_id1 = ? AND friend_user_id2 = ?", 
                new Object[] {toId, fromId});
            
            if(check1.next()) {
                check1.first();
                result = convertRowToObject(check1);
                
            } else if(check2.next()) {
                check2.first();
                result = convertRowToObject(check2);                
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return result.getId();
    }

    public static int addFriendship(Friendship f) {
        int changedRows = 0;        
        try {
            ResultSet check1 = Database.execSqlAndReturn("SELECT * from friends where friend_user_id1 = ? AND friend_user_id2 = ?", 
                new Object[] {f.getFromId(), f.getToId()});
            ResultSet check2 = Database.execSqlAndReturn("SELECT * from friends where friend_user_id1 = ? AND friend_user_id2 = ?", 
                new Object[] {f.getToId(), f.getFromId()});
            
            if(!check1.next() && !check2.next()) {
                changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO friends ( friend_user_id1, friend_user_id2 ) VALUES (?,?)", new Object[]{f.getFromId(), f.getToId()});
            } else {
                return 0;
            }        
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int updateFriendship(Friendship f) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("UPDATE friends SET friend_user_id1 = ?, friend_user_id2 = ? WHERE friend_id = ?", new Object[]{f.getFromId(), f.getToId(), f.getId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int deleteFriendship(int id) {
        int changedrows = 0;
        try {
            changedrows = Database.execSqlAndReturnChangedRows("DELETE FROM friends WHERE friend_id = ?", new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedrows;
    }

    public static int deleteFriendhsip(int fromId, int toId) {
        int changedRows = 0;
        int id = 0;
        
        try {
            id = FriendshipDao.getFriendshipId(fromId, toId);
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM friends WHERE friend_id = ?", new Object[]{id});
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    private static Friendship convertRowToObject(ResultSet row) throws SQLException {
        return new Friendship(row.getInt("friend_id"), row.getInt("friend_user_id1"),  row.getInt("friend_user_id2"));
    }

}
