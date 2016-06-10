package com.auidbook.prototype.Model.Fields;

/**
 * Created by mgundappan on 01-06-2016.
 */
public class User {

    private String userName;
    private String passWord;

    public User() {
    }

    public User(String userName, String passWord) {

        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
