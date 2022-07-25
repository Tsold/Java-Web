/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.admin;

import hr.algebra.repository.ISqlRepo;
import hr.algebra.repository.RepoFactory;
import hr.algebra.utils.ImageConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 *
 * @author tsold
 */
@WebServlet(name = "ManageProductServlet", urlPatterns = {"/manage-product"})
@MultipartConfig
public class ManageProductServlet extends HttpServlet {

   
    
   private ISqlRepo repo;

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
           repo =  RepoFactory.getRepository();
       } catch (Exception ex) {
           Logger.getLogger(ManageProductServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
         
        int id = Integer.parseInt(request.getParameter("id"));
       try {
           repo.deleteProduct(id);
       } catch (Exception ex) {
           Logger.getLogger(ManageProductServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       response.sendRedirect("admin-controls");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               try {
           repo =  RepoFactory.getRepository();
       } catch (Exception ex) {
           Logger.getLogger(ManageProductServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        
          int id = Integer.parseInt(request.getParameter("id"));
          String cat = request.getParameter("selectedValue");
          int category = Integer.parseInt((cat.substring(0, 2)).trim());
          String maker = request.getParameter("Maker");
          String productName = request.getParameter("Model");
          String oldName = request.getParameter("oldName");
          String shortdes = request.getParameter("Des");
          String price = request.getParameter("Price");
          

          Part image =  request.getPart("picture"); 
          InputStream is = image.getInputStream();
            
          BufferedImage bi = ImageIO.read(is);
              
          if (bi != null) {          
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            byte[] bytes = baos.toByteArray();
            
       try {
           repo.updateProduct(id, category, maker, productName, shortdes, Integer.parseInt(price), bytes);
       } catch (Exception ex) {
           Logger.getLogger(ManageProductServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
       ImageConverter.renameImage(oldName, productName);
        response.sendRedirect("admin-controls");
            
      }
          else{
          
                   try {
                       repo.updateProduct(id, category, maker, productName, shortdes, Integer.parseInt(price));
                   } catch (Exception ex) {
                       Logger.getLogger(ManageProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                   }
            ImageConverter.renameImage(oldName, productName);
            response.sendRedirect("admin-controls");
          }    
     
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
