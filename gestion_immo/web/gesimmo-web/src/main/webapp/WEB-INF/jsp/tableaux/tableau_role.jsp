
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<sec:authorize  ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN">
    <div class="btn-group">
        <button type="button"
                style="  margin-top:7px;  margin-bottom:7px; background:rgb(193,25,83); color: #ffffff;border-radius: 6px;font-size: 13px"
                class="btn btn-default" data-title="CreateRole"
                data-toggle="modal" data-target="#CreateRole"> <span class="glyphicon glyphicon-plus-sign"> <spring:message code="label.bouton.nouveau.role" /></span>
        </button>
        <!-- onClick="newUserDialog.show();" -->
    </div>
</sec:authorize>

<div class="table-responsive" >
    <table id="table_roles" class="table table-bordred table-striped  "   border="1" style="border-color: white;">
        <caption><h4 style="font-weight: bold;"><spring:message code="label.role.tableau.titre" /></h4></caption>
        <thead>
            <tr style="font-weight: bold;  background-color: #CCD4D9; text-align: center; ">
                <td> <spring:message code="label.role.tableau.header.nom"/></td>
                <td><spring:message code="label.role.tableau.header.description"/> </td>			 
                <td><spring:message code="label.role.tableau.header.actions"/></td>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${roles}" var="role">        
            <tr style="text-align: center;">	
                <td>${role.nameRole}</td>
                <td>${role.roleDesc}</td>
                <td >
                            <button title="Visualiser role" class="showRole btn btn-info btn-xs"
                                    data-id="${role.idRole}" data-toggle="modal" type="button">
                                <span class="glyphicon glyphicon-eye-open" ></span>
                            </button>

                            <button title="Supprimer" class="deleteRole btn btn-danger btn-xs"
                                    data-id="${role.idRole}" type="button" >

                                <span class="glyphicon glyphicon-trash" ></span>
                            </button>
                            <a href="ficheRole?idRole=${role.idRole}"><button title="Visualiser la fiche du role" class=" btn btn-info btn-xs" type="button">
                                    <span class="glyphicon glyphicon-folder-open" ></span>
                                </button></a>

                        </div>

                    </div>
                </td>
            </tr>


        </c:forEach>
        </tbody>
    </table>
</div>

<!-- ########################AJOUT ROLE##############################  -->
<div class="modal fade modal-admin" id="CreateRole" tabindex="-1" role="dialog" aria-labelledby="CreateRole" aria-hidden="true">

    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form:form commandName="roleForm" action="${pageContext.request.contextPath}/role/addRole" method="post" id="submitRoleForm">

                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white"><spring:message code="label.role.modal.editer.panel.heading"/></h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <fieldset><legend><spring:message code="label.role.modal.editer.panel.body.legend"/></legend>  
                        <label for="nomInput"><spring:message code="label.role.fiche.nom"/>: </label>
                        <form:input class="form-control" required="required"   path="nameRole" placeholder="Nom" id="nomRole"/>


                        <label for="prenomInput"><spring:message code="label.role.fiche.datecreation"/>: </label>
                        <form:input class="form-control" path="roleDesc" required="required" placeholder="Description" id="descRole"/>
                    </fieldset>
                </div>
                <div class="modal-footer ">

                    <button type="submit" onClick="" class="btn btn-info" value="submit"><spring:message code="label.bouton.valider"/></button>
                    <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.annuler"/></button>

                </div>
            </form:form>

        </div>
    </div> 

</div>


<!-- Modal suprimer un role-->
<div class="modal fade" id="deleteRoleConfirmation" tabindex="-1" role="dialog"
     aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </button>
                <h4 class="modal-title custom_align" id="Heading"> Suppression role</h4>
            </div>
            <div class="modal-body">

                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-warning-sign"></span> 
                    <p>Etes-vous sûr de vouloir supprimer ce role ?</p>
                </div>

            </div>
            <div class="modal-footer ">
                <button value="oui" type="submit" class="btn btn-success" id="okDeleteRoleConfirmation">
                    <span class="glyphicon glyphicon-ok-sign"></span> Oui
                </button>
                <button value="non" type="button" class="btn btn-default"
                        data-dismiss="modal" id="non">
                    <span class="glyphicon glyphicon-remove"></span> Non
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- Editer user et  Régeneration du mot de passe de l'utilisateur.-->

<div class="modal fade modal-admin" id="viewRoleModal" tabindex="-1" role="dialog" aria-labelledby="viewRoleModal" aria-hidden="true">


    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form commandName="editRoleForm" action="${pageContext.request.contextPath}/editRole" method="post" id="editRoleFormulaire">
                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white">Editer un role</h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <div class="alert alert-success" style="display: none" role="alert" id="roleEditSuccess">
                        <div class="alert alert-danger" role="alert" style="display: none" id="roleEditFailed"></div>
                    </div>
                    <fieldset><legend>Informations du role</legend>
                        <label for="usr">Nom du role</label>
                        <input type="text" class="form-control" required="required"  name="nameRole" placeholder="Nom" id="editNomRole"/>
                        <label for="usr">Description du role</label>
                        <input type="text" class="form-control" name="roleDesc" required="required" placeholder="Description" id="editDescRole"/>

                    </fieldset>
                </div>
                <div class="modal-footer ">
                    <button type="submit" id="formEditRoleButton" onClick="" class="btn btn-info" value="submit">Editer</button>
                    <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning">Fermer</button>
                </div>
            </form>

        </div>
    </div>
</div>
