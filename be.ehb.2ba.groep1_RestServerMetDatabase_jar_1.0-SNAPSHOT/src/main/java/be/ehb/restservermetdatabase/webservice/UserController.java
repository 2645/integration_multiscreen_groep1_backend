package be.ehb.restservermetdatabase.webservice;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ehb.restservermetdatabase.dao.UserDao;
import be.ehb.restservermetdatabase.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/lookup", method = RequestMethod.GET)
    public User getById(@RequestParam(value = "user_id", defaultValue = "0") int user_id, @RequestParam(value = "email", defaultValue = "") String user_mail) {
        // Aanroepen met
        // http://localhost:8080/users/lookup?user_id=2
        // http://localhost:8080/users/lookup?email=jan@gmail.com
        if (user_id != 0) {
            return UserDao.getUserById(user_id);
        } else if (!user_mail.equals("")) {
            return UserDao.getUserByEmail(user_mail);
        }
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public int voegToe(@RequestBody User nieuweUser) {
        // Aanroepen met
        // http://localhost:8080/users/create
        // Geef parameter mee in de body
        // Content type van de POST request is application/json
        // Default constructor nodig bij User-klasse voor automatische omzetting van JSON naar objecten		

        UserDao.voegUserToe(nieuweUser);
        return UserDao.getUserByEmail(nieuweUser.getUser_mail()).getUser_id();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ArrayList<User> search(@RequestParam(value = "firstname", defaultValue = "") String fname, @RequestParam(value = "lastname", defaultValue = "") String lname) {
        // Aanroepen met
        // http://localhost:8080/users/search?firstname=Dieter
        // http://localhost:8080/users/lookup?lastname=Holvoet
        // http://localhost:8080/users/lookup?firstname=Dieter&lastname=Holvoet

        ArrayList<User> user = null;
        if (fname.length() != 0 && lname.length() != 0) {
            user = UserDao.getUsersByFirstAndLastname(fname, lname);
        } else if (fname.length() != 0) {
            user = UserDao.getUsersByFirstname(fname);
        } else if (lname.length() != 0) {
            user = UserDao.getUsersByLastname(lname);
        }
        return user;
    }
    
    @RequestMapping(value="/update", method= RequestMethod.POST)
    public User update (@RequestBody User nieuweUser){
        UserDao.updateUser(nieuweUser);
        return UserDao.getUserByEmail(nieuweUser.getUser_mail());
    }

}
