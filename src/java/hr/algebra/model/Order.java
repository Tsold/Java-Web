/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.List;

/**
 *
 * @author tsold
 */
public class Order {

    public Order(int idUser, String adress, String payment, String date, double price) {
        this.idUser = idUser;
        this.adress = adress;
        this.payment = payment;
        this.date = date;
        this.price = price;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order(int idOrder, int idUser, String adress, String payment, String date, double price) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.adress = adress;
        this.payment = payment;
        this.date = date;
        this.price = price;
    }

    public Order() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public List<Cart> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<Cart> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    
    private int idOrder;
    private int idUser;
    private List<Cart> orderedItems;
    private String adress;
    private String payment;
    private String date;
    private double price;
    
}
