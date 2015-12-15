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
public class Game {
    public int game_id;
    public String game_name, game_description;

    public Game(int game_id, String game_name, String game_description) {
        this.game_id = game_id;
        this.game_name = game_name;
        this.game_description = game_description;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_description() {
        return game_description;
    }

    public void setGame_description(String game_description) {
        this.game_description = game_description;
    }
    
}
