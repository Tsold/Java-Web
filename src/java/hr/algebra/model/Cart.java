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
public class Cart {

    public Cart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Cart(int cartID, Product product, int quantity) {
        this.cartID = cartID;
        this.product = product;
        this.quantity = quantity;
    }

    public Cart() {
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int cartID;
    private Product product;
    private int quantity;
    
    
    
}
