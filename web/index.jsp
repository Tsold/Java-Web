<%-- 
    Document   : index
    Created on : 13-Dec-2021, 16:24:31
    Author     : tsold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


    <%@include file="page-includes/header.jsp" %>
    
   



<div class="container">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img class="d-block w-90 img-fluid" src="img/notino1.jpeg" alt="First slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-90 img-fluid" src="img/notino2.jpeg" alt="Second slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-90 img-fluid" src="img/notino3.jpeg" alt="Third slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-90 img-fluid" src="img/notino2.jpeg" alt="Third slide">
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


</div>

<div class="container  topMargin">
<div class="row">

<div class="textColumn  col">
<h3>90 DANA</h3>
<p>ZA POVRAT ROBE</p>
</div>

<div class="textColumn col simpleBorders">
    <h3>TU SMO ZA VAS</h3>
    <p>SVAKI RADNI DAN</p>
</div>

<div class="textColumn col">
    <h3>NAJVEĆI IZBOR</h3>
    <p>KOZMETIKE I PARFEMA U EUROPI</p>
</div>




</div>
</div>

<div class="container topMargin ">
<div class="row">
<img src="/img/midPage1.jpeg" alt="" class="rowImg col img-fluid">


<img src="/img/midPage2.jpeg" alt="" class="rowImg col img-fluid">


</div>



</div>


<div class="container center marginTop">

    <div class="row">
    
        <div class=" imgCategoryColumn">
    <img src="img/hp-perfumes.png" alt="" class="img-fluid imgRound">
    <p>Parfemi</p>
</div>
    
    <div class=" imgCategoryColumn">
      <img src="img/hp-makeup.png" alt="" class="img-fluid imgRound">
        <p>Make-up</p>
    </div>
    
    <div class=" imgCategoryColumn">
       <img src="img/hp-hair.png" alt="" class="img-fluid imgRound">
        <p>Kosa</p>

    </div>
    </div>
</div>


<div class="container center">





</div>




    
    

    <%@include file="page-includes/footer.jsp" %>

