<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<sec:authorize access="isAuthenticated()">
    <div style="margin: 25px;">
        <div class="modal fade " id="localisationModalForm" tabindex="-1" role="dialog"
             aria-labelledby="localisationModalForm" aria-hidden="true" >
            <div class="modal-dialog modal-large2">
                <div class="modal-content" >
           <form:form commandName="localisationForm" action="${pageContext.request.contextPath}/localisation/update" method="post" id="editlocalisationModalForm">
            <div class="modal-header" style="background-color: rgb(255,155,55)">
                <h3 class="panel-title " style="color: white">Modification de ${localisationForm.nom}</h3>
            </div>
            <div class="modal-body" style="color: #000033">
                <fieldset><legend>Modification de <span >${localisationForm.nom} </span></legend>  

                    <label for="selectDtype"><spring:message code="label.localisation.tableau.header.dtype"/>: </label>
                    <form:input name="dT"  class="form-control" required="required"  path="dT" placeholder="description" id="selectDtype" readonly="true"/>
                                     
                    <label for="addDescLocalisationInput"><spring:message code="label.localisation.tableau.header.description"/> : </label>
                    <form:input name="description"  class="form-control" required="required"  path="description" placeholder="description" id="addDescLocalisationInput"/>

                    <label for="nomLocaliteCorrige"><spring:message code="label.localisation.tableau.header.nom"/>: </label>
                    <form:input name="nom" class="form-control nomLocaliteCorrige" id="nomLocaliteCorrige" required="required"  path="nom" placeholder="" />

                    

                    <c:if test="${localisationForm.dT == 'INCIDENT'}">
                        <label  for="graviteLocalisation"><spring:message code="label.localisation.tableau.header.gravite"/>: </label>
                      <%--  <form:input name="gravite"  class="form-control graviteLocalisation" id="graviteLocalisation" required="required"  path="gravite" value="${localisationForm.gravite}" /> --%>

                         <form:select id="graviteLocalisation" path="gravite" name="type" class="form-control">
                        <form:options items="${localisationForm.gravites}" />
                    </form:select>
                        
                    </c:if>

                    <c:if test="${localisationForm.dT == 'SITE'}">

                        <label  for="nbObjetsLocalisation"><spring:message code="label.localisation.tableau.header.nbobjets"/>: </label>
                        <form:input name="nbObjets" class="form-control graviteLocalisation" id="nbObjetsLocalisation" required="required"  path="nbObjets" value="${localisationForm.nbObjets}" />

                    </c:if>    

                    <c:if test="${localisationForm.dT == 'BATIMENT'}">
                        <label  for="nbNiveauxLocalisation"><spring:message code="label.localisation.tableau.header.nbniveaux"/>: </label>
                        <form:input name="nbNiveaux" class="form-control graviteLocalisation" id="nbNiveauxLocalisation" required="required"  path="nbNiveaux" value="${localisationForm.nbNiveaux}" />
                    </c:if>
                        
                     <label  for="typeLocalite"><spring:message code="label.localisation.tableau.header.type"/> : </label>
                    <form:select id="typeLocalite" path="type" name="type" class="form-control">
                        <form:options items="${localisationForm.typeIncidentOuLocalite}" />
                    </form:select>

                    <label  for="selectRattachement"><spring:message code="label.localisation.tableau.header.rattachement"/>: </label>
                    <form:select id="selectRattachement" path="rattachement" name="rattachement" class="form-control">
                        <form:option value="" label="" /> 
                        <form:options items="${localisationForm.rattachements}" itemLabel="nomLocalisable" itemValue="idLocalisation" />
                    </form:select>
                    
                    <form:input type="hidden" path ="oldResponsable" value="${localisationForm.oldResponsable}" />
                    <form:input type="hidden" path ="oldRattachement" value="${localisationForm.oldRattachement}" />
                     <form:input type="hidden" path ="idLocalisation" value="${localisationForm.idLocalisation}" />
                    
                    <label  for="selectResponsable">Responsable: </label>
                    <form:select id="selectResponsable" path="responsable" name="responsableAttribution" class="form-control">
                        <form:option value="" label=""/> 
                        <form:options items="${localisationForm.listResponsables}" itemLabel="fullName"  itemValue="idUser" />
                    </form:select>
                </fieldset>
            </div>
            <div class="modal-footer ">
                <button type="submit"  class="btn btn-info" value="submit"><spring:message code="label.bouton.valider"/></button>
                <button type="button" id ="closeLocalisationForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.fermer"/></button>
            </div>

        </form:form>                         
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div> <!-- Fin Modal Modification Fiche -->
    </div>
</sec:authorize>