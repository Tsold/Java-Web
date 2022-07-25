/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.model.Cart;
import hr.algebra.model.Product;
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
@WebServlet(name = "ChangeCartProductNumber", urlPatterns = {"/product-number"})
public class ChangeCartProductNumber extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        String value = request.getParameter("selectedValue");
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        int numbOfProducts = Integer.parseInt((value.substring(0, 2)).trim());
        List<Cart> cartList = (List<Cart>) session.getAttribute("cart_list");
        
      
        int index = 0;
        for(Cart c : cartList){
        Product p = c.getProduct();
            if (p.getIDProduct()==id) {
                c.setQuantity(numbOfProducts);
                cartList.set(index,c);
                session.setAttribute("cart_list", cartList);
                response.sendRedirect("shoppingCart.jsp");
            }
        index++;
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
