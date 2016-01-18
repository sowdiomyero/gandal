<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<br>

<div class="table-responsive" >
    <table id="table_localisations" class="table table-bordred table-striped  "   border="1" style="border-color: white;">
        <caption><h4 style="font-weight: bold;"><spring:message code="label.localisation.tableau.titre" /></h4></caption>
        <thead>
            <tr  style="font-weight: bold;  background-color: #CCD4D9; text-align: center;">
                <td>DTYPE</td>
                <td><spring:message code="label.localisation.tableau.header.nom"/></td>
                <td><spring:message code="label.localisation.tableau.header.description"/> </td>
                <td><spring:message code="label.localisation.tableau.header.longitude"/></td>
                <td><spring:message code="label.localisation.tableau.header.latitude"/></td>
                <td><spring:message code="label.localisation.tableau.header.type"/></td>
                <td><spring:message code="label.localisation.tableau.header.gravite"/></td>
                <td><spring:message code="label.localisation.tableau.header.actions"/></td>
            </tr>
        </thead>
        <tbody>

            <c:forEach items="${localisations}" var="localisation" >
                <tr style="text-align: center;">
                    <td >${localisation.getDType()}</td>
                    <td >${localisation.nomLocalisable}</td>
                    <td>${localisation.description}</td>
                    <td>${localisation.longitude}</td>
                    <td>${localisation.latitude}</td>
                    <td>${localisation.type}</td>
                    <td> <c:if test="${localisation.getDType() == 'INCIDENT'}">${localisation.gravite}</c:if></td>
                        
                        <td>
                            <button title="<spring:message code="label.localisation.tableau.header.actions.title.supprimer"/>" class="deleteLocalisation btn btn-danger btn-xs"
                                data-id="${localisation.idLocalisation}" type="button" >
                            <span class="glyphicon glyphicon-trash" ></span>
                        </button>

                        <button title="<spring:message code="label.localisation.tableau.header.actions.title.visualiser"/>" class="showLocalisation btn btn-info btn-xs"
                                data-id="${localisation.idLocalisation}" data-dtype="${localisation.getDType()}" data-toggle="modal" type="button">
                            <span class="glyphicon glyphicon-eye-open" ></span>
                        </button>
                        <a href="ficheLocalisation?idLocalisation=${localisation.idLocalisation}&dtype=${localisation.getDType()}"> <button title="<spring:message code="label.localisation.tableau.header.actions.title.fiche"/>" class=" btn btn-info btn-xs"
                                                                                                                                            type="button">
                                <span class="glyphicon glyphicon-folder-open" ></span>
                            </button></a>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</div>

<!-- Editer un objet localisable-->

<div class="modal fade modal-admin" id="viewLocalisationModal" tabindex="-1" role="dialog" aria-labelledby="viewLocalisationModal" aria-hidden="true">


    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" >
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form  action="${pageContext.request.contextPath}/editLocalisation" method="post" id="editLocalisationFormulaire">
                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white"><spring:message code="label.localisation.modal.editer.panel.heading"/></h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <fieldset><legend><spring:message code="label.localisation.modal.editer.panel.body.legend"/></legend>  

                        <label for="editNomLocalisationInput"><spring:message code="label.localisation.tableau.header.nom"/>: </label>
                        <input class="form-control"  name="nom" placeholder="nom" id="editNomLocalisationInput"/>

                        <label for="editDescLocalisationInput"><spring:message code="label.localisation.tableau.header.description"/>: </label>
                        <input class="form-control" required="required"  name="description" placeholder="description" id="editDescLocalisationInput"/>


                        <label for="editLongLocalisationInput"><spring:message code="label.localisation.tableau.header.longitude"/>: </label>
                        <input class="form-control" name="longitude" required="required" placeholder="longitude" id="editLongLocalisationInput" disabled/>


                        <label for="editLatLocalisationInput"><spring:message code="label.localisation.tableau.header.latitude"/>: </label>
                        <input class="form-control" type="text" name="latitude" required="required" placeholder="latitude" id="editLatLocalisationInput" disabled/>

                        <label class="pull-left" for="editTypeLocalisationInput" ><spring:message code="label.localisation.tableau.header.type"/>: </label>
                        <select id="editTypeLocalisationInput" name="type" class="form-control">

                        </select>

                        <label class="pull-left" for="editSelectRattachement"><spring:message code="label.localisation.tableau.header.rattachement"/>: </label>
                        <select id="editSelectRattachement" name="rattachement" class="form-control">
                            <option value=""></option>
                            <c:forEach items="${localisations}" var="localisation" >
                                <option value="${localisation.idLocalisation}">${localisation}</option>
                            </c:forEach>
                        </select>

                        <label class="pull-left editGraviteLocalisation" for="graviteLocalisation"><spring:message code="label.localisation.tableau.header.gravite"/>: </label>
                        <input class="form-control editGraviteLocalisation" id="editGraviteLocalisation"  name="gravite" placeholder="" />
                    </fieldset>
                </div>
                <div class="modal-footer ">
                    <button type="submit" id="formEditLocalisationButton" onClick="" class="btn btn-info" value="submit"><spring:message code="label.bouton.editer"/></button>
                    <button type="button" id ="closeLocalisationForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.fermer"/></button>
                </div>
            </form>

        </div>
    </div>


</div>  


<!-- Confirmation suprimer un utilisateur-->
<div class="modal fade" id="deleteLocConfirmationModal" tabindex="-1" role="dialog"
     aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </button>
                <h4 class="modal-title custom_align" id="Heading"> <spring:message code="label.localisation.modal.supprimer.panel.heading"/></h4>
            </div>
            <div class="modal-body">

                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-warning-sign"></span> 
                    <p><spring:message code="label.localisation.modal.supprimer.panel.body.question"/></p>
                </div>

            </div>
            <div class="modal-footer ">
                <button value="oui" type="submit" class="btn btn-success" id="okDeleteLocModalButton">
                    <span class="glyphicon glyphicon-ok-sign"></span> <spring:message code="label.bouton.oui"/>
                </button>
                <button value="non" type="button" class="btn btn-default"
                        data-dismiss="modal" id="non">
                    <span class="glyphicon glyphicon-remove"></span> <spring:message code="label.bouton.non"/>
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


