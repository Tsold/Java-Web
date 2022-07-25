/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.model.Order;
import hr.algebra.model.User;
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
@WebServlet(name = "UserInfoServlet", urlPatterns = {"/user-info"})
public class UserInfoServlet extends HttpServlet {

   private ISqlRepo repo;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
           repo = RepoFactory.getRepository();
        } catch (Exception ex) {
            Logger.getLogger(LogOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Order> orders = new ArrayList<>();
        HttpSession session = request.getSession();
        User Id = (User) session.getAttribute("auth");
       try {
           orders = repo.getOrdersByUser(Id.getIDUser());
       } catch (Exception ex) {
           Logger.getLogger(UserInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
       
         session.setAttribute("orderList", orders);
       
       
       response.sendRedirect("userInfo.jsp");
    }

   
 


    @Override
    public String getServletInfo() {
        return "Short description";
    }




}
