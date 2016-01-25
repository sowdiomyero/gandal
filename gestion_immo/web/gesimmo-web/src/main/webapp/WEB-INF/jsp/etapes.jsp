
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<sec:authorize access="isAuthenticated()">


    <!-- Begin Body -->

    <div class="container-fluid" style="margin-left: 25px; margin-right: 25px;">    


        <div class="row">
            <div class="panel panel-default" style=" margin-bottom: 10px; ">
                <div class="panel-heading text-center">
                    <h2>GESTION DES ETAPES</h2>
                </div>
            </div>
        </div>


        <div class="row">


            <!--            Menu Administration-->
            <div class="col-md-2" >
                <%@include file="./menus/menu_projet.jsp" %>
            </div>

            <!--            Meteo Utilisateur-->
            <div class="col-md-10">
                <div class="row">
                    <%@include file="./meteo/meteo_etape.jsp" %>

                </div>

                <!--                Tableau utilisateur-->
                <div class="row">
                    <div class="col-lg-12" >
                        <%@include file="./tableaux/tableau_etape.jsp" %>

                    </div>
                </div>

            </div>
        </div>

    </div>


 
</sec:authorize>

