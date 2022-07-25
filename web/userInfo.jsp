<%-- 
    Document   : userInfo
    Created on : 13-Dec-2021, 22:47:03
    Author     : tsold
--%>

<%@page import="hr.algebra.model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<%

List<Order> orders = (List<Order>) request.getSession().getAttribute("orderList");
 


%>
<!DOCTYPE html>
<html>
    <%@include file="page-includes/header.jsp" %>
    
    

    <div class="container center">
        <a href="User-LogOut">   

            LOGOUT</a>
        
    </div>
    
    
    
    
    
    <div class="container center">

        <h2 class="basket">

            Narudžbe

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




 
    
    
    
    
    
    
    
    
    <%@include file="page-includes/footer.jsp" %>
</html>
