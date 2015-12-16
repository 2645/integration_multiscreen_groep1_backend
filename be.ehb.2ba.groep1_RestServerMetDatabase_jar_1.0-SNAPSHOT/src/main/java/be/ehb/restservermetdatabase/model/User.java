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
public class User {

    public int user_id, user_balance, user_currentavatar_id;
    public String user_firstname, user_lastname, user_mail, user_password;

    public User() {

    }

    public User(int user_id, int user_balance, int user_currentavatar_id, String user_firstname, String user_lastname, String user_mail, String user_password) {
        this.user_id = user_id;
        this.user_balance = user_balance;
        this.user_currentavatar_id = user_currentavatar_id;
        this.user_firstname = user_firstname;
        this.user_lastname = user_lastname;
        this.user_mail = user_mail;
        this.user_password = user_password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(int user_balance) {
        this.user_balance = user_balance;
    }

    public int getUser_currentavatar_id() {
        return user_currentavatar_id;
    }

    public void setUser_currentavatar_id(int user_currentavatar_id) {
        this.user_currentavatar_id = user_currentavatar_id;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String toString() {
        return this.user_firstname + " " + this.user_lastname;
    }
}
