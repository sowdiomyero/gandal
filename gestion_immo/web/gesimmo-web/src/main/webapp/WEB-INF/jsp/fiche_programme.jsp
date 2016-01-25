<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<sec:authorize access="isAuthenticated()">
    <div class="container-fluid" style="margin-left: 25px; margin-right: 25px;">    
        <div class="row">
            <div class="panel panel-default" style="margin-bottom: 10px; ">
                <div class="panel-heading text-center">
                    <h2><spring:message code="label.programme.header.titre" /></h2>
                </div>
            </div>
        </div>
        <div class="row">
            <!--Menu Administration-->
            <div class="col-md-2" >
                <%@include file="./menus/menu_programme.jsp" %>
            </div>
            <div class="col-md-10">
                <!-- Meteo Projet-->
                <div class="row">
                    <%--<%@include file="./meteo/meteo_programme.jsp" %>--%>
                </div>  
                <!--Bouton  Projet-->
                <div class="row" style="padding-left: 8px;">
                    <%@include file="./fragment/programme/programme_menu_buttons.jsp" %>
                </div>
                <!-- anel Information  Projet-->
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="./fragment/programme/programme_info_generales.jsp" %>
                    </div>
                    <div class="col-lg-12">
                        <ul class="nav nav-tabs nav-border-color nav-inverse" id="myTab">
                            <li class="active"> <a data-toggle="tab" href="#budgetProgrammeMenuJournaux">Budget</a></li>
                            <li > <a data-toggle="tab" href="#ficheProgrammeMenuJournaux">Journal</a></li>
                            <li><a data-toggle="tab" href="#ficheProgrammeMenuCycleDeVie">Historique états</a></li>
                            <li><a data-toggle="tab" href="#ficheProgrammeMenuEtapes">Etapes</a></li>
                            <li><a data-toggle="tab" href="#ficheProgrammeMenuContacts">Contacts</a></li>
                            <li><a data-toggle="tab" href="#ficheProgrammeMenuIndicateurs">Indicateurs</a></li>
                            <li><a data-toggle="tab" href="#ficheProgrammeMenuCommentaire">Commentaires</a></li>
                        </ul>
                        <div class="tab-content" id="myTabContent" >
                            <!--tab budget-->
                            <div id="budgetProgrammeMenuJournaux" class="row tab-pane fade active in">
                                <br/>
                                <%@include file="./fragment/programme/programme_budget.jsp" %>
                            </div>
                            <!--tab journal-->
                            <div id="ficheProgrammeMenuJournaux" class="row tab-pane fade">
                                <br/>
                                <%@include file="./fragment/programme/programme_tableau_journal.jsp" %>
                            </div>
                            <!--tab cycle de vie-->
                            <div id="ficheProgrammeMenuCycleDeVie" class="tab-pane fade">
                                <br/>
                                <%@include file="./fragment/programme/programme_tableau_cycle_de_vie.jsp" %>
                            </div>
                            <!--tab   etapes-->
                            <div id="ficheProgrammeMenuEtapes" class="tab-pane fade">
                                <br/>
                                <%@include file="./fragment/programme/programme_tableau_etapes.jsp" %>
                            </div>
                            <!--tab   contacts-->
                            <div id="ficheProgrammeMenuContacts" class="tab-pane fade">
                                <br/>
                                <%@include file="./fragment/programme/programme_contacts.jsp" %>
                            </div>
                            <!--tab   indicateurs-->
                            <div id="ficheProgrammeMenuIndicateurs" class="tab-pane fade">
                                <br/>
                                <%@include file="./fragment/programme/programme_tableau_indicateurs.jsp" %>
                            </div>
                            <!--tab   indicateurs-->
                            <div id="ficheProgrammeMenuCommentaire" class="tab-pane fade">
                                <br/>
                                <%@include file="./fragment/programme/programme_commentaires.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- Editer programme-->
        <div class="modal fade modal-admin" id="viewProjetModal" tabindex="-1" role="dialog" aria-labelledby="viewProjetModal" aria-hidden="true">
            <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
                <div class="panel panel-info" style="border-color: rgb(193,25,83)">
                    <form commandName="editProjetForm" action="${pageContext.request.contextPath}/editProjet" method="post" id="editProjetFormulaire">
                        <div class="panel-heading" style="background-color: rgb(193,25,83)">
                            <h3 class="panel-title " style="color: white">Editer un programme</h3>
                        </div>
                        <div class="panel-body" style="color: #000033">
                            <div class="alert alert-success" style="display: none" role="alert" id="programmeEditSuccess">
                                <div class="alert alert-danger" role="alert" style="display: none" id="programmeEditFailed"></div>
                            </div>
                            <fieldset><legend>Informations du programme</legend>
                                <div id="1">
                                    <label for="editNomProjet">Nom du programme</label>
                                    <input type="text" class="form-control" required="required"  name="nomActivite" placeholder="Nom" id="editNomProjet"/>
                                    <label for="editDescProjet">Description du programme</label>
                                    <input type="text" class="form-control" name="description" required="false" placeholder="Description" id="editDescProjet"/>
                                </div>
                                <div id="2">
                                    <label for="editDateDebPrevuProjet">Date debut prevu</label>
                                    <input type="date" class="form-control" name="dateDebPrevu" required="false" placeholder="Date debut prevu" id="editDateDebPrevuProjet"/>
                                    <label for="editDateFinPrevuProjet">Date fin prevu</label>
                                    <input type="date" class="form-control" name="dateFinPrevu" required="false" placeholder="Date fin prevu" id="editDateFinPrevuProjet"/>
                                </div>
                                <div id="3">
                                    <label for="dateFinProjet">Budget prévu </label>
                                    <input  class="form-control"   name="budgetPrevu" required="required" placeholder="budgetPrevu" id="editBudgetPrevuProjet"/>
                                </div>
                                <div id="4">       
                                    <label for="dateFinProjet">Nombre Emploi prévu </label>
                                    <input  class="form-control"  name="nbreEmploiPrevu" required="required" placeholder="nbreEmploiPrevu" id="editNbreEmploiPrevuProjet"/>
                                </div>
                            </fieldset>
                        </div>
                        <div class="modal-footer ">
                            <button type="submit" id="formEditProjetButton" onClick="" class="btn btn-info" value="submit">Valider</button>
                            <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning">Annuler</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Detail journal-->
        <div class="modal fade modal-admin" id="viewJournalModal" tabindex="-1" role="dialog" aria-labelledby="viewJournalModal" aria-hidden="true">

            <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
                <div class="panel panel-info" style="border-color: rgb(193,25,83)">
                    <c:url value="${pageContext.request.contextPath}/journal/update" var="urlChangerEtatJournal" />
                    <form  action="${pageContext.request.contextPath}/journal/update" method="get" id="editJournalFormulaire">
                        <div class="panel-heading" style="background-color: rgb(193,25,83)">
                            <h3 class="panel-title " style="color: white">Details du journal</h3>
                        </div>
                        <div class="panel-body" style="color: #000033">
                            <div class="alert alert-success" style="display: none" role="alert" id="programmeEditSuccess">
                                <div class="alert alert-danger" role="alert" style="display: none" id="programmeEditFailed"></div>
                            </div>
                            <fieldset><legend>Informations du journal</legend>
                                <input type="hidden" value="" id="editIdJournalHiddenProgramme"/>
                                <label for="editLibelleJournal">Libelle</label>
                                <input type="text" class="form-control"  required="true" placeholder="Libelle" id="editLibelleJournalProgramme"/>
                                <label for="editDescriptionJournal">Description</label>
                                <input type="text" class="form-control" required="true" placeholder="Description" id="editDescriptionJournalProgramme"/>
                            </fieldset>
                        </div>
                        <div class="modal-footer ">
                            <button type="button" id="formEditJournalProgrammeButton" class="btn btn-info" value="submit">Valider</button>
                            <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning">Annuler</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </sec:authorize>

