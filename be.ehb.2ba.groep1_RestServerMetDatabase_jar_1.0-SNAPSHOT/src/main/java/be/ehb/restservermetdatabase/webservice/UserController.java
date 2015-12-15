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
	public User getById(@RequestParam(value = "user_id", defaultValue = "1") int user_id) {
		// Aanroepen met
		// http://localhost:8080/Auteur/getById?auteurId=2
		return UserDao.getUserById(user_id);
	}
	
	@RequestMapping(value = "/voegToe", method = RequestMethod.POST)
	public int voegToe(@RequestBody User nieuweAuteur) {
		// Aanroepen met
		// http://localhost:8080/Auteur/voegToe
		// Geef parameter mee in de body: {"naam":"Smartphone","prijs":299.99}
		// Content type van de POST request is application/json
		// Default constructor nodig bij Auteur-klasse voor automatische omzetting van JSON naar objecten
		
		
		return UserDao.voegUserToe(nieuweAuteur);
	}
	
	
	

}
