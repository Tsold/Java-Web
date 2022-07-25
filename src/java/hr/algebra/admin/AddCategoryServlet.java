/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.admin;

import hr.algebra.repository.ISqlRepo;
import hr.algebra.repository.RepoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tsold
 */
@WebServlet(name = "AddCategoryServlet", urlPatterns = {"/add-category"})
public class AddCategoryServlet extends HttpServlet {

      private ISqlRepo repo;
      
      
         

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
           try {
           repo =  RepoFactory.getRepository();
       } catch (Exception ex) {
           Logger.getLogger(ManageProductServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
           
      String category = request.getParameter("Category");
      
          try {
              repo.addCategory(category);
          } catch (Exception ex) {
              Logger.getLogger(AddCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
          
        response.sendRedirect("admin-controls");
      
      
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
