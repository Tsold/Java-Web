/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.paypal;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import hr.algebra.model.Cart;
import hr.algebra.model.Order;
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
@WebServlet(name = "PaypalPayServlet", urlPatterns = {"/pay-paypal"})
public class PaypalPayServlet extends HttpServlet {

    private ISqlRepo repo;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("auth");
     		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");
                List<Cart> cartList = (List<Cart>) session.getAttribute("cart_list");
		try {
                        repo = RepoFactory.getRepository();
			PaymentServices paymentServices = new PaymentServices();
			Payment payment = paymentServices.executePayment(paymentId, payerId);
			Order ord = (Order) session.getAttribute("payPalOrder");
                        String pattern = "yyyy/MM/dd";
                        String date =new SimpleDateFormat(pattern).format(new Date());
                        repo.insertOrder(ord.getIdUser(),ord.getAdress(),ord.getPayment(),date,ord.getPrice());
                        int orderID =  repo.getOrderID(user.getIDUser());
                        
                                                
                        for(Cart c : cartList){
       
                        repo.insertOrderProducts(c, orderID);
                               }
                        session.setAttribute("cart_list",null);
			response.sendRedirect("user-info");
			
		} catch (PayPalRESTException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			ex.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (Exception ex) {
            Logger.getLogger(PaypalPayServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
