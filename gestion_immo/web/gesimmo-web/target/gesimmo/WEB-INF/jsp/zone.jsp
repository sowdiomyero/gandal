<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--Google map begin-->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<!--Google map begin End-->
<sec:authorize access="isAuthenticated()">

    <!-- Begin Body -->
    
    <div id="menu_actif_location" class="container" style="margin-top: 1px; margin-bottom: 5px;">        
        <div class="center-block" >
              <center><h4><spring:message code="label.localisation.header.zonage.titre" /></h4></center>
         </div>           
    </div>

    <div class="container-fluid" >
        
        <div class="row" style="margin-left: 2px;" >

            <jsp:useBean id="route" scope="application" class="sn.gandal.gesimmo.utils.RequestMappingUtils" />
            <!--            Details coordonnes collectees-->
            

            <!--         Carte -->
            <div class="col-md-10">
                <div id="map-canvas-zone" style="width: 100%; height: 800px">
                </div>
            </div>
            <!--         Carte -->
            
            <div class="col-md-2" >
                <div id="info" >

                    <form:form commandName="zoneFormCollector" action="${pageContext.request.contextPath}/${zoneRoute}" method="post" id="addZoneForm">
                        <div class="panel-heading bgcode">

                            <div>
                                <h3 class="panel-title ">Ajouter Une Zone</h3>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <fieldset>
                                    <!-- Form Name -->
                                    <legend>Formulaire d'Ajout</legend>
                                    <!-- Select Basic -->
                                    <label class="label label-success" >  Nom :</label>
                                    <form:input class="form-control"   path="nomZone" placeholder="Nom De La Zone" />
                                    <label class="label label-success" >  Code :</label>
                                    <form:input class="form-control"   path="codeZone" placeholder="Code De La Zone" />
                                    <label class="label label-success" >  Description:</label>
                                    <form:input class="form-control"   path="description" placeholder="description" />

                                    <label class="label label-success" >  Couleur de la zone:</label>
                                    <form:input class="form-control" type="color"   path="couleur" placeholder="Couleur" />



                                    <!-- Textarea -->

                                    <textarea style="height: 120px; display: none " class="form-control" id="polygonData" name="latLongs" placeholder="Coordonnées"></textarea>
                                    <!-- Button (Double) -->
                                    <div class="row">
                                        <div class="col-md-10">
                                        </div>
                                        <div class="col-md-10">
                                            <button id="btnAddZone" type="submit"  class="btn btn-success">Envoyer</button>
                                            <button id="btnReset" type="button"  class="btn btn-default">Reset</button>

                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

    </div>


    <%--<div class="modal fade modal-admin" id="addLocalization" tabindex="-1" role="dialog" aria-labelledby="addLocalization" aria-hidden="true">


        <div class="col-md-4  col-lg-offset-4"  style="margin-top:  25px" >
            <div class="panel panel-info" id="ficheLocalite"  style="border-color: rgb(193,25,83)">


            </div>

        </div>


    </div>--%>

  <script>

      $('#addZoneForm').submit(function(e) {
          var frm = $('#addZoneForm');
          e.preventDefault();
          var data = {};
          //Gather Data also remove undefined keys(buttons)
          $.each(this, function(i, v) {
              var input = $(v);
              data[input.attr("name")] = input.val();
              delete data["undefined"];
          });
          $.ajax({
              contentType: 'application/json; charset=utf-8',
              type: frm.attr('method'),
              url: frm.attr('action'),
              dataType: 'json',
              data: JSON.stringify(data),
              success: function(callback) {


                  if (callback.resultat === 200) {
                      $.notify(callback.msg, "success");
                      setTimeout(function() {
                                  window.close();
                              },
                              6000
                      );

                  } else {
                          $.notify(callback.msg, "error");
                          setTimeout(function() {
                                      window.close();
                                  },
                                  6000
                          );
                      }

              },
              error: function() {
                  //$('#confirmationContent').html("Une erreur est survenue pendant l'ajout de l'utilisateur");
                  //alert("Une erreur est survenue pendant la vérification");
                  $.notify("Une erreur est survenue pendant la vérification", "error");
              }
          });

      });
  </script>

</sec:authorize>

