/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ehb.restservermetdatabase.webservice;

import be.ehb.restservermetdatabase.dao.FriendshipDao;
import be.ehb.restservermetdatabase.dao.UserDao;
import be.ehb.restservermetdatabase.model.Friendship;
import be.ehb.restservermetdatabase.model.User;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dieter
 */

@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    @RequestMapping(value = "/lookup", method = RequestMethod.GET)
    public ArrayList<User> lookup(@RequestParam(value = "user_id", defaultValue = "0") int user_id, @RequestParam(value = "email", defaultValue = "") String user_mail) {

        if (user_id == 0) {
            if(user_mail.equals("")) {
                return null;
                
            } else {
                user_id = UserDao.getUserByEmail(user_mail).getUser_id();
            }
        }
        
        return FriendshipDao.getFriends(user_id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public int create(
            @RequestParam(value = "from_id", defaultValue = "0") int from_id, 
            @RequestParam(value = "from_email", defaultValue = "") String from_mail,
            @RequestParam(value = "to_id", defaultValue = "0") int to_id, 
            @RequestParam(value = "to_email", defaultValue = "") String to_mail
    ) {

        if (from_id == 0) {
            if(from_mail.equals("")) {
                return 0;
                
            } else {
                from_id = UserDao.getUserByEmail(from_mail).getUser_id();
            }
        }
        
        if (to_id == 0) {
            if(to_mail.equals("")) {
                return 0;
                
            } else {
                to_id = UserDao.getUserByEmail(to_mail).getUser_id();
            }
        }
        
        return FriendshipDao.voegFriendshipToe(new Friendship(from_id, to_id));
    }

    @RequestMapping(value = "/destroy", method = RequestMethod.POST)
    public int destroy(
            @RequestParam(value = "from_id", defaultValue = "0") int from_id, 
            @RequestParam(value = "from_email", defaultValue = "") String from_mail,
            @RequestParam(value = "to_id", defaultValue = "0") int to_id, 
            @RequestParam(value = "to_email", defaultValue = "") String to_mail
    ) {

        if (from_id == 0) {
            if(from_mail.equals("")) {
                return 0;
                
            } else {
                from_id = UserDao.getUserByEmail(from_mail).getUser_id();
            }
        }
        
        if (to_id == 0) {
            if(to_mail.equals("")) {
                return 0;
                
            } else {
                to_id = UserDao.getUserByEmail(to_mail).getUser_id();
            }
        }
        
        return FriendshipDao.verwijderFriendship(from_id, to_id);
    }
}
