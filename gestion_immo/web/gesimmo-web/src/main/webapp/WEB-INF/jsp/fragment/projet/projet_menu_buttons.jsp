<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row" style="padding: 4px; margin: 4px; float: right">
    <button class="showProjet btn btn-default btn-xs" data-toggle="modal"  
            data-target="#" id="btnModifierProjet"  data-id="${projet.idActivite}">
        <span class="glyphicon glyphicon-pencil"></span> Modifier projet
    </button>

</div>



<div class="row" style="padding: 4px; margin: 4px; float: right">
    <button class="btn btn-default btn-xs" data-title="CreateEtape"
            data-toggle="modal" data-target="#CreateEtape" id="btnEtape" >
        <span class="glyphicon glyphicon-plus-sign"></span> Ajouter étapes
    </button>

</div>
<div class="row" style="padding: 4px; margin: 4px; float: right">
    <button class="btn btn-default btn-xs" data-toggle="modal"  
            data-target="#AffectProjectToProjet " id="btnAffecterProjet" >
        <span class="glyphicon glyphicon-plus-sign"></span> Affecter au projet
    </button>
</div>
<div class="row" style="padding: 4px; margin: 4px; float: right">
    <button class="btn btn-default btn-xs" data-toggle="modal"  
            data-target="#AffectProjectToProgramme " id="btnAffectProjectToProgramme" >
        <span class="glyphicon glyphicon-plus-sign"></span> Affecter au programme
    </button>
</div>
<div class="row" style="padding: 4px; margin: 4px; float: right">
    <button class="btn btn-default btn-xs" data-toggle="modal"  
            data-target="#AffecterProjetLocalite " id="btnAffecterProjet" >
        <span class="glyphicon glyphicon-plus-sign"></span> Affecter a une localité
    </button>
</div>
<div class="row" style="padding: 4px; margin: 4px; float: right">
    <button class="btn btn-default btn-xs" data-toggle="modal"  
            data-target="#AffecterProjetPersonne " id="btnAffecterProjetPersonnes" >
        <span class="glyphicon glyphicon-plus-sign"></span> Affecter a une personne
    </button>
</div>


<c:url value="${pageContext.request.contextPath}/activite/changerEtat" var="urlChangerEtatProjet" />
<c:if test="${not empty projet}" >
    <c:if test="${projet.etatActuel.nom=='PLANIFIE'}" >
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProjetHidden="${projet.idActivite}" valueNomEtatHidden="DEMARRE" class="btn btn-info  btn-xs  projetChangerEtatFormButton">
                <span class="glyphicon glyphicon-play"></span> <spring:message code="label.projet.bouton.demarrer"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProjetHidden="${projet.idActivite}" valueNomEtatHidden="ABANDONNE" class="btn btn-danger  btn-xs  projetChangerEtatFormButton">
                <span class="glyphicon glyphicon-off"></span> <spring:message code="label.projet.bouton.abandonner"/>
            </button>
        </div>
    </c:if>
    <c:if test="${projet.etatActuel.nom=='DEMARRE'}" >
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProjetHidden="${projet.idActivite}" valueNomEtatHidden="TERMINE" class="btn btn-success  btn-xs  projetChangerEtatFormButton">
                <span class="glyphicon glyphicon-ok-circle"></span> <spring:message code="label.projet.bouton.terminer"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProjetHidden="${projet.idActivite}" valueNomEtatHidden="SUSPENDU" class="btn btn-warning  btn-xs  projetChangerEtatFormButton">
                <span class="glyphicon glyphicon-pause"></span> <spring:message code="label.projet.bouton.suspendre"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProjetHidden="${projet.idActivite}" valueNomEtatHidden="ABANDONNE" class="btn btn-danger  btn-xs  projetChangerEtatFormButton">
                <span class="glyphicon glyphicon-off"></span> <spring:message code="label.projet.bouton.abandonner"/>
            </button>
        </div>
    </c:if>
    <c:if test="${projet.etatActuel.nom=='SUSPENDU'}" >
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProjetHidden="${projet.idActivite}" valueNomEtatHidden="REDEMARRE" class="btn btn-info  btn-xs  projetChangerEtatFormButton">
                <span class="glyphicon glyphicon-play"></span> <spring:message code="label.projet.bouton.redemarrer"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProjetHidden="${projet.idActivite}" valueNomEtatHidden="ABANDONNE" class="btn btn-danger  btn-xs  projetChangerEtatFormButton">
                <span class="glyphicon glyphicon-off"></span> <spring:message code="label.projet.bouton.abandonner"/>
            </button>
        </div>
    </c:if>
    <c:if test="${projet.etatActuel.nom=='REDEMARRE'}" >
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProjetHidden="${projet.idActivite}" valueNomEtatHidden="TERMINE" class="btn btn-success  btn-xs  projetChangerEtatFormButton">
                <span class="glyphicon glyphicon-ok-circle"></span> <spring:message code="label.projet.bouton.terminer"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProjetHidden="${projet.idActivite}" valueNomEtatHidden="SUSPENDU" class="btn btn-warning  btn-xs  projetChangerEtatFormButton">
                <span class="glyphicon glyphicon-pause"></span> <spring:message code="label.projet.bouton.suspendre"/>
            </button>
        </div>
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button type="button" valueIdProjetHidden="${projet.idActivite}" valueNomEtatHidden="ABANDONNE" class="btn btn-danger  btn-xs  projetChangerEtatFormButton">
                <span class="glyphicon glyphicon-off"></span> <spring:message code="label.projet.bouton.abandonner"/>
            </button>
        </div>
    </c:if>
</c:if>
<c:if test="${empty projet}" >
    Aucun projet ...
</c:if>




