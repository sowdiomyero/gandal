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
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">
                            <span class="" aria-hidden="true"></span>
                        </button>
                        <h4 class="glyphicon glyphicon-user" id="Heading"> Modification Objet Localisable</h4>
                    </div>

                    <div class="modal-body" style="margin-left: 25px;">
                        <!-- FORMULAIRE DE MODIFICATION -->
                        <form method="POST" action="#" id="editlocalisationModalForm">
                            <div class="row" >
                                <!-- INFORMATIONS GENERALES -->
                                <div class="row" >
<!--                                    <fieldset>-->
                                        <h4>Informations</h4>
                                        <div class="row">
                                            <div class="col-lg-4 ">
                                                <label ><spring:message code="label.programme.panel.nom" />:</label>
                                             </div>
                                            <div class="col-lg-8 ">     
                                                <input name="nom" type="text" value="${localisationSelected.nomLocalisable}"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-4 ">
                                                <label ><spring:message code="label.programme.description" />:</label>
                                            </div>
                                            <div class="col-lg-8 "> 
                                                <input name="description" type="text"  value="${localisationSelected.description}"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-4 ">
                                                <label ><spring:message code="label.programme.etat" />:</label>
                                             </div>
                                            <div class="col-lg-8 ">     
                                                <input type="text"  value="${localisationSelected.etat}"/>
                                            </div>
                                        </div>

                                         <div class="row">
                                            <div class="col-lg-4 ">
                                                <label >Type :</label>
                                                 </div>
                                            <div class="col-lg-8 "> 
                                                <input type="text"  value="${localisationSelected.type}"/>
                                            </div>
                                        </div>

                                       <div class="row">
                                            <div class="col-lg-4 "> 
                                                <label >Categorie :</label>
                                             </div>
                                            <div class="col-lg-8 ">   
                                                <input type="text"  name="categorie"  value="${localisationSelected.typeLocalisation}"/>
                                            </div>
                                            
                                        </div>
<!--                                    </fieldset>-->

                                </div>

                                <div class="row">

<!--                                    <fieldset style="border: 1px solid green;">-->
                                        <h4>Personnes </h4>
                                        <div class="row">
                                            <div class="col-lg-4 ">  <!-- Début de la premiere ligne de la premiere colonne-->
                                                <label class="">Résponsable:</label>                                        
                                            </div>
                                            <div class="col-lg-8 ">
<!--                                                <input type="text" class="" name="attribution" value="${localisationSelected.attribution}"/>-->
                                                <select id="selectResponsable">
                                                    <option value="" label=""/> 
                                                    <c:forEach items="${responsables}" var="mapEntry" >
                                                            <option value="${mapEntry.key}" label="${mapEntry.value}"/> 
                                                        </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <label >Créateur: </label> 
                                            </div>
                                            <div class="col-lg-8">
                                                <input type="text" name="createur" value="${localisationSelected.createur}"/>
                                            </div>
                                        </div>
<!--                                    </fieldset>     -->
                                </div>   

                            </div>
                        </form>
                    </div>
                    <div class="modal-footer ">

                        <button type="submit" class="btn btn-success">
                            <span class="glyphicon glyphicon-ok-sign"></span> Valider
                        </button>
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove"></span> Annuler
                        </button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div> <!-- Fin Modal Modification Fiche -->
    </div>
</sec:authorize>