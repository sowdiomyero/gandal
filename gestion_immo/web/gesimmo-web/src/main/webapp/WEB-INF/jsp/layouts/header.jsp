<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<nav id="menu_haut" class="navbar navbar-default navbar-fixed-top " role="navigation">
    <div id="menu_haut_contact" class="container" style="margin-top: 2px; margin-bottom: 2px;width: 100%; display: none"> 
        
        <ul style="float:right; display: inline ;color:white;">                  
            <li style="display: inline"><i class="fa fa-paper-plane-o fa-10"> </i>
                <a href="#">Nous contacter</a>
            </li>
            <li style="display: inline"><i class="fa fa-code"></i>
                <a href="http://api.gesimmo.sn/">Developpeurs</a>
            </li>
            <li style="display: inline"><i class="fa fa-briefcase"></i>
                <a href="https://experts.gesimmo.sn/">Pricing</a>
            </li>
            <li style="border-right:none;display: inline"><i class="fa fa-phone fa-10"></i>
                Sénégal: <b>+221 77 704 90 88</b>
            </li>
        </ul>
           
    </div>
    <div class="container-fluid" style="border-color:#FF00FF; color:white">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a style="font-weight: bold; color: #fff" class="navbar-brand" href="#">GES-IMMO</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="color:#FFFFFF">
            <ul class="nav navbar-nav">
                <sec:authorize  ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN">
                    <li class="dropdown">
                        <a href="${pageContext.request.contextPath}" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;color: #fff"><span
                                class="glyphicon glyphicon-dashboard"> </span > <spring:message code="label.header.dashboard"  /><span class="caret"></span></a>

                    </li>


                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;color: #fff"><span
                                class="glyphicon glyphicon-globe"></span> <spring:message code="label.header.localisation"  /> <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li>
                                <a href="${pageContext.request.contextPath}/localisationList" style="font-weight: bold;"><span class="glyphicon"></span>  <spring:message code="label.header.localisation.tableau"  /></a>
                            </li>

                            <li>
                                <a href="${pageContext.request.contextPath}" style="font-weight: bold;"><span class="glyphicon"></span> <spring:message code="label.header.localisation.cartographie"  /></a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/zone/map" style="font-weight: bold;"><span class="glyphicon"></span>  <spring:message code="label.header.localisation.zonage"  /></a>
                            </li>

                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;color: #fff"><span
                                class="glyphicon glyphicon-tasks"> </span>  <spring:message code="label.header.programme"  />  <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li>

                                <a href="programmelist" style="font-weight: bold"><span class="glyphicon"> </span> <spring:message code="label.header.programme.list"  /> </a>
                            </li>


                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;color: #fff"><span
                                class="glyphicon glyphicon-tasks"> </span> <spring:message code="label.header.projet"  /> <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">

                            <li>

                                <a href="projetlist" style="font-weight: bold"><span class="glyphicon"> </span> <spring:message code="label.header.projet.list"  /></a>
                            </li>

                        </ul>
                    </li>





                </sec:authorize>

            </ul>


            <sec:authorize access="isAuthenticated()">
                <ul class="nav navbar-nav navbar-right">

                    <li style="font-weight: bold;color: #fff ; margin-top: 15px;"  >
                        <span > <a href="?locale=fr">Français</a> | <a href="?locale=en">English</a> </span>

                    </li>

                   

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;color: #fff"><span
                                class="glyphicon glyphicon-envelope"></span><span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li>

                                <a href="#" style="font-weight: bold;"><span class="glyphicon"></span> <spring:message code="label.header.message.email"  /></a>
                            </li>
                            <li>

                                <a href="#" style="font-weight: bold;"><span class="glyphicon"></span> <spring:message code="label.header.message.sms"  /></a>
                            </li>

                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;color: #fff"><span
                                class="glyphicon glyphicon-bell"> </span><div class="badge">5</div> <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li>

                                <a href="#" style="font-weight: bold;"><span class="glyphicon"></span><spring:message code="label.header.notif.new.email"  /> <span class="badge"> 5min </span></a>
                            </li>
                            <li>

                                <a href="#" style="font-weight: bold;"><span class="glyphicon"></span> <spring:message code="label.header.notif.new.sms"  />  <span class="badge"> 5min </span></a>
                            </li>
                            <li class="divider"></li>
                            <li >

                                <a href="#" style="font-weight: bold;"><span class="glyphicon"></span><spring:message code="label.header.notif.all"  /></a>
                            </li>

                        </ul>
                    </li> 
                    <li class="dropdown">
                        <a href="#" style="font-weight: bold;color: #fff" class="dropdown-toggle" data-toggle="dropdown"><span
                                class="glyphicon glyphicon-user"  style="font-weight: bold;" ></span><span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">

                            <li>
                                <a href="#" style="font-weight: bold;" data-toggle="modal" class="showProfil " data-id="<sec:authentication property="principal.username" />" ><span class="glyphicon glyphicon-cog"></span> <spring:message code="label.header.profil.profil"  /></a>
                            </li>
                            
                            <li>

                                <a href="userslist" style="font-weight: bold;"><span class="fa fa-users "></span> <spring:message code="label.header.param.list.users"  /></a>
                            </li>
                            <li>

                                <a href="roleslist" style="font-weight: bold;"><span class="fa fa-cogs "></span> <spring:message code="label.header.param.list.roles"  /></a>
                            </li>
                            <li>

                                <a href="indicateurslist" style="font-weight: bold;"><span class="fa fa-signal "></span> <spring:message code="label.header.param.list.indicateurs"  /></a>
                            </li>

                  
                            <sec:authorize  ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN">
                                <li><a href="${pageContext.request.contextPath}/settings" style="font-weight: bold;"><span class="glyphicon glyphicon-wrench"></span> <spring:message code="label.header.profil.param"  /></a></li>
                                </sec:authorize>
                            <li class="">
                                <a href="#"  style="font-weight: bold;"><span
                                        class="glyphicon glyphicon-question-sign"> </span> <spring:message code="label.header.profil.aide"  /></a>

                            </li>
                            <li class="divider"></li>
                            <li><a href="<c:url value="/j_spring_security_logout" />" style="font-weight: bold;"> <span
                                        class="glyphicon glyphicon-off"></span> <spring:message code="label.header.profil.deconnect"  /></a></li>

                        </ul>
                    </li>
                </ul>
            </sec:authorize>

        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
    <!-- contenu des modals deplacés vers htmlLayout.jsp-->
</nav>

