<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>





<sec:authorize access="isAuthenticated()">

    <script>
        $(document).ready(function() {
            //Initialize tooltips
            $('.nav-tabs > li a[title]').tooltip();
        });
        $(document).on('click', '.panel-heading span.clickable', function(e) {
            var $this = $(this);
            if (!$this.hasClass('panel-collapsed')) {
                $this.parents('.panel').find('.panel-body').slideUp();
                $this.addClass('panel-collapsed');
                $this.find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
            } else {
                $this.parents('.panel').find('.panel-body').slideDown();
                $this.removeClass('panel-collapsed');
                $this.find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
            }
        })
    </script>
    <div class="container-fluid" style="margin-left: 25px; margin-right: 25px;">
        <div class="row">
            <div class="panel panel-default" style=" margin-bottom: 10px; ">
                <div class="panel-heading text-center">
                    <h2><spring:message code="label.localisation.header.titre" /></h2>
                </div>  
            </div>
        </div>

        <div class="row">
            <div class="col-md-2" >
                <%@include file="./menus/menu_fiche_localisation.jsp" %>
            </div>
           
            <section>
                <div class="wizard col-md-10">
                    
                    <div class="row">
                        <%@include file="./meteo/meteo_localisation.jsp" %>
                    </div> 
                    <div class="wizard-inner">
                        <div class="connecting-line"></div>
                        <ul class="nav nav-tabs" role="tablist">

                            <li role="presentation" class="active">
                                <a href="#detail" data-toggle="tab" aria-controls="step1" role="tab" title="Detail">
                                    <span class="round-tab">
                                        <i class="glyphicon glyphicon-home"></i>
                                    </span>
                                </a>
                            </li>

                            <li role="presentation" >
                                <a href="#projets" data-toggle="tab" aria-controls="step2" role="tab" title="Projets">
                                    <span class="round-tab">
                                        <i class="glyphicon glyphicon-th-large"></i>
                                    </span>
                                </a>
                            </li>
                            <li role="presentation" >
                                <a href="#programmes" data-toggle="tab" aria-controls="step3" role="tab" title="Programmes">
                                    <span class="round-tab">
                                        <i class="glyphicon glyphicon glyphicon-th"></i>
                                    </span>
                                </a>
                            </li>

                            <li role="presentation" >
                                <a href="#ressources" data-toggle="tab" aria-controls="complete" role="tab" title="Ressources">
                                    <span class="round-tab">
                                        <i class="glyphicon glyphicon-road"></i>
                                    </span>
                                </a>
                            </li>

                        </ul>
                       
                    </div>


                    <form role="form">
                        <div class="tab-content">
                            <div class="tab-pane active" role="tabpanel" id="detail">
                                <!--                    Informations sur la localisation-->
                                <div class="col-lg-6">
                                    <div class="panel panel-primary" >
                                        <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                            <div>
                                                <a href="#" class="showLocalisation pull-right  " data-toggle="modal" style="color: black"
                                                   data-target="#UpdateDetailsLocalisation"  id="btnModifierDetails"><span
                                                        class="glyphicon glyphicon-pencil"></span></a>
                                            </div>
                                            <div>
                                                <h3 class="panel-title "><spring:message code="label.localisation.pannel.titre" /></h3>
                                                <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                            </div>

                                        </div>

                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <label><spring:message code="label.localisation.nom" /></label>
                                                </div>
                                                <div class="col-sm-5">${editLocalisationForm.nom}</div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <label><spring:message code="label.localisation.description" /></label>
                                                </div>
                                                <div class="col-sm-5">${editLocalisationForm.description}</div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <label><spring:message code="label.localisation.longitude" /></label>
                                                </div>
                                                <div class="col-sm-5"><div class="badge badge-info" role="alert">${editLocalisationForm.longitude}</div></div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <label><spring:message code="label.localisation.latitude" /></label>
                                                </div>
                                                <div class="col-sm-5"><div class="badge badge-info" role="alert">${editLocalisationForm.latitude}</div></div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <label><spring:message code="label.localisation.type" /></label>
                                                </div>
                                                <div class="col-sm-5">${editLocalisationForm.type}</div>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                                <!--                     Information sur le responsable            -->
                                <div class="row col-lg-6">
                                    <div class="panel panel-primary" >
                                        <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                            <div>
                                                <a href="#" class="showLocalisation pull-right  " data-toggle="modal" style="color: black"
                                                   data-target="#AffecterResponsable"  id="btnModifierDetails"><span
                                                        class="glyphicon glyphicon-hand-right"></span></a>
                                            </div>

                                            <div>
                                                <h3 class="panel-title "><spring:message code="label.localisation.responsable.panel.titre" /></h3>
                                                <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                            </div>

                                        </div>

                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <label><spring:message code="label.utilisateur.nom" /></label>
                                                </div>
                                                <div class="col-sm-5">${editUserForm.nom}</div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <label><spring:message code="label.utilisateur.prenom" /></label>
                                                </div>
                                                <div class="col-sm-5">${editUserForm.prenom}</div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <label><spring:message code="label.utilisateur.email" /></label>
                                                </div>
                                                <div class="col-sm-5"><div class="badge badge-info" role="alert">${editUserForm.email}</div></div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <label><spring:message code="label.utilisateur.login" /></label>
                                                </div>
                                                <div class="col-sm-5"><div class="badge badge-info" role="alert">${editUserForm.login}</div></div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <label><spring:message code="label.utilisateur.telephone" /></label>
                                                </div>
                                                <div class="col-sm-5"><div class="badge badge-info" role="alert">${editUserForm.telephone}</div></div>
                                                <br><br>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <!--                    Informations sur les indicateur-->
                                <div class="col-lg-6">
                                    <div class="panel panel-primary" >
                                        <div class="panel-heading" style="text-align: center;background-color: white;color: black;font: bold">
                                            <div>
                                                <a href="#" class="pull-right  " data-toggle="modal" style="color: black"
                                                   data-target="#"  id="btnModifierDetails"><span
                                                        class="glyphicon glyphicon-pencil"></span></a>
                                            </div>
                                            <div>
                                                <h3 class="panel-title "><spring:message code="label.indicateur.titre" /></h3>
                                                <span class="pull-left clickable" style="margin-top: -20px;"><i class="glyphicon glyphicon-chevron-up"></i></span>
                                            </div>

                                        </div>

                                        <div class="panel-body">

                                            <div class="row">        
                                                <%@include file="./tableaux/tableau_indicateur_localisation.jsp" %>
                                            </div>


                                        </div>
                                    </div>
                                </div>
                                <div class="row col-lg-6">

                                </div>


                            </div>


                            <div class="tab-pane" role="tabpanel" id="projets">

                                <div class="col-lg-12" >
                                    <%@include file="./tableaux/tableau_projet.jsp" %>
                                </div>

                            </div>
                            <div class="tab-pane" role="tabpanel" id="programmes">
                                <h3>En cour ...</h3>

                            </div>
                            <div class="tab-pane" role="tabpanel" id="ressources">
                                <h3>En cour ...</h3>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </form>
                </div>
            </section>
        </div>

    </div>

    <!--                                    Modification des détails de la localisation-->
    <div class="modal fade modal-admin" id="UpdateDetailsLocalisation" tabindex="-1" role="dialog" aria-labelledby="viewLocalisationModal" aria-hidden="true">
        <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
            <div class="panel panel-info" style="border-color: rgb(193,25,83)">
                <form commandName="editLocalisationForm" action="${pageContext.request.contextPath}/editLocalisation" method="post" id="editLocalisationFormulaire">
                    <div class="panel-heading" style="background-color: rgb(193,25,83)">
                        <h3 class="panel-title " style="color: white">Editer objet localisable</h3>
                    </div>
                    <div class="panel-body" style="color: #000033">
                        <fieldset><legend><spring:message code="label.localisation.header.titre"/></legend> 
                            <label for="editLongLocalisationInput">Longitude: </label>
                            <input class="form-control" value="${editLocalisationForm.longitude}" name="longitude"  required="required" placeholder="longitude" id="editLongLocalisationInput" disabled/>

                            <label for="editLatLocalisationInput">Latitude: </label>
                            <input class="form-control" value="${editLocalisationForm.latitude}" type="text" name="latitude" required="required" placeholder="latitude" id="editLatLocalisationInput" disabled/>

                            <label for="editNomLocalisationInput">Nom: </label>
                            <input class="form-control" value="${editLocalisationForm.nom}"  readonly="false" name="nom"  placeholder="nom" id="editNomLocalisationInput"/>

                            <label for="editDescLocalisationInput">Description: </label>
                            <input class="form-control" value="${editLocalisationForm.description}" required="required"  name="description"  placeholder="description" id="editDescLocalisationInput"/>

                            <label for="editTypeLocalisationInput">Type: </label>
                            <input class="form-control" value="${editLocalisationForm.type}" type="text" name="type" required="required" placeholder="type" id="editTypeLocalisationInput"/>

                        </fieldset>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" id="formEditLocalisationButton" onClick="" class="btn btn-info" value="submit">Enregistrer</button>
                        <button type="button" id ="closeLocalisationForm" onClick=""  data-dismiss="modal" class="btn btn-warning">Fermer</button>
                    </div>
                </form>

            </div>
        </div>
    </div>





    <!-- ########################Affecter responsable##############################  -->
    <div class="modal fade modal-admin container-fluid" id="AffecterResponsable" tabindex="-1" role="dialog" aria-labelledby="AffecterIndicateur" aria-hidden="true">

        <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">

            <div class="panel panel-info  " style="border-color: rgb(193,25,83)">

                <form:form commandName="editUserForm" action="${pageContext.request.contextPath}/affecterResponsable" method="post" id="affecterResponsableLocalite">
                    <div class="panel-heading" style="background-color: rgb(193,25,83)">
                        <h3 class="panel-title " style="color: white">Affecter responsable a un objet localisable</h3>
                    </div>
                    <div class="panel-body" style="color: #000033">
                        <fieldset><legend><spring:message code="label.localisation.header.titre"/></legend> 
                            <div class="table-responsive" >
                                <table id="table_utilisateurs" class="table table-bordred table-striped  "   border="0">
                                    <thead>
                                        <tr  style="font-weight: bold;  background-color: #CCD4D9">
                                            <td  >#</td>
                                            <td  >Prénom </td>
                                            <td>Nom </td>
                                            <td>Téléphone </td>
                                            <td>Login  </td>  
                                            <td>Id  </td>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${users}" var="user" >
                                            <tr>
                                                <td><form:radiobutton    path="idUser" value="${user.idUser}"  /></td>
                                                <td >${user.userPrenom}</td>
                                                <td>${user.userName}</td>
                                                <td> ${user.userPhone}</td>
                                                <td> ${user.compte.login}</td>
                                                <td> ${user.idUser}</td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </fieldset>
                    </div>

                    <div class="modal-footer ">
                        <button type="submit" id="formEditLocalisationButton" onClick="" class="btn btn-info" value="submit">Enregistrer</button>
                        <button type="button" id ="closeLocalisationForm" onClick=""  data-dismiss="modal" class="btn btn-warning">Fermer</button>
                    </div>
                </form:form>

            </div>

        </div>

    </div>


    <!-- ########################Affecter indicateur##############################  -->
    <div class="modal fade modal-admin" id="AffecterIndicateur" tabindex="-1" role="dialog" aria-labelledby="AffecterIndicateur" aria-hidden="true">

        <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">

            <div class="panel panel-info  " style="border-color: rgb(193,25,83)">
                <form commandName="affecterIndicateur" action="${pageContext.request.contextPath}/addIndicateurLocalisation" method="post" id="addIndicateurLocalisationForm">

                    <div class="panel-heading" style="background-color: rgb(193,25,83)">
                        <h3 class="panel-title " style="color: white"><spring:message code="label.indicateur.modal.affecter.heading" /></h3>
                    </div>
                    <div class="panel-body" style="color: #000033">

                        <fieldset><legend><spring:message code="label.indicateur.modal.editer.panel.body.legend" /></legend>
                            <label for="addIndicateurLocalisationInput"><spring:message code="label.indicateur.modal.affecter.body.choose" />: </label>
                            <select id="addIndicateurLocalisationInput"  name="nomIndicateur" class="form-control editSelectIndicateur">
                                <option value=""></option>
                                <c:forEach items="${indicateurs}" var="indicateur" >
                                    <option value="${indicateur.nomIndicateur}">${indicateur.nomIndicateur}</option>
                                </c:forEach>
                            </select>
                            <div id="chargerParametres">
                            </div>
                            <input type="hidden" name="idLocalisation"  value="${editLocalisationForm.idLocalisation}"/>
                        </fieldset>
                    </div>
                    <div class="modal-footer "> <button type="submit" onClick="" class="btn btn-info" value="submit"><spring:message code="label.bouton.valider" /></button>
                        <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.annuler" /></button>
                    </div>
                </form>

            </div>
        </div>
    </div>

    <!-- Editer indicateur-->
    <div class="modal fade modal-admin" id="viewValueIndicateurModal" tabindex="-1" role="dialog" aria-labelledby="viewValueIndicateurModal" aria-hidden="true">

        <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
            <div class="panel panel-info" style="border-color: rgb(193,25,83)">
                <form commandName="indicateurLocalisationForm" action="${pageContext.request.contextPath}/updateIndicateurLocalisation" method="post" id="editValueIndicateurFormulaire">
                    <div class="panel-heading" style="background-color: rgb(193,25,83)">
                        <h3 class="panel-title " style="color: white">Editer un projet</h3>
                    </div>
                    <div class="panel-body" style="color: #000033">

                        <fieldset><legend>Informations indicateur</legend>
                            <label for="editValeurIndicateur">Valeur</label>
                            <input type="text" class="form-control" required="required"  name="valeur" placeholder="Nom" id="editValeurIndicateur"/>
                            <input  type="hidden"  name="idIndicateurLocalisation"     id="editId"/>


                        </fieldset>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" id="formEditProjetButton" onClick="" class="btn btn-info" value="submit"><label><spring:message code="label.bouton.valider" /></label></button>
                        <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><label><spring:message code="label.bouton.annuler" /></label></button>
                    </div>
                </form>

            </div>
        </div>
    </div>

                    
    <!-- Modal suprimer un journal-->
    <div class="modal fade" id="deleteIndicateurLocalisationConfirmation" tabindex="-1" role="dialog"
         aria-labelledby="edit" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </button>
                    <h4 class="modal-title custom_align" id="Heading"><spring:message code="label.indicateur.modal.supprimer.panel.heading"/></h4>
                </div>
                <div class="modal-body">

                    <div class="alert alert-danger">
                        <span class="glyphicon glyphicon-warning-sign"></span> 
                        <p><spring:message code="label.indicateur.modal.supprimer.panel.body.question"/></p>
                    </div>

                </div>
                <div class="modal-footer ">
                    <button value="oui" type="submit" class="btn btn-success" id="okDeleteIndicateurLocalisationConfirmation">
                        <span class="glyphicon glyphicon-ok-sign"></span>  <spring:message code="label.bouton.oui"/>
                    </button>
                    <button value="non" type="button" class="btn btn-default"
                            data-dismiss="modal" id="non">
                        <span class="glyphicon glyphicon-remove"></span> <spring:message code="label.bouton.non"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>