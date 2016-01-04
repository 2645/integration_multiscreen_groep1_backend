/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ehb.restservermetdatabase.webservice;

import be.ehb.restservermetdatabase.dao.AchievementDao;
import be.ehb.restservermetdatabase.dao.GameDao;
import be.ehb.restservermetdatabase.dao.UserAchievementDao;
import be.ehb.restservermetdatabase.model.Achievement;
import be.ehb.restservermetdatabase.model.Game;
import be.ehb.restservermetdatabase.model.UserAchievement;
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
 * @author Jan
 */
@RestController
@CrossOrigin
@RequestMapping("/achievements")
public class AchievementController {

    @ControllerAdvice
    static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

        public JsonpAdvice() {
            super("callback");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Achievement> getAchievements(@RequestParam(value = "user_id", defaultValue = "0") int user_id) {
        // http://localhost:8080/achievements/list?user_id=1
        // http://localhost:8080/achievements/list
        if (user_id == 0) {
            ArrayList<Achievement> achievements = AchievementDao.getAchievements();
            for (int i = 0; i < achievements.size(); i++) {
                achievements.get(i).setIcon(getImg(achievements.get(i).getIcon()));
            }
            return achievements;

        } else {
            ArrayList<Achievement> achievements = UserAchievementDao.getPersonalAchievements(user_id);
            for (int i = 0; i < achievements.size(); i++) {
                achievements.get(i).setIcon(getImg(achievements.get(i).getIcon()));
            }
            return achievements;
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(
            @RequestBody Achievement a
    ) {
        a.setIcon(setImg(a.getIcon(), a.getName()));
        AchievementDao.addAchievement(a);
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Achievement lookup(@RequestParam(value = "achievement_id", defaultValue = "0") int achievement_id) {
        // http://localhost:8080/achievements/lookup?achievement_id=1
        Achievement a = AchievementDao.getAchievementById(achievement_id);
        a.setIcon(getImg(a.getIcon()));
        return a;
    }

    @RequestMapping(value = "/trigger", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void trigger(@RequestParam(value = "user_id", defaultValue = "0") int user_id, @RequestParam(value = "achievement_id", defaultValue = "0") int achievement_id) {
        // http://localhost:8080/achievements/trigger?user_id=1&achievement_id=1
        UserAchievementDao.AddUserAchievement(new UserAchievement(user_id, achievement_id));
    }

    public String getImg(String url) {
        BufferedImage img = null;
        File f = new File(url);
        if (f == null) {
            f = new File("unnamed.png");
        }
        try {
            img = ImageIO.read(new File(url));
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
            File of = new File("achievement_" + name + ".png");
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
