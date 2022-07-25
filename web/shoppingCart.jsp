<%-- 
    Document   : shoppingCart
    Created on : 13-Dec-2021, 17:47:03
    Author     : tsold
--%>

<%@page import="hr.algebra.model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<% 

    List<Cart> cart = (List<Cart>)request.getSession().getAttribute("cart_list");
    double checkout = 0;
    for(Cart k : cart){
    Product p = k.getProduct();
    checkout += (p.getPrice() * k.getQuantity());
    request.getSession().setAttribute("checkout",checkout);
    }

%>


<!DOCTYPE html>
<html>
    <%@include file="page-includes/header.jsp" %>
    
      <div class="container center">

        <h2 class="basket">

            Kosarica

        </h2>
    </div>



        <% 
            int index = 0;
        for(Cart c : cart){
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
            <form action="product-number?id=<%=prod.getIDProduct()%>" method="post">
         
      <select  name="selectedValue" onchange="this.form.submit()" class="basketSelector ">
        <option >1 kom</option>
        <option >2 kom</option>
        <option >3 kom</option>
        <option >4 kom</option>
        <option >5 kom</option>
        <option >6 kom</option>
        <option >7 kom</option>
        <option >8 kom</option>
        <option >9 kom</option>
        <option >10 kom</option>
        <option  style="Display:none;" selected > <%=c.getQuantity()%> kom</option>
      </select>
                   </form>
        </div>


        <div class="col-auto horizontalCenter ">
            <p class="basketPbold"><%=prod.getPrice() *c.getQuantity()%> <span>kn</span></p>
            <a href="remove-from-cart?id=<%=index%>"><p class="basketP">ukloniti</p></a>
           </div>



        </div>
</div>
        <% 
            index++;
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


    <div class="container marginTop">
        <div class="row center">

            <div class="leftButtonsCon col-6">
              
            <div class="whiteButton center">
                <p>


                    Natrag u trgovinu
                </p>

            </div>

        </div>


 <div class="col-5 rightButtonsCon">
     <a  style="text-decoration:none" href="user-order">

            <div class="blackButton center">

                <p>

                    Zavrsite kupovinu

                </p>

            </div>
             </a>
        </div>

   




        </div>
    </div>




    
    
        <%@include file="page-includes/footer.jsp" %>
</html>
