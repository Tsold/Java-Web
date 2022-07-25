    <%-- 
    Document   : login
    Created on : 13-Dec-2021, 17:46:38
    Author     : tsold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <%@include file="page-includes/header.jsp" %>

    <div class="container center">

        <h2 class="basket">

            Prijava

        </h2>
    </div>



    <div class="container  login ">
        <div class="col center">

            <div class=" logCol">
                <p class="loginP">
                   Username
                </p>

                <form action="User-Login" method="post">             

                    <input type="text" class="form-control" placeholder=""  name="Login-UserName" required
                           aria-describedby="basic-addon2">


  
                    <p class="loginP">
                        Lozinka
                    </p>

 
                    <input type="password" class="form-control" name="Login-Password" placeholder="" required
                           aria-describedby="basic-addon2">


                    <div class="loginbtn center marginTop ">  


                        <input class="blackButton" type="submit" value="Login" name="Login" />
                        
                        
                        
                         </div>

                </form>
                
         


         

            <div class="center loginP">

                <div class="row">
                  
                </div>


            </div>




        </div>
    </div>


</div>



<%@include file="page-includes/footer.jsp" %>
</html>
