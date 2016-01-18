<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>





<sec:authorize access="isAuthenticated()">
    <script>
        $(document).on('click', '.panel-heading span.clickable', function(e) {
            var $this = $(this);
            if (!$this.hasClass('panel-collapsed')) {
                $this.parents('.panel').find('.panel-body').slideUp();
                $this.addClass('panel-collapsed');
                $this.find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
            } else {
                $this.parents('.panel').find('.panel-body').slideDown();
                $this.removeClass('panel-collapsed');
                $this.find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
            }
        })</script>

    <div class="container-fluid" style="margin: 25px;">    


        <div class="row">
            <div class="panel panel-default" style="margin-top: 10px; margin-bottom: 10px; ">
                <div class="panel-heading text-center">
                    <h2><spring:message code="label.etape.header.titre" /></h2>
                </div>
            </div>
        </div>


        <div class="row">


            <!--            Menu Administration-->
            <div class="col-md-2" >
                <%@include file="./menus/menu_projet.jsp" %>
            </div>


            <div class="col-md-10">

                <!--            Meteo Etape-->
                <div class="row">
                    <%@include file="./meteo/meteo_etape.jsp" %>
                </div>  

                <!--            Bouton  Etape-->
                <div class="row" style=" ">
                    <div class="row" style="padding: 4px; margin: 4px; float: left">
                        <a onclick="" href="#">
                            <button 
                                class="btn btn-primary btn-xs" id="btnPlanifier"  >
                                <span class="glyphicon glyphicon-calendar"></span> <spring:message code="label.etape.bouton.planifier"/>
                            </button>
                        </a>
                    </div>
                    <div class="row" style="padding: 4px; margin: 4px; float: left">
                        <a onclick="" href="#"   id="demarrerProjet"><button    class="btn btn-info btn-xs" id="btnDemarrer ">
                                <span class="glyphicon glyphicon-play"></span> <spring:message code="label.etape.bouton.demarrer"/>
                            </button></a>
                    </div>

                    <div class="row" style="padding: 4px; margin: 4px; float: left">
                        <a onclick=" " href="#"><button    class="btn btn-warning btn-xs" id="btnSuspendre">
                                <span class="glyphicon glyphicon-pause"></span> <spring:message code="label.etape.bouton.suspendre"/>
                            </button></a>
                    </div>

                    <div class="row" style="padding: 4px; margin: 4px; float: left">
                        <a onclick=" " href="#"><button     class="btn btn-success btn-xs" id="btnTerminer">
                                <span class="glyphicon glyphicon-ok"></span> <spring:message code="label.etape.bouton.terminer"/>
                            </button></a>
                    </div>
                    <div class="row" style="padding: 4px; margin: 4px; float: left">
                        <a onclick=" " href="#"><button    class="btn btn-danger btn-xs" id="btnAbandonner">
                                <span class="glyphicon glyphicon-stop"></span> <spring:message code="label.etape.bouton.abandonner"/>
                            </button></a>
                    </div>
                    <div class="row" style="padding: 4px; margin: 4px; float: left">
                        <button class="showEtape btn btn-info btn-xs" data-toggle="modal"  
                                data-target="#" id="btnModifier"  data-id="${etape.idActivite}">
                            <span class="glyphicon glyphicon-pencil"></span> <spring:message code="label.etape.bouton.modifer"/>
                        </button>
                    </div>

                </div>



                <!--            Panel Information  Etape-->
                <div class="row">
                    <div class="col-lg-6">

                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieEtape " data-toggle="modal" style="color: black"
                                           onclick="modifierEtape(1)"   data-id="${etape.idActivite}"    data-target="#UpdateDetailsLocalisation"    ><span
                                                class="glyphicon glyphicon-pencil"></span></a>
                                    </div>
                                    <div>
                                        <h3 class="panel-title "><spring:message code="label.activite.fiche.panel.information"/></h3>
                                        <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>

                                </div>

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.nom" />:</label>
                                        </div>
                                        <div class="col-sm-5"> ${etape.nomActivite}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.description" />:</label>
                                        </div>
                                        <div class="col-sm-5">${etape.description}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.etat" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="badge badge-info" role="alert">DEMARRE
                                            </div></div>
                                    </div>


                                </div>
                            </div>

                        </div>

                        <!--            Panel Information  ETAPE-->             

                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right  btnModifierPartieEtape" data-toggle="modal" style="color: black"
                                           data-id="${etape.idActivite}" onclick="modifierEtape(6)"   ><span
                                                class="glyphicon glyphicon-pencil"></span></a>
                                    </div>
                                    <div>
                                        <h3 class="panel-title "><spring:message code="label.activite.fiche.panel.ponderation"/></h3>
                                        <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>

                                </div>

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.ponderation" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="progress">
                                                <div class="progress-bar" role="progressbar" aria-valuenow="60"
                                                     aria-valuemin="0" aria-valuemax="100"
                                                     style="width: ${etape.ponderation}%;">${etape.ponderation}%</div>
                                            </div></div>
                                    </div>



                                </div>
                            </div>

                        </div>

                        <!--            Panel Information  JOURNAL-->             

                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieEtape " data-toggle="modal" style="color: black"
                                           data-target="#UpdateDetailsLocalisation"   ><span
                                                class=""></span></a>
                                    </div>
                                    <div>
                                        <h3 class="panel-title "><spring:message code="label.activite.fiche.panel.journal"/></h3>
                                        <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>

                                </div>

                                <div class="panel-body">
                                    <div class="row">

                                        <%@include file="./tableaux/tableau_journal.jsp" %>

                                    </div>

                                </div>
                            </div>

                        </div>


                    </div>


                    <!--                      COLONNE DE GAUCHE                  -->
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieEtape  "  data-toggle="modal" style="color: black"
                                           data-target="#UpdateDetailsLocalisation"   ><span
                                                class="glyphicon glyphicon-pencil"></span></a>
                                    </div>
                                    <div>
                                        <h3 class="panel-title "><spring:message code="label.activite.fiche.panel.personne"/></h3>
                                        <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>

                                </div>

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.autorite" />:</label>
                                        </div>
                                        <div class="col-sm-5"><span class="glyphicon glyphicon-user">
                                                Dame DIAW </span></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.contact" />:</label>
                                        </div>
                                        <div class="col-sm-5"><span class="glyphicon glyphicon-user">
                                                Khadim CISSE </span></div>
                                    </div>

                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieEtape " data-toggle="modal" style="color: black"
                                           onclick="modifierEtape(2)"    data-id="${etape.idActivite}"    ><span
                                                class="glyphicon glyphicon-pencil"></span></a>
                                    </div>
                                    <div>
                                        <h3 class="panel-title "><spring:message code="label.activite.fiche.panel.date"/></h3>
                                        <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>

                                </div>

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.date.deb" />:</label>
                                        </div>
                                        <div class="col-sm-5">${etape.dateFinPrevu}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.date.fin" />:</label>
                                        </div>
                                        <div class="col-sm-5">${etape.dateFinPrevu}</div>
                                    </div>


                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieEtape  " data-toggle="modal" style="color: black"
                                           data-id="${etape.idActivite}" onclick="modifierEtape(3)"    ><span
                                                class="glyphicon glyphicon-pencil"></span></a>
                                    </div>
                                    <div>
                                        <h3 class="panel-title "><spring:message code="label.activite.fiche.panel.budget"/></h3>
                                        <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>

                                </div>

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.cout.reel" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="badge badge-info" role="alert">${etape.budgetPrevu}
                                            </div></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.cout.previsionnel" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="badge badge-info" role="alert">${etape.budgetPrevu}
                                            </div></div>
                                    </div>

                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieEtape " data-toggle="modal" style="color: black"
                                           data-id="${etape.idActivite}" onclick="modifierEtape(4)"    ><span
                                                class="glyphicon glyphicon-pencil"></span></a>
                                    </div>
                                    <div>
                                        <h3 class="panel-title "><spring:message code="label.activite.fiche.panel.emploi"/></h3>
                                        <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>

                                </div>

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.emploi.reel" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="badge badge-info" role="alert">${etape.nbreEmploiPrevu}
                                            </div></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.emploi.previsionnel" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="badge badge-info" role="alert">${etape.nbreEmploiPrevu}
                                            </div></div>
                                    </div>

                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right  btnModifierPartieEtape" data-toggle="modal" style="color: black"
                                           data-id="${etape.idActivite}" onclick="modifierEtape(5)"   ><span
                                                class="glyphicon glyphicon-pencil"></span></a>
                                    </div>
                                    <div>
                                        <h3 class="panel-title "><spring:message code="label.activite.fiche.panel.temps"/></h3>
                                        <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>

                                </div>

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.etape.taux.realisation" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="progress">
                                                <div class="progress-bar" role="progressbar" aria-valuenow="60"
                                                     aria-valuemin="0" aria-valuemax="100"
                                                     style="width: ${etape.tauxRealisation}%;">${etape.tauxRealisation}%</div>
                                            </div></div>
                                    </div>



                                </div>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>


    </sec:authorize>



    <!-- Editer étape-->
    <div class="modal fade modal-admin" id="viewEtapeModal" tabindex="-1" role="dialog" aria-labelledby="viewEtapeModal" aria-hidden="true">

        <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
            <div class="panel panel-info" style="border-color: rgb(193,25,83)">
                <form commandName="editEtapeForm" action="${pageContext.request.contextPath}/editEtape" method="post" id="editEtapeFormulaire">
                    <div class="panel-heading" style="background-color: rgb(193,25,83)">
                        <h3 class="panel-title " style="color: white; text-align: center;">Visualisation et Modification</h3>
                    </div>
                    <div class="panel-body" style="color: #000033">
                        <div class="alert alert-success" style="display: none" role="alert" id="etapeEditSuccess">
                            <div class="alert alert-danger" role="alert" style="display: none" id="etapeEditFailed"></div>
                        </div>
                        <fieldset><legend>Informations de l'étape</legend>

                            <div id="1">
                                <label for="editNomEtape"><spring:message code="label.etape.nom" /></label>
                                <input type="text" class="form-control" required="required"  name="nomActivite" placeholder="Nom" id="editNomEtape"/>
                                <label for="editDescEtape"><spring:message code="label.etape.description" /></label>
                                <input type="text" class="form-control" name="description" required="false" placeholder="Description" id="editDescEtape"/>
                            </div>
                            <div id="2">
                                <label for="editDateDebPrevuEtape"><spring:message code="label.etape.tableau.colonne.dateDeb" /></label>
                                <input type="date" class="form-control" name="dateDebPrevu" required="false" placeholder="Date debut prevu" id="editDateDebPrevuEtape"/>
                                <label for="editDateFinPrevuEtape"><spring:message code="label.etape.tableau.colonne.dateFin" /></label>
                                <input type="date" class="form-control" name="dateFinPrevu" required="false" placeholder="Date fin prevu" id="editDateFinPrevuEtape"/>
                            </div>

                            <div id="3"> 
                                <label for="editBudgetPrevuEtape"><spring:message code="label.etape.budget" /></label>
                                <input  class="form-control"   name="budgetPrevu" required="required" type="number" placeholder="budget Prevu" id="editBudgetPrevuEtape"/>
                            </div>  


                            <div id="4"> 
                                <label for="editNbreEmploiPrevuEtape"><spring:message code="label.etape.nbre_emploi" /></label>
                                <input  class="form-control"  name="nbreEmploiPrevu" required="required" type="number" placeholder="Emploi Prevu" id="editNbreEmploiPrevuEtape"/>
                            </div>  
                            <div id="6"> 
                                <label for="editPonderationEtape"><spring:message code="label.etape.ponderation" /></label>
                                <input  class="form-control"  name="ponderation" required="required" type="number" placeholder="Ponderation" id="editPonderationEtape"/>
                            </div>  

                            <div id="5"> 
                                <label for="editTauxRealisationEtape"><spring:message code="label.etape.taux.realisation" /></label>
                                <input  class="form-control"  name="tauxRealisation" type="number" placeholder="Taux réalisation" id="editTauxRealisationEtape"/>
                            </div>  
                        </fieldset>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" id="formEditEtapeButton" onClick="" class="btn btn-info" value="submit"><label><spring:message code="label.bouton.valider" /></label></button>
                        <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><label><spring:message code="label.bouton.annuler" /></label></button>
                    </div>
                </form>

            </div>
        </div>
    </div>