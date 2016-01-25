<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<sec:authorize access="isAuthenticated()">
    <div class="container">
    <div class="row" style="border-top: 4px solid #e1edf7 ">

        <div class="col-md-12" >

            <div class="row" style="margin-top: 20px">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Parametres de l'application
                        </div>
                        <div class="panel-body">

                            <div class="tabbable">
                                <ul class="nav nav-pills" style="margin-top: 25px">
                                    <li  class="active"><a href="#messagerie" data-toggle="tab">Messagerie</a></li>

                                    <li ><a href="#banque" data-toggle="tab">Banque</a></li>
                                    <li ><a href="#rapport" data-toggle="tab">Rapports</a></li>
                                </ul>
                                <div class="tab-content">

                                    <div class="tab-pane active" id="messagerie">

                                        <form:form commandName="messagerieForm" action="${pageContext.request.contextPath}/settings/messagerie" method="post" id="messagerieForm">
                                            <div class="" style="padding-top: 10px; margin-top: 10px">

                                                <div class="panel panel-info">
                                                    <div class="row">

                                                        <div class="col-md-12">
                                                            <div class="">

                                                                <div class="panel-body" style="color: #000033">
                                                                    <div class="alert-success" id="settingsMessageSuccess" >
                                                                            <%-- <c:if test="${not empty message}">--%>
                                                                        <div class="alert-danger" style="display: none" id="settingsMessageFailed" >
                                                                                <%--   <h2>${message} </h2> --%>
                                                                        </div>
                                                                            <%--  </c:if>--%>
                                                                    </div>
                                                                    <label for="hostInput">Host: </label>
                                                                    <form:input class="form-control" required="required"  path="host" placeholder="ex : smtp.domain.com" id="hostInput"/>


                                                                    <label for="portInput">Port: </label>
                                                                    <form:input class="form-control" path="port" required="required" placeholder="Numéro de Port d'écoute default secure = 587" id="portInput"/>


                                                                    <label for="emailInput">Email : </label>
                                                                    <form:input class="form-control" path="email" required="required" placeholder="Adresse Email d'envoi de l'application" id="emailInput"/>

                                                                    <label for="emailInput">Login Messagerie : </label>
                                                                    <form:input class="form-control" path="username" required="required" placeholder="Adresse Email d'envoi de l'application" id="loginInput"/>

                                                                    <label for="emailInput">Mot de Passe Messagerie : </label>
                                                                    <form:input class="form-control" path="password" required="required" placeholder="Mot De Passe d'authentification de la messagerie" id="passwordInput" type="password"/>

                                                                    <label for="securiteOptions">Securité : </label>
                                                                    <form:select class="form-control" path="securite" id="securiteOptions" required="required">
                                                                        <form:option value="">Type Sécurité</form:option>
                                                                        <c:forEach items="${securites}" var="securite">
                                                                            <form:option value="${securite}">${securite}</form:option>
                                                                        </c:forEach>
                                                                    </form:select>
                                                                    <form:input class="form-control" path="idMessagerie"  id="idMessagerieInput" type="hidden"/>

                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>


                                                </div>
                                                <div class="modal-footer ">

                                                    <button type="submit" onClick="" class="btn btn-info" value="submit">Editer</button>
                                                    <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning">Annuler</button>

                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                    <div class="tab-pane " id="alerte">

                                        Le contenue du tab Alerte
                                    </div>
                                    <!-- INFORMATIONS DE LA BANQUE -->


                                    <div class="tab-pane " id="rapport">

                                            <form:form commandName="rapportForm" action="${pageContext.request.contextPath}/settings/rapport" method="post" id="rapportForm">
                                                <div class="" style="padding-top: 10px; margin-top: 10px">

                                                    <div class="panel panel-info">
                                                        <div class="row">

                                                            <div class="col-md-12">
                                                                <div class="">

                                                                    <div class="panel-body" style="color: #000033">
                                                                        <div class="alert-success" id="rapportMessageSuccess" >
                                                                                <%-- <c:if test="${not empty message}">--%>
                                                                            <div class="alert-danger" id="rapportMessageFailed" >
                                                                                    <%--   <h2>${message} </h2> --%>
                                                                            </div>
                                                                                <%--  </c:if>--%>
                                                                        </div>
                                                                        <label for="emailRapportInput">Email Beneficiaire: </label>
                                                                        <form:input class="form-control" required="required"  path="externalEmailToSend" placeholder="beneficiaire@gmail.com" id="emailRapportInput"/>


                                                                        <label for="repertoireRapportInput">Repertoire de Sauvegarde : </label>
                                                                        <form:input class="form-control" path="repertoireRapport" required="required"  id="repertoireRapportInput"/>



                                                                        <label for="frequenceOptions">Fréquence : </label>
                                                                        <form:select class="form-control" path="frequence" id="frequenceOptions" required="required">
                                                                            <form:option value="">Fréquence de Génération</form:option>
                                                                            <c:forEach items="${frequencies}" var="frequence">
                                                                                <form:option value="${frequence}">${frequence.desc}</form:option>
                                                                            </c:forEach>
                                                                        </form:select>

                                                                        <label for="typeTransactionOptions">Données du Rapport : </label>
                                                                        <form:select class="form-control" path="typeTransaction" id="typeTransactionOptions" required="required">
                                                                            <form:option value="">Type de données</form:option>
                                                                            <c:forEach items="${typeDonnees}" var="type">
                                                                                <form:option value="${type}">${type.desc}</form:option>
                                                                            </c:forEach>
                                                                        </form:select>
                                                                        <label for="formatOptions">Format de Génération : </label>
                                                                        <form:select class="form-control" path="format" id="formatOptions" required="required">
                                                                            <form:option value="">Format </form:option>
                                                                            <c:forEach items="${formats}" var="formatDoc">
                                                                                <form:option value="${formatDoc}">${formatDoc}</form:option>
                                                                            </c:forEach>
                                                                        </form:select>

                                                                        <form:input class="form-control" path="idRapport"  id="idRapportInput" type="hidden"/>
<!--                                                                        <span style="padding-top:5px; margin-top:5px" id="genererRapports" class="glyphicon glyphicon-chevron-right" aria-hidden="true"> Rapports</span>-->
<!--                                                                        <div id="toggleDemo" class="collapse in" style="background: #F9F9F9; border: 1px solid #E1E1E8; margin: 10px 0; padding: 8px;">
                                                                            <input type="button" class="btn btn-primary" value="Toggle Button">
                                      
                                                                    </div>-->

                                                                </div>

                                                            </div>
                                                        </div>


                                                    </div>
                                                    <div class="modal-footer ">

                                                        <button type="submit" onClick="" class="btn btn-info" value="submit">Editer</button>
                                                        <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning">Annuler</button>

                                                    </div>
                                                </div>
                                            </form:form>


                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- Time Line alertes   -->






            </div>
        </div>

        <hr style="border: 2px solid #e1edf7"/>

    </div>
     </div>
</sec:authorize>

