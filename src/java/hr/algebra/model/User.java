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
public class User {

    public User(int IDUser, String Name, String Surname) {
        this.IDUser = IDUser;
        this.Name = Name;
        this.Surname = Surname;
    }
    
    
    public  User(){};


    public User(int IDUser, String Name, String Surname, int RoleID) {
        this.IDUser = IDUser;
        this.Name = Name;
        this.Surname = Surname;
        this.RoleID = RoleID;
    }

    public User(int IDUser, String Name, String Surname, int RoleID, int LoginCredID) {
        this.IDUser = IDUser;
        this.Name = Name;
        this.Surname = Surname;
        this.RoleID = RoleID;
        this.LoginCredID = LoginCredID;
    }

    public User(String Name, String Surname, int RoleID, int LoginCredID) {
        this.Name = Name;
        this.Surname = Surname;
        this.RoleID = RoleID;
        this.LoginCredID = LoginCredID;
    }

    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public int getLoginCredID() {
        return LoginCredID;
    }

    public void setLoginCredID(int LoginCredID) {
        this.LoginCredID = LoginCredID;
    }

	private int IDUser;
	private String Name;
	private String Surname;
	private int RoleID;
	private int LoginCredID;
}
