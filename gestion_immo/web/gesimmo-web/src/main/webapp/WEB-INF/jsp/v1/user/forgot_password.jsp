<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAnonymous()" >
    
    <div id="accordion" class="panel-group" >
        <h4 style="font-weight: bold;">Récupération de mot de passe</h4>
        <div class="panel panel-default">

            <div class="panel-heading">
                <form class="form-signin" role="form"
                      action="${pageContext.request.contextPath}/user/regenererPassword" method="post" id="forgotPassword"> 
                    <h4 style="font-weight: bold;" class="form-signin-heading">Entrez vos informations</h4>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input name="login" type="text" class="form-control"
                               placeholder="login" required autofocus></div>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                        <input
                            name="email" type="email" class="form-control"
                            placeholder="email" required></div> 
                    
                    
                    <div class="label">
                        <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #CCD4D9; border-color: #CCD4D9; color: #000">Soumettre
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <c:if test="${not empty error}">
        <div>
            <font color="red" style="font-weight: bold;"><br /> Login ou mot de passe invalide</font>
        </div>
        <div>
            <font color="red" style="font-weight: bold;"><br /> ${error}</font>
        </div>
    </c:if>
</sec:authorize>