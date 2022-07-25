<%-- 
    Document   : loginInfo
    Created on : 29-Dec-2021, 19:45:15
    Author     : tsold
--%>

<%@page import="hr.algebra.model.LoginInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<%

List<LoginInfo> loginList = (List<LoginInfo>) request.getSession().getAttribute("loginlist");
 


%>
<!DOCTYPE html>
<html>
    <%@include file="page-includes/header.jsp" %>
    
        <div class="container center">

        <h2 class="basket">

            Login Info

        </h2>
    </div>
    
    
    
    
    
<%
if (loginList != null) { 
    for(LoginInfo l : loginList){
       
%>



<div class="container basketBorders pastOrders">
    

        <div class="row basketMargins">
<div class="col-6">
   
                <div class="row">

                    <div class="topText col-auto">
                       
                        <p><%=l.getUserName()%></p>
                    </div>
                    <div class="topText middle col-auto">

                        <p><%= l.getIPAdress()%></p>
                    </div>

                    <div class="topText col-auto">

                        <p><%= l.getDateAndTime()%></p>
                    </div>
                </div>
                                   
            </div>


            <div class="col">
           

            </div>




          
                  </div>
               </div>
       
         
<%

  }
 }
%>



    
    
    
    
    
    
    
    
    <%@include file="page-includes/footer.jsp" %>
</html>
