/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ehb.restservermetdatabase.webservice;

import be.ehb.restservermetdatabase.dao.GameDao;
import be.ehb.restservermetdatabase.model.Game;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
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
@RequestMapping("/games")

public class GameController {

    @ControllerAdvice
    static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

        public JsonpAdvice() {
            super("callback");
        }
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game lookup(@RequestParam(value = "game_id", defaultValue = "0") int game_id) {
        if (game_id == 0) {
            return null;
        } else {
            return GameDao.getGameById(game_id);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Game> list() {
        return GameDao.getGames();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int create(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "description", defaultValue = "0") String description,
            @RequestParam(value = "icon", defaultValue = "") String icon
    ) {
        String url = setimg(icon, "game_" + name);
        GameDao.addGame(new Game(0, name, description, url));
        return GameDao.getGameByName(name).getId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int update(
            @RequestParam(value = "id", defaultValue = "0") int id,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "description", defaultValue = "0") String description,
            @RequestParam(value = "icon", defaultValue = "") String icon
    ) {
        String url = setimg(icon, "game_" + name);
        GameDao.updateGame(new Game(id, name, description, url));
        return GameDao.getGameByName(name).getId();
    }

    public String setimg(String img, String name) {
        try {
            byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(img.split(",")[1]);
            File of = new File("game_" + name + ".png");
            FileOutputStream osf = new FileOutputStream(of);
            osf.write(btDataFile);
            osf.flush();
            osf.close();
            return of.getAbsolutePath();
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
