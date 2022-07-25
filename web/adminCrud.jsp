<%-- 
    Document   : adminCrud
    Created on : 24-Dec-2021, 13:14:54
    Author     : tsold
--%>

<%@page import="hr.algebra.model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="page-includes/header.jsp" %>
    
    <%
  User auths = (User) request.getSession().getAttribute("auth");
   List<Category> catList = (List<Category>) request.getSession().getAttribute("catList");
    List<Product> products = (List<Product>) request.getSession().getAttribute("allProducts");
  
if (auths.getRoleID() == 1) {
        
    
    
    %>
    
           
    <div class="container center marginTop">

        <h2 class="basket">

            Add Category

        </h2>
    </div>
    
            <div class="container basketBorders">


         
                        <div class="row basketMargins">
          <div class="col-sm">
                 <form method="post" action="add-category">
                <div class="row">
                    

                           
                     <div class="topContainer col center">
                     <input class="medium" name="Category"  placeholder="Category Name" type="text" required>
                      
                    </div>
                  
                  
                    <div class="topText col-auto">
                        
                          <input type="submit" value="Add"  />
                      
                    </div>
              
                </div>
                           </form>
            </div>
                     <form class="row" method="post" action="delete-category">        
                            
                                    <div class="col-auto-sm horizontalCenter marginLeftBasket ">
      <select  name="selectedValue" class="basketSelector " required>
          <%
        
          for(Category cat : catList)
          {

          %>
        <option ><%=cat.getIDCategory() +" "+ cat.getType()%></option>
          <%
          }
          %>
       
         
      </select>
        </div>


        



        <div class="col-auto-sm horizontalCenter ">
                  <input type="submit" value="Delete"  />
           </div>
            </form>
   
 </div>
            

       
    </div>

    
    
  
                
        
    <div class="container center marginTop">

        <h2 class="basket">

            Add Product

        </h2>
    </div>
    
    
    
        <div class="container basketBorders">


            <form method="post" action="add-product" enctype="multipart/form-data">
                        <div class="row basketMargins">
          <div class="col-sm">
                <div class="row">
                    <div class="topContainer col center">
                     
                        <input type="file" name="picture" class="form-control-file" required>
                    </div>
                  
                    <div class="topText col-auto">
                        <input class="medium" name="Maker"  type="text" placeholder="Maker" required>
                        <input class="medium" name="Model" type="text" placeholder="Model" required>
                        <input class="medium" name="Des"  type="text" placeholder="Description" required>
                    </div>
                </div>
            </div>


        


        <div class="col-auto-sm horizontalCenter marginLeftBasket ">
      <select  name="selectedValue" class="basketSelector " required>
          <%
        
          for(Category cat : catList)
          {

          %>
        <option ><%=cat.getIDCategory() +" "+ cat.getType()%></option>
          <%
          }
          %>
       
         
      </select>
        </div>


        <div class="col-auto-sm horizontalCenter ">
           <input class="small" name="Price" type="text" placeholder="Price" required>
           <input type="submit" value="Add"  />
           </div>
   
 </div>
            
</form>
       
    </div>
    
    
    
    
    
    <div class="container center marginTop">

        <h2 class="basket">

            Edit Products

        </h2>
    </div>
    
    
    <%
   
    
    for(Product prod : products){
       
    
    %>
    
        <div class="container basketBorders">


            <form method="post" action="manage-product?id=<%=prod.getIDProduct()%>" enctype="multipart/form-data">
                        <div class="row basketMargins">
          <div class="col-sm">
                <div class="row">
                    <div class="topContainer col">
                        <img src="img/<%=prod.getProductName()%>.png" alt="" class="img-fluid basketImg">
                        <input type="file" name="picture" class="form-control-file">
                    </div>
                  
                    <div class="topText col-auto">
                        <input class="medium" name="Maker" value="<%=prod.getMaker()%>" type="text">
                        <input class="medium" name="Model" value="<%=prod.getProductName()%>" type="text">
                        <input class="medium" name="Des" value="<%=prod.getShortDescription()%>" type="text">
                    </div>
                </div>
            </div>


        


        <div class="col-auto-sm horizontalCenter marginLeftBasket ">
      <select  name="selectedValue" class="basketSelector ">
          <%
         String selected = "";
          for(Category cat : catList)
          {
              if (cat.IDCategory == prod.getProductTypeID() ) {
                      selected = cat.Type;
                  }
          %>
        <option ><%=cat.getIDCategory() +" "+ cat.getType()%></option>
          <%
          }
          %>
        <option  style="Display:none;" selected > <%=prod.getProductTypeID()+" "+ selected%> </option>
          

      </select>
        </div>


        <div class="col-auto-sm horizontalCenter ">
           <input class="small" name="Price" value="<%=prod.getPrice()%>" type="text">
           <input type="submit" value="Update"  />
            <a href="manage-product?id=<%=prod.getIDProduct()%>"><p class="basketP">Delete</p></a>
           
           </div>
    <input style="Display:none;" name="oldName" value="<%=prod.getProductName()%>" type="text">
 </div>
            
</form>
       
    </div>
    <%
    
    }
    }
    
    
    %>
    
    
    

    
    <%@include file="page-includes/footer.jsp" %>
</html>
