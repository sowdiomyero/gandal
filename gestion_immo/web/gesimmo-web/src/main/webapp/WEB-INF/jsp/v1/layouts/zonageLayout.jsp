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

            <title>Gestion Immo</title>
            <meta name="generator" content="idyalgroupe"/>
            <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
                <link href="${pageContext.request.contextPath}/css/idyal_ccs.css" type="text/css" rel="stylesheet">
                    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
                        <link href="${pageContext.request.contextPath}/css/jquery.dataTables.css" type="text/css" rel="stylesheet">
                            <link href="${pageContext.request.contextPath}/css/dataTables.responsive.css" type="text/css" rel="stylesheet">
                                <link href="${pageContext.request.contextPath}/css/sb-admin-rtl.css" type="text/css" rel="stylesheet">
                                    <link href="${pageContext.request.contextPath}/css/sb-admin.css" type="text/css" rel="stylesheet">
                                        <link href="${pageContext.request.contextPath}/css/font-awesome.css" type="text/css" rel="stylesheet">
                                            <link href="${pageContext.request.contextPath}/css/selectize.css" type="text/css" rel="stylesheet">
                                                <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
                                                <script src="${pageContext.request.contextPath}/js/jquery-dataTable.min.js"></script>
                                                <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                                                <script src="${pageContext.request.contextPath}/js/gandalFunctions.js"></script>
                                                <script src="${pageContext.request.contextPath}/js/gandalAjax.js"></script>
                                                <script src="${pageContext.request.contextPath}/js/notify.min.js"></script>
                                                <script src="${pageContext.request.contextPath}/js/esecure.js"></script>
                                                <script src="${pageContext.request.contextPath}/js/gesimmo.js"></script>
                                                <script src="${pageContext.request.contextPath}/js/selectize.js"></script>
                                                <script src="${pageContext.request.contextPath}/js/dataTables.responsive.js"></script>
                  

                                                <script type='text/javascript'>

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

                                                </script>
                                                 <!-- AJAX LOADER-->

                                                <script type="text/javascript">
                                                    function ajaxindicatorstart(text)
                                                    {
                                                        if (jQuery('body').find('#resultLoading').attr('id') != 'resultLoading') {
                                                            jQuery('body').append('<div id="resultLoading" style="display:none">' +
                                                                    '<div>' +
                                                                    '<img src="img/loading.gif" style="width: 100px; height: 100px">' +
                                                                    '<div>' + text + '</div>' +
                                                                    '</div>' +
                                                                    '<div class="bg"></div>' +
                                                                    '</div>');
                                                        }

                                                        jQuery('#resultLoading').css({
                                                            'width': '100%',
                                                            'height': '100%',
                                                            'position': 'fixed',
                                                            'z-index': '10000000',
                                                            'top': '0',
                                                            'left': '0',
                                                            'right': '0',
                                                            'bottom': '0',
                                                            'margin': 'auto'
                                                        });

                                                        jQuery('#resultLoading .bg').css({
                                                            'background': '#000000',
                                                            'opacity': '0.7',
                                                            'width': '100%',
                                                            'height': '100%',
                                                            'position': 'absolute',
                                                            'top': '0'
                                                        });

                                                        jQuery('#resultLoading>div:first').css({
                                                            'width': '250px',
                                                            'height': '75px',
                                                            'text-align': 'center',
                                                            'position': 'fixed',
                                                            'top': '0',
                                                            'left': '0',
                                                            'right': '0',
                                                            'bottom': '0',
                                                            'margin': 'auto',
                                                            'font-size': '16px',
                                                            'z-index': '10',
                                                            'color': '#ffffff'

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
                                                     url: "fetch_data.jsp",
                                                     cache: false,
                                                     success: function(res){
                                                     jQuery('#ajaxcontent').html(res);
                                                     }
                                                     });
                                                     }*/

                                                    //                                                jQuery(document).ajaxStart(function() {
                                                    //                                                    //show ajax indicator
                                                    //                                                    ajaxindicatorstart('Chargement des donnees... Veuillez patienter SVP');
                                                    //                                                }).ajaxStop(function() {
                                                    //                                                    //hide ajax indicator
                                                    //                                                    ajaxindicatorstop();
                                                    //                                                });
                                                </script>


                                                </head>


                                                <body style="overflow-x: hidden;" onload="initialize()">
                                                    <%--<div id='ajax_loader' style="position: fixed; left: 50%; top: 50%; display: none;">
                                                    <img   src="img/loading.gif" id="loading-indicator" style="display:none; " />
                                                    </div>--%>
                                                    <div class="wrap">
                                                        <div class="navbar navbar-bright navbar-fixed-top" role="banner">
                                                            <!-- Begin header -->
                                                            <div class="navbar-header">

                                                                <div class="col-md-2" id="header">
                                                                    <tiles:insertAttribute name="header"/>
                                                                </div>

                                                            </div>
                                                        </div>

                                                        <!--Begin content-->

                                                        <tiles:insertAttribute name="home"/>

                                                        <!--End  content-->




                                                        <!-- Begin Login form -->
                                                        <div class="container" style="margin-bottom: 10px; padding-bottom: 10px">
                                                            <div class="row">
                                                                <div class="col-md-3" id="leftCol">
                                                                    <tiles:insertAttribute name="login"/>
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




                                                    <!-- ######################## VISUALISER UN PROFIL UTILISATEUR CONNECTE ##############################  -->
                                                    <div class="modal fade modal-admin" id="viewProfilModal" tabindex="-1" role="dialog" aria-labelledby="viewProfilModal" aria-hidden="true">

                                                        <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
                                                            <div class="panel panel-info" style="border-color: rgb(193,25,83)">
                                                                <form:form commandName="editUserForm" action="${pageContext.request.contextPath}/user/changePassword" method="post" id="editProfilFormulaire">

                                                                    <div class="panel-heading" style="background-color: rgb(193,25,83)">
                                                                        <h3 class="panel-title" style="color: white;" >Editer  Profil Utilisateur</h3>

                                                                    </div>
                                                                    <div class="panel-body" style="color: #000033">
                                                                        <div class="alert alert-success" role="alert" id="profilMessageSuccess" style="display: none">
                                                                            <div class="alert alert-danger" role="alert" style="display: none" id="profilMessageFailed">
                                                                            </div>
                                                                        </div>
                                                                        <fieldset><legend>Informations utilisateur</legend>                      <label for="editProfilNomInput">Nom: </label>
                                                                            <input class="form-control" required="required"  name="nom" placeholder="Votre Nom" id="editProfilNomInput"/>


                                                                            <label for="editProfilPrenomInput">Prenom: </label>
                                                                            <input class="form-control" name="prenom" required="required" placeholder="Votre Prï¿½nom" id="editProfilPrenomInput"/>


                                                                            <label for="editProfilEmailInput">Email: </label>
                                                                            <input class="form-control" type="email" name="email" required="required" placeholder="Votre Email" id="editProfilEmailInput"/>

                                                                            <label for="editProfilTelInput">Telephone: </label>
                                                                            <input class="form-control"  name="telephone" required="required" placeholder="Votre Numero telephone" id="editProfilTelInput"
                                                                                   type="tel" pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$"/>

                                                                            <label for="editProfilLoginInput">Login: </label>
                                                                            <input class="form-control" readonly="false" name="login" required="required" placeholder="Votre login" id="editProfilLoginInput"/>


                                                                            <div style="float:right">
                                                                                <a href="#" data-toggle="modal" class="changePassword" ><span class="glyphicon glyphicon-cog"></span> Changer votre password</a>
                                                                            </div>

                                                                            <div id="panelPassword" style="display: none">

                                                                                <label for="editProfilPasswordInput">Password: </label>
                                                                                <input class="form-control" name="password" type="password"   placeholder="Votre mot de passe" id="editProfilPasswordInput"/>

                                                                                <label for="editProfilNewPasswordInput">Nouveau password: </label>
                                                                                <input class="form-control"  type="password" name="newPassword" placeholder="Votre nouveau password" id="editProfilNewPasswordInput"/>

                                                                                <label for="editProfilConfirmNewPasswordInput">Confirmer mot de passe: </label>
                                                                                <input class="form-control" type="password" name="confirmPassword"  placeholder="Votre mot de passe" id="editProfilConfirmNewPasswordInput"/>
                                                                                <input type="hidden" value="${pageContext.request.contextPath}" id="contextPath" />
                                                                            </div>
                                                                        </fieldset>

                                                                    </div>
                                                                    <div class="modal-footer ">

                                                                        <button type="submit" id="formEditButton" onClick="" class="btn btn-info" value="submit">Enregisrer</button>
                                                                        <button type="reset"  class=" annulerModifProfil btn btn-warning">Annuler Modifications</button>
                                                                        <button type="button" id="fermerModal" data-dismiss="modal"  class="btn btn-warning">Fermer</button>

                                                                    </div>
                                                                </form:form>

                                                            </div>
                                                        </div>
                                                    </div>


                                                    <script>
                                                        var bermudaTriangle;
                                                        function initialize() {
                                                            var myLatLng = new google.maps.LatLng(13.18895,-14.34402);

                                                            var mapOptions = {
                                                                zoom: 7,
                                                                center: myLatLng,
                                                                mapTypeId: google.maps.MapTypeId.RoadMap
                                                            };

                                                            var map = new google.maps.Map(document.getElementById('map-canvas-zone'), mapOptions);
                                                            // init default polygon centered in dakar city
                                                            
                                                            
                                                            var triangleCoords = [
                                                                new google.maps.LatLng(14.61947,-18.08761),
                                                                new google.maps.LatLng(14.28501,-17.8569),
                                                                new google.maps.LatLng(14.2388,-18.12606),
                                                                new google.maps.LatLng(14.21361,-18.2991)
                                                            ];
                                                            buildAllAreaDefined(map);
                                                            // Construct the polygon
                                                            bermudaTriangle = new google.maps.Polygon({
                                                                paths: triangleCoords,
                                                                draggable: true,
                                                                editable: true,
                                                                strokeColor: '#FF0000',
                                                                strokeOpacity: 0.5,
                                                                strokeWeight: 2,
                                                                fillColor: '#FF0000',
                                                                fillOpacity: 0.35
                                                            });

                                                            bermudaTriangle.setMap(map);
                                                            google.maps.event.addListener(bermudaTriangle, "dragend", getPolygonCoords);
                                                            google.maps.event.addListener(bermudaTriangle.getPath(), "insert_at", getPolygonCoords);
                                                            google.maps.event.addListener(bermudaTriangle.getPath(), "remove_at", getPolygonCoords);
                                                            google.maps.event.addListener(bermudaTriangle.getPath(), "set_at", getPolygonCoords);

                                                            loadData();
                                                        }

                                                        function getPolygonCoords() {
                                                            var len = bermudaTriangle.getPath().getLength();
                                                            var htmlStr = "";
                                                            for (var i = 0; i < len; i++) {
                                                                console.log(bermudaTriangle.getPath().getAt(i));
                                                                htmlStr += bermudaTriangle.getPath().getAt(i).toUrlValue(5) + ";";

                                                            }
                                                            $("#polygonData").val(htmlStr);
                                                            //document.getElementById('info').innerHTML = htmlStr;
                                                        }

                                                        function buildAllAreaDefined(map) {

                                                            $.ajax({
                                                                url: '${pageContext.request.contextPath}/zone/all',
                                                                type: 'GET',
                                                                dataType: 'json',
                                                                success: function(response) {
                                                                    response.forEach(function(item) {
                                                                        var value = item.points;
                                                                        var points = [];
                                                                        value.forEach(function(latLong) {
                                                                            var lat = parseFloat(latLong.split(",")[0]);
                                                                            var lon = parseFloat(latLong.split(",")[1]);
                                                                            points.push(new google.maps.LatLng(lat, lon));
                                                                            console.log("Valeur dans le tableau : LAT =" + lat + " Long = " + lon);
                                                                        });
                                                                        addPolygonOnMap(map,points, item.couleur);
                                                                        //  coords.push(points);
                                                                        //  points = [];

                                                                    });

                                                                }
                                                                , error: function() {

                                                                    console.log("Erreur de recepetion des points de zonage");

                                                                }
                                                            });
                                                        }
                                                        function addPolygonOnMap(map,coords, couleur) {
                                                            var polygons = new google.maps.Polygon({
                                                                paths: coords,
                                                                /*draggable: true,
                                                                 editable: true,*/
                                                                strokeColor: '#FF0000',
                                                                strokeOpacity: 0.5,
                                                                strokeWeight: 1,
                                                                fillColor: couleur,
                                                                fillOpacity: 0.25
                                                            });

                                                            polygons.setMap(map);
                                                        }

                                                       /* function loadData() {
                                                            $.ajax({
                                                                url: '${pageContext.request.contextPath}/zone/all',
                                                                type: 'GET',
                                                                dataType: 'json',
                                                                success: function(response) {
                                                                    response.forEach(function(item) {
                                                                        var value = item.points;
                                                                        value.forEach(function(latLong) {
                                                                            var lat = parseFloat(latLong.split(",")[0]);
                                                                            var lon = parseFloat(latLong.split(",")[1]);

                                                                            console.log("Valeur dans le tableau : LAT =" + lat + " Long = " + lon);
                                                                        })

                                                                    });

                                                                }
                                                                , error: function() {

                                                                    console.log("Données reçues  : " + (data));

                                                                }
                                                            });

                                                        }*/
                                                    </script>

                                                </body>
                                                </html>