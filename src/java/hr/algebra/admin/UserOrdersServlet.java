/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.admin;


import hr.algebra.model.*;
import hr.algebra.model.Order;
import hr.algebra.repository.ISqlRepo;
import hr.algebra.repository.RepoFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tsold
 */
@WebServlet(name = "UserOrdersServlet", urlPatterns = {"/admin-orders"})
public class UserOrdersServlet extends HttpServlet {

    
   private ISqlRepo repo;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try {
           repo =  RepoFactory.getRepository();
       } catch (Exception ex) {
           Logger.getLogger(ManageProductServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
          
          
         HttpSession session = request.getSession();
         
         List<Order> orders = new ArrayList<>();
         List<User> users = new ArrayList<>();
        
       try {
           orders = repo.getAllOrders();
           users = repo.getAllUsers();
       } catch (Exception ex) {
           Logger.getLogger(UserOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       session.setAttribute("allOrders", orders);
       session.setAttribute("currentOrders", orders);
       session.setAttribute("users", users);
       
       response.sendRedirect("adminOrders.jsp");
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             try {
           repo =  RepoFactory.getRepository();
       } catch (Exception ex) {
           Logger.getLogger(ManageProductServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        HttpSession session = request.getSession();
        String user = request.getParameter("selectedUser");
        int selectedUser = Integer.parseInt((user.substring(0, 2)).trim());
        String beginDate = request.getParameter("Date1");
        String endDate = request.getParameter("Date2");
       
        
        List<Order> orders = (List<Order>) session.getAttribute("allOrders");
        List<Order> selectedOrders = new ArrayList<>(); 
        for (Order o : orders) {
            if (o.getIdUser() == selectedUser) {
                selectedOrders.add(o);
            }
        }
        
       try {
           List<Order> finalOrders = getBetweenDates(beginDate, endDate, selectedOrders);
           session.setAttribute("currentOrders", finalOrders);
           response.sendRedirect("adminOrders.jsp");
       } catch (ParseException ex) {
           Logger.getLogger(UserOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
    }
    
    
    private List<Order> getBetweenDates(String beginDate,String endDate, List<Order> selected) throws ParseException{
     List<Order> orders = new ArrayList<>(); 
     Date min = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    .parse(beginDate);
     Date max = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    .parse(endDate);
    
            for (Order o : selected) {
                Date now = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
                    .parse(o.getDate());
                if (between(now,min,max)) {
                    orders.add(o);
                }
        }
    return orders;
    }
    
    
    public boolean between(Date date, Date dateStart, Date dateEnd) {
    if (date != null && dateStart != null && dateEnd != null) {
        if (date.after(dateStart) && date.before(dateEnd)) {
            return true;
        }
        else {
            return false;
        }
    }
    return false;
}
    
    

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
