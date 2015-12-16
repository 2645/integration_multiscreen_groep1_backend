package be.ehb.restservermetdatabase.webservice;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ehb.restservermetdatabase.dao.BarcodeDao;
import be.ehb.restservermetdatabase.model.Barcode;

@RestController
@RequestMapping("/barcode")
public class BarcodeController {

    @RequestMapping(value = "/lookup", method = RequestMethod.GET)
    public Barcode getById(@RequestParam(value = "barcode_id", defaultValue = "0") int barcode_id) {
        // Aanroepen met
        // http://localhost:8080/barcode/lookup?bacode_id=2
        if (barcode_id != 0) {
            return BarcodeDao.getBarcodeById(barcode_id);
        }
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Barcode b) {
        // Aanroepen met
        // http://localhost:8080/barcode/lookup?bacode_id=2
        BarcodeDao.voegBarcodeToe(b);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody Barcode b) {
        BarcodeDao.updateBarcode(b);
    }

}
