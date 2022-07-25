/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.admin;

import hr.algebra.model.Product;
import hr.algebra.repository.ISqlRepo;
import hr.algebra.repository.RepoFactory;
import hr.algebra.servlet.LogOutServlet;
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
@WebServlet(name = "WebManagementServlet", urlPatterns = {"/admin-controls"})
public class WebManagementServlet extends HttpServlet {


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
         
         List<Product> products = new ArrayList<>();
         
       try {
           products = (List<Product>) repo.getAllProducts();
       } catch (Exception ex) {
           Logger.getLogger(WebManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
         
       session.setAttribute("allProducts", products);
         
         
         response.sendRedirect("adminCrud.jsp");
    }
  
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
