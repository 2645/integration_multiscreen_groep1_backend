package be.ehb.restservermetdatabase.webservice;

import be.ehb.restservermetdatabase.dao.QuestionDao;
import be.ehb.restservermetdatabase.model.Question;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {
    
    @ControllerAdvice
    static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
        public JsonpAdvice() {
            super("callback");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Question> getById(@RequestParam(value = "question_id", defaultValue = "0") int question_id) {
        // Aanroepen met
        // http://localhost:8080/question/list?question_id=2
        ArrayList<Question> questions = new ArrayList<Question>();
        if (question_id != 0) {
            questions.add(QuestionDao.getQuestionById(question_id));
        }else{
            questions = QuestionDao.getQuestions();
        }
        return questions;
    }
}
