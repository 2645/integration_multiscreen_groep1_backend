package be.ehb.restservermetdatabase.webservice;

import be.ehb.restservermetdatabase.dao.AchievementDao;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ehb.restservermetdatabase.dao.AttractionDao;
import be.ehb.restservermetdatabase.model.Attraction;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
        ArrayList<Attraction> attractions = AttractionDao.getAttractions();
        for (int i = 0; i < attractions.size(); i++) {
            attractions.get(i).setImg(getImg(attractions.get(i).getImg()));
        }
        return attractions;
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Attraction getAttractionById(@RequestParam(value = "attraction_id", defaultValue = "0") int attraction_id) {
        // http://localhost:8080/attractions/lookup?attraction_id=5
        Attraction a = AttractionDao.getAttractionById(attraction_id);
        a.setImg(getImg(a.getImg()));
        return a;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int create(@RequestBody Attraction a) {
        a.setImg(setImg(a.getImg(), a.getName()));
        AttractionDao.addAttraction(a);
        return AttractionDao.getAttractionByName(a.getName()).getId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Attraction update(@RequestBody Attraction a) {
        a.setImg(setImg(a.getImg(), a.getName()));
        AttractionDao.updateAttraction(a);
        return AttractionDao.getAttractionByName(a.getName());
    }

    @RequestMapping(value = "/queue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Attraction> queuetime(@RequestParam(value = "attraction_id", defaultValue = "0") int attraction_id) {
        // http://localhost:8080/attractions/queue?attraction_id=5        
        ArrayList<Attraction> result = new ArrayList<Attraction>();
        if (attraction_id > 0) {
            Attraction a = AttractionDao.getAttractionById(attraction_id);
            a.setImg(setImg(a.getImg(), a.getName()));
            result.add(a);
        } else {
            result = AttractionDao.getAttractions();
            for (int i = 0; i < result.size(); i++) {
                result.get(i).setImg(getImg(result.get(i).getImg()));
            }
        }
        return result;
    }

    @RequestMapping(value = "/updatequeue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateQueuetime(@RequestParam(value = "attraction_id", defaultValue = "0") int attraction_id, @RequestParam(value = "attraction_queuetime") int attraction_queuetime) {
        // http://localhost:8080/attractions/updatequeue?attraction_id=5&attraction_queuetime=500
        AttractionDao.updateQueueTime(attraction_id, attraction_queuetime);
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
            File of = new File("attraction_" + name + ".png");
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
