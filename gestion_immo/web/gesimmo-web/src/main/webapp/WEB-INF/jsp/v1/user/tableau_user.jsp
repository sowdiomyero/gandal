
<sec:authorize  ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN">
    <div class="btn-group">
        <button type="button"
                style="  margin-top:7px;  margin-bottom:7px; background:rgba(26,4,82,0.47); color: #ffffff;border-radius: 6px;font-size: 13px"
                class="btn btn-default" data-title="CreateUser"
                data-toggle="modal" data-target="#CreateUser"> <span class="glyphicon glyphicon-plus-sign"> Nouvel Utilisateur</span>
        </button>
        <!-- onClick="newUserDialog.show();" -->
    </div>
</sec:authorize>
<div class="table-responsive" >
    <table id="table_utilisateurs" class="table table-bordred table-striped  "   border="1" style="border-color: white;">
        <caption><h4 style="font-weight: bold;">Liste des Utilisateurs</h4></caption>
        <thead>
            <tr  style="font-weight: bold;  background-color: #CCD4D9; text-align: center;">
                <td  >Prénom </td>
                <td>Nom </td>
                <td>Téléphone </td>
                <td>Email  </td>
                <td>Login  </td>
                <td>Actions</td>
            </tr>
        </thead>
        <tbody>

        <c:forEach items="${users}" var="user" >
            <tr style="text-align: center;">
                <td >${user.userPrenom}</td>
                <td>${user.userName}</td>
                <td> ${user.userPhone}</td>
                <td> ${user.userMail}</td>
                <td> ${user.compte.login}</td>
                <td >
                    <button title="Supprimer" class="deleteUser btn btn-danger btn-xs"
                            data-id="${user.idUser}" type="button" >
                        <span class="glyphicon glyphicon-trash" ></span>
                    </button>

                    <button title="Visualiser Profil" class="showUser btn btn-info btn-xs"
                            data-id="${user.idUser}" data-toggle="modal" type="button">
                        <span class="glyphicon glyphicon-eye-open" ></span>
                    </button>
            <c:if test="${user.compte.etatCompte==1}">
                <button title="Bloquer" class="bloquerUser btn btn-warning btn-xs"
                        data-id="${user.idUser}"  type="button">
                    <span class="fa fa-lock" ></span>
                </button>
            </c:if>
            <c:if test="${user.compte.etatCompte==0}">
                <button title="Débloquer" class="debloquerUser btn btn-success btn-xs"
                        data-id="${user.idUser}" type="button">
                    <span class="fa fa-unlock" ></span>
                </button>
            </c:if>
            <a href="${pageContext.request.contextPath}/user/view/${user.idUser}"> <button title="Fiche de l'utilisateur" class=" btn btn-info btn-xs"
                                                               type="button">
                    <span class="glyphicon glyphicon-folder-open" ></span>
                </button></a>

            </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>





<!-- ########################M ##############################################################################################################  -->




<!-- ########################AJOUT UTILISATEUR##############################  -->
<div class="modal fade modal-admin" id="CreateUser" tabindex="-1" role="dialog" aria-labelledby="CreateUser" aria-hidden="true">

    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">

        <div class="panel panel-info  " style="border-color: rgb(193,25,83)">
            <form:form commandName="subscriber" action="${pageContext.request.contextPath}/user/new" method="post" id="submitForm">

                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white">Info Utilisateur</h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <fieldset><legend>Informations utilisateur</legend>  
                        <label for="nomInput">Nom: </label>
                        <form:input class="form-control" required="required"  path="nom" placeholder="Votre Nom" id="nomInput"/>


                        <label for="prenomInput">Prenom: </label>
                        <form:input class="form-control" path="prenom" required="required" placeholder="Votre PrÃ©nom" id="prenomInput"/>


                        <label for="emailInput">Email: </label>
                        <form:input class="form-control" path="email" required="required" placeholder="Votre Email" id="emailInput"/>
                        <label for="editTelInput">Téléphone: </label>
                        <form:input class=" form-control"  path="telephone" required="required" placeholder="Votre Numero téléphone" id="TelInput"
                                    type="tel" pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$"/>


                        <label for="roleOptions">Role: </label>

                        <form:select   class="form-control" multiple="true" path="userRoles" required="required" >
                            <form:options  items="${subscriber.roles}"  cssClass="colored"  />
                        </form:select>
                    </fieldset>
                </div>
                <div class="modal-footer "> <button type="submit" onClick="" class="btn btn-info" value="submit">Valider</button>
                    <button type="button" onClick=""  data-dismiss="modal" class="btn btn-warning">Annuler</button>
                </div>
            </form:form>

        </div>
    </div>
</div>




<!-- Confirmation suprimer un utilisateur-->
<div class="modal fade" id="deleteConfirmationModal" tabindex="-1" role="dialog"
     aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </button>
                <h4 class="modal-title custom_align" id="Heading"> Suppression utilisateur</h4>
            </div>
            <div class="modal-body">

                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-warning-sign"></span> 
                    <p>Etes-vous sûr de vouloir supprimer cet utilisateur ?</p>
                </div>

            </div>
            <div class="modal-footer ">
                <button value="oui" type="submit" class="btn btn-success" id="okDeletionModalButton">
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



<!--Confirmation Régeneration du mot de passe de l'utilisateur.-->
<div class="modal fade" style="z-index: 1051" id="regenererConfirmationModal" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <form class="form-signin" role="form"
              action="${pageContext.request.contextPath}/user/password/regenerer" method="post" id="RegenererModalForm">
            <div class="modal-content">
                <div class="modal-header">
                    <!--                        <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                            </button>-->
                    <h4 class="modal-title custom_align" id="Heading"> Régeneration du mot de passe de l'utilisateur</h4>
                </div>
                <div class="modal-body">
                    <div class="alert-success" id="regenereSuccess">    
                        <div class="alert-danger" style="display: none" id="regenereFailed"></div>
                    </div>
                    <div class="alert alert-warning" id="messageConfirmation">
                        <span class="glyphicon glyphicon-warning-sign"></span> 
                        <p>Etes-vous sûr(e) de vouloir régenerer le mot de passe de cet utilisateur ?</p>
                    </div>

                    <input name="login" type="hidden" class="form-control"
                               placeholder="login" required autofocus id="loginUserRegenerer">
                        <input
                            name="email" type="hidden" class="form-control"
                            placeholder="email" required id="emailUserRegenerer">

                </div>
                <div class="modal-footer ">
                    <button value="oui" type="submit" class="btn btn-success"  id="okRegenererModalButton">
                        <span class="glyphicon glyphicon-ok-sign"></span> Oui
                    </button>
                    <button type="button" class="btn btn-warning" data-dismiss="modal" id="non">
                        <span class="glyphicon glyphicon-remove"></span> Non
                    </button>
                </div>
            </div>
        </form>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- Editer user et  Régeneration du mot de passe de l'utilisateur.-->

<div class="modal fade modal-admin" id="viewUserModal" tabindex="-1" role="dialog" aria-labelledby="viewUserModal" aria-hidden="true">


    <div class="col-md-4  col-lg-offset-4" style="margin-top:  25px" id="">
        <div class="panel panel-info" style="border-color: rgb(193,25,83)">
            <form commandName="editUserForm" action="${pageContext.request.contextPath}/user/api/update" method="post" id="editUserFormulaire">
                <div class="panel-heading" style="background-color: rgb(193,25,83)">
                    <h3 class="panel-title " style="color: white">Editer profil utilisateur</h3>
                </div>
                <div class="panel-body" style="color: #000033">
                    <fieldset><legend>Informations utilisateur</legend>  
                        <input class="form-control" type="hidden" name="idUser"  id="editIdUserInput"/>
                        <label for="editLoginInput">Login: </label>
                        <input class="form-control" readonly="false" name="login" required="required" placeholder="Votre login" id="editLoginInput"/>

                        <label for="editNomInput">Nom: </label>
                        <input class="form-control" required="required"  name="nom" placeholder="Votre Nom" id="editNomInput"/>


                        <label for="editPrenomInput">Prénom: </label>
                        <input class="form-control" name="prenom" required="required" placeholder="Votre Prénom" id="editPrenomInput"/>


                        <label for="editEmailInput">Email: </label>
                        <input class="form-control" type="email" name="email" required="required" placeholder="Votre Email" id="editEmailInput"/>

                        <label for="editTelInput">Téléphone: </label>
                        <input class=" form-control"  name="telephone" required="required" placeholder="Votre Numero téléphone" id="editTelInput"
                               type="tel" pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$"/>

                        <button type="button" id="regenerePassword"  data-toggle="modal" data-target="#regenererConfirmationModal" class=" bottom btn btn-info" value="submit">Regénérer mot de passe utilisateur</button>
                    </fieldset>
                </div>
                <div class="modal-footer ">
                    <button type="submit" id="formEditButton" onClick="" class="btn btn-info" value="submit">Editer</button>
                    <button type="button" id ="closeForm" onClick=""  data-dismiss="modal" class="btn btn-warning">Fermer</button>
                </div>
            </form>

        </div>
    </div>


</div>                                     