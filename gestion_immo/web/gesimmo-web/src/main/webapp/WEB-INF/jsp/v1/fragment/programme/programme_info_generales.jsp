<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-md-6 " style="border-top: solid 1px;">
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.panel.nom" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6"> ${programme.nomActivite}</div>
    </div>
    <div class="row">
        <div class="col-md-3  col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.description" />:</label>
        </div>
        <div class="col-md-9col-sm-8 col-xs-6"> ${programme.description}</div>
    </div>
    <div class="row">
        <div class="col-md-3  col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.etat" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6">
            <div class="badge badge-info" role="alert">${programme.etatActuel.nom}</div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3  col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.ponderation" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6 ">
            <div class="badge badge-info" role="alert"> ${programme.ponderation}</div>
        </div>
    </div>
</div>

<div class="col-md-6" style="border-top: solid 1px;">
    <div class="row">
        <div class="col-md-3  col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.date_debut_prevu" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6"> <fmt:formatDate type="date" value="${programme.dateDebPrevu}" /> </div>
    </div>
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.date_debut_reelle" />:</label>
        </div>
        <div class="col-md-9 col-sm-8 col-xs-6"><fmt:formatDate type="date" value="${programme.dateDebReel}" /></div>
    </div>
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.date_fin_prevu" />:</label>
        </div>
        <div class="col-md-9 col-sm-8 col-xs-6">
            <fmt:formatDate type="date" value="${programme.dateFinPrevu}" />
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label><spring:message code="label.programme.date_fin_reelle" />:</label>
        </div>
        <div class="col-md-9 col-sm-8 col-xs-6"> <fmt:formatDate type="date" value="${programme.dateFinReel}" /></div>
    </div>
</div>

<div class="col-md-6" style="border-top: solid 1px;">
    <br/>
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label>Realisation:</label>
        </div>
        <div class="col-md-9 col-sm-8 col-xs-6">
            <div class="progress  progress-striped active">
                <c:if test="${programme.tauxRealisation le 25}">
                    <div class="progress-bar progress-bar-danger " role="progressbar" aria-valuenow="60"
                         aria-valuemin="0" aria-valuemax="100"
                         style="width: ${programme.tauxRealisation}%;">${programme.tauxRealisation}%</div>
                </c:if>
                <c:if test="${programme.tauxRealisation gt 25 and programme.tauxRealisation le 50}">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60"
                         aria-valuemin="0" aria-valuemax="100"
                         style="width: ${programme.tauxRealisation}%;">${programme.tauxRealisation}%</div>
                </c:if>
                <c:if test="${programme.tauxRealisation gt 50 and programme.tauxRealisation le 75}">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="60"
                         aria-valuemin="0" aria-valuemax="100"
                         style="width: ${programme.tauxRealisation}%;">${programme.tauxRealisation}%</div>
                </c:if>
                <c:if test="${programme.tauxRealisation gt 75 }">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60"
                         aria-valuemin="0" aria-valuemax="100"
                         style="width: ${programme.tauxRealisation}%;">${programme.tauxRealisation}%</div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label>Budget :</label>
        </div>
        <div class="col-md-9 col-sm-8 col-xs-6">
            <c:if test="${programme.budgetReel le programme.budgetPrevu}">
                <div class="progress  progress-striped active">
                    <div class="progress-bar progress-bar-success " role="progressbar" aria-valuenow="60"
                         aria-valuemin="0" aria-valuemax="100"
                         style="width: ${(programme.budgetReel *100) div (programme.budgetPrevu)}%;"><fmt:formatNumber type="number" minFractionDigits="3" value="${(programme.budgetReel *100) div (programme.budgetPrevu)}" />%</div>
                </div>
            </c:if>
          
            <c:if test="${programme.budgetReel gt programme.budgetPrevu}">
                <div class="progress progress-striped active">
                    <div class="progress-bar progress-bar-success" style="width: ${(programme.budgetPrevu *100) div (programme.budgetReel)}%"/>100% </div>
                <div class="progress-bar progress-bar-danger" style="width: ${((programme.budgetReel-programme.budgetPrevu) * 100) div (programme.budgetReel)}%"/><fmt:formatNumber type="number" minFractionDigits="3" value="${(((programme.budgetReel-programme.budgetPrevu) * 100) div (programme.budgetReel))}" />% </div>
                </div>
            </c:if>

        </div>
    </div>
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label>Temps :</label>
        </div>
        <div class="col-md-9 col-sm-8 col-xs-6">
            <div class="progress  progress-striped active">
                <div class="progress-bar progress-bar-warning " role="progressbar" aria-valuenow="60"
                     aria-valuemin="0" aria-valuemax="100"
                     style="width: 25%;">25%</div>
            </div>
        </div>
    </div>

</div>
<div class="col-md-6 " style="border-top: solid 1px;">
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <label><spring:message code="label.activte.responsable" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6"> Khadim Cisse</div>
    </div>
    <div class="row">
        <div class="col-md-3  col-sm-4 col-xs-6">
            <label><spring:message code="label.activte.contact" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6"> Khadim Cisse</div>
    </div>
    <div class="row">
        <div class="col-md-3  col-sm-4 col-xs-6">
            <label><spring:message code="label.activte.createur" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6">
               <div class="col-md-9  col-sm-8 col-xs-6"> Khadim Cisse</div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3  col-sm-4 col-xs-6">
            <label><spring:message code="label.activte.superviseur" />:</label>
        </div>
        <div class="col-md-9  col-sm-8 col-xs-6 ">
             <div class="col-md-9  col-sm-8 col-xs-6"> Khadim Cisse</div>
        </div>
    </div>
</div>