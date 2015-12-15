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
public class Score {
    public int score_id, game_id, user_id, score_value;
    public java.sql.Date score_date;

    public Score(int score_id, int game_id, int user_id, int score_value, Date score_date) {
        this.score_id = score_id;
        this.game_id = game_id;
        this.user_id = user_id;
        this.score_value = score_value;
        this.score_date = score_date;
    }

    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getScore_value() {
        return score_value;
    }

    public void setScore_value(int score_value) {
        this.score_value = score_value;
    }

    public Date getScore_date() {
        return score_date;
    }

    public void setScore_date(Date score_date) {
        this.score_date = score_date;
    }
    
    
    
}
