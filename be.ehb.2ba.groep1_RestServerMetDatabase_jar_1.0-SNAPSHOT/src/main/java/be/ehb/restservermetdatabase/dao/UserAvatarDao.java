package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.UserAvatar;

public class UserAvatarDao {

    public static ArrayList<UserAvatar> getUserAvatars() {
        ArrayList<UserAvatar> result = new ArrayList<UserAvatar>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from usersavatar");
            if (results != null) {
                while (results.next()) {
                    UserAvatar current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static ArrayList<UserAvatar> getUserAvatarById(int id) {
        ArrayList<UserAvatar> result = new ArrayList<UserAvatar>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from usersavatar where user_id = ?", new Object[]{id});
            if (results != null) {
                while (results.next()) {
                    UserAvatar current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static int addUserAvatar(UserAvatar u) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO usersavatar ( user_id, avatar_id ) VALUES (?,?)", new Object[]{u.getUserId(), u.getAvatarId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int deleteUserAvatar(int userId, int avatarId) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM usersavatar WHERE user_id = ? AND avatar_id = ?", new Object[]{userId,avatarId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    private static UserAvatar convertRowToObject(ResultSet row) throws SQLException {
        return new UserAvatar(row.getInt("user_id"), row.getInt("avatar_id"));
    }

}
