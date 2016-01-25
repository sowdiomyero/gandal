<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<sec:authorize access="isAuthenticated()">

 
    <!-- Begin Body -->
    
    <div id="menu_actif_location" class="container" style="margin-top: 1px; margin-bottom: 0px;">        
            <div class="center-block" >
                  <center><h4><spring:message code="label.localisation.header.titre" /></h4></center>
             </div>           
    </div>

    <div class="container-fluid" style="margin-left: 5px; margin-right: 5px; ">    

        <div class="row" style="background: #EEE;border-top: 2px solid rgb(255,155,55);border-bottom: 2px solid rgb(255,155,55)"> filter area</div>
        <div class="row">


            <!--            Fiche objet localisable -->
            <div  class="col-md-3 resizable" style="min-height: 500px; overflow-y: auto; border-right: 1px solid #EEE;" >
                 <%@include file="./listeObjetsLocalisables.jsp" %>
            </div>

            <div class="col-md-8" style="margin-left: 10px">              

                <!--                Tableau utilisateur-->
                <div class="row" style="margin-bottom: 15px">
                    <div class="col-lg-12" style="margin-left: 10px">                         
                        
                        <div class="row "  id="localisationItemArea" >
                            <%@include file="./ficheLocalisation.jsp" %>
                        </div> 
                        
                    </div>
                </div>

            </div>
        </div>

    </div>
<script src="http://maps.googleapis.com/maps/api/js?sensor=false&extension=.js&output=embed"></script>
</sec:authorize>

