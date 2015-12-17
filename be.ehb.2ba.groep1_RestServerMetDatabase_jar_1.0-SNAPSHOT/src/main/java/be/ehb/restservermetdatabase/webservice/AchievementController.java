/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ehb.restservermetdatabase.webservice;

import be.ehb.restservermetdatabase.dao.AchievementDao;
import be.ehb.restservermetdatabase.dao.UserAchievementDao;
import be.ehb.restservermetdatabase.model.Achievement;
import be.ehb.restservermetdatabase.model.UsersAchievement;
import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @author Jan
 */

@RestController
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
            return AchievementDao.getAchievements();
            
        } else {
            return UserAchievementDao.getPersonalAchievements(user_id);
        }
    }
    
    @RequestMapping(value = "/lookup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Achievement lookup(@RequestParam(value = "achievement_id", defaultValue = "0") int achievement_id) {
        // http://localhost:8080/achievements/lookup?achievement_id=1
        return AchievementDao.getAchievementById(achievement_id);
    }
    
    @RequestMapping(value = "/trigger", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void trigger(@RequestParam(value = "user_id", defaultValue = "0") int user_id, @RequestParam(value = "achievement_id", defaultValue = "0") int achievement_id) {
        // http://localhost:8080/achievements/trigger?user_id=1&achievement_id=1
        UserAchievementDao.voegUsersAchievementtoe(new UsersAchievement(user_id,achievement_id));
    }
}
