package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Question;

public class QuestionDao {

    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> result = new ArrayList<Question>();
        try {
            ResultSet results = Database.voerSqlUitEnHaalResultaatOp("SELECT * from questions");
            if (results != null) {
                while (results.next()) {
                    Question current = converteerHuidigeRijNaarObject(results);
                    result.add(current);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return result;
    }

    public static Question getQuestionById(int id) {
        Question result = null;
        try {
            ResultSet results = Database.voerSqlUitEnHaalResultaatOp("SELECT * from questions where question_id = ?", new Object[]{id});
            if (results != null) {
                results.first();
                result = converteerHuidigeRijNaarObject(results);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }

        return result;
    }

    public static int addQuestion(Question newQ) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("INSERT INTO questions ( question, optiona, optionb, optionc, correctanswer) VALUES (?,?,?,?,?)", new Object[]{newQ.getQuestion(), newQ.getOptionA(), newQ.getOptionB(), newQ.getOptionC(), newQ.getAnswer()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int updateQuestion(Question newQ) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("UPDATE questions SET question = ?, optiona = ?, optionb = ?, optionc = ?, correctanswer = ? WHERE question_id = ?", new Object[]{newQ.getQuestion(), newQ.getOptionA(), newQ.getOptionB(), newQ.getOptionC(), newQ.getAnswer(), newQ.getQuestion_id()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    public static int verwijderQuestion(int id) {
        int aantalAangepasteRijen = 0;
        try {
            aantalAangepasteRijen = Database.voerSqlUitEnHaalAantalAangepasteRijenOp("DELETE FROM questions WHERE question_id = ?", new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return aantalAangepasteRijen;
    }

    private static Question converteerHuidigeRijNaarObject(ResultSet mijnResultset) throws SQLException {
        return new Question(mijnResultset.getInt("question_id"), mijnResultset.getString("question"),mijnResultset.getString("optiona"), mijnResultset.getString("optionb"), mijnResultset.getString("optionc"), mijnResultset.getString("correctanswer"));
    }

}

