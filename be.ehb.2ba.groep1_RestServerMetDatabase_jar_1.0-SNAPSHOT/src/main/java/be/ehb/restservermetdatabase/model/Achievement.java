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
public class Achievement {
    public int achievement_id,  achievement_list_order;
    public String  achievement_google_id, achievement_name, achievement_description, achievement_icon;

    public Achievement(int achievement_id, int achievement_list_order, String achievement_google_id, String achievement_name, String achievement_description, String achievement_icon) {
        this.achievement_id = achievement_id;
        this.achievement_list_order = achievement_list_order;
        this.achievement_google_id = achievement_google_id;
        this.achievement_name = achievement_name;
        this.achievement_description = achievement_description;
        this.achievement_icon = achievement_icon;
    }

    public int getAchievement_id() {
        return achievement_id;
    }

    public void setAchievement_id(int achievement_id) {
        this.achievement_id = achievement_id;
    }

    public int getAchievement_list_order() {
        return achievement_list_order;
    }

    public void setAchievement_list_order(int achievement_list_order) {
        this.achievement_list_order = achievement_list_order;
    }

    public String getAchievement_google_id() {
        return achievement_google_id;
    }

    public void setAchievement_google_id(String achievement_google_id) {
        this.achievement_google_id = achievement_google_id;
    }

    public String getAchievement_name() {
        return achievement_name;
    }

    public void setAchievement_name(String achievement_name) {
        this.achievement_name = achievement_name;
    }

    public String getAchievement_description() {
        return achievement_description;
    }

    public void setAchievement_description(String achievement_description) {
        this.achievement_description = achievement_description;
    }

    public String getAchievement_icon() {
        return achievement_icon;
    }

    public void setAchievement_icon(String achievement_icon) {
        this.achievement_icon = achievement_icon;
    }
}
