<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-12 " style="border-bottom: solid 1px;">
    <div class="row">
        <div class="col-md-2 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.panel.nom" />:</label>
        </div>
        <div class="col-md-10  col-sm-8 col-xs-6"> ${programme.nomActivite}</div>
    </div>
    <div class="row">
        <div class="col-md-2  col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.description" />:</label>
        </div>
        <div class="col-md-10 col-sm-8 col-xs-6"> ${programme.description}</div>
    </div>
    <div class="row">
        <div class="col-md-2  col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.etat" />:</label>
        </div>
        <div class="col-md-10  col-sm-8 col-xs-6">
            <div class="badge badge-info" role="alert">DEMARRE</div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2  col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.ponderation" />:</label>
        </div>
        <div class="col-md-10  col-sm-8 col-xs-6"> ${programme.ponderation}</div>
    </div>
</div>

<div class="col-md-12" style="border-bottom: solid 1px;">
    <div class="row">
        <div class="col-md-2  col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.date_debut_prevu" />:</label>
        </div>
        <div class="col-md-10  col-sm-8 col-xs-6"> <fmt:formatDate type="date" value="${programme.dateDebPrevu}" /> </div>
    </div>
    <div class="row">
        <div class="col-md-2 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.date_debut_reelle" />:</label>
        </div>
        <div class="col-md-10 col-sm-8 col-xs-6"><fmt:formatDate type="date" value="${programme.dateDebReel}" /></div>
    </div>
    <div class="row">
        <div class="col-md-2 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.date_fin_prevu" />:</label>
        </div>
        <div class="col-md-10 col-sm-8 col-xs-6">
         <fmt:formatDate type="date" value="${programme.dateFinPrevu}" />
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.date_fin_reelle" />:</label>
        </div>
        <div class="col-md-10 col-sm-8 col-xs-6"> <fmt:formatDate type="date" value="${programme.dateFinReel}" /></div>
    </div>
</div>
<div class="col-md-12" style="border-bottom: solid 1px;">
    <div class="row">
        <div class="col-md-2 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.budget_prevu" />:</label>
        </div>
        <div class="col-md-10 col-sm-8 col-xs-6"> <div class="badge badge-info" role="alert">${programme.budgetPrevu}</div> </div>
    </div>
    <div class="row">
        <div class="col-md-2 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.budget_reel" />:</label>
        </div>
        <div class="col-md-10 col-sm-8 col-xs-6"><div class="badge badge-info" role="alert">${programme.budgetPrevu}</div></div>
    </div>
    <div class="row">
        <div class="col-md-2 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.nbr_emplois_prevus" />:</label>
        </div>
        <div class="col-md-10 col-sm-8 col-xs-6">
            <div class="badge badge-info" role="alert">${programme.nbreEmploiPrevu}</div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.nbr_emplois_reels" />:</label>
        </div>
        <div class="col-md-10 col-sm-8 col-xs-6">
            <div class="badge badge-info" role="alert">${programme.nbreEmploiReel}</div> 
        </div>
    </div>
</div>
