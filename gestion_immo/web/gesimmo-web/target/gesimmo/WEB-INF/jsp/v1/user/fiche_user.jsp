<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<sec:authorize access="isAuthenticated()">


    <div class="container-fluid" style="margin-left: 25px; margin-right: 25px;">    


        <div class="row">
            <div class="panel panel-default" style=" margin-bottom: 10px; ">
                <div class="panel-heading text-center">
                    <h2><spring:message code="label.utilisateur.header.titre"/></h2>
                </div>
            </div>
        </div>


        <div class="row">


            <!--            Menu Administration-->
            <div class="col-md-2" >
                <%@include file="./menu_user.jsp" %>
            </div>

            <!--            Meteo Utilisateur-->
            <div class="col-md-10">
                <div class="row">
                    <%@include file="./meteo_user.jsp" %>
                </div>

                <!--                Tableau utilisateur-->
                <form:form commandName="ficheUserForm" action="${pageContext.request.contextPath}/user/update" method="post" id="updateUserForm">
                    <div class="col-lg-12" >
                        <c:if test="${ficheUserForm.etatCompte==1}">
                            <form:button data-id="${ficheUserForm.idUser}"   type="button" title="Bloquer"   class="bloquerUser btn btn-md btn-danger glyphicon glyphicon-glyphicon glyphicon-thumbs-down icon-white " ><spring:message code="label.bouton.bloquer"/> </form:button>
                        </c:if>
                        <c:if test="${ficheUserForm.etatCompte==0}"> 
                            <form:button data-id="${ficheUserForm.idUser}"   type="button" title="Bloquer"   class="debloquerUser btn btn-md btn-success glyphicon glyphicon-glyphicon glyphicon-thumbs-up icon-white " ><spring:message code="label.bouton.debloquer"/></form:button>
                        </c:if>

                        <button  type="button" class=" btn btn-md btn-info glyphicon glyphicon-glyphicon glyphicon-thumbs-up icon-white " >  Surveiller </button>
                    </div>
                    <div class="panel panel-primary" >
                        <div class="panel-heading" style="text-align: center;background-color: #CCD4D9;color: black;font: bold">
                            <h3 class="panel-title" ><spring:message code="label.utilisateur.fiche.titre"/><span class="glyphicon glyphicon-folder-open"></span><a href="${pageContext.request.contextPath}//userslist" style="float: right;"><span class="fa fa-reply-all"></span> <spring:message code="label.bouton.span.retour"/></a></h3>
                        </div>
                        <form:hidden path="idUser"   class="form-control" id="idUser"/>
                        <div style="margin: 20px;" >
                            <div class="form-group row "  >
                                <div class="col-lg-3">
                                    <label for="usr"><spring:message code="label.utilisateur.date.creation"/></label>
                                    <form:input class="form-control" path="dateCreation" required="required" placeholder="Description" id="" disabled="true"/>
                                </div>

                                <div class="col-lg-3">
                                    <label for="usr"><spring:message code="label.utilisateur.date.modification"/></label>
                                    <form:input class="form-control" required="required"  path="dateUpdated" placeholder="Nom" id="" disabled="true"/>
                                </div>
                            </div>
                            <div class="form-group row "  >
                                <div class="col-lg-3">
                                    <label for="usr"><spring:message code="label.utilisateur.nomUtilisateur"/></label>
                                    <form:input path="login" required="required" placeholder="Login"  type="text"  class="form-control" disabled="true"  id="loginUpdate"/>

                                </div>

                                <div class="col-lg-3">
                                    <label for="email"><spring:message code="label.utilisateur.email"/></label>
                                    <form:input path="email"  required="required" placeholder="Email" type="text"  class="form-control" id="emailUpdate"/>
                                </div>

                            </div>
                            <div class="form-group row "  >
                                <div class="col-lg-3">
                                    <label for="usr"><spring:message code="label.utilisateur.nom"/></label>
                                    <form:input path="nom" required="required" placeholder="Nom"  type="text"  class="form-control" id="nomUpdate"/>
                                </div>
                                <div class="col-lg-3">
                                    <label for="usr"><spring:message code="label.utilisateur.telephone"/></label>
                                    <form:input path="telephone" required="required" placeholder="Telephone"  type="text"  class="form-control" id="telephoneUpdate"/>
                                </div>

                            </div>

                            <div class="form-group row "  >
                                <div class="col-lg-3">
                                    <label for="usr"><spring:message code="label.utilisateur.prenom"/></label>
                                    <form:input path="prenom" placeholder="Prenom"  required="required" type="text" class="form-control" id="prenomUpdate" />
                                </div>

                                <div class="col-lg-3 " >
                                    <label  for="usr"><spring:message code="label.utilisateur.roles"/></label>
                                    <br>

                                    <form:select   class="form-control" multiple="true" path="userRoles" required="required" >
                                        <form:options  items="${roles}"  cssClass="colored"  />
                                    </form:select>

                                </div>

                                <div class="col-lg-12">
                                    <span class=""></span>
                                    <button  type="submit" value="submit"  class=" btn btn-md  fa fa-pencil-square-o " style="background-color: rgb(193,25,83); color: white;float: right; "><spring:message code="label.bouton.update"/></button>
                                </div>
                            </div>


                        </div>

                    </form:form>
                </div>







            </div>





        </div>
    </div>

</sec:authorize>

