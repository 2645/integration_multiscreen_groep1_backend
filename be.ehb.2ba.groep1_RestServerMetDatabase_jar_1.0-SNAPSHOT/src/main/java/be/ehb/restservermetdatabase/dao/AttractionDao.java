package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Attraction;

public class AttractionDao {

    public static ArrayList<Attraction> getAttractions() {
        ArrayList<Attraction> result = new ArrayList<Attraction>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from attractions");
            if (results != null) {
                while (results.next()) {
                    Attraction current = convertRowToObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static Attraction getAttractionById(int id) {
        Attraction result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from attractions where attraction_id = ?", new Object[]{id});
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

    public static int addAttraction(Attraction a) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO attractions ( attraction_name, attraction_description, attraction_img, attraction_queuetime, attraction_lat, attraction_long ) VALUES (?,?,?,?,?,?)", new Object[]{a.getName(),a.getDescription(), a.getImg(), a.getQueuetime(), a.getLat(), a.getLon()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }
    
    public static Attraction getAttractionByName(String name){
    Attraction result = null;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from attractions where attraction_name = ?", new Object[]{name});
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

    public static int updateAttraction(Attraction a) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("UPDATE attractions SET attraction_name = ?, attraction_description = ?, attraction_img = ?, attraction_queuetime = ?, attraction_lat = ?, attraction_long = ? WHERE attraction_id = ?", new Object[]{a.getName(),a.getDescription(), a.getImg(), a.getQueuetime(), a.getLat(), a.getLon(), a.getId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int verwijderAttraction(int id) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM attractions WHERE attraction_id = ?", new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }
    
    public static int queuetimeById(int id){
        int result = 0;
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT attraction_queuetime from attractions where attraction_id = ?", new Object[]{id});
            if (results != null) {
                results.first();
                result = results.getInt("attraction_queuetime");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }
    
    public static void updateQueueTime(int id, int time){
        Attraction a = AttractionDao.getAttractionById(id);
        a.setQueuetime(time);
        AttractionDao.updateAttraction(a);
    }
    
    public static ArrayList<Integer> queuetime(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT attraction_queuetime from attractions");
            if (results != null) {
                while (results.next()) {                    
                    result.add(results.getInt("attraction_queuetime"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    private static Attraction convertRowToObject(ResultSet row) throws SQLException {
        return new Attraction(row.getInt("attraction_id"), row.getInt("attraction_queuetime"), row.getString("attraction_name"), row.getString("attraction_description"), row.getString("attraction_img"), row.getFloat("attraction_lat"), row.getFloat("attraction_long"));
    }

}
