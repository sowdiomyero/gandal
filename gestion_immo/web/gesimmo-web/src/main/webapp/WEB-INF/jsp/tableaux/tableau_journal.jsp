<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<ol>
    <div class="row">
        <div class="col-sm-1">    </div>
        <div class="col-sm-3">  <label><spring:message code="label.journal.libelle"/></label></div>
        <div class="col-sm-3 "> <label><spring:message code="label.journal.description"/></label> </div> 
        <div class="col-sm-3 "> <label><spring:message code="label.journal.dateUpdated"/></label> </div> 
        <div class="col-sm-2 "> <label><spring:message code="label.role.tableau.header.actions"/></label></div>
    </div>
</ol>

<ol id="listJournal">
    <c:forEach items="${journals}" var="journal" >
        <div class="row">
            <div class="col-sm-1">
                <li></li>
            </div>
            <div class="col-sm-3">${journal.libelle}</div>
            <div class="col-sm-3 foo">${journal.description}</div> 
            <div class="col-sm-3 "><fmt:formatDate value="${journal.dateUpdated}" pattern="dd-MM-yyyy HH:mm:ss" /></div> 
            <div class="col-sm-2 "><div class="row">
                    <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                        <button title="<spring:message code="label.projet.tableau.header.actions.title.visualiser"/>" class="showJournal btn btn-info btn-xs"
                                data-id="${journal.idJournal}" type="button" >
                            <span class="glyphicon glyphicon-eye-open" ></span>
                        </button>
                    </div>

                    <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                        <button title="<spring:message code="label.projet.tableau.header.actions.title.supprimer"/>" class="deleteJournal btn btn-danger btn-xs"
                                data-id="${journal.idJournal}" type="button" >
                            <span class="glyphicon glyphicon-trash" ></span>
                        </button>
                    </div>


                </div></div> 


        </div><br>
    </c:forEach>
</ol> 

<sec:authorize  ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN ">
    <div class="btn-group">
        <button type="button"
                style="  margin-top:7px;  margin-bottom:7px; background:rgb(193,25,83); color: #ffffff;border-radius: 6px;font-size: 13px"
                class="btn btn-default" data-title="CreateJournal"
                data-toggle="modal" data-target="#CreateJournal"> <span class="glyphicon glyphicon-plus-sign"> <spring:message code="label.bouton.nouveau.journal" /></span>
        </button>

    </div>
</sec:authorize>



<!-- ########################AJOUT JOURNAL##############################  -->
<div class="modal fade modal-admin" id="CreateJournal" tabindex="-1" role="dialog" aria-labelledby="CreateJournal" aria-hidden="true">

    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form:form commandName="journalForm" action="${pageContext.request.contextPath}/addJournal" method="post" id="submitJournalForm">

                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white"><spring:message code="label.journal.ajouter.title.1" /></h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <fieldset><legend><spring:message code="label.journal.ajouter.title.2" /></legend>  
                        <label for="nomProjet"><spring:message code="label.journal.libelle" /></label>
                        <form:input class="form-control" required="required"   path="libelle"   id="libelleJournal"/>


                        <label for="descProjet"><spring:message code="label.journal.description" /></label>
                        <form:textarea  class="form-control" path="description" required="required"   id="descJournal"/>

                        <form:hidden class="form-control" path="idActivite"    placeholder="Description" id="descProjet"  /> 



                    </fieldset>
                </div>
                <div class="modal-footer ">

                    <button type="submit" onClick="" class="btn btn-info" value="submit"><spring:message code="label.bouton.valider" /></button>
                    <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.annuler" /></button>

                </div>
            </form:form>

        </div>
    </div> 

</div>


<!-- Editer JOURNAL-->

<div class="modal fade modal-admin" id="viewJournalModal" tabindex="-1" role="dialog" aria-labelledby="viewJournalModal" aria-hidden="true">

    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form commandName="journalForm" action="${pageContext.request.contextPath}/editJournal" method="post" id="editJournalFormulaire">
                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white"><spring:message code="label.journal.editer.title.1" /></h3>
                </div>
                <div class="panel-body" style="color: #000033">

                    <fieldset><legend><spring:message code="label.journal.editer.title.2" /></legend>
                        <label for="editLibelleJournal"><spring:message code="label.journal.libelle" /></label>
                        <input type="text" class="form-control" required="required"  name="libelle" placeholder="" id="editLibelleJournal"/>
                        <label for="editDescJournal"><spring:message code="label.journal.description" /></label>
                        <input type="text" class="form-control" name="description" required="required" placeholder="" id="editDescJournal"/> 
                        <input type="hidden" class="form-control" name="idJournal"    id="editIdJournal"/> 


                    </fieldset>
                </div>
                <div class="modal-footer ">
                    <button type="submit" id="formEditJournalButton" onClick="" class="btn btn-info" value="submit"><label><spring:message code="label.bouton.valider" /></label></button>
                    <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><label><spring:message code="label.bouton.annuler" /></label></button>
                </div>
            </form>

        </div>
    </div>
</div>
                
                
                <!-- Modal suprimer un journal-->
<div class="modal fade" id="deleteJournalConfirmation" tabindex="-1" role="dialog"
     aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </button>
                <h4 class="modal-title custom_align" id="Heading"><spring:message code="label.journal.modal.supprimer.panel.heading"/></h4>
            </div>
            <div class="modal-body">

                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-warning-sign"></span> 
                    <p><spring:message code="label.journal.modal.supprimer.panel.body.question"/></p>
                </div>

            </div>
            <div class="modal-footer ">
                <button value="oui" type="submit" class="btn btn-success" id="okDeleteJournalConfirmation">
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





