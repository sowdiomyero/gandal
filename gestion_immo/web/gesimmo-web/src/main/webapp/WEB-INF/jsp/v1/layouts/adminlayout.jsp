<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">

            <title>Geo-Pil</title>
            <meta name="generator" content="idyalgroupe"/>
            <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
                <link href="${pageContext.request.contextPath}/css/idyal_ccs.css" type="text/css" rel="stylesheet">
                    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
                        <link href="${pageContext.request.contextPath}/css/jquery.dataTables.css" type="text/css" rel="stylesheet">
                            <!-- Le styles -->
                            <!--<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
                            <!--[if lt IE 9]>
                            <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
                            <![endif]-->

                            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
                            <script src="${pageContext.request.contextPath}/js/jquery-dataTable.min.js"></script>
                            <%--<script src="js/bootstrap.min.js"></script>--%>
                            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                            <script src="${pageContext.request.contextPath}/js/notify.min.js"></script>
                            <script src="${pageContext.request.contextPath}/js/esecure.js"></script>
                            <script src="${pageContext.request.contextPath}/js/gesimmo.js"></script>
                            <%--<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>--%>
<!--                            <script type='text/javascript'>

                                $(document).ready(function() {

                                    $('#sidebar').affix({
                                        offset: {
                                            top: 245
                                        }
                                    });

                                    var $body = $(document.body);
                                    var navHeight = $('.navbar').outerHeight(true) + 10;

                                    $body.scrollspy({
                                        target: '#leftCol',
                                        offset: navHeight
                                    });

                                });

                            </script>-->

        <script type="text/javascript" src="https://gandal.atlassian.net/s/7445f1673c4f0ec4af08e92de75a1edf-T/fr_FRjns6pq/64014/25/1.4.24/_/download/batch/com.atlassian.jira.collector.plugin.jira-issue-collector-plugin:issuecollector/com.atlassian.jira.collector.plugin.jira-issue-collector-plugin:issuecollector.js?locale=fr-FR&collectorId=ad2ef015"></script>
        <!-- AJAX LOADER-->

        <script type="text/javascript">
            function ajaxindicatorstart(text)
            {
                if(jQuery('body').find('#resultLoading').attr('id') != 'resultLoading'){
                    jQuery('body').append('<div id="resultLoading" style="display:none">' +
                            '<div>' +
                            '<img src="img/loading.gif" style="width: 100px; height: 100px">' +
                            '<div>'+text+'</div>' +
                            '</div>' +
                            '<div class="bg"></div>' +
                            '</div>');
                }

                jQuery('#resultLoading').css({
                    'width':'100%',
                    'height':'100%',
                    'position':'fixed',
                    'z-index':'10000000',
                    'top':'0',
                    'left':'0',
                    'right':'0',
                    'bottom':'0',
                    'margin':'auto'
                });

                jQuery('#resultLoading .bg').css({
                    'background':'#000000',
                    'opacity':'0.7',
                    'width':'100%',
                    'height':'100%',
                    'position':'absolute',
                    'top':'0'
                });

                jQuery('#resultLoading>div:first').css({
                    'width': '250px',
                    'height':'75px',
                    'text-align': 'center',
                    'position': 'fixed',
                    'top':'0',
                    'left':'0',
                    'right':'0',
                    'bottom':'0',
                    'margin':'auto',
                    'font-size':'16px',
                    'z-index':'10',
                    'color':'#ffffff'

                });

                jQuery('#resultLoading .bg').height('100%');
                jQuery('#resultLoading').fadeIn(300);
                jQuery('body').css('cursor', 'wait');
            }

            function ajaxindicatorstop()
            {
                jQuery('#resultLoading .bg').height('100%');
                jQuery('#resultLoading').fadeOut(300);
                jQuery('body').css('cursor', 'default');
            }

            /*function callAjax()
             {
             jQuery.ajax({
             type: "GET",
             url: "fetch_data.php",
             cache: false,
             success: function(res){
             jQuery('#ajaxcontent').html(res);
             }
             });
             }*/

            jQuery(document).ajaxStart(function () {
                //show ajax indicator
                ajaxindicatorstart('Chargement des donnees... Veuillez patienter SVP');
            }).ajaxStop(function () {
                        //hide ajax indicator
                        ajaxindicatorstop();
                    });
        </script>
                            </head>


                            <body style="">
                                <div class="wrap">
                                    <div class="navbar navbar-bright navbar-fixed-top" role="banner">
                                        <!-- Begin header -->
                                        <div class="navbar-header">

                                            <div class="col-md-2" id="header">
                                                <tiles:insertAttribute name="header"/>
                                            </div>

                                        </div>
                                    </div>
                                    <div id="masthead"></div>

                                    <!-- Begin Body -->
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-md-3" >
                                                <tiles:insertAttribute name="home"/>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Begin Login form -->
                                    <div class="container" >
                                        <div class="row">
                                            <div class="col-md-3"  >
                                                <tiles:insertAttribute  name="login" />
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Begin footer-->

                                    <div id="row">
                                        <div class="col-md-3" id="footerid">
                                            <tiles:insertAttribute name="footer"/>
                                        </div>
                                    </div>

                                </div>
                                <!-- -->
                                <!-- ########################AJOUT UTILISATEUR##############################  -->
                                <div class="modal fade modal-admin" id="CreateUser" tabindex="-1" role="dialog" aria-labelledby="CreateUser" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <form:form commandName="subscriber" action="${pageContext.request.contextPath}/user/addUser" method="post" id="submitForm">
                                            <div class="modal-content" style="width:900px; margin-left: -150px">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">
                                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                                    </button>
                                                    <div style="margin-bottom: 5px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;">AJOUT
                                                        UTILISATEUR
                                                    </div>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row">

                                                        <div class="col-md-12" id="leftCol2">
                                                            <div class="panel panel-info">
                                                                <div class="panel-heading">
                                                                    <h3 class="panel-title">Info Utilisateur</h3>
                                                                    <%--   <c:if test="${not empty message}">
                                                                           <div class="message green">${message}</div>--%>
                                                                    <%-- </c:if>--%>
                                                                </div>
                                                                <div class="panel-body" style="color: #000033">
                                                                    <div class="alert-success" id="userMessageSuccess" style="display: none" >
                                                                        <%-- <c:if test="${not empty message}">--%>
                                                                        <div class="alert-danger" style="display: none" id="userMessageFailed" >
                                                                            <%--   <h2>${message} </h2> --%>
                                                                        </div>
                                                                        <%--  </c:if>--%>
                                                                    </div>
                                                                    <label for="nomInput">Nom: </label>
                                                                    <form:input class="form-control" required="required"  path="nom" placeholder="Votre Nom" id="nomInput"/>


                                                                    <label for="prenomInput">Prenom: </label>
                                                                    <form:input class="form-control" path="prenom" required="required" placeholder="Votre Prénom" id="prenomInput"/>


                                                                    <label for="emailInput">Email: </label>
                                                                    <form:input class="form-control" path="email" required="required" placeholder="Votre Email" id="emailInput"/>


                                                                    <label for="roleOptions">Role: </label>
                                                                    <form:select class="form-control" path="role" id="roleOptions" required="required">
                                                                        <form:option value="">Select Role</form:option>
                                                                        <c:forEach items="${roles}" var="role">
                                                                            <form:option value="${role}">${role}</form:option>
                                                                        </c:forEach>
                                                                    </form:select>

                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer ">

                                                    <button type="submit" onClick="" class="btn btn-info" value="submit">Valider</button>
                                                    <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning">Annuler</button>

                                                </div>
                                            </div>
                                        </form:form>
                                    </div>

                                </div>

                                <!-- ######################## VISUALISER UN PROFIL UTILISATEUR ##############################  -->
                                <div class="modal fade modal-admin" id="viewProfilModal" tabindex="-1" role="dialog" aria-labelledby="viewProfilModal" aria-hidden="true">
                                    <div class="modal-dialog">

                                        <div class="modal-content" style="width:900px; margin-left: -150px">
                                            <form commandName="editUserForm" action="${pageContext.request.contextPath}/user/changePassword" method="post" id="editProfilFormulaire">
                                                <div class="modal-header">

                                                    <div style="margin-bottom: 5px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;">Editer
                                                        Profil Utilisateur
                                                    </div>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row">

                                                        <div class="col-md-12" id="leftCol">
                                                            <div class="panel panel-info">
                                                                <div class="panel-heading">
                                                                    <h3 class="panel-title">Info Utilisateur</h3>
                                                                    <c:if test="${not empty message}">
                                                                        <div class="message green">${message}</div>
                                                                    </c:if>
                                                                </div>
                                                                <div class="panel-body" style="color: #000033">
                                                                    <div class="alert alert-success" role="alert" id="profilMessageSuccess" style="display: none">
                                                                        <div class="alert alert-danger" role="alert" style="display: none" id="profilMessageFailed">
                                                                        </div>
                                                                    </div>

                                                                    <label for="editProfilNomInput">Nom: </label>
                                                                    <input class="form-control" required="required"  name="nom" placeholder="Votre Nom" id="editProfilNomInput"/>


                                                                    <label for="editProfilPrenomInput">Prenom: </label>
                                                                    <input class="form-control" name="prenom" required="required" placeholder="Votre Prénom" id="editProfilPrenomInput"/>


                                                                    <label for="editProfilEmailInput">Email: </label>
                                                                    <input class="form-control"  type="email" name="email" required="required" placeholder="Votre Email" id="editProfilEmailInput"/>

                                                                    <label for="editProfilTelInput">Telephone: </label>
                                                                    <input class="form-control"  name="telephone"  placeholder="Votre Numero telephone" id="editProfilTelInput"
                                                                           type="tel" pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$"/>

                                                                    <label for="editProfilLoginInput">Login: </label>
                                                                    <input class="form-control" readonly="false" name="login" required="required" placeholder="Votre login" id="editProfilLoginInput"/>

                                                                    <label for="editProfilPasswordInput">Password: </label>
                                                                    <input class="form-control" name="password" type="password"  required="required" placeholder="Votre mot de passe" id="editProfilPasswordInput"/>

                                                                    <div style="float:right">
                                                                        <a href="#" data-toggle="modal" class="changePassword" ><span class="glyphicon glyphicon-cog"></span> Changer votre password</a>
                                                                    </div>

                                                                    <div id="panelPassword" style="display: none">
                                                                        <label for="editProfilNewPasswordInput">Nouveau password: </label>
                                                                        <input class="form-control"  type="password" name="newPassword" placeholder="Votre nouveau password" id="editProfilNewPasswordInput"/>

                                                                        <label for="editProfilConfirmNewPasswordInput">Confirmer mot de passe: </label>
                                                                        <input class="form-control" type="password" name="confirmPassword"  placeholder="Votre mot de passe" id="editProfilConfirmNewPasswordInput"/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer ">

                                                    <button type="submit" id="formEditButton" onClick="" class="btn btn-info" value="submit">Enregisrer</button>
                                                    <button type="reset"   class="annulerModifProfil btn btn-warning">Annuler Modifications</button>
                                                    <button type="button" id="fermerModal" data-dismiss="modal"  class="btn btn-warning">Fermer</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </body>
                            </html>