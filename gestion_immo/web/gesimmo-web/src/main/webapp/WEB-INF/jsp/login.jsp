<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<sec:authorize access="isAnonymous()" >
    
    <div class="container" style="margin-bottom: 15px; padding-bottom: 15px">
        <img src="img/bg2.jpg" class="bg" />
        <div class="row" style="">
        <div class="col-lg-3"></div>

        <div class="col-lg-6" style=" margin-top: 100px">
            
            <div id="" class="panel-group" >

                <div class="panel panel-default" style="background-color: rgba(255,255,255,0.90);color: white; font-size: 16px">

                     <div style="font-weight: bold; color:rgb(255,155,55); font-size: 20px; text-align: center;"><spring:message code="label.title"  /></div>
                    <center>
                        
                        <a href="?locale=fr" style="color: rgb(53,204,204); font-weight: bold" >Français</a> | <a style="color: rgb(53,204,204); font-weight: bold" href="?locale=en" >English</a> 
                    
                    </center>


                    <div class="panel-heading" style="background-color: rgba(255,255,255,0.47)">
                        <form class="form-signin" role="form" action='<c:url value='j_spring_security_check' />' method="post">
                            
                            <div style="margin-bottom: 25px; margin-top: 30px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input name="j_username" type="text" class="form-control"
                                       placeholder="login" required autofocus></div>
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="fa fa-unlock-alt fa-17"></i></span>
                                <input
                                    name="j_password" type="password" class="form-control"
                                    placeholder="Password" required></div> 
                            <label class="checkbox">
                                <input style="font-weight: bold;" type="checkbox" value="remember-me"> <spring:message code="label.session.active"  />
                            </label>

                            <div class="label">
                                <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #CCD4D9; border-color: #CCD4D9; color: #000"><spring:message code="label.bouton.connecter"  />
                                </button>
                            </div>
                        </form>
                        <div class="text-center">
                            <a href="forgotPassword" style="font-weight: bold;"><spring:message code="label.mot.passe.oublie"  /></a>
                        </div>
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
        </div>

        <div class="col-lg-3"> </div>
    </div>

</div>

</sec:authorize>