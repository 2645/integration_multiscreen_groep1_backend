package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Avatar;

public class AvatarDao {

    public static ArrayList<Avatar> getAvatars() {
        ArrayList<Avatar> result = new ArrayList<>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from avatars");
            if (results != null) {
                while (results.next()) {
                    Avatar current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }
    
    public static ArrayList<Avatar> getAvatarsByUser(int id) {
        ArrayList<Avatar> result = new ArrayList<Avatar>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from usersavatars WHERE user_id = ?", new Object[]{id});
            if (results != null) {
                while (results.next()) {
                    Avatar current = AvatarDao.getAvatarById(results.getInt("avatar_id"));
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static Avatar getAvatarById(int id) {
        Avatar result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from avatars where avatar_id = ?", new Object[]{id});
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

    public static Avatar getAvatarByName(String name) {
        Avatar result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from avatars where avatar_name = ?", new Object[]{name});
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

    public static int addAvatar(Avatar a) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO avatars ( avatar_name, avatar_img, avatar_price ) VALUES (?,?,?)", new Object[]{a.getName(), a.getImg(), a.getPrice()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int updateAvatar(Avatar a) {
        int changedRows = 0;
        int id = a.getId();        
        if(id == 0) {
            return 0;            
        } else {
            if(a.getImg().equals("")) {
                a.setImg(AvatarDao.getAvatarById(id).getImg());
            }
            
            if(a.getName().equals("")) {
                a.setName(AvatarDao.getAvatarById(id).getName());
            }            
            if(a.getPrice() == 0) {
                a.setPrice(AvatarDao.getAvatarById(id).getPrice());
            }
        }        
        try {
            changedRows = Database.execSqlAndReturnChangedRows("UPDATE avatars SET avatar_name = ?, avatar_img = ?, avatar_price = ? WHERE avatar_id = ?", new Object[]{a.getName(), a.getImg(), a.getPrice(), id});
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }        
        return changedRows;
    }

    public static int deleteAvatar(int id) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM avatars WHERE avatar_id = ?", new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    private static Avatar convertRowToObject(ResultSet row) throws SQLException {
        return new Avatar(row.getInt("avatar_id"),  row.getInt("avatar_price"), row.getString("avatar_name"),  row.getString("avatar_img"));
    }

}
