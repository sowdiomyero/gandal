<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<sec:authorize access="isAuthenticated()">
<div style="margin-left: 8px">
    <div  style="padding: 4px; margin: 4px;display: inline-table " id="btnModifierObjetLocalisable">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="#localisationModalForm"   data-id="${localisation.idLocalisation}">
            <span class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#localisationModalForm"></span> Modifier 
        </button>

    </div>
    <div style="padding: 4px; margin: 4px; display: inline-table">
        <button class="btn btn-default btn-xs" data-title="CreateEtape"
                data-toggle="modal" data-target="#CreateEtape" id="btnEtape" >
            <span class="glyphicon glyphicon-map-marker"></span> Géographie
        </button>

    </div>
    <div  style="padding: 4px; margin: 4px; display: inline-table">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="#AffectProjectToProjet " id="btnAffecterProjet" >
            <span class="glyphicon glyphicon-comment"></span> Commenter
        </button>
    </div>
    <div  style="padding: 4px; margin: 4px;display: inline-table">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="#AffectProjectToProjet " id="btnAffecterProjet" >
            <span class="glyphicon glyphicon-floppy-save"></span> Dupliquer
        </button>
    </div>
    <div  style="padding: 4px; margin: 4px; display: inline-table">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="#AffectProjectToProgramme " id="btnAffectProjectToProgramme" >
            <span class="glyphicon glyphicon-user"></span> Affecter Responsable
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

<%@include file="../modals/editLocalisation.jsp" %>

    </sec:authorize>
               
