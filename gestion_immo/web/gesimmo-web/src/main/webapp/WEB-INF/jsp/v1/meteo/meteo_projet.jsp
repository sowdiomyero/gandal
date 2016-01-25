 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-lg-3 col-md-6">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-comments fa-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${nbr_projet_termine}</div>
                    <div><spring:message code="label.projet.recherche.etat.termine" /></div>
                </div>
            </div>
        </div>  
        <a href="#">
            <div class="panel-footer">
                <span class="pull-left"><spring:message code="label.div.meteo.details" /></span>

                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>

<div class="col-lg-3 col-md-6">
    <div class="panel panel-green" style="border-color: #5cb85c;">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-comments fa-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${nbr_projet_en_cours}</div>
                    <div><spring:message code="label.projet.recherche.etat.demarre" /></div>
                </div>
            </div>
        </div>
        <a href="#">
            <div class="panel-footer ">
                <span class="pull-left"><spring:message code="label.div.meteo.details" /></span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>

<div class="col-lg-3 col-md-6">
    <div class="panel panel-red" style="border-color: #d9534f;">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-comments fa-5x"></i>     
                </div>

                <div class="col-xs-9 text-right">
                    <div class="huge">${nbr_projet_suspendu}</div>
                    <div><spring:message code="label.projet.recherche.etat.suspendu" /></div>
                </div>
            </div>
        </div>
        <a href="#">
            <div class="panel-footer">
                <span class="pull-left"><spring:message code="label.div.meteo.details" /></span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>

<div class="col-lg-3 col-md-6">
    <div class="panel panel-yellow" style=" border-color: #f0ad4e;">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-comments fa-5x"></i>     
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${nbr_projet_abandonne}</div>
                    <div><spring:message code="label.projet.recherche.etat.abandonne" /></div>
                </div>
            </div>
        </div>
        <a href="#">
            <div class="panel-footer">
                <span class="pull-left"><spring:message code="label.div.meteo.details" /></span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>

