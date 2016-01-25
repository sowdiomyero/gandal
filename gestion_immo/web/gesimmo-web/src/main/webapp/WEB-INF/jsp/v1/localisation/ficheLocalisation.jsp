<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="row" >
    <%@include file="./localisationActionsGroup.jsp" %>
</div>
<div class="row" >
<!-- INFORMATIONS GENERALES -->
<div class="col-md-6" >
    <div class="row row-detail-container">  <!-- Début de la premiere ligne de la premiere colonne-->
        <div class="row-detail-icon">
            <span class="glyphicon glyphicon-pencil" ></span>
        </div>
        <div class="row-detail-header">
            <span>Informations</span> 
        </div>

        <div>
            <div class="row">
                <div class="col-md-3 col-sm-4 col-xs-6">
                    <label><spring:message code="label.programme.panel.nom" />:</label>
                </div>
                <div class="col-md-9  col-sm-8 col-xs-6"> ${localisationSelected.nomLocalisable}</div>
            </div>
            <div class="row">
                <div class="col-md-3  col-sm-4 col-xs-6">
                    <label><spring:message code="label.programme.description" />:</label>
                </div>
                <div class="col-md-9col-sm-8 col-xs-6"> ${localisationSelected.description}</div>
            </div>
            <div class="row">
                <div class="col-md-3  col-sm-4 col-xs-6">
                    <label><spring:message code="label.programme.etat" />:</label>
                </div>
                <div class="col-md-9  col-sm-8 col-xs-6">
                    <div class="badge badge-info" role="alert">${localisationSelected.etat}</div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3  col-sm-4 col-xs-6">
                    <label>Type :</label>
                </div>
                <div class="col-md-9  col-sm-8 col-xs-6 ">
                    <div class="badge badge-info" role="alert"> ${localisationSelected.type}</div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-3  col-sm-4 col-xs-6">
                    <label>Categorie :</label>
                </div>
                <div class="col-md-9  col-sm-8 col-xs-6 ">
                    <div class="badge badge-info" role="alert"> ${localisationSelected.typeLocalisation}</div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-3  col-sm-4 col-xs-6">
                    <label>Etiquettes :</label>
                </div>
                <div class="col-md-9  col-sm-8 col-xs-6 ">
                    <span class="label label-primary">Tags1</span>
                    <span class="label label-warning">Tags2</span>
                </div>
            </div>

        </div>

    </div>

    <div class="row row-detail-container">  <!-- Seconde ligne de la premiere colonne-->

        <div class="row-detail-icon">
            <span class="glyphicon glyphicon-pencil" ></span>
        </div>
        <div class="row-detail-header">
            Adresse
        </div>

        <div >             
            <span >14 Rue Felix Faure</span> <br>   
            <span >Dakar Plateau</span> <br> 
            <span >SENEGAL </span> <br> 
        </div>
    </div>

    <div class="row row-detail-container">  <!-- Troisieme ligne de la premiere colonne-->


        <div class="row-detail-header">
            Activites
        </div>

        <div >             
            <ul class="nav nav-pills nav-justified" id="tab">
                <li ><a href="#toutes" data-toggle="tab">Toutes</a></li>
                <li><a href="#journaux" data-toggle="tab">Journal</a></li>
                <li><a href="#demandes" data-toggle="tab">Demandes</a></li>
                <li><a href="#activites" data-toggle="tab">Activites</a></li>
            </ul>
            <div class="tab-content" style="margin-top: 20px">    
                <div class="tab-pane" id="toutes">Toutes les Activites + Journaux + Demandes en vrac ...</div>
                <div class="tab-pane" id="journaux">Les Journaux uniquement en vrac ...</div>
                <div class="tab-pane" id="demandes">Toutes les Demandes en vrac ...</div>
                <div class="tab-pane" id="activites">Toutes les Activites  en vrac ...</div>
            </div>
        </div>
    </div>
</div>
<!-- DEBUT COLONNE DE DROITE -->
<div class="col-md-5">
    <div class="row row-detail-container">  <!-- Début de la premiere ligne de la premiere colonne-->
        <div class="row-detail-icon">
            <span class="glyphicon glyphicon-pencil" ></span>
        </div>
        <div class="row-detail-header">
            Personnes
        </div>

        <div>
            <div class="row">
                <div class="col-md-6  col-sm-4 col-xs-6">
                    <label>Résponsable:</label>
                </div>
                <div class="col-md-6  col-sm-8 col-xs-6"> 
                   ${localisationSelected.attribution}
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-sm-4 col-xs-6">
                    <label>Créateur: </label>
                </div>
                <div class="col-md-6 col-sm-8 col-xs-6">
                    ${localisationSelected.createur}
                </div>
            </div>
        </div>   
    </div>

    <div class="row row-detail-container">  <!-- Début de la premiere ligne de la premiere colonne-->
        <div class="row-detail-icon">
            <span class="glyphicon glyphicon-pencil" ></span>
        </div>
        <div class="row-detail-header">
            Dates
        </div>

        <div>
            <div class="row">
                <div class="col-md-6  col-sm-4 col-xs-6">
                    <label>Création:</label>
                </div>
                <div class="col-md-6  col-sm-8 col-xs-6"> 
                    <fmt:formatDate type="date" value="${localisationSelected.dateCreation}" /> 
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-sm-4 col-xs-6">
                    <label>Mise à jour:</label>
                </div>
                <div class="col-md-6 col-sm-8 col-xs-6">
                    <fmt:formatDate type="date" value="${localisationSelected.dateCreation}" />
                </div>
            </div>
        </div>   
    </div>

    <div class="row row-detail-container">  <!-- Début de la premiere ligne de la premiere colonne-->
        <div class="row-detail-icon">
            <span class="glyphicon glyphicon-pencil" ></span>
        </div>
        <div class="row-detail-header">
            Statistiques
        </div>

        <div>
            <div class="row">
                <div class="col-md-6  col-sm-4 col-xs-6">
                    <label>Demandes En Attente:</label>
                </div>
                <div class="col-md-6  col-sm-8 col-xs-6"> 
                    <span class="badge">12</span> 
                </div>
            </div>
            <div class="row">
                <div class="col-md-6  col-sm-4 col-xs-6">
                    <label>Demandes Résolues:</label>
                </div>
                <div class="col-md-6  col-sm-8 col-xs-6"> 
                    <span class="badge">12</span> 
                </div>
            </div>
            
            <div class="row">
                
                <c:if test="${localisationSelected.typeLocalisation.trim().equalsIgnoreCase('SITE')}">
                    <div class="col-md-6 col-sm-4 col-xs-6">
                        <label>Nombre Batiments</label>
                    </div>
                    <div class="col-md-6 col-sm-8 col-xs-6">
                        <span class="label label-primary">${localisationSelected.nbObjets} Batiments</span> 
                    </div>
                </c:if>
                <c:if test="${localisationSelected.typeLocalisation.trim().equalsIgnoreCase('BATIMENT')}">
                    <div class="col-md-6 col-sm-4 col-xs-6">
                        <label>Nombre Niveaux</label>
                    </div>
                    <div class="col-md-6 col-sm-8 col-xs-6">
                        <span class="label label-primary">${localisationSelected.nbNiveaux} Etages</span> 
                    </div>
                </c:if>            
            </div>

            <div class="row">
                <div class="col-md-6  col-sm-4 col-xs-6">
                    <label>Nombres Places:</label>
                </div>
                <div class="col-md-6  col-sm-8 col-xs-6"> 
                    <span class="badge">120</span> 
                </div>
            </div>
            <div class="row">
                <div class="col-md-6  col-sm-4 col-xs-6">
                    <label>Places Disponibles:</label>
                </div>
                <div class="col-md-6  col-sm-8 col-xs-6"> 
                    <span class="badge">120</span> 
                </div>
            </div>
        </div>   
    </div>
</div>
</div>
                <script>
    function activeTab(tab) {
        $('.nav-pills a[href="#' + tab + '"]').tab('show');
    }
    ;
    activeTab('toutes');
</script>


