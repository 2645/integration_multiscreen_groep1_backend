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
    private int barcodeId, userId;
    private String rawdata;
    public java.sql.Date date;
    
    public Barcode(){
        
    }

    public Barcode(int barcodeId, int userId, String rawdata, Date date) {
        this.barcodeId = barcodeId;
        this.userId = userId;
        this.rawdata = rawdata;
        this.date = date;
    }

    public int getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(int barcodeId) {
        this.barcodeId = barcodeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRawdata() {
        return rawdata;
    }

    public void setRawdata(String rawdata) {
        this.rawdata = rawdata;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
