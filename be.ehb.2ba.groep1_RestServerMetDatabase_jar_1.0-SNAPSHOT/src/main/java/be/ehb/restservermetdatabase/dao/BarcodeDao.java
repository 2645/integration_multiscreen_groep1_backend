package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Barcode;

public class BarcodeDao {

    public static ArrayList<Barcode> getBarcode() {
        ArrayList<Barcode> resultaat = new ArrayList<Barcode>();
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from barcodes");
            if (mijnResultset != null) {
                while (mijnResultset.next()) {
                    Barcode huidigeScore = converteerHuidigeRijNaarObject(mijnResultset);
                    resultaat.add(huidigeScore);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return resultaat;
    }

    public static Barcode getBarcodeById(int id) {
        Barcode resultaat = null;
        try {
            ResultSet mijnResultset = Database.voerSqlUitEnHaalResultaatOp("SELECT * from barcodes where barcode_id = ?", new Object[]{id});
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

    public static int voegBarcodeToe(Barcode nieuweBarcode) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO barcodes ( barcode_rawdata, user_id, barcode_date ) VALUES (?,?,?)", new Object[]{nieuweBarcode.getBarcode_rawdata(), nieuweBarcode.getUser_id(), nieuweBarcode.getBarcode_date()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updateBarcode(Barcode nieuweBarcode) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE barcodes SET barcode_rawdata = ?, user_id = ?, barcode_date = ? where barcode_id = ?", new Object[]{nieuweBarcode.getBarcode_rawdata(), nieuweBarcode.getUser_id(), nieuweBarcode.getBarcode_date(), nieuweBarcode.getBarcode_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderScore(int barcodeId) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM barcodes WHERE barcode_id = ?", new Object[]{barcodeId});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static Barcode converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new Barcode(mijnResultset.getInt("barcode_id"), mijnResultset.getInt("user_id"), mijnResultset.getString("barcode_rawdata"), mijnResultset.getDate("barcode_date"));
    }

}
