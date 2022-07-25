
<%@page import="hr.algebra.model.Category"%>
<%@page import="java.util.List"%>
<%@page import="hr.algebra.repository.RepoFactory"%>
<%@page import="hr.algebra.repository.ISqlRepo"%>
<%@page import="hr.algebra.model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hr.algebra.model.User"%>
<%

    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }
      String cartItems;
      int inCart = 0;

      
      
    if (true) {
             List<Cart> cartList = (List<Cart>)request.getSession().getAttribute("cart_list");
             if (cartList != null) {
                     for(Cart c : cartList){
                     inCart += c.getQuantity();
                 }
             }
        }
          if (inCart == 0) {
              cartItems= "";
          }
      else{
      cartItems = Integer.toString(inCart);
      }

    ISqlRepo navRepo = RepoFactory.getRepository();
    List<Category> categories = navRepo.getAllCategories();
    request.getSession().setAttribute("catList", categories);
        
%>

<body>
    
<div id="mySidenav" class="sidenav">

      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <% 
    for(Category cat : categories){
    
    %>
<a href="selected-category?id=<%=cat.getIDCategory()%>" class=""><p><%=cat.getType()%></p></a>

   <% 
    
    }
    %>
    
    
       <% 
    if (auth != null && auth.getRoleID() == 1) {
            
        

    
    %>
    
    
    <a href="admin-controls" class=""><p>Upravljanje Webshopom</p></a>
    <a href="login-info" class=""><p>Login Info</p></a>
    <a href="admin-orders" class=""><p>Narudzbe</p></a>
         
    
    
      <% 
    
    }
    %>
</div>


    <!-- main nav-->
    <div class="container-fluid">
    <div class="row invisDel">

        <div class="disnone col-3">
        <div class="row">
        <div class="topContainer">
            <img src="img/dostavaTruck.png" alt="" class="img-fluid">
        </div>
        <div class="topText col-auto">
           <h2>BESPLATNA DOSTAVA NA SVE</h2>
           <p>KUPITE</p>
        </div>
    </div>
</div>

<div class="disnone col-3">
        <div class="row">
        <div class="topContainer">
            <img src="img/ParfemTopNav.png" alt="" class="img-fluid">
        </div>
        <div class="topText col-auto">
           <h2>TOP PARFEMI</h2>
           <p>PO AKCIJSKIM CIJENAMA</p>
        </div>
    </div>
</div>


<div class="disnone col-3">
<div class="row">
        <div class="topContainer">
            <img src="img/90dana.png" alt="" class="img-fluid">
        </div>
        <div class="topText col-auto ">
           <h2>KUPOVINA BEZ BRIGE</h2>
           <p>90 DANA ZA POVRAT ROBE</p>
        </div>
    </div>
</div>




            <div class="disnone topNumber col center invisDel">
               <h2>Nazovite +385(01) 775 72 97
            </h2>
            </div>

            <div class="disnone topNumber col center invisDel">
              <p>CIJENA DOSTAVE I PLA?ANJE</p>
             </div>




    </div>

    </div>
    <header class="header center">
        <div class="container-fluid marginTop">
            <div class="row marginLeft">
                <div class="col-sm-auto">
                    <div class="header__logo center">
                       
                                          <button onclick="openNav()" type="button" id="sidebarCollapse" class="visible btn btn1">
                        <i class="fas fa-align-left"></i>
                    </button>
                        <a style="text-decoration:none" href="index.jsp"><h2>NOTINO</h2></a>
                    </div>
                </div>
                <div class="col center invisDel">
                        <div class="invisDel input-group mb-3">
                            <input type="text" class="invisDel form-control" placeholder="Trazi parfem, kozmetiku, brend" aria-label="Recipient's username" aria-describedby="basic-addon2">
                            </div>
                </div>
                <div class="col-sm-auto center margins">
                       <div class="row">
                                    <%

                                        if (auth != null)
                                        {
                                        
                                        }
                                    %>
                      <a href="User-Login">   <i class="fas fa-user-alt white col" ></i></a>
                      
                                                          <%

                                        if (auth != null)
                                        {
                                        
                                        }
                                    %>
                                    <a href="shopping-cart">    <i class="fas fa-shopping-basket white col"><%=cartItems%>  </i></a>
                     <p class="invisDel col">Kosarica</p> 
                    </div>
                </div>
                      
            </div>
          
                     
        </div>
    </header>

    <div class="container-fluid bottomNav black ">
<div class="invisDel row marginLeft">
    
    <% 
    for(Category cat : categories){
    
    %>
<a href="selected-category?id=<%=cat.getIDCategory()%>" class=""><p><%=cat.getType()%></p></a>

   <% 
    
    }
    %>
    
    
       <% 
    if (auth != null && auth.getRoleID() == 1) {
            
        

    
    %>
    
    
    <a href="admin-controls" class=""><p>Upravljanje Webshopom</p></a>
    <a href="login-info" class=""><p>Login Info</p></a>
    <a href="admin-orders" class=""><p>Narudzbe</p></a>
         
    
    
      <% 
    
    }
    %>
</div>
    </div>
    <!-- main nav end-->