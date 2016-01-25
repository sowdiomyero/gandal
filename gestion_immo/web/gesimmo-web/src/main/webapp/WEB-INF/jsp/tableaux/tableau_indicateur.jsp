<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<sec:authorize  ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN ">
    <div class="btn-group">
        <button type="button"
                style="  margin-top:7px;  margin-bottom:7px; background:rgb(193,25,83); color: #ffffff;border-radius: 6px;font-size: 13px"
                class="btn btn-default" data-title="CreateIndicateur"
                data-toggle="modal" data-target="#CreateIndicateur"> <span class="glyphicon glyphicon-plus-sign"> <label><spring:message code="label.bouton.nouveau.indicateur" /></label></span>
        </button>

    </div>
</sec:authorize>
<br>

<div class="table-responsive" >
    <table id="table_indicateurs" class="table table-bordred table-striped"   border="1" style="border-color: white;">
        <caption><h4 style="font-weight: bold;"><spring:message code="label.indicateur.tableau.titre" /></h4></caption>
        <thead>
            <tr  style="font-weight: bold;  background-color: #CCD4D9; text-align: center;">
                <td><spring:message code="label.indicateur.tableau.header.nom"/></td>
                <td><spring:message code="label.indicateur.tableau.header.description"/> </td>
                <td><spring:message code="label.indicateur.tableau.header.unite"/></td>
                <td><spring:message code="label.indicateur.tableau.header.actions"/></td>
            </tr>
        </thead>
        <tbody>

            <c:forEach items="${indicateurs}" var="indicateur" >
                <tr style="text-align: center;">
                    <td >${indicateur.nomIndicateur}</td>
                    <td>${indicateur.libelle}</td>
                    <td>${indicateur.uniteIndicateur}</td>
                    <td>
                        <button title="<spring:message code="label.indicateur.tableau.header.actions.title.supprimer"/>" class="deleteIndicateur btn btn-danger btn-xs"
                                data-id="${indicateur.idIndicateur}" type="button" >
                            <span class="glyphicon glyphicon-trash" ></span>
                        </button>

                        <button title="<spring:message code="label.indicateur.tableau.header.actions.title.visualiser"/>" class="showIndicateur btn btn-info btn-xs"
                                data-id="${indicateur.idIndicateur}" data-toggle="modal" type="button">
                            <span class="glyphicon glyphicon-eye-open" ></span>
                        </button>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</div>

<!-- Confirmation suprimer un indicateur-->
<div class="modal fade" id="deleteIndConfirmationModal" tabindex="-1" role="dialog"
     aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </button>
                <h4 class="modal-title custom_align" id="Heading"> <spring:message code="label.indicateur.modal.supprimer.panel.heading"/></h4>
            </div>
            <div class="modal-body">

                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-warning-sign"></span> 
                    <p><spring:message code="label.indicateur.modal.supprimer.panel.body.question"/></p>
                </div>

            </div>
            <div class="modal-footer ">
                <button value="oui" type="submit" class="btn btn-success" id="okDeleteIndModalButton">
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

<!-- ########################AJOUT INDICATEUR##############################  -->
<div class="modal fade modal-admin" id="CreateIndicateur" tabindex="-1" role="dialog" aria-labelledby="CreateIndicateur" aria-hidden="true">

    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form:form commandName="indicateurForm" action="${pageContext.request.contextPath}/addIndicateur" method="post" id="submitIndicateurForm">

                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white"><spring:message code="label.indicateur.modal.ajouter.panel.heading"/>
                    </h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <fieldset><legend><spring:message code="label.indicateur.modal.editer.panel.body.legend"/></legend>  
                        <label for="nomIndicateur"><spring:message code="label.indicateur.tableau.header.nom"/>:</label>
                        <form:input class="form-control" required="required"   path="nomIndicateur" placeholder="Nom" id="nomIndicateur"/>


                        <label for="descIndicateur"><spring:message code="label.indicateur.tableau.header.description"/>:</label>
                        <form:input class="form-control" path="libelleIndicateur" required="required" placeholder="Description" id="descIndicateur"/>

                        <label for="uniteIndicateur"><spring:message code="label.indicateur.tableau.header.unite"/>:</label>
                        <form:input  class="form-control" path="uniteIndicateur" required="required" placeholder="unite" id="uniteIndicateur"/>
                    </fieldset>
                </div>
                <div class="modal-footer ">

                    <button type="submit" onClick="" class="btn btn-info" value="submit"><spring:message code="label.bouton.valider"/> </button>
                    <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.annuler"/></button>

                </div>
            </form:form>

        </div>
    </div> 

</div>

                    <!-- ########################VIEW INDICATEUR##############################  -->

<div class="modal fade modal-admin" id="viewIdicateurModal" tabindex="-1" role="dialog" aria-labelledby="viewIdicateurModal" aria-hidden="true">


    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form commandName="indicateurForm" action="${pageContext.request.contextPath}/editIndicateur" method="post" id="editIndicateurFormulaire">
                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white"><spring:message code="label.indicateur.modal.ajouter.panel.heading"/></h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <fieldset><legend><spring:message code="label.indicateur.modal.editer.panel.body.legend"/></legend>  

                         <label for="editNomIndicateur"><spring:message code="label.indicateur.tableau.header.nom"/>:</label>
                        <input class="form-control" required="required"   name="nomIndicateur" placeholder="Nom" id="editNomIndicateur"/>


                        <label for="editDescIndicateur"><spring:message code="label.indicateur.tableau.header.description"/>:</label>
                        <input class="form-control" name="libelleIndicateur" required="required" placeholder="Description" id="editDescIndicateur"/>

                        <label for="editUniteIndicateur"><spring:message code="label.indicateur.tableau.header.unite"/>:</label>
                        <input  class="form-control" name="uniteIndicateur" required="required" placeholder="unite" id="editUniteIndicateur"/>
                    </fieldset>
                </div>
                <div class="modal-footer ">

                    <button type="submit" onClick="" class="btn btn-info" value="submit"><spring:message code="label.bouton.editer"/> </button>
                    <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.annuler"/></button>
                </div>
            </form>

        </div>
    </div>


</div>    
