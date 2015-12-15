package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.UserAvatar;

public class UserAvatarDao {

    public static ArrayList<UserAvatar> getUserAvatars() {
        ArrayList<UserAvatar> resultaat = new ArrayList<UserAvatar>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from usersavatar");
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    UserAvatar huidigeUserAvatar = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeUserAvatar);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static ArrayList<UserAvatar> getUserAvatarById(int id) {
        ArrayList<UserAvatar> resultaat = new ArrayList<UserAvatar>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from usersavatar where user_id = ?", new Object[]{id});
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    UserAvatar huidigeUserAvatar = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeUserAvatar);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static int voegUserAvatarToe(UserAvatar nieuweUserAvatar) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO usersavatar ( user_id, avatar_id ) VALUES (?,?)", new Object[]{nieuweUserAvatar.getUser_id(), nieuweUserAvatar.getAvatar_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderFriends(int userId, int avatarId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM usersavatar WHERE user_id = ? AND avatar_id = ?", new Object[]{userId,avatarId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static UserAvatar converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new UserAvatar(mijnResultset.getInt("user_id"), mijnResultset.getInt("avatar_id"));
    }

}
