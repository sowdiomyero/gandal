 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="sn.gandal.gesimmo.modele.client.entities.TableConfig" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<form:form commandName="localisationFormFilter" action="${pageContext.request.contextPath}/localisationListFilter" method="post" id="localisationFilterFormulaire">
    <div class="panel-heading bgcode">
        <div>
            <h3 class="panel-title ">Recherche</h3>
        </div>
    </div>

    <div class="panel-body">
        <div class="row">
            <fieldset>
                <!-- Form Name -->
                <legend>Formulaire de recherche</legend>
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

                <c:if test="${localisationFormFilter.dT == 'LOCALITE'}">
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
                        <button id="btnSearch" type="submit" name="rechercher" class="btn btn-success" style="font-size: 11px">Search</button>
                        <button id="btnSendSms" type="button" name="envoyerSms" class="btn btn-default" style="font-size: 11px">Sms</button>
                        <button id="btnSendSms" type="button" name="envoyerEmail" class="btn btn-default" style="font-size: 11px">Email</button>

                    </div>
                </div>
                </div>
            </fieldset>
        </div>
    </div>
</form:form>


