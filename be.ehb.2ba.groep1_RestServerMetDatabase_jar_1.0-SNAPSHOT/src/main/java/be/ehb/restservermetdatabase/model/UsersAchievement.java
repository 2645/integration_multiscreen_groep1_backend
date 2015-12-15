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
public class UsersAchievement {
    public int user_id, achievement_id;

    public UsersAchievement(int user_id, int achievement_id) {
        this.user_id = user_id;
        this.achievement_id = achievement_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAchievement_id() {
        return achievement_id;
    }

    public void setAchievement_id(int achievement_id) {
        this.achievement_id = achievement_id;
    }
}
