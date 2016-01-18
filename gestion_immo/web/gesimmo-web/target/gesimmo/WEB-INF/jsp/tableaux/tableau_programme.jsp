<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<sec:authorize  ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN ">
    <div class="btn-group">
        <button type="button"
                style="  margin-top:7px;  margin-bottom:7px; background:rgb(193,25,83); color: #ffffff;border-radius: 6px;font-size: 13px"
                class="btn btn-default" data-title="CreateProjet"
                data-toggle="modal" data-target="#CreateProjet"> <span class="glyphicon glyphicon-plus-sign"> <label><spring:message code="label.bouton.nouveau.programme" /></label></span>
        </button>

    </div>
</sec:authorize>
<div class="table-responsive" >
    <table id="table_programmes" class="table table-bordred table-striped  table-condensed"   border="1" style="border-color: white;">
        <caption>
            <h4 style="font-weight: bold;"><label><spring:message code="label.header.programme.list" /></label></h4>
        </caption>
        <thead>
            <tr style="font-weight: bold;  background-color: #CCD4D9; text-align: center;">
                <td><label><spring:message code="label.utilisateur.nom" /></label></td>
                <td><label><spring:message code="label.projet.tableau.colonne.desc" /></label></td>
                <td><label><spring:message code="label.projet.tableau.colonne.dateDeb" /></label></td>
                <td><label><spring:message code="label.projet.tableau.colonne.dateFin" /></label></td>
                <td><label><spring:message code="label.projet.tableau.colonne.actions" /></label></td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${programmes}" var="programme" >
                <tr style="text-align: center;">
                    <td>${programme.nomActivite}</td>
                    <td>${programme.description}</td>
                    <td>${programme.dateDebPrevu}</td>
                    <td>${programme.dateFinPrevu}</td>

                    <td>
                                <button title="<spring:message code="label.projet.tableau.header.actions.title.visualiser"/>" class="showProgramme btn btn-info btn-xs"
                                        data-id="${programme.idActivite}" type="button" >
                                    <span class="glyphicon glyphicon-eye-open" ></span>
                                </button>
                                <button title="<spring:message code="label.projet.tableau.header.actions.title.supprimer"/>" class="deleteProgramme btn btn-danger btn-xs"
                                        data-id="${programme.idActivite}" type="button" >
                                    <span class="glyphicon glyphicon-trash" ></span>
                                </button>
                                <a href="ficheProgramme?idProgramme=${programme.idActivite}"><button title="<spring:message code="label.projet.tableau.header.actions.title.fiche"/>" class=" btn btn-info btn-xs" type="button">
                                        <span class="glyphicon glyphicon-folder-open" ></span>
                                    </button></a>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</div>

<!-- ########################AJOUT PROGRAMME##############################  -->
<div class="modal fade modal-admin" id="CreateProjet" tabindex="-1" role="dialog" aria-labelledby="CreateProgramme" aria-hidden="true">

    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form:form commandName="programmeForm" action="${pageContext.request.contextPath}/addProgramme" method="post" id="submitProgrammeForm">

                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white"><spring:message code="label.programme.panel.titre"/>
                    </h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <fieldset><legend><spring:message code="label.programme.modal.editer.panel.body.legend"/></legend>  
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

                    <button type="submit" onClick="" class="btn btn-info" value="submit"><spring:message code="label.bouton.valider"/> </button>
                    <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.annuler"/></button>

                </div>
            </form:form>

        </div>
    </div> 
</div>

<!-- Modal suprimer un programme-->
<div class="modal fade" id="deleteProgrammeConfirmation" tabindex="-1" role="dialog"
     aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </button>
                <h4 class="modal-title custom_align" id="Heading"><spring:message code="label.programme.modal.supprimer.panel.heading"/></h4>
            </div>
            <div class="modal-body">

                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-warning-sign"></span> 
                    <p><spring:message code="label.programme.modal.supprimer.panel.body.question"/></p>
                </div>

            </div>
            <div class="modal-footer ">
                <button value="oui" type="submit" class="btn btn-success" id="okDeleteProgrammeConfirmation">
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

<!-- Editer programme-->

<div class="modal fade modal-admin" id="viewProgrammeModal" tabindex="-1" role="dialog" aria-labelledby="viewProgrammeModal" aria-hidden="true">

    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form commandName="editProgrammeForm" action="${pageContext.request.contextPath}/editProgramme" method="post" id="editProgrammeFormulaire">
                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white">Editer un programme</h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <div class="alert alert-success" style="display: none" role="alert" id="programmeEditSuccess">
                        <div class="alert alert-danger" role="alert" style="display: none" id="programmeEditFailed"></div>
                    </div>
                    <fieldset><legend>Informations du programme</legend>
                        <label for="editNomProgramme"><spring:message code="label.projet.nom" /></label>
                        <input type="text" class="form-control" required="required"  name="nomActivite" placeholder="Nom" id="editNomProgramme"/>
                        <label for="editDescProgramme"><spring:message code="label.projet.description" /></label>
                        <input type="text" class="form-control" name="description" required="false" placeholder="Description" id="editDescProgramme"/>
                        
                        <label for="editDateDebPrevuProgramme"><spring:message code="label.projet.tableau.colonne.dateDeb" /></label>
                        <input type="date" class="form-control" name="dateDebPrevu" required="false" placeholder="Date debut prevu" id="editDateDebPrevuProgramme"/>
                        
                        <label for="editDateFinPrevuProgramme"><spring:message code="label.projet.tableau.colonne.dateFin" /></label>
                        <input type="date" class="form-control" name="dateFinPrevu" required="false" placeholder="Date fin prevu" id="editDateFinPrevuProgramme"/>

                        <label for="editBudgetPrevuProgramme"><spring:message code="label.projet.budget" /></label>
                        <input  class="form-control"   name="budgetPrevu" required="required" type="number" placeholder="budget Prevu" id="editBudgetPrevuProgramme"/>

                        <label for="editNbreEmploiPrevuProgramme"><spring:message code="label.projet.nbre_emploi" /></label>
                        <input  class="form-control"  name="nbreEmploiPrevu" required="required" type="number" placeholder="Emploi Prevu" id="editNbreEmploiPrevuProgramme"/>
                    </fieldset>
                </div>
                <div class="modal-footer ">
                    <button type="submit" id="formEditProgrammeButton" onClick="" class="btn btn-info" value="submit"><label><spring:message code="label.bouton.valider" /></label></button>
                    <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><label><spring:message code="label.bouton.annuler" /></label></button>
                </div>
            </form>

        </div>
    </div>
</div>

