/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ehb.restservermetdatabase.webservice;

import be.ehb.restservermetdatabase.dao.AvatarDao;
import be.ehb.restservermetdatabase.dao.UserDao;
import be.ehb.restservermetdatabase.model.Avatar;
import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @author Dieter
 */

@RestController
@RequestMapping("/avatars")

public class AvatarController {
    
    @ControllerAdvice
    static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
        public JsonpAdvice() {
            super("callback");
        }
    }
    
    @RequestMapping(value = "/lookup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Avatar lookup(@RequestParam(value = "avatar_id", defaultValue = "0") int avatar_id) {
        if (avatar_id == 0) {
            return null;            
        } else {
            return AvatarDao.getAvatarById(avatar_id);
        }
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Avatar> list(@RequestParam(value = "user_id", defaultValue = "0") int user_id, @RequestParam(value = "user_mail", defaultValue = "") String user_mail) {
        if (user_id == 0 && user_mail.equals("")) {
            return AvatarDao.getAvatars();            
        } else {
            if(!user_mail.equals("")) {
                user_id = UserDao.getUserByEmail(user_mail).getId();
            }
            return AvatarDao.getAvatarsByUser(user_id);
        }
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public int create(
            @RequestParam(value = "name", defaultValue = "") String name, 
            @RequestParam(value = "price", defaultValue = "0") int price, 
            @RequestParam(value = "img", defaultValue = "") String img
    ) {        
        AvatarDao.addAvatar(new Avatar(0, price, name, img));
        return AvatarDao.getAvatarByName(name).getId();
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public int update(
            @RequestParam(value = "id", defaultValue = "0") int id, 
            @RequestParam(value = "name", defaultValue = "") String name, 
            @RequestParam(value = "price", defaultValue = "0") int price, 
            @RequestParam(value = "img", defaultValue = "") String img
    ) {        
        AvatarDao.updateAvatar(new Avatar(id,price, name, img));
        return AvatarDao.getAvatarByName(name).getId();
    }
}
