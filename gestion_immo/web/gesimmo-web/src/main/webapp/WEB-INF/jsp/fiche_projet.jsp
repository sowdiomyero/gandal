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

    <div class="container-fluid" style="margin-left: 25px; margin-right: 25px;">    


        <div class="row">
            <div class="panel panel-default" style=" margin-bottom: 10px; ">
                <div class="panel-heading text-center">
                    <h2><spring:message code="label.projet.header.titre" /></h2>
                </div>
            </div>
        </div>


        <div class="row">


            <!--            Menu Administration-->
            <div class="col-md-2" >
                <%@include file="./menus/menu_projet.jsp" %>
            </div>


            <div class="col-md-10">

                <!--            Meteo Projet-->
                <div class="row">
                    <%@include file="./meteo/meteo_projet.jsp" %>
                </div>  

                <!--            Bouton  Projet-->
             
                <div class="row" >

                    <%@include file="./fragment/projet/projet_menu_buttons.jsp" %>





                </div>



                <!--            Panel Information  Projet-->
                <div class="row">
                    <div class="col-lg-6">

                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieProjet " data-toggle="modal" style="color: black"
                                           data-id="${projet.idActivite}" onclick="modifier(1)"    data-target="#UpdateDetailsLocalisation"    ><span
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
                                            <label><spring:message code="label.projet.nom" />:</label>
                                        </div>
                                        <div class="col-sm-5"> ${projet.nomActivite}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.projet.description" />:</label>
                                        </div>
                                        <div class="col-sm-5">${projet.description}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.projet.etat" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="badge badge-info" role="alert">${projet.etatActuel.nom}
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
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieProjet " data-toggle="modal" style="color: black"
                                           data-target="#UpdateDetailsLocalisation"   ><span
                                                class=""></span></a>
                                    </div>
                                    <div>
                                        <h3 class="panel-title "><spring:message code="label.activite.fiche.panel.etape"/></h3>
                                        <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>

                                </div>

                                <div class="panel-body">
                                    <div class="row">
                                        <%@include file="./tableaux/list_etape_fiche_projet .jsp" %>


                                    </div>

                                </div>
                            </div>

                        </div>

                        <!--            Panel Information  JOURNAL-->             

                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieProjet " data-toggle="modal" style="color: black"
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
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieProjet  "  data-toggle="modal" style="color: black"
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
                                            <label><spring:message code="label.projet.autorite" />:</label>
                                        </div>
                                        <div class="col-sm-5"><span class="glyphicon glyphicon-user">
                                                Dame DIAW </span></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.projet.contact" />:</label>
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
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieProjet " data-toggle="modal" style="color: black"
                                           data-id="${projet.idActivite}" onclick="modifier(2)"  ><span
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
                                            <label><spring:message code="label.projet.date.deb" />:</label>
                                        </div>
                                        <div class="col-sm-5">${projet.dateFinPrevu}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.projet.date.fin" />:</label>
                                        </div>
                                        <div class="col-sm-5">${projet.dateFinPrevu}</div>
                                    </div>


                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieProjet  " data-toggle="modal" style="color: black"
                                           data-id="${projet.idActivite}" onclick="modifier(3)"    ><span
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
                                            <label><spring:message code="label.projet.cout.reel" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="badge badge-info" role="alert">${projet.budgetPrevu}
                                            </div></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.projet.cout.previsionnel" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="badge badge-info" role="alert">${projet.budgetPrevu}
                                            </div></div>
                                    </div>

                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right btnModifierPartieProjet " data-toggle="modal" style="color: black"
                                           data-id="${projet.idActivite}" onclick="modifier(4)"   ><span
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
                                            <label><spring:message code="label.projet.emploi.reel" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="badge badge-info" role="alert">${projet.nbreEmploiPrevu}
                                            </div></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.projet.emploi.previsionnel" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="badge badge-info" role="alert">${projet.nbreEmploiPrevu}
                                            </div></div>
                                    </div>

                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="panel panel-primary" >
                                <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                    <div>
                                        <a href="#" class="showLocalisation pull-right  " data-toggle="modal" style="color: black"
                                           data-target="#UpdateDetailsLocalisation"  id="btnModifierDetails"><span
                                                class=" "></span></a>
                                    </div>
                                    <div>
                                        <h3 class="panel-title "><spring:message code="label.activite.fiche.panel.temps"/></h3>
                                        <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>

                                </div>

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <label><spring:message code="label.projet.taux.realisation" />:</label>
                                        </div>
                                        <div class="col-sm-5"><div class="progress">
                                                <div class="progress-bar" role="progressbar" aria-valuenow="60"
                                                     aria-valuemin="0" aria-valuemax="100"
                                                     style="width: ${projet.tauxRealisation}%;">${projet.tauxRealisation}%</div>
                                            </div></div>
                                    </div>



                                </div>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>




        <!-- Editer projet-->
        <div class="modal fade modal-admin" id="viewProjetModal" tabindex="-1" role="dialog" aria-labelledby="viewProjetModal" aria-hidden="true">

            <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
                <div class="panel panel-info" style="border-color: rgb(193,25,83)">
                    <form commandName="editProjetForm" action="${pageContext.request.contextPath}/editProjet" method="post" id="editProjetFormulaire">
                        <div class="panel-heading" style="background-color: rgb(193,25,83)">
                            <h3 class="panel-title " style="color: white">Editer un projet</h3>
                        </div>
                        <div class="panel-body" style="color: #000033">
                            <div class="alert alert-success" style="display: none" role="alert" id="projetEditSuccess">
                                <div class="alert alert-danger" role="alert" style="display: none" id="projetEditFailed"></div>
                            </div>
                            <fieldset><legend>Informations du projet</legend>
                                <div id="1">
                                    <label for="editNomProjet">Nom du projet</label>
                                    <input type="text" class="form-control" required="required"  name="nomActivite" placeholder="Nom" id="editNomProjet"/>
                                    <label for="editDescProjet">Description du projet</label>
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
                        
              <!-- ########################Affecter projet a une personne##############################  -->
        <div class="modal fade modal-admin container-fluid" id="AffecterProjetPersonne" tabindex="-1" role="dialog" aria-labelledby="AffecterProjetPersonne" aria-hidden="true">
            <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
                <div class="panel panel-info" style="border-color: rgb(193,25,83)">

                    <form:form commandName="userActiviteForm" action="${pageContext.request.contextPath}/affecterProjetPersonne" method="post" id="affecterProjetPersonneForm">
                        <div class="panel-heading" style="background-color: rgb(193,25,83)">
                            <h3 class="panel-title " style="color: white">Affecter projet a une personne</h3>
                        </div>
                        <div class=" panel-body " style="color: #000033">
                            <fieldset><legend></legend> 
                                <label for="addProjetPersonneInput"><spring:message code="label.projet.modal.select.personne"/></label>
                                <select  id="addProjetPersonneInput" name="idUser" required="true" class="form-control ">
                                    <option value=""></option>
                                    <c:forEach items="${users}" var="user" >
                                        <option value="${user.idUser}">${user.compte.login} : ${user.userName} ${user.userPrenom}</option>
                                    </c:forEach>
                                </select>
                                <label for="idProjetPersonneFonction"><spring:message code="label.projet.modal.select.fonction"/></label>
                                <select  id="idProjetPersonneFonction" name="fonction" required="true" class="form-control ">
                                    <option value=""></option>
                                    <option value="Responsable">Responsable</option>
                                    <option value="Auditeur">Auditeur</option>
                                    <option value="Controleur">Contrôleur</option>
                                    <option value="Superviseur">Superviseur</option>
                                </select>
                            </fieldset>
                        </div>

                        <div class="modal-footer ">
                            <button type="submit" id="formEditLocalisationButton" onClick="" class="btn btn-info" value="submit">Enregistrer</button>
                            <button type="button" id ="closeLocalisationForm" onClick=""  data-dismiss="modal" class="btn btn-warning">Fermer</button>
                        </div>
                    </form:form>

                </div>
            </div>
        </div>          

        <!-- ########################Affecter projet a une localite##############################  -->
        <div class="modal fade modal-admin container-fluid" id="AffecterProjetLocalite" tabindex="-1" role="dialog" aria-labelledby="AffecterProjetLocalite" aria-hidden="true">
            <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
                <div class="panel panel-info" style="border-color: rgb(193,25,83)">

                    <form:form commandName="editLocalisationForm" action="${pageContext.request.contextPath}/affecterProjetLocalite" method="post" id="affecterProjetLocalite">
                        <div class="panel-heading" style="background-color: rgb(193,25,83)">
                            <h3 class="panel-title " style="color: white">Affecter projet a une localite</h3>
                        </div>
                        <div class=" panel-body " style="color: #000033">
                            <fieldset><legend><spring:message code="label.projet.header.titre"/></legend> 
                                <label for="selectNomLocalite"><spring:message code="label.projet.modal.select.titre"/></label>
                                <select  id="addProjetLocalisationInput"  class="form-control ">
                                    <option value=""></option>
                                    <c:forEach items="${listLocalite}" var="localisable" >
                                        <option value="${localisable.idLocalisation}">${localisable.nomLocalisable}(${localisable.latitude},${localisable.longitude})</option>
                                    </c:forEach>
                                </select>
                            </fieldset>
                        </div>

                        <div class="modal-footer ">
                            <button type="submit" id="formEditLocalisationButton" onClick="" class="btn btn-info" value="submit">Enregistrer</button>
                            <button type="button" id ="closeLocalisationForm" onClick=""  data-dismiss="modal" class="btn btn-warning">Fermer</button>
                        </div>
                    </form:form>

                </div>
            </div>
        </div>




        <!-- Affecter projet au programme-->
        <div class="modal fade modal-admin" id="AffectProjectToProgramme" tabindex="-1" role="dialog" aria-labelledby="AffectProjectToProgramme" aria-hidden="true">

            <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
                <div class="panel panel-info" style="border-color: rgb(193,25,83)">
                    <form  action="${pageContext.request.contextPath}/affectProjectToProgram" method="post" id="affectProjettoProgrammmeFormulaire">
                        <div class="panel-heading" style="background-color: rgb(193,25,83)">
                            <h3 class="panel-title " style="color: white"><spring:message code="label.projet.bouton.affecter.programme"/></h3>
                        </div>
                        <div class="panel-body" style="color: #000033">
                            <div class="alert alert-success" style="display: none" role="alert" id="projetEditSuccess">
                                <div class="alert alert-danger" role="alert" style="display: none" id="projetEditFailed"></div>
                            </div>
                            <fieldset><legend><spring:message code="label.programme.modal.editer.panel.body.legend"/></legend>
                                <label for="addProjetToProgrammeInput"><spring:message code="label.projet.bouton.affecter.programme"/></label>
                                <select  id="addProjetToProgrammeInput"  class="form-control selectize" name="idProgramme">
                                    <option value=""></option>
                                    <c:forEach items="${programmes}" var="programme" >
                                        <option value="${programme.idActivite}">${programme.nomActivite}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="idProjet" value="${projet.idActivite}">
                            </fieldset>
                        </div>
                        <div class="modal-footer ">
                            <button type="submit" id="formEditProjetButton" onClick="" class="btn btn-info" value="submit"><label><spring:message code="label.bouton.valider" /></label></button>
                            <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><label><spring:message code="label.bouton.annuler" /></label></button>
                        </div>
                    </form>

                </div>
            </div>
        </div>




        <!-- ########################AJOUT ETAPE##############################  -->
        <div class="modal fade modal-admin" id="CreateEtape" tabindex="-1" role="dialog" aria-labelledby="CreateEtape" aria-hidden="true">

            <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
                <div class="panel panel-info" style="border-color: rgb(193,25,83)">
                    <form:form commandName="etapeForm" action="${pageContext.request.contextPath}/addEtape" method="post" id="submitEtapeForm">

                        <div class="panel-heading" style="background-color: rgb(193,25,83)">
                            <h3 class="panel-title " style="color: white; text-align: center;">AJOUT
                                ETAPE</h3>
                        </div>
                        <div class="panel-body" style="color: #000033">
                            <fieldset><legend>Informations Etape</legend>  
                                <label for="nomEtape">Nom: </label>
                                <form:input class="form-control" required="required" path="nomActivite" placeholder="Nom" id="nomEtape"/>


                                <label for="descEtape">Description </label>
                                <form:input class="form-control" path="description" placeholder="Description" id="descEtape"/>

                                <label for="dateDebEtape">Date debut prévu </label>
                                <form:input type="date" class="form-control" path="dateDebPrevu" placeholder="DateDebPrevu" id="dateDebEtape"/>

                                <label for="dateFinEtape">Date fin prévu </label>
                                <form:input type="date" class="form-control" path="dateFinPrevu" placeholder="DateFinPrevu" id="dateFinEtape"/>

                                <label for="budgetPrevuEtape">Budget prévu </label>
                                <form:input  class="form-control" path="budgetPrevu" required="required" type="number" placeholder="budgetPrevu" id="budgetPrevuEtape"/>

                                <label for="nbreEmploiPrevu">Nombre Emploi prévu </label>
                                <form:input  class="form-control" path="nbreEmploiPrevu" type="number" placeholder="nbreEmploiPrevu" id="nbreEmploiPrevu"/>

                                <label for="ponderationEtape">Pondération </label>
                                <form:input  class="form-control" path="ponderation" required="required" type="number" placeholder="Pondération" onkeyup="nombreAddPonderation()" id="ponderationEtape"/>
                            
                                <form:hidden  class="form-control" path="idParent"     id="idParentEtape"/>
                            </fieldset>
                        </div>
                        <div class="modal-footer ">

                            <button type="submit" onClick="" class="btn btn-info" value="submit">Valider</button>
                            <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning">Annuler</button>

                        </div>
                    </form:form>

                </div>
            </div> 

        </div>


    </sec:authorize>

