<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row" style="padding: 4px; margin: 4px; float: right">
    <button class=" btn btn-default btn-xs" data-toggle="modal"  
            data-target="#" id="btnModifierProgramme"  data-id="${projet.idActivite}">
        <span class="glyphicon glyphicon-pencil"></span> Modifier programme
    </button>
</div>
<div class="row" style="padding: 4px; margin: 4px; float: right">
    <button class="btn btn-default btn-xs" data-toggle="modal"  
            data-target="# " id="btnEtape" >
        <span class="glyphicon glyphicon-plus-sign"></span> Ajouter étapes
    </button>
</div>
<div class="row" style="padding: 4px; margin: 4px; float: right">
    <button class="btn btn-default btn-xs" data-toggle="modal"  
            data-target="#AffectProjectToProgramme " id="btnAffecterProgramme" >
        <span class="glyphicon glyphicon-plus-sign"></span> Affecter au programme
    </button>
</div>
<c:url value="${pageContext.request.contextPath}/activite/changerEtat" var="urlChangerEtatProgramme" />
<c:if test="${not empty programme}" >
     <c:if test="${empty programme.etatActuel or programme.etatActuel eq null}" >
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="PLANIFIE" class="btn btn-info  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-play"></span> <spring:message code="label.projet.bouton.planifier"/>
            </button>
        </div>
    </c:if>
    <c:if test="${programme.etatActuel.nom=='PLANIFIE'}" >
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="DEMARRE" class="btn btn-info  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-play"></span> <spring:message code="label.projet.bouton.demarrer"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="ABANDONNE" class="btn btn-danger  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-off"></span> <spring:message code="label.projet.bouton.abandonner"/>
            </button>
        </div>
    </c:if>
    <c:if test="${programme.etatActuel.nom=='DEMARRE'}" >
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="TERMINE" class="btn btn-success  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-ok-circle"></span> <spring:message code="label.projet.bouton.terminer"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="SUSPENDU" class="btn btn-warning  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-pause"></span> <spring:message code="label.projet.bouton.suspendre"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="ABANDONNE" class="btn btn-danger  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-off"></span> <spring:message code="label.projet.bouton.abandonner"/>
            </button>
        </div>
    </c:if>
    <c:if test="${programme.etatActuel.nom=='SUSPENDU'}" >
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="REDEMARRE" class="btn btn-info  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-play"></span> <spring:message code="label.projet.bouton.redemarrer"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="ABANDONNE" class="btn btn-danger  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-off"></span> <spring:message code="label.projet.bouton.abandonner"/>
            </button>
        </div>
    </c:if>
    <c:if test="${programme.etatActuel.nom=='REDEMARRE'}" >
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="TERMINE" class="btn btn-success  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-ok-circle"></span> <spring:message code="label.projet.bouton.terminer"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="SUSPENDU" class="btn btn-warning  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-pause"></span> <spring:message code="label.projet.bouton.suspendre"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProgrammeHidden="${programme.idActivite}" valueNomEtatHidden="ABANDONNE" class="btn btn-danger  btn-xs  programmeChangerEtatFormButton">
                <span class="glyphicon glyphicon-off"></span> <spring:message code="label.projet.bouton.abandonner"/>
            </button>
        </div>
    </c:if>
</c:if>
<c:if test="${empty programme}" >
    Aucun programme ...
</c:if>




