<%-- 
    Document   : adminOrders
    Created on : 30-Dec-2021, 20:54:00
    Author     : tsold
--%>

<%@page import="hr.algebra.model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="page-includes/header.jsp" %>
    
    <%
  User auths = (User) request.getSession().getAttribute("auth");
  List<Order> orders = (List<Order>) request.getSession().getAttribute("currentOrders");
  List<User> users = (List<User>) request.getSession().getAttribute("users");

  
if (auths.getRoleID() == 1) {
        
    
    
    %>
        
     <div class="container center marginTop">

        <h2 class="basket">

            Filter

        </h2>
    </div>
    
    
    
    <div class="container basketBorders pastOrders">
    
<form method="post" action="admin-orders">
        <div class="row basketMargins">
<div class="col-6">

                <div class="row">

                    <div class="topText col-auto">
                       
                                <input name="Date1" type="date" required>
                    </div>
                    <div class="topText middle col-auto">
      <select  name="selectedUser" class="basketSelector " required>
          <%
        
          for(User u : users)
          {

          %>
        <option ><%=u.getIDUser()+" "+ u.getName()+" "+ u.getSurname()%></option>
          <%
          }
          %>
       
         
      </select>
                       
                    </div>

                    <div class="topText col-auto">

                               <input name="Date2" type="date" required>
                    </div>
                </div>
                                 
            </div>


            <div class="col">
           

            </div>



        <div class="col-auto horizontalCenter ">
             <input type="submit" value="Search"  />
           </div>
          
                  </div>
           </form>
               </div>
           
    <div class="container center marginTop">

        <h2 class="basket">

            Orders

        </h2>
    </div>
    
    
    
    <%
if (orders != null) { 
    for(Order o : orders){
        
%>



<div class="container basketBorders pastOrders">
    

        <div class="row basketMargins">
<div class="col-6">
    <a href="selected-order?id=<%=o.getIdOrder()%>">
                <div class="row">

                    <div class="topText col-auto">
                       
                        <p><%= o.getDate()%></p>
                    </div>
                    <div class="topText middle col-auto">

                        <p><%= o.getPayment()%></p>
                    </div>

                    <div class="topText col-auto">

                        <p><%= o.getAdress()%></p>
                    </div>
                </div>
                                   </a>
            </div>


            <div class="col">
           

            </div>



        <div class="col-auto horizontalCenter ">
            <p class="basketPbold"><%=o.getPrice()%><span> kn</span></p>
            <a href="delete-order?id=<%=o.getIdOrder()%>"><p class="basketP">ukloniti</p></a>
           </div>
          
                  </div>
               </div>
       
         
<%

  }
 }
%>

    
    
    
    
    
    
    
    
    
    
    
    
    
    
        <%
    
    }
    
    
    
    %>
    
    
    

    
    <%@include file="page-includes/footer.jsp" %>
</html>
