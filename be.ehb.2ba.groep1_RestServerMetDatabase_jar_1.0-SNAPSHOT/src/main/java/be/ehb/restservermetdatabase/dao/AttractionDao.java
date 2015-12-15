package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Attraction;

public class AttractionDao {

    public static ArrayList<Attraction> getAttractions() {
        ArrayList<Attraction> resultaat = new ArrayList<Attraction>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from attractions");
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    Attraction huidigeAttraction = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeAttraction);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static Attraction getAttractionById(int id) {
        Attraction resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from attractions where attraction_id = ?", new Object[]{id});
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

    public static int voegAttractionToe(Attraction nieuweAttraction) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO attractions ( attraction_name, attraction_description, attraction_img, attraction_queuetime, attraction_lat, attraction_long ) VALUES (?,?,?,?,?,?)", new Object[]{nieuweAttraction.getAttraction_name(), nieuweAttraction.getAttraction_description(), nieuweAttraction.getAttraction_img(), nieuweAttraction.getAttraction_queuetime(), nieuweAttraction.getAttraction_lat(), nieuweAttraction.getAttraction_long()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updateAttraction(Attraction nieuweAttraction) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE attractions SET attraction_name = ?, attraction_description = ?, attraction_img = ?, attraction_queuetime = ?, attraction_lat = ?, attraction_long = ? WHERE attraction_id = ?", new Object[]{nieuweAttraction.getAttraction_name(), nieuweAttraction.getAttraction_description(), nieuweAttraction.getAttraction_img(), nieuweAttraction.getAttraction_queuetime(), nieuweAttraction.getAttraction_lat(), nieuweAttraction.getAttraction_long(), nieuweAttraction.getAttraction_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderAttraction(int attractionId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM attractions WHERE attraction_id = ?", new Object[]{attractionId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static Attraction converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new Attraction(mijnResultset.getInt("attraction_id"), mijnResultset.getInt("attraction_queuetime"), mijnResultset.getString("attraction_name"), mijnResultset.getString("attraction_description"), mijnResultset.getString("attraction_img"), mijnResultset.getFloat("attraction_lat"), mijnResultset.getFloat("attraction_long"));
    }

}
