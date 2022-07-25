package hr.algebra.dao;

import hr.algebra.model.Cart;
import hr.algebra.model.Category;
import hr.algebra.model.LoginInfo;
import hr.algebra.model.Order;
import hr.algebra.model.User;
import hr.algebra.model.Product;
import hr.algebra.repository.ISqlRepo;
import hr.algebra.utils.ImageConverter;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class SqlRepo implements ISqlRepo {

    private static final String VALIDATE_USER = "{call ValidateUser(?, ?)} " ;
    private static final String GetAllUsers = "{call GetAllUsers} " ;

    private static final String GetAllCategories = "{call GetAllCategories} " ;
    private static final String InsertCategory = "{call InsertCategory(?)} " ;
    private static final String DeleteCategory = "{call DeleteCategory(?)} " ;
    
    private static final String GET_PRODUCTS = "{ call GetProducts }";
    private static final String GetProductsByCategory = "{ call GetProductsByCategory(?) }";
    private static final String GetSpecificProduct = "{ call GetSpecificProduct(?) }";
    private static final String UpdateProduct = "{ call UpdateProduct(?,?,?,?,?,?,?) }";
    private static final String UpdateProductSameImage = "{ call UpdateProductSameImage(?,?,?,?,?,?) }";
    private static final String InsertProduct = "{ call InsertProduct(?,?,?,?,?,?) }";
    private static final String DeleteProduct = "{ call DeleteProduct(?) }";


    private static final String CartInsert = "{ call CartInsert(?, ?, ?)}";
    private static final String GetCartProducts = "{ call GetCartProducts(?) }";
    private static final String CartReset = "{ call CartReset(?) }";
    
    private static final String OrderInsert = "{ call InsertOrder(?, ?, ?, ?, ?)}";
    private static final String InsertOrderProducts = "{ call InsertOrderProducts(?, ?, ?)}";
    private static final String GetOrders = "{ call GetOrders(?) }";
    private static final String GetAllOrders = "{ call GetAllOrders }";
    private static final String GetOrderID = "{ call getOrderID(?) }";
    private static final String DeleteOrder = "{ call DeleteOrder(?) }";
    private static final String GetPastOrder = "{ call GetPastOrder(?) }";
    
    private static final String GetAllLoginInfo = "{ call GetAllLoginInfo }";
    private static final String InsertLoginInfo = "{ call InsertLoginInfo(?, ?, ?)}";
    






    @Override
    public User validateUser(String userName, String passWord) throws Exception {
        DataSource ds = DataSourceSingleton.getInstance();

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(VALIDATE_USER)) {

            stmt.setString(1, userName);
            stmt.setString(2, passWord);

            try (ResultSet rs = stmt.executeQuery()) {

                    if (rs.next()) {
                        return new User(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4)
                        );
                    }
            }
        }
        return new User();
    }





    int boolToInt(Boolean b) {
        return b.compareTo(false);
    }



//Products
    @Override
    public List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();

        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GET_PRODUCTS)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString (3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBytes(7)
                ));
            }
        }
        for(Product p : products){
         ImageConverter.convertImage(p);
        
        }
        return products;   
    }

    @Override
    public List<Product> getProductsByCategory(int type) throws Exception {
         List<Product> products = new ArrayList<>();

        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GetProductsByCategory)) {
              stmt.setInt(1, type);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString (3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBytes(7)
                ));
            }
        }
        for(Product p : products){
         ImageConverter.convertImage(p);
        
        }
        
        return products;    
    
    }

    @Override
    public Product getSingleProduct(int id) throws Exception {
     DataSource ds = DataSourceSingleton.getInstance();
        Product product = new Product();

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GetSpecificProduct)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            product.setIDProduct(rs.getInt("IDProduct"));
            product.setProductTypeID(rs.getInt("ProductTypeID"));
            product.setMaker(rs.getString("Maker"));
            product.setProductName(rs.getString("ProductName"));
            product.setShortDescription(rs.getString("ShortDescription"));
            product.setPrice(rs.getInt("Price"));
            product.setImg(rs.getBytes("Img"));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ImageConverter.convertImage(product);
        return product;  
    
    }

    @Override
    public void deleteProduct(int ID) throws Exception {
     DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(DeleteProduct);
            stmt.setInt(1,ID);

            stmt.execute();

        } 
    }

    @Override
    public void addProduct(int ProductTypeID, String Maker, String ProductName, String ShortDescription, int Price, byte[] Img) throws Exception {
  DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {
            
            CallableStatement stmt = con.prepareCall(InsertProduct);
            stmt.setInt(1,ProductTypeID);
            stmt.setString(2,Maker);
            stmt.setString(3,ProductName);
            stmt.setString(4,ShortDescription);
            stmt.setInt(5,Price);
            stmt.setBytes(6,Img);

            stmt.execute();

        }  
    }

    @Override 
    public List<Category> getAllCategories() throws Exception {
         List<Category> categories = new ArrayList<>();

        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GetAllCategories)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt(1),
                        rs.getString (2)
                ));
            }
        }

        return categories;   
    }

    @Override
    public Category getSingleCategory(int ID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCategory(int ID) throws Exception {
      List<Product> delProds =  getProductsByCategory(ID);
      for(Product p : delProds){
      deleteProduct(p.getIDProduct());
      }
           DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(DeleteCategory);
            stmt.setInt(1,ID);

            stmt.execute();

        }   
      
      
    }

    @Override
    public void addCategory(String category) throws Exception {
           DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(InsertCategory);
            stmt.setString(1,category);

            stmt.execute();

        }   
    }

    @Override
    public List<Cart> getCartProducts(int UserID) throws Exception {
         List<Cart> cartList = new ArrayList<>();

        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GetCartProducts)) {
            stmt.setInt(1, UserID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cartList.add(new Cart(
                       new Product(rs.getInt(1)),
                       rs.getInt(2)
                ));
            }
        }

        return cartList;   
    }

    @Override
    public void cartInsert(Cart c,int UserID) throws Exception {
        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(CartInsert);
            stmt.setInt(1,UserID);
            stmt.setInt(2,c.getProduct().getIDProduct());
            stmt.setInt(3,c.getQuantity());

            stmt.execute();
    }
  }

    @Override
    public void resetCart(int UserID) throws Exception {
           DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(CartReset);
            stmt.setInt(1,UserID);
            stmt.execute();

        } 
    }

    @Override
    public List<Order> getOrdersByUser(int ID) throws Exception {
          List<Order> orderList = new ArrayList<>();

        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GetOrders)) {
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderList.add(new Order(
                       rs.getInt(1),
                       rs.getInt(2),
                       rs.getString(3),
                       rs.getString(4),
                       rs.getString(5),
                       rs.getDouble(6)
                ));
            }
        }

        return orderList;   
    }
    
  

    @Override
    public void insertOrder(int userID,String adress,String payment,String date,Double price) throws Exception {
        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(OrderInsert);
            stmt.setInt(1,userID);
            stmt.setString(2,adress);
            stmt.setString(3,payment);
            stmt.setString(4,date);
            stmt.setDouble(5,price);

            stmt.execute();
    }
    }

    @Override
    public int getOrderID(int ID) throws Exception {
       DataSource ds = DataSourceSingleton.getInstance();
        int order = 0;

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GetOrderID)) {

            stmt.setInt(1, ID);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            order = rs.getInt("IDOrder");
           
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return order;  
    }

    @Override
    public void insertOrderProducts(Cart c,int orderID) throws Exception {
         DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(InsertOrderProducts);
            stmt.setInt(1,c.getProduct().getIDProduct());
            stmt.setInt(2,c.getQuantity());
            stmt.setInt(3,orderID);

            stmt.execute();
    }
    }

    @Override
    public void deleteOrder(int orderID) throws Exception {
        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(DeleteOrder);
            stmt.setInt(1,orderID);
            stmt.execute();

        } 
    }

    @Override
    public List<Cart> GetPastOrder(int id) throws Exception {
        List<Cart> orderItems = new ArrayList<>();


        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GetPastOrder)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product prod = new Product();
                orderItems.add(new Cart(
                       prod = getSingleProduct(rs.getInt(1)),
                       rs.getInt(2)
                ));
            }
        }

        return orderItems;   
    }

    @Override
    public void updateProduct(int ID,int ProductTypeID, String Maker, String ProductName, String ShortDescription, int Price, byte[] Img) throws Exception {
        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(UpdateProduct);
            stmt.setInt(1,ID);
            stmt.setInt(2,ProductTypeID);
            stmt.setString(3,Maker);
            stmt.setString(4,ProductName);
            stmt.setString(5,ShortDescription);
            stmt.setInt(6,Price);
            stmt.setBytes(7,Img);
            

            stmt.execute();

        }  
    }
    

    @Override
    public void updateProduct(int ID,int ProductTypeID, String Maker, String ProductName, String ShortDescription, int Price) throws Exception {
          DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(UpdateProductSameImage);
            stmt.setInt(1,ID);
            stmt.setInt(2,ProductTypeID);
            stmt.setString(3,Maker);
            stmt.setString(4,ProductName);
            stmt.setString(5,ShortDescription);
            stmt.setInt(6,Price);

            

            stmt.execute();

        }   
    }

    @Override
    public List<LoginInfo> getAllLoginInfo() throws Exception {
        List<LoginInfo> loginList = new ArrayList<>();


        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GetAllLoginInfo)) {
            ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                loginList.add(new LoginInfo(
                        rs.getInt(1),
                        rs.getString (2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
        }

        return loginList;   
    }

    @Override
    public void insertLoginInfo(LoginInfo log) throws Exception {
                DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection()) {

            CallableStatement stmt = con.prepareCall(InsertLoginInfo);
            stmt.setString(1,log.getUserName());
            stmt.setString(2,log.getDateAndTime());
            stmt.setString(3,log.getIPAdress());

            stmt.execute();
    }
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        List<Order> orderList = new ArrayList<>();

        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GetAllOrders)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderList.add(new Order(
                       rs.getInt(1),
                       rs.getInt(2),
                       rs.getString(3),
                       rs.getString(4),
                       rs.getString(5),
                       rs.getDouble(6)
                ));
            }
        }

        return orderList;   
    }

    @Override
    public List<User> getAllUsers() throws Exception {
       List<User> users = new ArrayList<>();

        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareCall(GetAllUsers)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                       rs.getInt(1),
                       rs.getString(2),
                       rs.getString(3)
                ));
            }
        }

        return users;   
    }



    


}
