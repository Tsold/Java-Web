/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.model.Cart;
import hr.algebra.repository.ISqlRepo;
import hr.algebra.repository.RepoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "OrderSelectServlet", urlPatterns = {"/selected-order"})
public class OrderSelectServlet extends HttpServlet {

 
   private ISqlRepo repo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         try {
           repo = RepoFactory.getRepository();
        } catch (Exception ex) {
            Logger.getLogger(LogOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         HttpSession session = request.getSession();
         int id = Integer.parseInt(request.getParameter("id"));
         List<Cart> order = new ArrayList<>();
       try {
           order = repo.GetPastOrder(id);
       } catch (Exception ex) {
           Logger.getLogger(OrderSelectServlet.class.getName()).log(Level.SEVERE, null, ex);
       }

         session.setAttribute("selectedOrder", order);
         
         response.sendRedirect("pastOrder.jsp");
         
         
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
