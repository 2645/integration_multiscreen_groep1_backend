/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ehb.restservermetdatabase.webservice;

import be.ehb.restservermetdatabase.dao.GameDao;
import be.ehb.restservermetdatabase.model.Game;
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
            Game g = GameDao.getGameById(game_id);
            g.setIcon(getImg(g.getIcon()));
            return g;
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Game> list() {
        ArrayList<Game> games = GameDao.getGames();
        for (Game g : games) {
            g.setIcon(getImg(g.getIcon()));
        }
        return games;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int create(
            @RequestBody Game g
    ) {
        g.setIcon(setImg(g.getIcon(), g.getName()));
        GameDao.addGame(g);
        return GameDao.getGameByName(g.getName()).getId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int update(
            @RequestBody Game g
    ) {
        g.setIcon(setImg(g.getIcon(), g.getName()));
        GameDao.updateGame(g);
        return GameDao.getGameByName(g.getName()).getId();
    }

    public String setImg(String img, String name) {
        try {
            byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(img.split(",")[1]);
            File of = new File("game_" + name + ".png");
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
}
