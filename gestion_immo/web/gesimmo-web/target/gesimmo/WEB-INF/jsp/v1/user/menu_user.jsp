 <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="row panel panel-default" style="padding-bottom: 15px;" >
    <ul class="nav nav-pills nav-stacked">
        <li class="bgcode"><a href="#"><span >Administration</span></a></li>
        <li><a href="${pageContext.request.contextPath}/user/list">Utilisateurs</a></li>
        <li><a href="${pageContext.request.contextPath}/role/list">Roles</a></li>
        <li><a href="#">Groupes</a></li>
        <li><a href="#">Autorisations</a></li>
    </ul>
</div>

<div class="row panel panel-default" style="padding-bottom: 15px;" >
    <ul class="nav nav-pills nav-stacked">
        <li class="bgcode"><a  href="#"><span>Monitoring</span></a></li>
        <li><a href="#">Tableau de bord</a></li>
        <li> <a href="#"> Surveillance</a></li>
<!--        <li> <a href="#"> <spring:message code="label.title"/></a></li>-->
     </ul>
</div>
