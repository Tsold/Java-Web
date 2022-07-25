/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.model.Cart;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tsold
 */
@WebServlet(name = "ShoppingCartServlet", urlPatterns = {"/shopping-cart"})
public class ShoppingCartServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    List<Cart> cartList = (List<Cart>) request.getSession().getAttribute("cart_list");
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
