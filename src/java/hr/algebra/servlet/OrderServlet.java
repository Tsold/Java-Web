/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import com.paypal.base.rest.PayPalRESTException;
import hr.algebra.model.Cart;
import hr.algebra.model.Order;
import hr.algebra.paypal.PaymentServices;
import hr.algebra.model.OrderDetail;
import hr.algebra.model.User;
import hr.algebra.repository.ISqlRepo;
import hr.algebra.repository.RepoFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "OrderServlet", urlPatterns = {"/user-order"})
public class OrderServlet extends HttpServlet {

   private ISqlRepo repo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
         User user = (User) session.getAttribute("auth");
        if (user != null) {
            response.sendRedirect("order.jsp");
        }
        else{
        response.sendRedirect("login.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           repo = RepoFactory.getRepository();
        } catch (Exception ex) {
            Logger.getLogger(LogOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
       HttpSession session = request.getSession();
       String paymentMethod = request.getParameter("paymentMethod");
       String adress = request.getParameter("adress");   
       User user = (User) session.getAttribute("auth");
       List<Cart> cartList = (List<Cart>) session.getAttribute("cart_list");
       int orderID = 0;
       String pattern = "yyyy/MM/dd";
       String date =new SimpleDateFormat(pattern).format(new Date());
       double checkout =(double)session.getAttribute("checkout");
       float ordercheck = (float) ((float) checkout * 0.15);
       float tax = (float) ((float) checkout *0.2 *0.15);
       float noTax = (float) ((float) checkout *0.8 * 0.15);
     
       
       
        if ("Paypal".equals(paymentMethod)) {
            Order ord = new Order(user.getIDUser(), adress, paymentMethod,date,checkout);
            session.setAttribute("payPalOrder", ord);
            		OrderDetail orderDetail = new OrderDetail("Notino",noTax,0,tax,ordercheck);
                        

		try {
			PaymentServices paymentServices = new PaymentServices();
			String approvalLink = paymentServices.authorizePayment(orderDetail);
			response.sendRedirect(approvalLink);
                        
			
		} catch (PayPalRESTException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			ex.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
        }
        else if("Pouzece".equals(paymentMethod)){
       
       try {
           repo.insertOrder(user.getIDUser(), adress, paymentMethod,date,checkout);
       } catch (Exception ex) {
           Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       try {
        orderID =  repo.getOrderID(user.getIDUser());
       } catch (Exception ex) {
           Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       for(Cart c : cartList){
            try {
                repo.insertOrderProducts(c, orderID);
            } catch (Exception ex) {
                Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
       }
       
        session.setAttribute("cart_list",null);
       
        response.sendRedirect("user-info");
       try {
           repo.resetCart(user.getIDUser());
       } catch (Exception ex) {
           Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
