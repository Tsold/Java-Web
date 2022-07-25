/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "RemoveFromCart", urlPatterns = {"/remove-from-cart"})
public class RemoveFromCart extends HttpServlet {





    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession session = request.getSession();
          List<Cart> cartList = (List<Cart>) session.getAttribute("cart_list");
          int id = Integer.parseInt(request.getParameter("id"));
          cartList.remove(id);
          session.setAttribute("cart_list", cartList);
          if (cartList != null) {
           response.sendRedirect("shoppingCart.jsp");
        }
          else{
           response.sendRedirect("index.jsp");
          }
       
          
    }

  
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
