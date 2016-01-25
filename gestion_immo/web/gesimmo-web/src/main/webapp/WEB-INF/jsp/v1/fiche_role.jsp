<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
                    <h2><spring:message code="label.role.header.titre"/></h2>
                </div>
            </div>
        </div>
        <div class="row">
            <!--            Menu Administration-->
            <div class="col-md-2" >
                <%@include file="./menus/menu_user.jsp" %>
            </div>

            <!--            Meteo Utilisateur-->
            <div class="col-md-10">
                <div class="row">
                    <%@include file="./meteo/meteo_role.jsp" %>
                </div>

                <!--                Tableau utilisateur-->


                <div class="panel panel-primary " >
                    <div class="panel-heading" style=" background-color: #CCD4D9 ; color:  black" >
                        <div class="row">
                            <div class="col-md-6"><h2 class="panel-title" style="text-align: center; font-weight: bold"><spring:message code="label.role.tableau.header.actions.title.fiche"/>  <span class="glyphicon glyphicon-folder-open"></span></h2></div>
                            <div class="col-md-6"><h2 class="panel-title" style="text-align: center; font-weight: bold" pull="left"><a href="${pageContext.request.contextPath}/roleslist"><span class="fa fa-reply-all"></span> <spring:message code="label.bouton.span.retour"/></a></h2></div>
                        </div>
                    </div>
                    <div style="margin: 20px;" >
                        <div class="form-group row "  >
                        <form:form commandName="roleForm" action="${pageContext.request.contextPath}/updateRole" method="post" id="submitRoleFormUpdate">
                            <div class="form-group row "  >
                                    <div class="col-lg-3">
                                        <label for="descRoleUpdate"><spring:message code="label.role.fiche.description"/> </label>
                                        <form:input class="form-control" path="roleDesc" required="required" placeholder="Description" id="descRoleUpdate"/>
                                    </div>

                                    <div class="col-lg-3">
                                        <label for="nomRoleUpdate"><spring:message code="label.role.fiche.nom"/> </label>
                                        <form:input class="form-control" required="required"  path="nameRole" placeholder="Nom" id="nomRoleUpdate"/>
                                        <form:hidden class="form-control" required="required"  path="idRole" placeholder="Nom" id="idRoleUpdate"/>
                                    </div>
                                </div>
                                    <div class="form-group row "  >
                                    <div class="col-lg-3">
                                        <label for="usr"><spring:message code="label.role.fiche.datecreation"/></label>
                                        <form:input class="form-control" path="dateCreation" required="required" placeholder="Description" id="" disabled="true"/>
                                    </div>

                                    <div class="col-lg-3">
                                        <label for="usr"><spring:message code="label.role.fiche.dateupdate"/></label>
                                        <form:input class="form-control" required="required"  path="dateUpdated" placeholder="Nom" id="" disabled="true"/>
                                    </div>
                                </div>
                                <div class="form-group row "  >
                                    <div class="col-lg-12">
                                        <span class=""></span>
                                        <button  type="submit" class=" btn btn-md  fa fa-pencil-square-o " style="float: right; background-color: rgb(193,25,83); color: white;">  <spring:message code="label.bouton.update"/> </button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                    <div style="margin: 20px;" >
                        <div class="form-group row "  >
                            <%@include file="./tableaux/tableau_user.jsp" %>
                        </div>
                    </div>          
                </div>
            </div>
        </div>
    </div>



    <!-- Modal suprimer un utilisateur-->
    <div class="modal fade" id="deleteConfirmationModal" tabindex="-1" role="dialog"
         aria-labelledby="edit" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </button>
                    <h4 class="modal-title custom_align" id="Heading"> Suppression utilisateur</h4>
                </div>
                <div class="modal-body">

                    <div class="alert alert-danger">
                        <span class="glyphicon glyphicon-warning-sign"></span> 
                        <p>Etes-vous sûr de vouloir supprimer cet utilisateur ?</p>
                    </div>

                </div>
                <div class="modal-footer ">
                    <button value="oui" type="submit" class="btn btn-success" id="okDeletionModalButton">
                        <span class="glyphicon glyphicon-ok-sign"></span> Oui
                    </button>
                    <button value="non" type="button" class="btn btn-default"
                            data-dismiss="modal" id="non">
                        <span class="glyphicon glyphicon-remove"></span> Non
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
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

                    <form action="/user/view" method="get">
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



    <!-- Régeneration du mot de passe de l'utilisateur.-->
    <div class="modal fade" style="z-index: 1051" id="regenererConfirmationModal" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <!--                        <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                            </button>-->
                    <h4 class="modal-title custom_align" id="Heading"> Régeneration du mot de passe de l'utilisateur</h4>
                </div>
                <div class="modal-body">
                    <div class="alert-success" id="regenereSuccess">    
                        <div class="alert-danger" style="display: none" id="regenereFailed"></div>
                    </div>
                    <div class="alert alert-warning" id="messageConfirmation">
                        <span class="glyphicon glyphicon-warning-sign"></span> 
                        <p>Etes-vous sûr(e) de vouloir régenerer le mot de passe de cet utilisateur ?</p>
                    </div>

                </div>
                <div class="modal-footer ">
                    <button value="oui" type="submit" class="btn btn-success" data-dismiss="modal" id="okRegenererModalButton">
                        <span class="glyphicon glyphicon-ok-sign"></span> Oui
                    </button>
                    <button type="button" class="btn btn-warning" data-dismiss="modal" id="non">
                        <span class="glyphicon glyphicon-remove"></span> Non
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>


    <div class="modal fade modal-admin" id="viewUserModal" tabindex="-1" role="dialog" aria-labelledby="viewUserModal" aria-hidden="true">
        <div class="modal-dialog">

            <div class="modal-content" style="width:900px; margin-left: -150px">
                <form commandName="editUserForm" action="${pageContext.request.contextPath}/user/editUser" method="post" id="editUserFormulaire">
                    <div class="modal-header">
                        <!--                        <button type="button" class="close" data-dismiss="modal"
                                                        aria-hidden="true">
                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                                </button>-->
                        <div style="margin-bottom: 5px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;">Editer
                            Profil Utilisateur
                        </div>
                    </div>
                    <div class="modal-body">
                        <div class="row">

                            <div class="col-md-12" id="leftCol">
                                <div class="panel panel-info">
                                    <div class="panel-body" style="color: #000033">
                                        <!--                                        <div class="alert alert-success" style="display: none" role="alert" id="userEditSuccess">
                                                                                    <div class="alert alert-danger" role="alert" style="display: none" id="userEditFailed"></div>
                                                                                </div>-->
                                        <label for="editLoginInput">Login: </label>
                                        <input class="form-control" readonly="false" name="login" required="required" placeholder="Votre login" id="editLoginInput"/>

                                        <label for="editNomInput">Nom: </label>
                                        <input class="form-control" required="required"  name="nom" placeholder="Votre Nom" id="editNomInput"/>


                                        <label for="editPrenomInput">Prénom: </label>
                                        <input class="form-control" name="prenom" required="required" placeholder="Votre Prénom" id="editPrenomInput"/>


                                        <label for="editEmailInput">Email: </label>
                                        <input class="form-control" type="email" name="email" required="required" placeholder="Votre Email" id="editEmailInput"/>

                                        <label for="editTelInput">Téléphone: </label>
                                        <input class=" form-control"  name="telephone" required="required" placeholder="Votre Numero téléphone" id="editTelInput"
                                               type="tel" pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$"/>

                                        <button type="button" id="regenerePassword"  data-toggle="modal" data-target="#regenererConfirmationModal" class=" bottom btn btn-info" value="submit">Regénérer mot de passe utilisateur</button>
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer ">

                        <button type="submit" id="formEditButton" onClick="" class="btn btn-info" value="submit">Editer</button>
                        <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning">Fermer</button>

                    </div>
                </form>
            </div>

        </div>

    </div>

</sec:authorize>

