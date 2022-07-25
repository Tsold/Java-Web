/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.model.Cart;
import hr.algebra.model.LoginInfo;
import hr.algebra.model.Product;
import hr.algebra.model.User;
import hr.algebra.repository.ISqlRepo;
import hr.algebra.repository.RepoFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/User-Login"})
public class LoginServlet extends HttpServlet {

    private ISqlRepo repo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String realPath = getServletContext().getRealPath("");
        System.out.println(realPath);
         request.getSession().setAttribute("RealPath",realPath);
         User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
       response.sendRedirect("user-info");
    }
    else{
      response.sendRedirect("login.jsp");
    }
     
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            repo = RepoFactory.getRepository();
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

         
          
        try {
            String userName = request.getParameter("Login-UserName");
            String passWord = request.getParameter("Login-Password");

            User user = repo.validateUser(userName, passWord);   
            request.getSession().setAttribute("auth", user);
            if (user != null) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();  
                List<Cart> fakeCartList = repo.getCartProducts(user.getIDUser());
                List<Cart> realCartList = new ArrayList<>();
                if (!fakeCartList.isEmpty()) {
                for(Cart c : fakeCartList){
                Product p = repo.getSingleProduct(c.getProduct().getIDProduct());
                
                realCartList.add(new Cart(p,c.getQuantity()));
                }
            
                request.getSession().setAttribute("cart_list",realCartList);
                
                LoginInfo logs = new LoginInfo(user.getName() +" "+ user.getSurname(),dtf.format(now).toString(),request.getRemoteAddr());
                repo.insertLoginInfo(logs);
                response.sendRedirect("index.jsp");
                }else{
                
                
                LoginInfo logs = new LoginInfo(user.getName() +" "+ user.getSurname(),dtf.format(now).toString(),request.getRemoteAddr());
                repo.insertLoginInfo(logs);
                response.sendRedirect("index.jsp");
                
                
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
             response.sendRedirect("login.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
