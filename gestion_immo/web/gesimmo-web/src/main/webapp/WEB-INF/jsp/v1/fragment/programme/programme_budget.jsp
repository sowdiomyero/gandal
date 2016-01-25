<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="row">
        <div class="col-md-3  col-sm-4 col-xs-6">
            <label ><spring:message code="label.programme.budget_prevu" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6">${programme.budgetPrevu}</div>
    </div>
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.budget_reel" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6">${programme.budgetReel}</div>
    </div>
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.nbr_emplois_prevus" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6">${programme.nbreEmploiPrevu}</div>
    </div>
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.nbr_emplois_reels" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6">${programme.nbreEmploiReel}</div>
    </div>

</div>

