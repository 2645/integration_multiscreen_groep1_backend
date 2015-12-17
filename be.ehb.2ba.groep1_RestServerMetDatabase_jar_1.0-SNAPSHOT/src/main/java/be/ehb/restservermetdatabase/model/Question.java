/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ehb.restservermetdatabase.model;

/**
 *
 * @author janhd
 */
public class Question {
    private int id;
    private String question, optionA, optionB, optionC, answer;
    
    public Question(){
        
    }

    public Question(int id, String question, String optiona, String optionb, String optionc, String answer) {
        this.id = id;
        this.question = question;
        this.optionA = optiona;
        this.optionB = optionb;
        this.optionC = optionc;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionB = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionb(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionc(String optionC) {
        this.optionC = optionC;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
