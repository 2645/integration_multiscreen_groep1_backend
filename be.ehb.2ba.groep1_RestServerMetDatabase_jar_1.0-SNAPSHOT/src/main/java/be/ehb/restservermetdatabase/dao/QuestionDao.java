package be.ehb.restservermetdatabase.dao;

import java.sql.*;
import java.util.ArrayList;

import be.ehb.restservermetdatabase.model.Question;

public class QuestionDao {

    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> result = new ArrayList<Question>();
        try {
            ResultSet results = Database.execSqlAndReturn("SELECT * from questions");
            if (results != null) {
                while (results.next()) {
                    Question current = convertRowToObject(results);
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
            ResultSet results = Database.execSqlAndReturn("SELECT * from questions where question_id = ?", new Object[]{id});
            if (results != null) {
                results.first();
                result = convertRowToObject(results);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return result;
    }

    public static int addQuestion(Question q) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("INSERT INTO questions ( question, optiona, optionb, optionc, correctanswer) VALUES (?,?,?,?,?)", new Object[]{q.getQuestion(), q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getAnswer()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int updateQuestion(Question q) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("UPDATE questions SET question = ?, optiona = ?, optionb = ?, optionc = ?, correctanswer = ? WHERE question_id = ?", new Object[]{q.getQuestion(), q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getAnswer(), q.getId()});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    public static int verwijderQuestion(int id) {
        int changedRows = 0;
        try {
            changedRows = Database.execSqlAndReturnChangedRows("DELETE FROM questions WHERE question_id = ?", new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Foutafhandeling naar keuze
        }
        return changedRows;
    }

    private static Question convertRowToObject(ResultSet mijnResultset) throws SQLException {
        return new Question(mijnResultset.getInt("question_id"), mijnResultset.getString("question"),mijnResultset.getString("optiona"), mijnResultset.getString("optionb"), mijnResultset.getString("optionc"), mijnResultset.getString("correctanswer"));
    }

}

