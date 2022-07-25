package hr.algebra.repository;


import hr.algebra.model.Cart;
import hr.algebra.model.Product;
import hr.algebra.model.Category;
import hr.algebra.model.LoginInfo;
import hr.algebra.model.Order;
import hr.algebra.model.User;

import java.util.List;

public interface ISqlRepo {

    
    
     //User
     User validateUser(String userName,String passWord) throws Exception;
     List<User> getAllUsers() throws Exception;
     
     //Products
     List<Product> getAllProducts() throws Exception;
     List<Product> getProductsByCategory(int type) throws Exception;
     Product getSingleProduct(int id) throws Exception;
     void deleteProduct(int ID) throws Exception;
     void addProduct(int ProductTypeID, String Maker, String ProductName, String ShortDescription, int Price, byte[] Img) throws Exception;
     void updateProduct(int ID,int ProductTypeID, String Maker, String ProductName, String ShortDescription, int Price, byte[] Img) throws Exception;
     void updateProduct(int ID,int ProductTypeID, String Maker, String ProductName, String ShortDescription, int Price) throws Exception;


     //Category
     List<Category> getAllCategories() throws Exception;
     Category getSingleCategory(int ID) throws Exception;
     void deleteCategory(int ID) throws Exception;
     void addCategory(String category) throws Exception;

     
     //Cart
     List<Cart> getCartProducts(int UserID) throws Exception;
     void cartInsert(Cart c,int UserID) throws Exception;
     void resetCart(int UserId) throws Exception;
     
     //Order
     List<Order> getOrdersByUser(int ID) throws Exception;
     void insertOrder(int userID,String adress,String payment,String date,Double price) throws Exception;
     int getOrderID(int ID) throws Exception;
     void insertOrderProducts(Cart c,int orderID) throws Exception;
     void deleteOrder(int orderID) throws Exception;
     List<Cart> GetPastOrder(int id) throws Exception;
     List<Order> getAllOrders() throws Exception;
     
     
     //LoginInfo
     List<LoginInfo> getAllLoginInfo() throws Exception;
     void insertLoginInfo(LoginInfo log) throws Exception;
  
     
     
}
