/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ehb.restservermetdatabase.webservice;

import be.ehb.restservermetdatabase.dao.AvatarDao;
import be.ehb.restservermetdatabase.dao.UserDao;
import be.ehb.restservermetdatabase.model.Avatar;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @author Dieter
 */
@RestController
@CrossOrigin
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
            Avatar a = AvatarDao.getAvatarById(avatar_id);
            a.setImg(getImg(a.getImg()));
            return a;
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Avatar> list(@RequestParam(value = "user_id", defaultValue = "0") int user_id, @RequestParam(value = "user_mail", defaultValue = "") String user_mail) {
        if (user_id == 0 && user_mail.equals("")) {
            ArrayList<Avatar> avatars = AvatarDao.getAvatars();
            for (int i = 0; i < avatars.size(); i++) {
                avatars.get(i).setImg(getImg(avatars.get(i).getImg()));
            }
            return avatars;
        } else {
            if (!user_mail.equals("")) {
                user_id = UserDao.getUserByEmail(user_mail).getId();
            }
            ArrayList<Avatar> avatars = AvatarDao.getAvatarsByUser(user_id);
            for (int i = 0; i < avatars.size(); i++) {
                avatars.get(i).setImg(getImg(avatars.get(i).getImg()));
            }
            return avatars;
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int create(@RequestBody Avatar a) {
        a.setImg(setImg(a.getImg(), a.getName()));
        AvatarDao.addAvatar(a);
        return AvatarDao.getAvatarByName(a.getName()).getId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Avatar update(@RequestBody Avatar a) {
        a.setImg(setImg(a.getImg(), a.getName()));
        AvatarDao.updateAvatar(a);
        return AvatarDao.getAvatarByName(a.getName());
    }

    public String getImg(String url) {
        BufferedImage img = null;
        File f = null;
        try {
            f = new File(url);
        } catch (NullPointerException e) {
            f = new File("unnamed.png");
        }
        try {
            img = ImageIO.read(f);
        } catch (IOException e) {
        }
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "png", bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = new sun.misc.BASE64Encoder().encode(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;    
    }

    public String setImg(String img, String name) {
        try {
            byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(img.split(",")[1]);
            File of = new File("avatar_" + name + ".png");
            FileOutputStream osf = new FileOutputStream(of);
            osf.write(btDataFile);
            osf.flush();
            osf.close();
            return of.getPath();
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
