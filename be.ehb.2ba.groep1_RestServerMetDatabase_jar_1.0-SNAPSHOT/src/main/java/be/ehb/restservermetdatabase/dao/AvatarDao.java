package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Avatar;

public class AvatarDao {

    public static ArrayList<Avatar> getAvatars() {
        ArrayList<Avatar> resultaat = new ArrayList<>();
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
    
    public static ArrayList<Avatar> getAvatarsByUser(int user_id) {
        ArrayList<Avatar> resultaat = new ArrayList<>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from usersavatars WHERE user_id = ?", new Object[]{user_id});
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    Avatar huidigeAvatar = AvatarDao.getAvatarById(mijnResultset.getInt("avatar_id"));
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

    public static Avatar getAvatarByName(String name) {
        Avatar resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from avatars where avatar_name = ?", new Object[]{name});
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
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO avatars ( avatar_name, avatar_img, avatar_price ) VALUES (?,?,?)", new Object[]{nieuweAvatar.getAvatar_name(), nieuweAvatar.getAvatar_img(), nieuweAvatar.getAvatar_price()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updateAvatar(Avatar nieuweAvatar) {
        int aantalAangepasteRijen = 0;
        int avatar_id = nieuweAvatar.getAvatar_id();
        
        if(avatar_id == 0) {
            return 0;
            
        } else {
            if(nieuweAvatar.getAvatar_img().equals("")) {
                nieuweAvatar.setAvatar_img(AvatarDao.getAvatarById(avatar_id).getAvatar_img());
            }
            
            if(nieuweAvatar.getAvatar_name().equals("")) {
                nieuweAvatar.setAvatar_name(AvatarDao.getAvatarById(avatar_id).getAvatar_name());
            }
            
            if(nieuweAvatar.getAvatar_price() == 0) {
                nieuweAvatar.setAvatar_price(AvatarDao.getAvatarById(avatar_id).getAvatar_price());
            }
        }
        
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE avatars SET avatar_name = ?, avatar_img = ?, avatar_price = ? WHERE avatar_id = ?", new Object[]{nieuweAvatar.getAvatar_name(), nieuweAvatar.getAvatar_img(), nieuweAvatar.getAvatar_price(), avatar_id});
        
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
        return new Avatar(mijnResultset.getInt("avatar_id"), mijnResultset.getString("avatar_name"),  mijnResultset.getString("avatar_img"),  mijnResultset.getInt("avatar_price"));
    }

}
