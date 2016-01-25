<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<sec:authorize  ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN ">
    <div class="btn-group">
        <button type="button"
                style="  margin-top:7px;  margin-bottom:7px; background:rgb(193,25,83); color: #ffffff;border-radius: 6px;font-size: 13px"
                class="btn btn-default" data-title="CreateProjet"
                data-toggle="modal" data-target="#CreateProjet"> <span class="glyphicon glyphicon-plus-sign"> <label><spring:message code="label.bouton.nouveau.projet" /></label></span>
        </button>

    </div>
</sec:authorize>
<div class="table-responsive" >
    <table id="table_projets" class="table table-bordred table-striped  table-condensed"   border="1" style="border-color: white;">
        <caption>
            <h4 style="font-weight: bold;"><label><spring:message code="label.header.projet.list" /></label></h4>
        </caption>
        <thead>
            <tr style="font-weight: bold;  background-color: #CCD4D9; text-align: center;">
                <td><label><spring:message code="label.utilisateur.nom" /></label></td>
                <td><label><spring:message code="label.projet.tableau.colonne.desc" /></label></td>
                <td><label><spring:message code="label.projet.tableau.colonne.dateDeb" /></label></td>
                <td><label><spring:message code="label.projet.tableau.colonne.dateFin" /></label></td>
                <td><label><spring:message code="label.etape.tableau.colonne.avancement" /></label></td>
                <td><label><spring:message code="label.projet.tableau.colonne.actions" /></label></td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${projets}" var="projet" >
                <tr style="text-align: center;">
                    <td>${projet.nomActivite}</td>
                    <td>${projet.description}</td>
                    <td>${projet.dateDebPrevu}</td>
                    <td>${projet.dateFinPrevu}</td>
                    <td><span class="badge" style="width: 100%;"><div style="width: ${projet.tauxRealisation}%; background-color: green;">${projet.tauxRealisation} %</div></span></td>
                    <td style="width: 15%;">
                        <div class="row" style="margin-left: 15%;">
                            <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                                <button title="<spring:message code="label.projet.tableau.header.actions.title.visualiser"/>" class="showProjet btn btn-info btn-xs"
                                        data-id="${projet.idActivite}" type="button" >
                                    <span class="glyphicon glyphicon-eye-open" ></span>
                                </button>
                            </div>

                            <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                                <button title="<spring:message code="label.projet.tableau.header.actions.title.supprimer"/>" class="deleteProjet btn btn-danger btn-xs"
                                        data-id="${projet.idActivite}" type="button" >
                                    <span class="glyphicon glyphicon-trash" ></span>
                                </button>
                            </div>

                            <div class="col-lg-1">
                                <a href="ficheProjet?idProjet=${projet.idActivite}"><button title="<spring:message code="label.projet.tableau.header.actions.title.fiche"/>" class=" btn btn-info btn-xs" type="button">
                                        <span class="glyphicon glyphicon-folder-open" ></span>
                                    </button></a>

                            </div>

                            <div class="col-lg-1">
                                <a href="etapelist?activite=${projet.idActivite}"><button title="etape" class=" btn btn-warning btn-xs" type="button">
                                        <span class="glyphicon glyphicon-list" ></span>
                                    </button></a>

                            </div>

                        </div>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</div>

<!-- ########################AJOUT PROJET##############################  -->
<div class="modal fade modal-admin" id="CreateProjet" tabindex="-1" role="dialog" aria-labelledby="CreateProjet" aria-hidden="true">

    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form:form commandName="projetForm" action="${pageContext.request.contextPath}/addProjet" method="post" id="submitProjetForm">

                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white"><spring:message code="label.projet.panel.titre"/>
                        </h3>
                </div>
                 <div class="panel-body" style="color: #000033">
                    <fieldset><legend><spring:message code="label.projet.modal.editer.panel.body.legend"/></legend>  
                        <label for="nomProjet"><spring:message code="label.localisation.tableau.header.nom"/>:</label>
                        <form:input class="form-control" required="required"   path="nomActivite" placeholder="Nom" id="nomProgramme"/>


                        <label for="descProjet"><spring:message code="label.role.tableau.header.description"/>:</label>
                        <form:input class="form-control" path="description" required="required" placeholder="Description" id="descProgramme"/>

                        <label for="dateDebProjet"><spring:message code="label.projet.tableau.colonne.dateDeb"/>:</label>
                        <form:input type="date" class="form-control" path="dateDebPrevu" required="required" placeholder="DateDebPrevu" id="dateDebPrevuProgramme"/>

                        <label for="dateFinProjet"><spring:message code="label.projet.tableau.colonne.dateFin"/>:</label>
                        <form:input type="date" class="form-control" path="dateFinPrevu" required="required" placeholder="DateFinPrevu" id="dateFinPrevuProgramme"/>

                        <label for="budgetPrevu"><spring:message code="label.projet.budget"/>:</label>
                        <form:input  class="form-control" path="budgetPrevu" required="required" type="number" placeholder="budgetPrevu" id="budgetPrevuProgramme"/>

                        <label for="nbreEmploiPrevu"><spring:message code="label.projet.nbre_emploi"/>:</label>
                        <form:input  class="form-control" path="nbreEmploiPrevu" required="required" type="number" placeholder="nbreEmploiPrevu" id="nbreEmploiPrevuProgramme"/>
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


<!-- Modal suprimer un projet-->
<div class="modal fade" id="deleteProjetConfirmation" tabindex="-1" role="dialog"
     aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </button>
                <h4 class="modal-title custom_align" id="Heading"><spring:message code="label.projet.modal.supprimer.panel.heading"/></h4>
            </div>
            <div class="modal-body">

                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-warning-sign"></span> 
                    <p><spring:message code="label.projet.modal.supprimer.panel.body.question"/></p>
                </div>

            </div>
            <div class="modal-footer ">
                <button value="oui" type="submit" class="btn btn-success" id="okDeleteProjetConfirmation">
                    <span class="glyphicon glyphicon-ok-sign"></span>  <spring:message code="label.bouton.oui"/>
                </button>
                <button value="non" type="button" class="btn btn-default"
                        data-dismiss="modal" id="non">
                    <span class="glyphicon glyphicon-remove"></span> <spring:message code="label.bouton.non"/>
                </button>
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
                        <label for="editNomProjet"><spring:message code="label.projet.nom" /></label>
                        <input type="text" class="form-control" required="required"  name="nomActivite" placeholder="Nom" id="editNomProjet"/>
                        <label for="editDescProjet"><spring:message code="label.projet.description" /></label>
                        <input type="text" class="form-control" name="description" required="false" placeholder="Description" id="editDescProjet"/>
                        <label for="editDateDebPrevuProjet"><spring:message code="label.projet.tableau.colonne.dateDeb" /></label>
                        <input type="date" class="form-control" name="dateDebPrevu" required="false" placeholder="Date debut prevu" id="editDateDebPrevuProjet"/>
                        <label for="editDateFinPrevuProjet"><spring:message code="label.projet.tableau.colonne.dateFin" /></label>
                        <input type="date" class="form-control" name="dateFinPrevu" required="false" placeholder="Date fin prevu" id="editDateFinPrevuProjet"/>

                        <label for="editBudgetPrevuProjet"><spring:message code="label.projet.budget" /></label>
                        <input  class="form-control"   name="budgetPrevu" required="required" type="number" placeholder="budget Prevu" id="editBudgetPrevuProjet"/>

                        <label for="editNbreEmploiPrevuProjet"><spring:message code="label.projet.nbre_emploi" /></label>
                        <input  class="form-control"  name="nbreEmploiPrevu" required="required" type="number" placeholder="Emploi Prevu" id="editNbreEmploiPrevuProjet"/>
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

<!-- ########################RECHERCHE PROJET##############################  -->
<div class="modal fade modal-admin" id="RechercheProjet" tabindex="-1" role="dialog" aria-labelledby="RechercheProjet" aria-hidden="true">

    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form:form commandName="projetForm" action="${pageContext.request.contextPath}/rechercherProjet" method="post" id="submitProjetFormRecherche">

                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white; text-align: center;"><label><spring:message code="label.projet.recherche.title" /></label></h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <fieldset><legend style="text-align: center;"><label><spring:message code="label.projet.recherche.sousTitle" /></label></legend>  
                        <label for="nomProjet"><spring:message code="label.utilisateur.nom" /></label>
                        <form:input class="form-control" required="false"   path="nomActivite" placeholder="Nom projet" id="nomProjet"/>

                        <label for="dateDebProjet"><spring:message code="label.projet.tableau.colonne.dateDeb" /></label>
                        <form:input type="date" class="form-control" path="dateDebPrevu" required="false" placeholder="DateDebPrevu" id="dateDebProjet"/>

                        <label for="dateFinProjet"><spring:message code="label.projet.tableau.colonne.dateFin" /></label>
                        <form:input type="date" class="form-control" path="dateFinPrevu" required="false" placeholder="DateFinPrevu" id="dateFinProjet"/>

                        <label for="budgetPrevu"><spring:message code="label.projet.recherche.budget" /></label>
                        <form:input  class="form-control" path="budgetPrevu" required="false" placeholder="Montant budget" id="budgetPrevu"/>

                        <label for="etat"><spring:message code="label.projet.recherche.etat" /></label>
                        <form:select   class="form-control" multiple="false" path="etat" required="required" >
                            <option value="${etat_desactive}"  cssClass="colored" ><label><spring:message code="label.projet.recherche.etat.tous" /></label></option>
                            <option value="${etat_abandonne}"  cssClass="colored" ><label><spring:message code="label.projet.recherche.etat.abandonne" /></label></option>
                            <option value="${etat_creation}"  cssClass="colored" ><label><spring:message code="label.projet.recherche.etat.cree" /></label></option>
                            <option value="${etat_en_cours}"  cssClass="colored" ><label><spring:message code="label.projet.recherche.etat.demarre" /></label></option>
                            <option value="${etat_suspendu}"  cssClass="colored" ><label><spring:message code="label.projet.recherche.etat.suspendu" /></label></option>
                            <option value="${etat_redemarre}"  cssClass="colored" ><label><spring:message code="label.projet.recherche.etat.redemarre" /></label></option>      
                            <option value="${etat_termine}"  cssClass="colored" ><label><spring:message code="label.projet.recherche.etat.termine" /></label></option>
                        </form:select>
                    </fieldset>
                </div>
                <div class="modal-footer ">

                    <a rel="tooltip" href="rechercherprojet" data-original-title="Lancer la recherche avancée">                       
                        <button type="submit" onClick="" class="btn btn-primary" value="submit"><span class="glyphicon glyphicon-search" > <label><spring:message code="label.bouton.lancer" /></label></button></a>
                    <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning"><label><spring:message code="label.bouton.annuler" /></label></button>

                </div>
            </form:form>

        </div>
    </div> 

</div>
