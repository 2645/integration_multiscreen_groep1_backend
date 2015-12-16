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
public class Friendship {
    public int friendship_id, from_id, to_id;

    public Friendship(int friend_id, int from_id, int to_id) {
        this.friendship_id = friend_id;
        this.from_id = from_id;
        this.to_id = to_id;
    }
    
    public Friendship(int from_id, int to_id) {
        this.friendship_id = 0;
        this.from_id = from_id;
        this.to_id = to_id;
    }

    public int getFriendship_id() {
        return friendship_id;
    }

    public void setFriendship_id(int friendship_id) {
        this.friendship_id = friendship_id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }
}
