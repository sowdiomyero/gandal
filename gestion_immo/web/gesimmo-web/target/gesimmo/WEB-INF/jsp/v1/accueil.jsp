<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--Google map begin-->
<script src="http://maps.googleapis.com/maps/api/js?sensor=false&extension=.js&output=embed"></script> 
<script src="js/cartographie.js"></script> 
<!--Google map begin End-->

 
<sec:authorize access="isAuthenticated()">

    <!-- Begin Body -->
    <div id="menu_actif_location" class="container" style="margin-top: 1px; margin-bottom: 5px;">        
            <div class="center-block" >
                  <center><h4><spring:message code="label.localisation.header.titre" /></h4></center>
             </div>           
    </div>
    <div class="container-fluid" style="margin-left: 5px; margin-right: 5px;">    

        <div class="row">
            <!--            Menu Administration-->
            
            <!--         Carte -->
            <div class="col-md-10" >
                <div id="map-canvas" style="width: 100%; height: 800px;"></div>
                
                <div id="legend" style="background-color: greenyellow; padding: 5px; margin: 3px">
                    <h4>LEGENDE</h4>
                    <div>
                        <img src="img/markers/hotel_0star.png" /> Hotels
                    </div>
                    <div>
                        <img src="img/markers/villa.png" /> Batiments
                    </div>
                  </div>
            </div>
            <!--         Carte -->
            
            <div id="panelRechercheLocalisationCarte" class="col-md-2" >
                 
                <form:form commandName="localisationFormFilter" action="${pageContext.request.contextPath}/localisationListFilter" method="post" id="localisationFilterFormulaire">
    <div class="panel-heading bgcode" >
        <div>
            <h3 class="panel-title ">Recherche</h3>
        </div>
    </div>

    <div class="panel-body">
        <div class="row">
            <fieldset>
                <!-- Form Name -->
                <legend>Formulaire</legend>
                <!-- Select Basic -->
                <label class="label label-default" for="selectDtypeRecherche"><spring:message code="label.localisation.tableau.header.dtype"/>: </label>
                <form:select id="selectDtypeRecherche" path="dT" class="form-control " multiple="single">
                    <form:options items="${localisationFormFilter.dType}" />
                </form:select>
                <label class="label label-success" >  Nom :</label>
                <form:input class="form-control"   path="nom" placeholder="nom" />
                <label class="label label-success" >  Description:</label>
                <form:input class="form-control"   path="description" placeholder="description" />
               
                <label class="label label-default" for="selectDtypeRecherche">Type: </label>
                <form:select id="typeLocalite" path="type" class="form-control">
                    <form:options items="${localisationFormFilter.typeIncidentOuLocalite}" />
                </form:select>

                <c:if test="${localisationFormFilter.dT == 'BATIMENT'}">
                    formulaire localisation<br>
                </c:if>
                <c:if test="${localisationFormFilter.dT == 'INCIDENT'}">
                    formulaire incident<br>
                </c:if>
                    <c:if test="${localisationFormFilter.dT == 'SITE'}">
                    formulaire incident<br>
                </c:if>
                <!-- Textarea -->
                <label class="label label-default" for="textarea">Message</label>
                <textarea class="form-control" id="textarea" name="message" placeholder="Composer ici votre message"></textarea>
                <!-- Button (Double) -->
                <div class="row">
                    <div class="col-md-1"></div>
                    <div  class="col-md-11 btn-group" role="group">
                        <button id="btnSearch" type="submit"  class="btn btn-success" style="font-size: 11px">Search</button>
                        <button id="btnSendSms" type="button"  class="btn btn-default" style="font-size: 11px">Sms</button>
                        <button id="btnSendEmail" type="button"  class="btn btn-default" style="font-size: 11px">Email</button>
                    </div>
                </div>
            </fieldset>
        </div>
    </div>
</form:form>
                
            </div>
        </div>

    </div>
    <!--Ajout d'un objet localisable-->

    <div class="modal fade modal-admin" id="addLocalization" tabindex="-1" role="dialog" aria-labelledby="addLocalization" aria-hidden="true">


        <div class="col-md-4  col-lg-offset-4"  style="margin-top:  25px" >
            <div class="panel panel-info" id="ficheLocalite"  style="border-color: rgb(193,25,83)">
                

            </div>

        </div>


    </div>



</sec:authorize>

