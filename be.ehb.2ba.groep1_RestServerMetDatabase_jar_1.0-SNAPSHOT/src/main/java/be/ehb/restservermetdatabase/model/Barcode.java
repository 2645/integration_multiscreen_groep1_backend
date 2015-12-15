/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ehb.restservermetdatabase.model;

import java.sql.Date;

/**
 *
 * @author janhd
 */
public class Barcode {
    public int barcode_id, user_id;
    public String barcode_rawdata;
    public java.sql.Date barcode_date;

    public Barcode(int barcode_id, int user_id, String barcode_rawdata, Date barcode_date) {
        this.barcode_id = barcode_id;
        this.user_id = user_id;
        this.barcode_rawdata = barcode_rawdata;
        this.barcode_date = barcode_date;
    }

    public int getBarcode_id() {
        return barcode_id;
    }

    public void setBarcode_id(int barcode_id) {
        this.barcode_id = barcode_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBarcode_rawdata() {
        return barcode_rawdata;
    }

    public void setBarcode_rawdata(String barcode_rawdata) {
        this.barcode_rawdata = barcode_rawdata;
    }

    public Date getBarcode_date() {
        return barcode_date;
    }

    public void setBarcode_date(Date barcode_date) {
        this.barcode_date = barcode_date;
    }
    
}
