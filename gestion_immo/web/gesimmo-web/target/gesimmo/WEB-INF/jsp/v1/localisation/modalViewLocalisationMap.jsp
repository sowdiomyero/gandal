<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<sec:authorize access="isAuthenticated()">
    
    <div style="margin: 25px;">
        <div class="modal fade " id="localisationMapModalForm" tabindex="-1" role="dialog"
             aria-labelledby="localisationMapModalForm" aria-hidden="true">
            <div class="modal-dialog modal-large2">
                <div class="modal-content" >
           
            <div class="modal-header" style="background-color: rgb(255,155,55)">
                <h3 class="panel-title " style="color: white">Situation Géographique du Site ${localisationForm.nom}</h3>
            </div>
            <div class="modal-body" style="color: #000033">
               
                <div id="show-map" style="height: 600px;">
                    
                    <iframe width="100%" height="100%" frameborder="0" scrolling="no" 
                            marginheight="0" marginwidth="0" 
                            src="https://maps.google.com/maps?q=${localisationForm.latitude},${localisationForm.longitude}&hl=es;z=4&amp;output=embed"></iframe>
                    
                    <br /><small><a href="https://maps.google.com/maps?q='+data.lat+','+data.lon+'&hl=es;z=14&amp;output=embed" style="color:#0000FF;text-align:left" target="_blank">Voir en Gros Plan</a></small>


<!--                    https://maps.googleapis.com/maps/api/staticmap?center=Berkeley,CA&zoom=14&size=400x400&key=AIzaSyDqiR7IeTzBvY1XjOGDZ6wWerWLWwfHn_s-->
                </div>
            </div>
            <div class="modal-footer ">
                <button type="submit"  class="btn btn-info" value="submit"><spring:message code="label.bouton.valider"/></button>
                <button type="button" id ="closeLocalisationForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.fermer"/></button>
            </div>

                              
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div> <!-- Fin Modal Modification Fiche -->
    </div>
</sec:authorize>