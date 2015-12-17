package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Barcode;

public class BarcodeDao {

    public static ArrayList<Barcode> getBarcode() {
        ArrayList<Barcode> result = new ArrayList<Barcode>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from barcodes");
            if (results != null) {
                while (results.next()) {
                    Barcode current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static Barcode getBarcodeById(int id) {
        Barcode result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from barcodes where barcode_id = ?", new Object[]{id});
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

    public static int addBarcode(Barcode b) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO barcodes ( barcode_rawdata, user_id, barcode_date ) VALUES (?,?,?)", new Object[]{b.getRawdata(), b.getUserId(), b.getDate()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int updateBarcode(Barcode b) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("UPDATE barcodes SET barcode_rawdata = ?, user_id = ?, barcode_date = ? where barcode_id = ?", new Object[]{b.getRawdata(), b.getUserId(), b.getDate(), b.getBarcodeId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int deleteBarcode(int id) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM barcodes WHERE barcode_id = ?", new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    private static Barcode convertRowToObject(ResultSet row) throws SQLException {
        return new Barcode(row.getInt("barcode_id"), row.getInt("user_id"), row.getString("barcode_rawdata"), row.getDate("barcode_date"));
    }

}
