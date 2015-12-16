package be.ehb.restservermetdatabase.webservice;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ehb.restservermetdatabase.dao.AttractionDao;
import be.ehb.restservermetdatabase.model.Attraction;

@RestController
@RequestMapping("/attractions")
public class AttractionController {

     @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ArrayList<Attraction> getAttractions() {
        // Aanroepen met
        // http://localhost:8080/users/lookup?user_id=2
        // http://localhost:8080/users/lookup?email=jan@gmail.com
        return AttractionDao.getAttractions();
    }
}
