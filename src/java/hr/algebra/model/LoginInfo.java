/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

/**
 *
 * @author tsold
 */
public class LoginInfo {

    public LoginInfo(int ID, String UserName, String DateAndTime, String IPAdress) {
        this.ID = ID;
        this.UserName = UserName;
        this.DateAndTime = DateAndTime;
        this.IPAdress = IPAdress;
    }

    public LoginInfo(String UserName, String DateAndTime, String IPAdress) {
        this.UserName = UserName;
        this.DateAndTime = DateAndTime;
        this.IPAdress = IPAdress;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getDateAndTime() {
        return DateAndTime;
    }

    public void setDateAndTime(String DateAndTime) {
        this.DateAndTime = DateAndTime;
    }

    public String getIPAdress() {
        return IPAdress;
    }

    public void setIPAdress(String IPAdress) {
        this.IPAdress = IPAdress;
    }
    
    private int ID;
    private String UserName;
    private String DateAndTime;
    private String IPAdress;

    
  
    
}
