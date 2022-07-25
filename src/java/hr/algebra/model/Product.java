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
public class Product {

    public Product(int IDProduct) {
        this.IDProduct = IDProduct;
    }
    
    public Product(){};

    public Product(int IDProduct, int ProductTypeID, String Maker, String ProductName, String ShortDescription, int Price, byte[] Img) {
        this.IDProduct = IDProduct;
        this.ProductTypeID = ProductTypeID;
        this.Maker = Maker;
        this.ProductName = ProductName;
        this.ShortDescription = ShortDescription;
        this.Price = Price;
        this.Img = Img;
    }

    public Product(int ProductTypeID, String Maker, String ProductName, String ShortDescription, int Price, byte[] Img) {
        this.ProductTypeID = ProductTypeID;
        this.Maker = Maker;
        this.ProductName = ProductName;
        this.ShortDescription = ShortDescription;
        this.Price = Price;
        this.Img = Img;
    }



    public int getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(int IDProduct) {
        this.IDProduct = IDProduct;
    }

    public int getProductTypeID() {
        return ProductTypeID;
    }

    public void setProductTypeID(int ProductTypeID) {
        this.ProductTypeID = ProductTypeID;
    }

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String Maker) {
        this.Maker = Maker;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String ShortDescription) {
        this.ShortDescription = ShortDescription;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public byte[] getImg() {
        return Img;
    }

    public void setImg(byte[] Img) {
        this.Img = Img;
    }
    

	public int IDProduct;
	public int ProductTypeID;
	public String Maker;
	public String ProductName;
	public String ShortDescription;
	public int Price;
	public byte[] Img;
}
    
    
    

