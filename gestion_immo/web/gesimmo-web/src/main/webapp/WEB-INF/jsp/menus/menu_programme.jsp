 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="row panel panel-default" style="padding-bottom: 15px;" >
    <ul class="nav nav-pills nav-stacked">
        <li class="active "><a href="#"><span style="  font-weight: bold;color: #fff">Activite</span></a></li>
        <li><a href="programmelist"> <spring:message code="label.header.programme" /></a></li>
        <li><a href="projetlist"> <spring:message code="label.header.projet" /></a></li>
    </ul>
</div>

<div class="row panel panel-default" style="padding-bottom: 15px;" >
    <ul class="nav nav-pills nav-stacked">
        <li class="active"><a  href="#"><span style="color: #FFFFFF;font-weight: bold;"><spring:message code="label.programme.menu.recherche.titre" /></span></a></li>
        <li> <a href="#" data-toggle="modal" data-target="#RechercheProjet"><span class="glyphicon glyphicon-search"><spring:message code="label.programme.menu.recherche.nom" /></span></a></li>
    </ul>
</div>
