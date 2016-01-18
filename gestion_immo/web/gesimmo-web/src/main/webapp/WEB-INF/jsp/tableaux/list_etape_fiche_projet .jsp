<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<ol>
    <div class="row">
        <div class="col-sm-1">    </div>
        <div class="col-sm-2">  <label><spring:message code="label.utilisateur.nom"/></label></div>
        <div class="col-sm-2 "> <label><spring:message code="label.projet.tableau.colonne.dateDeb"/></label> </div> 
        <div class="col-sm-2 "> <label><spring:message code="label.projet.tableau.colonne.dateFin" /></label></div>
         <div class="col-sm-2 "> <label><spring:message code="label.etape.tableau.colonne.avancement" /></label></div>
        <div class="col-sm-3 "> <label><spring:message code="label.projet.tableau.colonne.actions" /></label></div>

    </div>
</ol>

<ol id="list_etapes">
    <c:forEach items="${activitesProjet}" var="etape" >        
        <div class="row">
            <div class="col-sm-1">
                <li></li>
            </div>
            <div class="col-sm-2">${etape.nomActivite}</div>
            <div class="col-sm-2 foo">${etape.dateDebPrevu}</div> 
            <div class="col-sm-2 foo">${etape.dateFinPrevu}</div> 
             <div class="col-sm-2 foo"><span class="badge" style="width: 100%;"><div style="width: ${etape.tauxRealisation}%; background-color: green;">${etape.tauxRealisation} %</div></span></div> 

            <div class="col-sm-3 ">
                <div class="row">
                    <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                        <a href="ficheEtape?idEtape=${etape.idActivite}"><button title="<spring:message code="label.etape.tableau.header.actions.title.fiche"/>" class=" btn btn-info btn-xs" type="button">
                                <span class="glyphicon glyphicon-folder-open" ></span>
                            </button></a>
                    </div>

                    <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                        <button title="<spring:message code="label.etape.tableau.header.actions.title.supprimer"/>" class="deleteEtape btn btn-danger btn-xs"
                                data-id="${etape.idActivite}" type="button" >
                            <span class="glyphicon glyphicon-trash" ></span>
                        </button>
                    </div>

                </div>
            </div> 


        </div><br>
    </c:forEach>
</ol> 



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