/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.model.Cart;
import hr.algebra.model.Product;
import hr.algebra.repository.ISqlRepo;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/add-to-cart"})
public class AddToCartServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
            List<Cart> cartList = new ArrayList<>();
            HttpSession session = request.getSession();
            int id = Integer.parseInt(request.getParameter("id"));
            
            
            Product cartProduct = new Product();
            Cart cart = new Cart();
            
            List<Product> products = (List<Product>)  session.getAttribute("products_list");
              for(Product p : products){
                if (p.IDProduct == id) {
                   cartProduct = p;
                }
            }

            int quantity = 1;

            
            
            List<Cart> cart_List = (List<Cart>) session.getAttribute("cart_list");
            


           
               cart.setProduct(cartProduct);
               cart.setQuantity(quantity);
            if (cart_List == null) {
                cartList.add(cart);
                session.setAttribute("cart_list", cartList);
                response.sendRedirect("productPage.jsp");
                    
            }
            else{
                boolean notHere = true;
                
                int index = 0;
                for(Cart c : cart_List){
                Product p = c.getProduct();
            
                if (p.getIDProduct() == id) {
                notHere = false;
               Cart updated = new Cart(p, c.getQuantity()+1);
               cart_List.set(index, updated);
               session.setAttribute("cart_list",cart_List);
                  response.sendRedirect("productPage.jsp");
                }
                index++;
            }
                
                
                if (notHere) {
                    cart_List.add(cart);
                    session.setAttribute("cart_list", cart_List);
                     response.sendRedirect("productPage.jsp");
                    
                }
                
                
            }

           
    }
    


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
