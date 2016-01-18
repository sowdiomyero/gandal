
<div class="form-group">
    <label class="col-sm-2 control-label">Taux de realisation</label>
    <div class="control-label">
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
<div class="form-group">
    <label class="col-sm-2 control-label">Consommation budget</label>
    <div class="control-label progress  progress-striped active">
        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="60"
             aria-valuemin="0" aria-valuemax="100"
             style="width: 90%;">90%</div>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-2 control-label">Consommation temps</label>
    <div class="control-label progress  progress-striped active">
        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="60"
             aria-valuemin="0" aria-valuemax="100"
             style="width: 92%;">92%</div>
    </div>
</div>