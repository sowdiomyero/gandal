<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<sec:authorize access="isAuthenticated()">
<div style="margin-left: 8px">
    <div  style="padding: 4px; margin: 4px;display: inline-table " id="btnModifierObjetLocalisable">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="#localisationModalForm"   data-id="${localisationForm.idLocalisation}">
            <span class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#localisationModalForm"></span> Modifier 
        </button>

    </div>
    <div style="padding: 4px; margin: 4px; display: inline-table">
        <button class="btn btn-default btn-xs" data-title="localisationMapModalForm" data-target="#localisationMapModalForm"
                data-id="${localisationForm.idLocalisation}"
                data-nom="${localisationForm.nom}"
                data-lat="${localisationForm.latitude}"
                data-lon="${localisationForm.longitude}"             
                
                data-toggle="modal"  id="btnLocalisationMapModalForm" >
            <span class="glyphicon glyphicon-map-marker"></span> Géographie
        </button>

    </div>
    <c:if test="${localisationForm.dT == 'BATIMENT'}">
     <div  style="padding: 4px; margin: 4px; display: inline-table">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="# " id="btnAjouterNiveau" >
            <span class="glyphicon glyphicon-plus-sign"></span> Niveau
        </button>
    </div>
     </c:if>
            
            <c:if test="${localisationForm.dT == 'SITE'}">
     <div  style="padding: 4px; margin: 4px; display: inline-table">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="# " id="btnAjouterBayiment" >
            <span class="glyphicon glyphicon-plus-sign"></span> Batiment
        </button>
    </div>
     </c:if>
    
    <div  style="padding: 4px; margin: 4px;display: inline-table">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="#AffectProjectToProjet " id="btnAffecterProjet" >
            <span class="glyphicon glyphicon-floppy-save"></span> Dupliquer
        </button>
    </div>
    

    <div  style="padding: 4px; margin: 4px; display: inline-table">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="#AffectProjectToProgramme " id="btnAffectProjectToProgramme" >
            <span class="glyphicon glyphicon-download-alt"></span> Exporter
        </button>
    </div>

</div>

           
            
            
<!-- MODAL POUR MODIFICATION DE LA FICHE -->

<%@include file="./modalEditLocalisation.jsp" %>
<%@include file="./modalViewLocalisationMap.jsp" %>

    </sec:authorize>
               
