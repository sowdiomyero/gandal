<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<ol>
    <div class="row">
        <div class="col-sm-1">    </div>
        <div class="col-sm-3">  <label><spring:message code="label.indicateur.tableau.header.nom"/></label></div>
        <div class="col-sm-3 "> <label>Valeur</label> </div> 
        <div class="col-sm-3 "> <label><spring:message code="label.indicateur.tableau.header.unite"/></label> </div> 
        <div class="col-sm-2 "> <label><spring:message code="label.indicateur.tableau.header.actions"/></label></div>


    </div>
</ol>

<ol id="listindicateurLocalisation">
    <c:forEach items="${indicateursLocalisation}" var="i" >
        <div class="row">
            <div class="col-sm-1">
                <li></li>
            </div>



            <div class="col-sm-3">${i.indicateur.nomIndicateur}</div>
            <div class="col-sm-3 foo">${i.valeur}</div> 

            <div class="col-sm-3 foo">${i.indicateur.uniteIndicateur}</div> 
            <div class="col-sm-2 "><div class="row">
                    <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                        <button title="<spring:message code="label.projet.tableau.header.actions.title.visualiser"/>" class="showValueIndicateur btn btn-info btn-xs"
                                data-id="${i.idLocalisationIndicateur}" type="button" >
                            <span class="glyphicon glyphicon-eye-open" ></span>
                        </button>
                    </div>

                    <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                        <button title="<spring:message code="label.projet.tableau.header.actions.title.supprimer"/>" class="deleteIndicateurLocalisation btn btn-danger btn-xs"
                                data-id="${i.idLocalisationIndicateur}" type="button" >
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
                class="btn btn-default" data-title="AffecterIndicateur"
                data-toggle="modal" data-target="#AffecterIndicateur"> <span class="glyphicon glyphicon-plus-sign"> <spring:message code="label.indicateur.modal.ajouter.panel.heading"/></span>
        </button>



    </div>
</sec:authorize>

   
        
