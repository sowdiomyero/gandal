<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="sn.gandal.gesimmo.modele.client.entities.TableConfig" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="isAuthenticated()">
    <c:if test="${localisationForm.resultat == 1100}">

        <form:form commandName="localisationForm" action="${pageContext.request.contextPath}/addLocalisation" method="post" id="addLocalisationFormulaire">
            <div class="panel-heading" style="background-color: rgb(193,25,83)">
                <h3 class="panel-title " style="color: white"><spring:message code="label.localisation.modal.editer.panel.body.legend" /></h3>
            </div>
            <div class="panel-body" style="color: #000033">
                <fieldset><legend><spring:message code="label.localisation.modal.ajouter.panel.heading" /></legend>  

                    <label class="pull-left" for="selectDtype"><spring:message code="label.localisation.tableau.header.dtype"/>: </label>
                    <form:select id="selectDtype" path="dT" class="form-control " multiple="single">
                        <form:options items="${localisationForm.dType}" />
                    </form:select>
                    <label class="pull-left" for="nomLocaliteGoogle"><spring:message code="label.localisation.tableau.header.nom"/>  (Google) </label>
                    <form:input class="form-control nomLocaliteGoogle" path="nomLocaliteGoogle" id="nomLocaliteGoogle" required="required"  placeholder="Nom Fourni par Google" readonly="true"/>


                    <label for="addDescLocalisationInput"><spring:message code="label.localisation.tableau.header.description"/> : </label>
                    <form:input class="form-control" required="required"  path="description" placeholder="description" id="addDescLocalisationInput"/>

                    <label class="pull-left" for="gps"><spring:message code="label.localisation.tableau.header.coordonnees"/> : </label>
                    <form:input class="form-control" id="gps" path="coordonnees" required="required" placeholder="Les Coordonnées Géographiques" type="text" readonly="true"/>

                    <label class="pull-left" for="nomLocaliteCorrige"><spring:message code="label.localisation.tableau.header.nom"/>  (Correction): </label>
                    <form:input class="form-control nomLocaliteCorrige" id="nomLocaliteCorrige" required="required"  path="nom" placeholder="" />

                    <label class="pull-left" for="typeLocalite"><spring:message code="label.localisation.tableau.header.type"/> : </label>
                    <form:select id="typeLocalite" path="type" class="form-control">
                        <form:options items="${localisationForm.typeIncidentOuLocalite}" />
                    </form:select>

                    <label class="pull-left" for="selectRattachement"><spring:message code="label.localisation.tableau.header.rattachement"/>: </label>
                    <form:select id="selectRattachement" path="rattachement" class="form-control">
                        <form:option value="" label=""/> 
                        <c:forEach items="${localisationForm.rattachements}" var="e" >
                                <form:option value="${e.idLocalisation}" label="${e}"/> 
                            </c:forEach>
                    </form:select>

                    <c:if test="${localisationForm.dT == 'INCIDENT'}">

                        <label class="pull-left" for="graviteLocalisation"><spring:message code="label.localisation.tableau.header.gravite"/>: </label>
                        <form:input class="form-control graviteLocalisation" id="graviteLocalisation" required="required"  path="gravite" placeholder="" />

                    </c:if>

                    <c:if test="${localisationForm.dT == 'SITE'}">

                        <label class="pull-left" for="nbObjetsLocalisation"><spring:message code="label.localisation.tableau.header.nbobjets"/>: </label>
                        <form:input class="form-control graviteLocalisation" id="nbObjetsLocalisation" required="required"  path="nbObjets" placeholder="Nombre d'objets" />

                    </c:if>    

                    <c:if test="${localisationForm.dT == 'BATIMENT'}">

                        <label class="pull-left" for="nbNiveauxLocalisation"><spring:message code="label.localisation.tableau.header.nbniveaux"/>: </label>
                        <form:input class="form-control graviteLocalisation" id="nbNiveauxLocalisation" required="required"  path="nbNiveaux" placeholder="Nombre de Niveaux" />


                    </c:if>
                        
                    <label class="pull-left" for="selectResponsable">Responsable: </label>
                    <form:select id="selectResponsable" path="responsable" class="form-control">
                        <form:option value="" label=""/> 
                        <c:forEach items="${localisationForm.responsables}" var="mapEntry" >
                                <form:option value="${mapEntry.key}" label="${mapEntry.value}"/> 
                            </c:forEach>
                    </form:select>
                </fieldset>
            </div>
            <div class="modal-footer ">
                <button type="submit" id="formAddLocalisationButton"  class="btn btn-info" value="submit"><spring:message code="label.bouton.valider"/></button>
                <button type="button" id ="closeLocalisationForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.fermer"/></button>
            </div>

        </form:form>
    </c:if>
    <c:if test="${localisationForm.resultat != 1100}">
        <div class="panel-heading" style="background-color: rgb(193,25,83)">
            <h3 class="panel-title " style="color: white"><spring:message code="label.localisation.modal.editer.panel.body.legend" /></h3>
        </div>
        <div class="panel-body" style="color: #000033">
            ${localisationForm.msg}
        </div>
         <div class="modal-footer ">
                <button type="button" id ="closeLocalisationForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.fermer"/></button>
            </div>
    </c:if>
</sec:authorize>

