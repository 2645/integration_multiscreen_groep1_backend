package be.ehb.restservermetdatabase.webservice;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ehb.restservermetdatabase.dao.BarcodeDao;
import be.ehb.restservermetdatabase.model.Barcode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@RestController
@RequestMapping("/barcode")
public class BarcodeController {
    
    @ControllerAdvice
    static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
        public JsonpAdvice() {
            super("callback");
        }
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Barcode getById(@RequestParam(value = "barcode_id", defaultValue = "0") int barcode_id) {
        // Aanroepen met
        // http://localhost:8080/barcode/lookup?bacode_id=2
        if (barcode_id != 0) {
            return BarcodeDao.getBarcodeById(barcode_id);
        }
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Barcode b) {
        // Aanroepen met
        // http://localhost:8080/barcode/lookup?bacode_id=2
        BarcodeDao.voegBarcodeToe(b);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Barcode b) {
        BarcodeDao.updateBarcode(b);
    }

}
