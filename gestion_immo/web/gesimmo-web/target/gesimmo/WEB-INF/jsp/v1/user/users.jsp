<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<sec:authorize access="isAuthenticated()">

    <div id="menu_actif_location" class="container" style="margin-top: 1px; margin-bottom: 5px;">        
        <div class="center-block" >
              <center><h4>GESTION DES UTILISATEURS</h4></center>
         </div>           
    </div>
    <!-- Begin Body -->

    <div class="container-fluid" style="margin-left: 25px; margin-right: 25px;">    




        <div class="row">


            <!--            Menu Administration-->
            <div class="col-md-2" >
                <%@include file="./menu_user.jsp" %>
            </div>

            <!--            Meteo Utilisateur-->
            <div class="col-md-10">
                <div class="row">
                    <%@include file="./meteo_user.jsp" %>
                </div>

                <!--                Tableau utilisateur-->
                <div class="row">
                    <div class="col-lg-12" >
                        <%@include file="./tableau_user.jsp" %>
                    </div>
                </div>

            </div>
        </div>

    </div>

 <!-- AFFICHAGE D'UNE FICHE UTILISATEUR -->

    <div class="modal fade" id="profil" tabindex="-1" role="dialog"
         aria-labelledby="profil" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">
                        <span class="" aria-hidden="true"></span>
                    </button>
                    <h4 class="glyphicon glyphicon-user" id="Heading"> Profil Utilisateur</h4>
                </div>

                <div class="modal-body">

                    <form action="${pageContext.request.contextPath}/user/view" method="GET">
                        <div class="alert alert-danger">
                            <span class="glyphicon glyphicon-bookmark"></span>
                            <p>Not Yet Implemented : Ajouter la fiche de l'utilisateur ICI</p>
                        </div>
                    </form>

                </div>
                <div class="modal-footer ">

                    <button type="submit" class="btn btn-success">
                        <span class="glyphicon glyphicon-ok-sign"></span> Valider
                    </button>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove"></span> Annuler
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div> 

 
   <!-- Modal de confirmation  -->

    <div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog"
         aria-labelledby="edit" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </button>
                    <h4 class="modal-title custom_align" id="Heading"> Confirmation </h4>
                </div>
                <div class="modal-body">

                    <div class="alert alert-danger">
                        <span class="glyphicon glyphicon-info-sign"></span> 
                        <p id="confirmationContent">Résultat de votre requête :</p>
                    </div>

                </div>
                <div class="modal-footer ">

                    <button value="oui" type="submit" class="btn btn-success" data-dismiss="modal" >
                        <span class="glyphicon glyphicon-ok-sign"></span> Oui
                    </button>

                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>

 
 
  

</sec:authorize>

