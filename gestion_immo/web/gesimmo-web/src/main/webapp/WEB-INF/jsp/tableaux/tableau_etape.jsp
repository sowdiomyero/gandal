<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<sec:authorize  ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN ">
    <div class="btn-group">
        <button type="button"
                style="  margin-top:7px;  margin-bottom:7px; background:rgb(193,25,83); color: #ffffff;border-radius: 6px;font-size: 13px"
                class="btn btn-default" data-title="CreateEtape"
                data-toggle="modal" data-target="#CreateEtape"> <span class="glyphicon glyphicon-plus-sign"> <label><spring:message code="label.bouton.nouveau.etape" /></label></span>
        </button>

    </div>
</sec:authorize>
<div class="table-responsive" >
    <table id="table_etapes" class="table table-bordred table-striped  table-condensed"   border="1" style="border-color: white;">
        <caption>
            <h4 style="font-weight: bold;"><label><spring:message code="label.header.etape.list" /></label></h4>
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
            <c:forEach items="${etapes}" var="etape" >
                <tr style="text-align: center;">
                    <td>${etape.nomActivite}</td>
                    <td>${etape.description}</td>
                    <td style="width: 12%;">${etape.dateDebPrevu}</td>
                    <td style="width: 12%;">${etape.dateFinPrevu}</td>
                    <td style="width: 15%;"><span class="badge" style="width: 100%;"><div style="width: ${etape.tauxRealisation}%; background-color: green;">${etape.tauxRealisation} %</div></span></td>
                    <td style="width: 12%;">
                        <div class="row" style="margin-left: 15%;">
                            <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                                <button title="<spring:message code="label.etape.tableau.header.actions.title.visualiser"/>" class="showEtape btn btn-info btn-xs"
                                        data-id="${etape.idActivite}" type="button" >
                                    <span class="glyphicon glyphicon-eye-open" ></span>
                                </button>
                            </div>

                            <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                                <button title="<spring:message code="label.etape.tableau.header.actions.title.supprimer"/>" class="deleteEtape btn btn-danger btn-xs"
                                        data-id="${etape.idActivite}" type="button" >
                                    <span class="glyphicon glyphicon-trash" ></span>
                                </button>
                            </div>

                            <div class="col-lg-1">
                                <a href="ficheEtape?idEtape=${etape.idActivite}"><button title="<spring:message code="label.etape.tableau.header.actions.title.fiche"/>" class=" btn btn-info btn-xs" type="button">
                                        <span class="glyphicon glyphicon-folder-open" ></span>
                                    </button></a>

                            </div>

                        </div>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
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


<!-- Modal suprimer une étape-->
<div class="modal fade" id="deleteEtapeConfirmation" tabindex="-1" role="dialog"
     aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </button>
                <h4 class="modal-title custom_align" id="Heading"><spring:message code="label.etape.modal.supprimer.panel.heading"/></h4>
            </div>
            <div class="modal-body">

                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-warning-sign"></span> 
                    <p><spring:message code="label.etape.modal.supprimer.panel.body.question"/></p>
                </div>

            </div>
            <div class="modal-footer ">
                <button value="oui" type="submit" class="btn btn-success" id="okDeleteEtapeConfirmation">
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
                        <label for="editNomEtape"><spring:message code="label.etape.nom" /></label>
                        <input type="text" class="form-control" required="required"  name="nomActivite" placeholder="Nom" id="editNomEtape"/>
                        <label for="editDescEtape"><spring:message code="label.etape.description" /></label>
                        <input type="text" class="form-control" name="description" required="false" placeholder="Description" id="editDescEtape"/>
                        <label for="editDateDebPrevuEtape"><spring:message code="label.etape.tableau.colonne.dateDeb" /></label>
                        <input type="date" class="form-control" name="dateDebPrevu" required="false" placeholder="Date debut prevu" id="editDateDebPrevuEtape"/>
                        <label for="editDateFinPrevuEtape"><spring:message code="label.etape.tableau.colonne.dateFin" /></label>
                        <input type="date" class="form-control" name="dateFinPrevu" required="false" placeholder="Date fin prevu" id="editDateFinPrevuEtape"/>

                        <label for="editBudgetPrevuEtape"><spring:message code="label.etape.budget" /></label>
                        <input  class="form-control"   name="budgetPrevu" required="required" type="number" maxlength="9" placeholder="budget Prevu" id="editBudgetPrevuEtape"/>

                        <label for="editNbreEmploiPrevuEtape"><spring:message code="label.etape.nbre_emploi" /></label>
                        <input  class="form-control"  name="nbreEmploiPrevu" type="number" placeholder="Emploi Prevu" maxlength="4" id="editNbreEmploiPrevuEtape"/>

                        <label for="editPonderationEtape"><spring:message code="label.etape.ponderation" /></label>
                        <input  class="form-control"  name="ponderation" required="required" type="number" maxlength="5" placeholder="Ponderation" onkeyup="nombreEditPonderation()" id="editPonderationEtape"/>

                        <label for="editTauxRealisationEtape"><spring:message code="label.etape.taux.realisation" /></label>
                        <input  class="form-control"  name="tauxRealisation" type="number" placeholder="Taux réalisation" maxlength="5" onkeyup="nombreEditTaux()" id="editTauxRealisationEtape"/>
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

<script type='text/javascript'>
    function nombreAddPonderation(event) {
        var source = document.getElementById("ponderationEtape");
        var value = source.value;
        if (isNaN(parseFloat(value))) {
            source.value = "";
        }
        else if (value !== parseInt(value) + ".") {
            source.value = parseFloat(value);
        }
        if (value > 100) {
            alert('La pondération ne doit pas dépasser 100');
            source.value = source.value / 10;
        }
    }

    function nombreEditPonderation(event) {
        var source = document.getElementById("editPonderationEtape");
        var value = source.value;
        if (isNaN(parseFloat(value))) {
            source.value = "";
        }
        else if (value !== parseInt(value) + ".") {
            source.value = parseFloat(value);
        }
        if (value > 100) {
            alert('La pondération ne doit pas dépasser 100');
            source.value = source.value / 10;
        }
    }

    function nombreEditTaux(event) {
        var source = document.getElementById("editTauxRealisationEtape");
        var value = source.value;
        if (isNaN(parseFloat(value))) {
            source.value = "";
        }
        else if (value !== parseInt(value) + ".") {
            source.value = parseFloat(value);
        }
        if (value > 100) {
            alert('Le taux de réalisation ne doit pas dépasser 100');
            source.value = source.value / 10;
        }
    }
</script>