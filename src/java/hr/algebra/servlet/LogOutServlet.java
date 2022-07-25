/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.model.Cart;
import hr.algebra.model.User;
import hr.algebra.repository.ISqlRepo;
import hr.algebra.repository.RepoFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "LogOutServlet", urlPatterns = {"/User-LogOut"})
public class LogOutServlet extends HttpServlet {

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
        
        try (PrintWriter out = response.getWriter()) {
            if (session.getAttribute("auth") != null) {
                

                List<Cart> cartList = (List<Cart>) session.getAttribute("cart_list");
                User user = (User) session.getAttribute("auth");
                if (cartList != null) {
                    repo.resetCart(user.getIDUser());
                    for(Cart c : cartList){
                    repo.cartInsert(c,user.getIDUser());
                    }
                }
                session.removeAttribute("auth");
                session.setAttribute("cart_list",null);
                response.sendRedirect("login.jsp");
                
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception ex) {
            Logger.getLogger(LogOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }


}
