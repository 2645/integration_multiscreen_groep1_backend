package be.ehb.restservermetdatabase.webservice;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ehb.restservermetdatabase.dao.AuteurDao;
import be.ehb.restservermetdatabase.model.Auteur;

@RestController
@RequestMapping("/Auteur")
public class AuteurController {
	
	//OPMERKING: meestal wordt er niet zomaar doorverbonden naar methodes van de DAO
	//Meestal wordt hier nog extra code rondgeschreven.
	//Er moet ook aan security gedacht worden, een gebruiker zomaar toelaten alle gegevens uit de database te wissen is geen goed idee
	
	@RequestMapping("/getAll")
	public ArrayList<Auteur> getAll() {
		// Aanroepen met
		// http://localhost:8080/Auteur/getAll
		return AuteurDao.getAuteurs();
	}

	@RequestMapping("/getById")
	public Auteur getById(@RequestParam(value = "auteurId", defaultValue = "1") int auteurId) {
		// Aanroepen met
		// http://localhost:8080/Auteur/getById?auteurId=2

		return AuteurDao.getAuteurById(auteurId);
	}
	
	@RequestMapping(value = "/voegToe", method = RequestMethod.POST)
	public int voegToe(@RequestBody Auteur nieuweAuteur) {
		// Aanroepen met
		// http://localhost:8080/Auteur/voegToe
		// Geef parameter mee in de body: {"naam":"Smartphone","prijs":299.99}
		// Content type van de POST request is application/json
		// Default constructor nodig bij Auteur-klasse voor automatische omzetting van JSON naar objecten
		
		
		return AuteurDao.voegAuteurToe(nieuweAuteur);
	}
	
	
	

}
