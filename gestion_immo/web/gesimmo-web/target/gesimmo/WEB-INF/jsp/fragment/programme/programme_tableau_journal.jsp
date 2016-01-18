<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row" >
    <c:if test="${not empty journals}">
        <div class="table-responsive" >
            <div class="row" style="padding: 4px; margin: 4px; float: left">
                <button class="btn btn-success btn-xs" data-toggle="modal"  
                        valueIdProgrammeHidden="${programme.idActivite}" id="btnProgrammeAjouterJournal" >
                    <span class="glyphicon glyphicon-plus-sign"></span> Ajouter
                </button>
            </div>
            <table id="table_journal" class="table table-bordred table-striped  "   border="0">
                <thead>
                    <tr  style="font-weight: bold;  background-color: #CCD4D9">
                        <td>Date </td>
                        <td>Libelle </td>
                        <td>Details </td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${journals}" var="journal" >
                        <tr>
                            <td><fmt:formatDate type="both" value="${journal.dateCreation}" /></td>
                    <td> ${journal.libelle}</td>
                    <td>
                        <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                            <button title="Visualiser journal" class="showJournal btn btn-info btn-xs"
                                    data-id="${journal.idJournal}"  type="button" >

                                <span class="glyphicon glyphicon-eye-open" ></span>
                            </button>
                        </div>
                        <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                            <button title="Supprimer journal" class="deleteJournalProgrammeById btn btn-danger btn-xs"
                                    data-id="${journal.idJournal}"  type="button" >

                                <span class="glyphicon glyphicon-trash" ></span>
                            </button>
                        </div>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <!--Modal confirmation suppresion-->
        <div class="modal fade" id="deleteJournalConfirmationModal" tabindex="-1" role="dialog"
             aria-labelledby="edit" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </button>
                        <h4 class="modal-title custom_align" id="Heading"> Suppression journal</h4>
                    </div>
                    <div class="modal-body">

                        <div class="alert alert-danger">
                            <span class="glyphicon glyphicon-warning-sign"></span> 
                            <p>Etes-vous sûr de vouloir supprimer ce journal ?</p>
                        </div>

                    </div>
                    <div class="modal-footer ">
                        <button value="oui" type="submit" class="btn btn-success" id="confirmationSuppresionJournalProgramme">
                            <span class="glyphicon glyphicon-ok-sign"></span> Oui
                        </button>
                        <button value="non" type="button" class="btn btn-default"
                                data-dismiss="modal" id="non">
                            <span class="glyphicon glyphicon-remove"></span> Non
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!--Modal ajouter journal-->


    </c:if>
    <c:if test="${ empty journals}">
        <div class="row" style="padding: 4px; margin: 4px; float: left">
            <button class="btn btn-success btn-xs" data-toggle="modal"  
                    valueIdProgrammeHidden="${programme.idActivite}" id="btnProgrammeAjouterJournal" >
                <span class="glyphicon glyphicon-plus-sign"></span> Ajouter
            </button>
        </div><br/>
        <h4>Aucun journal</h4>
    </c:if>

    <div class="modal fade modal-admin" id="ajoutJournalProgrammeModal" tabindex="-1" role="dialog" aria-labelledby="viewJournalModal" aria-hidden="true">

        <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
            <div class="panel panel-info" style="border-color: rgb(193,25,83)">
                <form  action="${pageContext.request.contextPath}/journal/update" method="get" id="editJournalFormulaire">
                    <div class="panel-heading" style="background-color: rgb(193,25,83)">
                        <h3 class="panel-title " style="color: white">Details du journal</h3>
                    </div>
                    <div class="panel-body" style="color: #000033">
                        <div class="alert alert-success" style="display: none" role="alert" id="programmeEditSuccess">
                            <div class="alert alert-danger" role="alert" style="display: none" id="programmeEditFailed"></div>
                        </div>
                        <fieldset><legend>Informations du journal</legend>
                            <label for="addLibelleJournalProgramme">Libelle</label>
                            <input type="text" class="form-control"  required="true" placeholder="Libelle" id="addLibelleJournalProgramme"/>
                            <label for="addDescriptionJournalProgramme">Description</label>
                            <input type="text" class="form-control" required="true" placeholder="Description" id="addDescriptionJournalProgramme"/>
                        </fieldset>
                    </div>
                    <div class="modal-footer ">
                        <button type="button" id="btnValiderAjoutJournalProgramme" class="btn btn-info" value="submit">Valider</button>
                        <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning">Annuler</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>