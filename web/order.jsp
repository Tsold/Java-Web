<%-- 
    Document   : order
    Created on : 19-Dec-2021, 17:22:21
    Author     : tsold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <%@include file="page-includes/header.jsp" %>
    <form action="user-order" method="post">
    <div class="container topMargin">

    <div class="col center">

        <div class="row orders ">

<div class="col-sm center">
   
    <div>
          <h2>Adresa</h2>
          <input class="form-control" type="text" name="adress" placeholder="Unesite Adresu" required>

        </div>
    </div>

        <div class="col-sm vertical center">
            <div>
            <h2>Nacin Placanja</h2>
  
  <div>
            <input class="orderRadio" type="radio" name="paymentMethod" id="exampleRadios1" value="Pouzece" checked>
            <label class="" for="exampleRadios1">
            Pouzece
            </label>
        </div>
            <input class="orderRadio" type="radio" name="paymentMethod" id="exampleRadios1" value="Paypal">
            <label class="" for="exampleRadios1">
             Paypal 
            </label>
        </div>
          </div>

                </div>



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


        <div class="blackButton center">

              <input class="blackButton" type="submit" value="Naruci" name="Naruci" />

        </div>
    </div>

    </div>
</div>
   </form>     

    

<%@include file="page-includes/footer.jsp" %>
</html>
