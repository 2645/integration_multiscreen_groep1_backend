package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Avatar;

public class AvatarDao {

    public static ArrayList<Avatar> getAvatars() {
        ArrayList<Avatar> resultaat = new ArrayList<Avatar>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from avatars");
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    Avatar huidigeAvatar = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeAvatar);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static Avatar getAvatarById(int id) {
        Avatar resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from avatars where avatar_id = ?", new Object[]{id});
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

    public static int voegAvatarToe(Avatar nieuweAvatar) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO avatars ( avatar_name, avatar_img ) VALUES (?,?)", new Object[]{nieuweAvatar.getAvatar_name(), nieuweAvatar.getAvatar_img()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updataAvatar(Avatar nieuweAvatar) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE avatars SET avatar_name = ?, avatar_img = ? WHERE avatar_id = ?", new Object[]{nieuweAvatar.getAvatar_name(), nieuweAvatar.getAvatar_img(), nieuweAvatar.getAvatar_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderAvatar(int avatarId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM avatars WHERE avatar_id = ?", new Object[]{avatarId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static Avatar converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new Avatar(mijnResultset.getInt("avatar_id"), mijnResultset.getString("avatar_name"),  mijnResultset.getString("avatar_img"));
    }

}
