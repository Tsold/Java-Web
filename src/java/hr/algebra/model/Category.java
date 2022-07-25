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
public class Category {

    public Category(int IDCategory, String Type) {
        this.IDCategory = IDCategory;
        this.Type = Type;
    }

    public Category(String Type) {
        this.Type = Type;
    }

    public int getIDCategory() {
        return IDCategory;
    }

    public void setIDCategory(int IDCategory) {
        this.IDCategory = IDCategory;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

 
    	public int IDCategory;
	public String Type;
    
    
}
