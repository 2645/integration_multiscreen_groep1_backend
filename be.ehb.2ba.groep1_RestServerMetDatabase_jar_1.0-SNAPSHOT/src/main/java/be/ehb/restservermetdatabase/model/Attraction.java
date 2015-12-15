/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ehb.restservermetdatabase.model;

/**
 *
 * @author janhd
 */
public class Attraction {
  public int attraction_id, attraction_queuetime;
  public String attraction_name, attraction_description, attraction_img;
  public float attraction_lat, attraction_long;

    public Attraction(int attraction_id, int attraction_queuetime, String attraction_name, String attraction_description, String attraction_img, float attraction_lat, float attraction_long) {
        this.attraction_id = attraction_id;
        this.attraction_queuetime = attraction_queuetime;
        this.attraction_name = attraction_name;
        this.attraction_description = attraction_description;
        this.attraction_img = attraction_img;
        this.attraction_lat = attraction_lat;
        this.attraction_long = attraction_long;
    }

    public int getAttraction_id() {
        return attraction_id;
    }

    public void setAttraction_id(int attraction_id) {
        this.attraction_id = attraction_id;
    }

    public int getAttraction_queuetime() {
        return attraction_queuetime;
    }

    public void setAttraction_queuetime(int attraction_queuetime) {
        this.attraction_queuetime = attraction_queuetime;
    }

    public String getAttraction_name() {
        return attraction_name;
    }

    public void setAttraction_name(String attraction_name) {
        this.attraction_name = attraction_name;
    }

    public String getAttraction_description() {
        return attraction_description;
    }

    public void setAttraction_description(String attraction_description) {
        this.attraction_description = attraction_description;
    }

    public String getAttraction_img() {
        return attraction_img;
    }

    public void setAttraction_img(String attraction_img) {
        this.attraction_img = attraction_img;
    }

    public float getAttraction_lat() {
        return attraction_lat;
    }

    public void setAttraction_lat(float attraction_lat) {
        this.attraction_lat = attraction_lat;
    }

    public float getAttraction_long() {
        return attraction_long;
    }

    public void setAttraction_long(float attraction_long) {
        this.attraction_long = attraction_long;
    }
  
}
