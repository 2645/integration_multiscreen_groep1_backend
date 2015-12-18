package be.ehb.restservermetdatabase.webservice;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ehb.restservermetdatabase.dao.AttractionDao;
import be.ehb.restservermetdatabase.model.Attraction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@RestController
@CrossOrigin
@RequestMapping("/attractions")
public class AttractionController {
    
    @ControllerAdvice
    static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
        public JsonpAdvice() {
            super("callback");
        }
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Attraction> getAttractions() {
        // Aanroepen met
        // http://localhost:8080/attractions/list
        return AttractionDao.getAttractions();
    }
    
    @RequestMapping(value = "/lookup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Attraction getAttractionById(@RequestParam(value = "attraction_id", defaultValue = "0") int attraction_id){
        // http://localhost:8080/attractions/lookup?attraction_id=5
        return AttractionDao.getAttractionById(attraction_id);
    }
    
    @RequestMapping(value= "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int createAttraction(@RequestBody Attraction nieuweAttraction){
        AttractionDao.addAttraction(nieuweAttraction);
        return AttractionDao.getAttractionByName(nieuweAttraction.getName()).getId();
    }
    
    @RequestMapping(value="/update", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Attraction update (@RequestBody Attraction nieuweAttraction){
        AttractionDao.updateAttraction(nieuweAttraction);
        return AttractionDao.getAttractionByName(nieuweAttraction.getName());
    }
    
    @RequestMapping (value="/queue", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Attraction> queuetime(@RequestParam(value="attraction_id", defaultValue="0") int attraction_id){
        // http://localhost:8080/attractions/queue?attraction_id=5        
        ArrayList<Attraction> result = new ArrayList<Attraction>();
        if(attraction_id > 0){
            result.add(AttractionDao.getAttractionById(attraction_id));
        } else {
            result =  AttractionDao.getAttractions();
        }
        return result;
    } 
    
    @RequestMapping (value="/updatequeue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateQueuetime (@RequestParam(value="attraction_id", defaultValue="0") int attraction_id, @RequestParam(value="attraction_queuetime") int attraction_queuetime ){
         // http://localhost:8080/attractions/updatequeue?attraction_id=5&attraction_queuetime=500
        AttractionDao.updateQueueTime(attraction_id, attraction_queuetime);        
    }
}
