<%-- 
    Document   : pastOrder
    Created on : 23-Dec-2021, 20:29:25
    Author     : tsold
--%>

<%@page import="hr.algebra.model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <%@include file="page-includes/header.jsp" %>
 
 <div class="marginTop"></div>
 
        <% 
          double checkout = 0;
          List<Cart> cart = (List<Cart>)request.getSession().getAttribute("selectedOrder");
        for(Cart c : cart){
             Product p = c.getProduct();
             checkout += (p.getPrice() * c.getQuantity());
            Product prod = c.getProduct();
            
%>
<div class="container basketBorders">
    <div class="row basketMargins">


          <div class="col-5">
                <div class="row">
                    <div class="topContainer">
                        <img src="img/<%=prod.getProductName()%>.png" alt="" class="img-fluid basketImg">
                    </div>
                    <div class="topText col-auto">
                        <h2><%=prod.getMaker()+" "%>  <%=prod.getProductName()%></h2>
                        <p><%=prod.getShortDescription()%></p>
                    </div>
                </div>
            </div>


            <div class="col-2 horizontalCenter ">
             <p class="basketP"><%=prod.getPrice()%> <span>kn</span></p>
            </div>


        <div class="col-2 horizontalCenter marginLeftBasket ">
         
      <select  name="selectedValue"  class="basketSelector ">
        <option  style="Display:none;" selected > <%=c.getQuantity()%> kom</option>
      </select>
                 
        </div>


        <div class="col-auto horizontalCenter ">
            <p class="basketPbold"><%=prod.getPrice() *c.getQuantity()%> <span>kn</span></p>
           <p class="basketP"></p>
           </div>



        </div>
</div>
        <% 
           
}
 

%>

    <div class="container greyCon">

        <div class="row">
            <h2>Ukupna cijena s PDV
            </h2>
            <h3>
                       <%=checkout %>
            </h3>

        </div>




    </div>
 
 
 
 
 
 
 
 
    <%@include file="page-includes/footer.jsp" %>
</html>
