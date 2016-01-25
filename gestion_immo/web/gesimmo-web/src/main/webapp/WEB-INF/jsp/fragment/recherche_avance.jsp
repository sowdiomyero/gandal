
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<sec:authorize access="isAuthenticated()">
    <!-- Begin Body -->

    <ul class="nav nav-pills nav-stacked">
        <li class="active ">
            <a href="#">
                <span style="  font-weight: bold;color: #fff">Filtre</span>
            </a>
        </li>
        <form:form commandName="projetFormFilter"  action="programmelisteFilter" method="post" id="filtrerProjetForm">

            <br/>
            <div class="pull-right">
                <button type="submit" id="submitProjetFormButton"class="btn btn-success pull-right">Rechercher</button>
            </div>
            <div class="alert alert-block alert-danger"
                 style="display:none">
            </div>
            <ul class="nav nav-pills">
                <li class="active"><a href="#infoProjetFilter2" data-toggle="tab">Info projet</a></li>
                <li><a href="#infoProjetTemporelles2" data-toggle="tab">Info temprelles</a></li>
                <li><a href="#infoProjetEstimation2" data-toggle="tab">Estimaions</a></li>
                <li><a href="#criteresProjetFilter2" data-toggle="tab">Criteres</a></li>
            </ul>

            <div class="tab-content">
                <div class="tab-pane active" id="infoProjetFilter2">
                    <div class="col-sm-3 col-xs-6">
                        <!--Nom contenant :-->
                        <label class="label label-danger" > Libelle</label>
                        <form:input type="text"  class="form-control" path="nomProjet" name="nomProjet"  required="" />
                    </div>

                    <div class="col-sm-3 col-xs-6">
                        <!--Nom Dsecription :-->
                        <label class="label label-success" > Dsecription</label>
                        <form:input type="text"  class="form-control" path="description" name="description"  required="" />
                    </div>



                    <div class="col-sm-3 col-xs-6">
                        <!--Etat projet:-->
                        <label class="label label-success">Etat</label>
                        <form:select   class="form-control" multiple="true" path="etatActuel" required="required" >
                            <form:option value="TOUT" label="--Please Select"/>

                            <form:options  items="${etats}"  cssClass="colored"  />
                        </form:select>
                    </div>
                </div>

                <div class="tab-pane " id="infoProjetTemporelles2">

                    <div class="col-sm-3 col-xs-6">
                        <!--Date debut entre -->
                        <label class="label label-danger">Debut entre</label>
                        <form:input type="date" class="form-control "  path="dateDebutMin"    />
                    </div>
                    <div class="col-sm-3 col-xs-6">
                        <label class="label label-danger">Et</label>
                        <form:input type="date" class="form-control"   path="dateDebutMax"    />
                    </div>
                    <div class="col-sm-3 col-xs-6">
                        <!--Date fin entre -->
                        <label class="label label-success">Fin entre</label>
                        <form:input type="date" class="form-control"   path="dateFinMin"  />
                    </div>
                    <div class="col-sm-3 col-xs-6">
                        <label class="label label-success">Et</label>
                        <form:input type="date" class="form-control"   path="dateFinMax"  />
                    </div>

                </div>
                <div class="tab-pane" id="infoProjetEstimation2">
                    <div class="col-sm-3 col-xs-6">
                        <!-- Ponderation  entre-->
                        <label class="label label-danger">Ponderation  entre</label>
                        <form:input type="number" class="form-control" min="0" max="100"     path="ponderationMin" required="required"/>
                    </div>
                    <div class="col-sm-3 col-xs-6">
                        <label class="label label-danger">Et</label>
                        <form:input type="number" class="form-control" min="0" max="100"   path="ponderationMax" required="required"/>
                    </div>
                    <div class="col-sm-3 col-xs-6">
                        <!-- Taux realisation-->
                        <label class="label label-success"> Taux realisation entre</label>
                        <form:input type="number" class="form-control" min="0"  max="100"   path="tauxMin" required="required"/>
                    </div>
                    <div class="col-sm-3 col-xs-6">
                        <label class="label label-success">Et</label>
                        <form:input type="number" class="form-control" min="0"  max="100"   path="tauxMax" required="required"/>
                    </div>
                </div>
                <div class="tab-pane" id="criteresProjetFilter2">
                    <div class="col-sm-2 col-xs-3">
                        <!--budget prevu-->
                        <label class="label label-danger">Budget prevu  entre</label>
                        <form:input type="number" class="form-control" min="0"   path="budgetPrevuMin"  required="required"/>
                    </div>
                    <div class="col-sm-2 col-xs-3">
                        <label class="label label-danger">Et</label>
                        <form:input type="number" class="form-control" min="0"  path="budgetPrevuMax"  required="required"/>
                    </div>
                    <div class="col-sm-2 col-xs-3">
                        <!--  Nombre logements entre:-->
                        <label class="label label-success"> Nombre d'emploi prevu entre</label>
                        <form:input type="number" class="form-control" min="0"   path="nbreEmploiPrevuMin"  required="required"/>
                    </div>
                    <div class="col-sm-2 col-xs-3">
                        <label class="label label-success">Et</label>
                        <form:input type="number" class="form-control" min="0"   path="nbreEmploiPrevuMax" required="required"/>
                    </div>

                </div> 
            </form:form>
    </ul>

</sec:authorize>

