<%-- 
    Document   : productPafe
    Created on : 13-Dec-2021, 18:00:50
    Author     : tsold
--%>

<%@page import="hr.algebra.utils.ImageConverter"%>
<%@page import="hr.algebra.repository.RepoFactory"%>
<%@page import="java.util.List"%>
<%@page import="hr.algebra.model.Product"%>
<%@page import="hr.algebra.repository.ISqlRepo"%>
<%@page import="hr.algebra.repository.ISqlRepo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<% 
    List<Product> products =(List<Product>) request.getSession().getAttribute("products_list");
%>

<!DOCTYPE html>
<html>
    <%@include file="page-includes/header.jsp" %>
    
<div class="container-fluid center shop">

    <div class="row">


        <div class="col categories">

           <h2>Kategorije</h2>
    <% 
    for(Category cat : categories){
    
    %>
<a href="selected-category?id=<%=cat.getIDCategory()%>" class=""><p><%=cat.getType()%></p></a>

   <% 
    
    }
    %>
        </div>


   <div class="col-auto">

    <h1>Proizvodi</h1>


    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img class="d-block w-90 img-fluid" src="img/parfemNotino1.jpeg" alt="First slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-90 img-fluid" src="img/parfemNotino1.jpeg" alt="Second slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-90 img-fluid" src="img/parfemNotino1.jpeg" alt="Third slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-90 img-fluid" src="img/parfemNotino1.jpeg" alt="Third slide">
          </div>
        </div>

        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>

      </div>



<div class="row">

    
    <% 
        if (!products.isEmpty()) {
                
           
            for(Product prod : products){
      


    
    
    
    %>
    <div class="imgProduct">
        <button onclick="location.href='add-to-cart?id=<%=prod.getIDProduct()%>'" class="toCart">Add</button>
        <img src="img/<%=prod.getProductName()%>.png" alt="" class="img-fluid">
        <p>   <%=prod.getMaker().toString()%> </p>${prod.getMaker()}
        <h4>   <%=prod.getProductName()%></h4>
        <p class="concetration">   <%=prod.getShortDescription()%></p>
        <h3>   <%= prod.getPrice() %> <span>kn</span></h3>

    </div>
    <% 
    
    
            }
 }
    
    %>







</div>
</div>














   </div>
</div>

    
        
        <%@include file="page-includes/footer.jsp" %>
</html>
