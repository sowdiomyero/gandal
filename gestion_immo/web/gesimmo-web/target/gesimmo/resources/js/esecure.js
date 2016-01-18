$(document).ready(function() {
    /*Partie de home.JSP */
    var infoProfil;
    $('#mytable').dataTable({
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Transactions par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ of _PAGES_",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });
    $('#mytable_nv').dataTable({
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Transactions par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ of _PAGES_",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });




    $(function() {
        $('a').tooltip({placement: 'left'});
    });



    /*La partie de l'edition d'un utilisateurs des transactions non valides */
    $('.tableauTransactions').on('click', '.detailTransaction', function() {
        var idTransaction = $(this).attr('data-id');
        oTable = $('.transactionsClient').dataTable();
        oTable.fnDestroy();
        $.ajax({
            url: 'transaction/get',
            type: 'get',
            data: 'idTransaction=' + idTransaction,
            success: function(response) {
                //alert('response received'+response.montant);
                //Informations du client provenant du serveur

                $('#tr_prenomClient').attr('value', response.prenomClient);
                $('#tr_nomClient').attr('value', response.nomClient);
                $('#tr_numCarte').attr('value', response.numCarte);
                $('#tr_numCompte').attr('value', response.numCompte);
                $('#tr_email').attr('value', response.email);
                $('#tr_adresseClient').attr('value', response.adresseClient);
                //Informations de la transaction provenant du serveur                
                $('#tr_nomCommercant').attr('value', response.nomCommercant);
                $('#tr_website').attr('value', response.siteWebCommercant);
                $('#tr_adresseCommercant').attr('value', response.adresseCommercant);
                $('#tr_montantTransaction').attr('value', response.montantTransaction);
                $('#tr_dateTransaction').attr('value', response.dateTransaction);
                var listeTransactions = response.transactions;
                $('#listeTransactionsClient').html("");
                for (var i = 0; i < listeTransactions.length; i++)
                {
                    var trContent = "<tr><td>" + listeTransactions[i].nomClient + "</td>";
                    trContent += "<td>" + listeTransactions[i].prenomClient + "</td>";
                    trContent += "<td>" + listeTransactions[i].dateTransaction + "</td>";
                    trContent += "<td>" + listeTransactions[i].siteWebCommercant + "</td>";
                    trContent += "<td>" + listeTransactions[i].montantTransaction + "</td>";
                    trContent += "<td>" + listeTransactions[i].etatTransaction + "</td>";
                    trContent += "</tr>";
                    $('#listeTransactionsClient').append(trContent);
                    trContent = null;
                }
                $('.transactionsClient').dataTable({
                    oLanguage: {
                        sLengthMenu: "Afficher: _MENU_ Transactions par page ",
                        sSearch: "Rechercher : ",
                        sZeroRecords: "Aucune valeur trouvee !!",
                        sInfo: "Afficher page _PAGE_ of _PAGES_",
                        oPaginate: {
                            sFirst: "Premier",
                            sPrevious: "Precedent",
                            sNext: "Suivant",
                            sLast: "Dernier"
                        }
                    },
                    "pagingType": "full_numbers"
                });
                listeTransactions = null;
                $('#edit').modal();
                // ajax success callback
            }, error: function(response) {
                //alert('Une exception est survenue au niveau du Serveur ' + response);
                $.notify("Une exception est survenue au niveau du Serveur : " + response, "error");
            }
        });
    });
    /*Fin de la partien home.jsp*/
    /*==============================================AJOUT D'UN NOUVEAU SCRIPT====================================================*/
    /*Début partie pour setting.jsp*/
    $('#messagerieForm').submit(function(e) {
        var frm = $('#messagerieForm');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    //alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+"  Role: "+callback.role);
                    //$(this).html("Action effectuee avec succes");
                    $.notify("Action effectuee avec succes", "success");
                    //$('#settingsMessageSuccess').html(callback.msg);
                } else {
                    $('#settingsMessageSuccess').html(callback.msg);
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $('#settingsMessageFailed').html("Une erreur est survenue pendant l'ajout des Parametres de messagerie");
                $.notify("Une erreur est survenue pendant l'ajout des Parametres de messagerie ", "error");
            }
        });
    });
    /*BANQUE*/

    $('#banqueForm').submit(function(e) {
        var frm = $('#banqueForm');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    //alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+"  Role: "+callback.role);
                    //$(this).html("Action effectuee avec succes");
                    $.notify("Action effectuee avec succes", "success");
                    //$('#banqueMessageSuccess').html(callback.msg);
                } else {
                    //$('#banqueMessageSuccess').html(callback.msg);
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                //$('#banqueMessageFailed').html("Une erreur est survenue pendant l'ajout des Parametres de la Banque");
                $.notify("Une erreur est survenue pendant l'ajout des Parametres de la Banque ", "error");
            }
        });
    });
    /* GENERATION DES RAPPORTS*/

    $('#rapportForm').submit(function(e) {
        var frm = $('#rapportForm');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    //alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+"  Role: "+callback.role);
                    //$(this).html("Action effectuee avec succes");
                    $.notify("Action effectuee avec succes", "success");
                    //$('#rapportMessageSuccess').html(callback.msg);
                } else {
                    //$('#rapportMessageSuccess').html(callback.msg);
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                //$('#rapportMessageSuccess').html("Une erreur est survenue pendant l'ajout des Parametres des Rapports");
                $.notify("Une erreur est survenue pendant l'ajout des Parametres des Rapports ", "error");
            }
        });
    });
    $("#genererRapports").click(function() {
        $("#toggleDemo").collapse('toggle');
    });
    /*Fin de la partien setting.jsp*/
    /*==============================================AJOUT D'UN NOUVEAU SCRIPT====================================================*/
    /*Début partie users.jsp*/
    var loginUser = null;
    $('#table_utilisateurs').dataTable({
        responsive: true,
        "aaSorting": [[0, "asc"]],
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Utilisateurs par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ of _PAGES_",
            sInfoFiltered: "(Filtrés sur _MAX_ utilisateurs)",
            sInfoEmpty: "Aucun utilisateur trouvé",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });
    
    function deleteUserById(idUser) {
        routeController.user.deleteUserById(idUser);
    }
    
    $('#table_utilisateurs').on('click', '.deleteUser', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteConfirmationModal').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeletionModalButton', function() {
                    $('#deleteConfirmationModal').hide();
                    deleteUserById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });
    //Code de chargement d'un utilisateur selectionné sur la liste des utilisateurs
    $('#table_utilisateurs').on('click', '.showUser', function(e) {
        e.preventDefault();
        $('#userEditSuccess').css('display', 'none');
        $('#userEditFailed').css('display', 'none');
        var idUser = $(this).attr('data-id');
        $('#viewUserModal').modal();
        
        routeController.user.getUserById(idUser, populateUserModalForm);
//        $.ajax({
//            url: 'user/getUser',
//            type: 'get',
//            data: 'idUser=' + idUser,
//            success: function(response) {
//                //Informations du client provenant du serveur  
//                populateUserModalForm(response);
//            }
//            , error: function(response) {
//                alert('ajax failed' + response);
//            }
//        });
    });
    function populateUserModalForm (ajaxResponse){
           $('#editNomInput').val(ajaxResponse.nom);
           $('#editPrenomInput').val(ajaxResponse.prenom);
           $('#editEmailInput').val(ajaxResponse.email);
           $('#editTelInput').val(ajaxResponse.telephone);
           $('#editLoginInput').val(ajaxResponse.login);
    }
    //Partie pour la modification d'un utilisateur
    $('#editUserFormulaire').submit(function(e) {
        var frm = $('#editUserFormulaire');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $('#userEditSuccess').html(callback.msg);
                    hideModal('viewUserModal');
                    //$('#viewUserModal').modal('hide');  // masquer le modal après modification du user
                    // masquer le modal
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1500);
                }

                else {
                    $.notify(callback.msg, "warn");

                }
            },
            error: function() {
                $('#userMessageFailed').html("Une erreur est survenue pendant l'ajout de l'utilisateur ");
                $('#userMessageFailed').css('display', 'block');
                $.notify("Une erreur est survenue pendant l'ajout de l'utilisateur", "error");
            }
        });
    });
//    function regenererPassword(login, email)
//    {
//        $.ajax({
//            contentType: 'application/json; charset=utf-8',
//            type: 'get',
//            url: 'user/regenererPassword',
//            dataType: 'json',
//            data: 'login=' + login+'&email='+email,
//            success: function(callback) {
//                if (callback.resultat === 200)
//                    $('#userEditSuccess').html(callback.msg);
//                $.notify(callback.msg, "success");
//            },
//            error: function() {
//                $('#userEditFailed').html("Une erreur est survenue pendant l'ajout de l'utilisateur");
//                $.notify("Une erreur est survenue pendant l'ajout de l'utilisateur", "error");
//            }
//        });
//    }
    $('#regenerePassword').click(function(e) {
        e.preventDefault();
        loginUser = $('#editLoginInput').val();
        emailUser = $('#editEmailInput').val();
        $('#loginUserRegenerer').val(loginUser);
        $('#emailUserRegenerer').val(emailUser);
        hideModal('viewUserModal');
        $('#regenererConfirmationModal').show();
    });
    $('#RegenererModalForm').submit(function(e) {
        var frm = $('#RegenererModalForm');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        //regenererPassword(loginUser, emailUser);
        //$('#regenererConfirmationModal').hide();
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
//                    $('#userEditSuccess').html(callback.msg);

                    hideModal('regenererConfirmationModal');
                    //$('#viewUserModal').modal('hide');  // masquer le modal après modification du user
                    // masquer le modal
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1500);
                }

                else {
                    $.notify(callback.msg, "warn");

                }
            },
            error: function() {
                $('#userMessageFailed').html("Une erreur est survenue pendant la regeneration du mot de passe ");
                $('#userMessageFailed').css('display', 'block');
                $.notify("Une erreur est survenue pendant la regeneration du mot de passe ", "error");
            }
        });
    });
    /*Fin partie users.jsp*/
    /*==============================================AJOUT D'UN NOUVEAU SCRIPT====================================================*/
    /*Début partie FormulaireClient.jsp*/
    function closeWindow() {
        netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
        window.open('', '_self');
        window.close();
    }
    function hideModal(idModal) {
        $('#' + idModal).modal('hide');
    }
    // var message = $('#idMessage').val();
    var reponse = $('#idResultat').val();
    //$('#idMessage').html(message);
    if (reponse === "300") {
        $.notify("Cette demande a été déjà validée.", "error");
        $('#validerTransaction').addClass('disabled');
        $('#annulerTransaction').addClass('disabled');
    }
    if (reponse === "700") {
        $.notify("Cette demande a été déjà annulée.", "error");
        $('#validerTransaction').addClass('disabled');
        $('#annulerTransaction').addClass('disabled');
    }
    if (reponse === "800") {
        $.notify("Cette demande a été déjà acceptée.", "error");
        $('#validerTransaction').addClass('disabled');
        $('#annulerTransaction').addClass('disabled');
    }
    $('#verifInfoFormulaireId').submit(function(e) {
        $('#verifAdresseContent').html("");
        var frm = $('#verifInfoFormulaireId');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                $('#confirmationContent').html(callback.msg);
                $.notify(callback.msg, "success");
                if (callback.resultat === 200) {
                    $.notify("La validation des vos informations a réussie. Vous allez être redirigés vers la page de confirmation de votre banque.", "success");
                    //$('#confirmationContent').html("La validation des vos informations a réussie. Vous allez être redirigés vers la page de confirmation de votre banque.");
                    setTimeout(function() {
                        window.close();
                    },
                            6000
                            );
                    // closeWindow();
                } else {
                    $('#nbVerif').val(callback.nbVerif);
                    $('#nbTentativeAutorise').val(callback.nbTentativeAutorise);

                    if (callback.nbVerif <= callback.nbTentativeAutorise) {
                        $('#confirmationContent').addClass('error-validation');
                        $('#confirmationContent').html(callback.msg);
                        $('#verifAdresseContent').html(callback.msgAdress);
                        if (callback.msgNomClientSaisie !== null) {
                            $('#nomverif').val("");
                            $('#nomverif').after('<span class="form-control-feedback" aria-hidden="true"></span>');
                            $('#divNomVerif').addClass('has-error');
                        }

                        if (callback.msgPrenomClientSaisie !== null) {
                            $('#prenomverif').val("");
                            $('#prenomverif').after('<span class="form-control-feedback" aria-hidden="true"></span>');
                            $('#divPrenomverif').addClass('has-error');
                        }

                        if (callback.msgemailSaisie !== null) {
                            $('#emailverif').val("");
                            $('#emailverif').after('<span class="form-control-feedback" aria-hidden="true"></span>');
                            $('#divEmailverif').addClass('has-error');
                        }

                        if (callback.msgVilleSaisie !== null) {
                            $('#villeverif').val("");
                            $('#villeverif').after('<span class="form-control-feedback" aria-hidden="true"></span>');
                            $('#divVilleverif').addClass('has-error');
                        }
                        if (callback.msgCodePostalSaisie !== null) {
                            $('#codepostalverif').val("");
                            $('#codepostalverif').after('<span class="form-control-feedback" aria-hidden="true"></span>');
                            $('#divCodepostalverif').addClass('has-error');
                        }
                    }
                    else {
                        $('#confirmationContent').html("La validation des vos informations a échouée, Vous avez eppuisé tous vos tentatives. Pour des raisons de sécurité, nous ne pouvons poursuivre la validation. Vueillez contacter votre conseiller.");
                        $('#confirmationContent').addClass('error-validation');
                        setTimeout(function() {
                            window.close();
                        },
                                6000
                                );
                    }
                }
            },
            error: function() {
                //$('#confirmationContent').html("Une erreur est survenue pendant l'ajout de l'utilisateur");
                //alert("Une erreur est survenue pendant la vérification");
                $.notify("Une erreur est survenue pendant la vérification", "error");
            }
        });

    });
    $('#annulerTransaction').click(function(e) {
        e.preventDefault();
        $.ajax({
            url: 'transaction/annulation',
            type: 'get',
            success: function(response) {

            }, error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });
    /*Fin partie FormulaireClient.jsp*/
    /*==============================================AJOUT D'UN NOUVEAU SCRIPT====================================================*/
    /*Début partie header.jsp*/
    $('#submitForm').submit(function(e) {
        var frm = $('#submitForm');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    //alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+"  Role: "+callback.role);
                    $(this).html("Action effectuee avec succes");
                    $('#userMessageSuccess').html(callback.msg);
                    $.notify(callback.msg, "success");
                    hideModal('CreateUser');
                    setTimeout(function() {
                        location.reload();
                    }, 1500);
                    $('#userMessageSuccess').addClass('alert');
                    $('#userMessageSuccess').css('display', 'block');
                } else {
                    $('#userMessageSuccess').html(callback.msg);
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $('#userMessageFailed').html("Une erreur est survenue pendant l'ajout de l'utilisateur");
                $.notify("Une erreur est survenue pendant l'ajout de l'utilisateur", "error");
            }
        });
    });

    function hideOrShowElement(divId) {
        if ($('#' + divId).css('display') === 'none') {
            $('#' + divId).css('display', 'block');
        } else {
            $('#' + divId).css('display', 'none');
        }
    }

    function hideElement(divId) {
        if ($('#' + divId).css('display') === 'block') {
            $('#' + divId).css('display', 'none');
        }
    }

    //l'evenement au click du lien change password
    $('.changePassword').click(function(e) {
        e.preventDefault();
//            var login = $(this).attr('data-id');
        $('#editProfilPasswordInput').val('');
        $('#panelPassword').toggle('slow');
        return false;
    });

    //Partie pour la modification d'un utilisateur
    $('#editProfilFormulaire').submit(function(e) {

        var frm = $('#editProfilFormulaire');
        var contextPath = $('#contextPath').val();
        e.preventDefault();
        var data = {};

        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    // alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+");
                    $(this).html("Success!");
                    $('#profilMessageSuccess').html(callback.msg);
                    hideModal('viewProfilModal');
                    $.notify(callback.msg, "success");
                    $('#profilMessageSuccess').css('display', 'block');
                }
                if (callback.resultat === 300)
                {
                    //  $('#profilMessageFailed').show();
                    $('#profilMessageFailed').html(callback.msg);
                    $.notify(callback.msg, "error");
                    $('#profilMessageFailed').css('display', 'block');
                }
                if (callback.resultat === 600)
                {
                    $('#editProfilFormulaire').hide();
                    window.location = contextPath;
                }
            },
            error: function() {
                $('#profilMessageFailed').html("Une erreur est survenue pendant l'ajout de l'utilisateur");
                $.notify("Une erreur est survenue pendant l'ajout de l'utilisateur", "error");
                $('#profilMessageFailed').css('display', 'block');
            }
        });
    });

    $('.showProfil').click(function(e) {
        e.preventDefault();
        $('#profilMessageSuccess').css('display', 'none');
        $('#profilMessageFailed').css('display', 'none');
        var login = $(this).attr('data-id');

        $.ajax({
            url: 'user/getProfil',
            type: 'get',
            data: 'login=' + login,
            success: function(response) {
                infoProfil = response;
                $('#viewProfilModal').modal();
                $('#editProfilNomInput').val(response.nom);
                $('#editProfilPrenomInput').val(response.prenom);
                $('#editProfilEmailInput').val(response.email);
                $('#editProfilTelInput').val(response.telephone);
                $('#editProfilLoginInput').val(response.login);
                /*$('#editProfilPasswordInput').val(response.password);*/
            }, error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });
    $('.annulerModifProfil').click(function(e) {
        e.preventDefault();
        $('#editProfilNomInput').val(infoProfil.nom);
        $('#editProfilPrenomInput').val(infoProfil.prenom);
        $('#editProfilEmailInput').val(infoProfil.email);
        $('#editProfilTelInput').val(infoProfil.telephone);
        $('#editProfilLoginInput').val(infoProfil.login);
        // $('#editProfilPasswordInput').val(infoProfil.password);
    });




    //Bloquer un utilisateur

    $('#table_utilisateurs').on('click', '.bloquerUser', function(e) {
        e.preventDefault();
        var idUser = $(this).attr('data-id');
        $.ajax({
            url: 'user/bloquer',
            type: 'get',
            data: 'idUser=' + idUser,
            success: function(response) {
                $.notify(response.msg, "success");
                setTimeout(function() {
                    location.reload();
                }, 1700);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });






    $('#updateUserForm').on('click', '.bloquerUser', function(e) {
        e.preventDefault();
        var idUser = $(this).attr('data-id');
        $.ajax({
            url: 'user/bloquer',
            type: 'get',
            data: 'idUser=' + idUser,
            success: function(response) {
                $.notify(response.msg, "success");
                setTimeout(function() {
                    location.reload();
                }, 1700);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });
    $('#updateUserForm').on('click', '.debloquerUser', function(e) {
        e.preventDefault();
        var idUser = $(this).attr('data-id');
        $.ajax({
            url: 'user/debloquer',
            type: 'get',
            data: 'idUser=' + idUser,
            success: function(response) {
                $.notify(response.msg, "success");
                setTimeout(function() {
                    location.reload();
                }, 1700);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });


    //Bloquer un utilisateur

    $('#table_utilisateurs').on('click', '.debloquerUser', function(e) {
        e.preventDefault();
        var idUser = $(this).attr('data-id');
        $.ajax({
            url: 'user/debloquer',
            type: 'get',
            data: 'idUser=' + idUser,
            success: function(response) {
                $.notify(response.msg, "success");
                setTimeout(function() {
                    location.reload();
                }, 1700);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });




//    ROLE TABLE
    $('#table_roles').dataTable({
        responsive: true,
        "aaSorting": [[0, "asc"]],
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Roles par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ sur _PAGES_",
            sInfoFiltered: "(Filtrés sur _MAX_ roles)",
            sInfoEmpty: "Aucun role trouvé",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });




//    ADD ROLE

    $('#submitRoleForm').submit(function(e) {
        var frm = $('#submitRoleForm');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    hideModal('CreateRole');
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant l'ajout du role", "error");
            }
        });
    });

    $('#submitRoleFormUpdate').submit(function(e) {
        var frm = $('#submitRoleFormUpdate');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification du role", "error");
            }
        });
    });

    //DELETE ROLE
    function deleteRoleById(idRole) {

        $.ajax({
            url: 'role/deleteRole?idRole=' + idRole,
            type: 'delete',
            success: function(response) {
                if (response.resultat === 200) {

                    $('#' + idRole).remove();
                    $.notify(response.msg, "success");
                } else {

                    $.notify(response.msg, "warn");
                }
            }, error: function(response) {

                $.notify(response.msg, "error");
            }
        });
    }

    //CONFIRMATION DELETE ROLE

    $('#table_roles').on('click', '.deleteRole', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteRoleConfirmation').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeleteRoleConfirmation', function() {
                    $('#deleteRoleConfirmation').hide();
                    deleteRoleById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });



    //Update utilisateur
    $('#updateUserForm').submit(function(e) {
        var frm = $('#updateUserForm');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification du user", "error");
//                 $.notify("Une erreur est survenue pendant la modification du user", "error");

            }
        });
    });
    //Code de chargement d'un role selectionné sur la liste des roles
    $('#table_roles').on('click', '.showRole', function(e) {
        e.preventDefault();
        $('#roleEditSuccess').css('display', 'none');
        $('#roleEditFailed').css('display', 'none');
        var idRole = $(this).attr('data-id');
        $('#viewRoleModal').modal();
        $.ajax({
            url: 'getRole',
            type: 'get',
            data: 'idRole=' + idRole,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editDescRole').val(response.roleDesc);
                $('#editNomRole').val(response.nameRole);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });

    //Partie pour la modification d'un role
    $('#editRoleFormulaire').submit(function(e) {
        var frm = $('#editRoleFormulaire');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $('#roleEditSuccess').html(callback.msg);
                    hideModal('viewRoleModal');
                    //$('#viewUserModal').modal('hide');  // masquer le modal après modification du user
                    // masquer le modal
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1500);
                }

                else {
                    $.notify(callback.msg, "warn");

                }
            },
            error: function(callback) {
                $('#userMessageFailed').html("Une erreur est survenue pendant la modification ");
                $('#userMessageFailed').css('display', 'block');
                $.notify("Une erreur est survenue pendant la modification " + callback.msg, "error");
            }
        });
    });

    //Update utilisateur
    $('#forgotPassword').submit(function(e) {
        var frm = $('#forgotPassword');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        $(location).attr("href", "/gesimmo");
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification du user", "error");
//                 $.notify("Une erreur est survenue pendant la modification du user", "error");

            }
        });
    });

    //    PROJET TABLE
    $('#table_projets').dataTable({
        responsive: true,
        "aaSorting": [[0, "asc"]],
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Projets par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ sur _PAGES_",
            sInfoFiltered: "(Filtrés sur _MAX_ projets)",
            sInfoEmpty: "Aucun projet trouvé",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });

    // Update projet
    $('#editProjetFormulaire').submit(function(e) {
        var frm = $('#editProjetFormulaire');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification du projet", "error");
            }
        });
    });

    // ADD PROJET   
    $('#submitProjetForm').submit(function(e) {
        var frm = $('#submitProjetForm');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant l'ajout du projet", "error");
            }
        });
    });


    //Code de chargement d'un projet selectionné sur la liste des projets
    $('#table_projets').on('click', '.showProjet', function(e) {

        e.preventDefault();
        $('#projetEditSuccess').css('display', 'none');
        $('#projetEditFailed').css('display', 'none');
        var idProjet = $(this).attr('data-id');
        $('#viewProjetModal').modal();
        $.ajax({
            url: 'getProjet',
            type: 'get',
            data: 'idActivite=' + idProjet,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editDescProjet').val(response.description);
                $('#editNomProjet').val(response.nomActivite);
                $('#editDateDebPrevuProjet').val(response.dateDebPrevu);
                $('#editDateFinPrevuProjet').val(response.dateFinPrevu);

                $('#editBudgetPrevuProjet').val(response.budgetPrevu);
                $('#editNbreEmploiPrevuProjet').val(response.nbreEmploiPrevu);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });

    //Code de chargement d'un projet selectionné sur la fiche du projet
    $('.showProjet').on('click', function(e) {

        e.preventDefault();
        $('#projetEditSuccess').css('display', 'none');
        $('#projetEditFailed').css('display', 'none');
        var idProjet = $(this).attr('data-id');
        $('#viewProjetModal').modal();
        $.ajax({
            url: 'getProjet',
            type: 'get',
            data: 'idActivite=' + idProjet,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editDescProjet').val(response.description);
                $('#editNomProjet').val(response.nomActivite);
                $('#editDateDebPrevuProjet').val(response.dateDebPrevu);
                $('#editDateFinPrevuProjet').val(response.dateFinPrevu);

                $('#editBudgetPrevuProjet').val(response.budgetPrevu);
                $('#editNbreEmploiPrevuProjet').val(response.nbreEmploiPrevu);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });

    //DELETE PROJET
    function deleteProjetById(idProjet) {

        $.ajax({
            url: 'projet/deleteProjet?idActivite=' + idProjet,
            type: 'delete',
            success: function(response) {
                if (response.resultat === 200) {

                    $('#' + idProjet).remove();
                    $.notify(response.msg, "success");
                } else {

                    $.notify(response.msg, "warn");
                }
            }, error: function(response) {

                $.notify(response.msg, "error");
            }
        });
    }

    //CONFIRMATION DELETE PROJET

    $('#table_projets').on('click', '.deleteProjet', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteProjetConfirmation').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeleteProjetConfirmation', function() {
                    $('#deleteProjetConfirmation').hide();
                    deleteProjetById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });

    // RECHERCHE PROJET   
    $('#submitProjetFormRecherche').submit(function(e) {
        var frm = $('#submitProjetFormRecherche');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1500);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la recherche d'un projet", "error");
            }
        });
    });

    //    PROJET TABLE RECHERCHE
    $('#table_projets_recherche').dataTable({
        responsive: true,
        "aaSorting": [[0, "asc"]],
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Projets par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ sur _PAGES_",
            sInfoFiltered: "(Filtrés sur _MAX_ projets)",
            sInfoEmpty: "Aucun projet trouvé",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });

    //Localisation Table
    $('#table_localisations').dataTable({
        responsive: true,
        "aaSorting": [[0, "asc"]],
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Objet localisable par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ sur _PAGES_",
            sInfoFiltered: "(Filtrés sur _MAX_ utilisateurs)",
            sInfoEmpty: "Aucun Objet localisable trouvé",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });
    //Code de chargement d'un objet localisable selectionné sur la liste des objets localisables
    $('#table_localisations').on('click', '.showLocalisation', function(e) {
        e.preventDefault();
        var idLocalisation = $(this).attr('data-id');
        var dtype = $(this).attr('data-dtype');
                $('#viewLocalisationModal').modal();
        $.ajax({
            url: 'getLocalisation',
            type: 'get',
            data: 'idLocalisation=' + idLocalisation + '&dtype=' + dtype,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editNomLocalisationInput').val(response.nom);
                $('#editDescLocalisationInput').val(response.description);
                $('#editLongLocalisationInput').val(response.longitude);
                $('#editLatLocalisationInput').val(response.latitude);
                $('#editTypeLocalisationInput').html('');
                var tab = (response.typeIncidentOuLocalite);
                var i=0;
                for (var typ in tab) {
                    $('#editTypeLocalisationInput').append('<option value="' + tab[i] + '" >' + tab[i] + '</option>');
                    i++;
                }
                $('#editTypeLocalisationInput').val(response.type);
                $('#editSelectRattachement').val(response.rattachement);
                $('#editGraviteLocalisation').val(response.gravite);
                if(dtype == "LOCALITE"){
                    $(".editGraviteLocalisation").hide();
                }
                if(dtype == "INCIDENT"){
                    $(".editGraviteLocalisation").show();
                }
                
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });

    //Update objet localisable
    $('#editLocalisationFormulaire').submit(function(e) {
        var frm = $('#editLocalisationFormulaire');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification de l'objet localisable", "error");

            }
        });
    });


    $('#affecterResponsableLocalite').submit(function(e) {
        var frm = $('#affecterResponsableLocalite');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification de l'objet localisable", "error");

            }
        });
    });

    //add objet localisable
    $(document).on('submit', '#addLocalisationFormulaire', function(e) {
        var frm = $('#addLocalisationFormulaire');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant l'ajout de l'objet localisable", "error");

            }
        });
    });
    function deleteLocalisationById(idLocalisation) {

        $.ajax({
            url: 'localisation/deleteLocalisation?idLocalisation=' + idLocalisation,
            type: 'delete',
            success: function(response) {
                if (response.resultat === 200) {
                    setTimeout(function() {
                        location.reload();
                    }, 1000);
                    //$('#confirmationContent').html(response.msg);
                    $('#' + idLocalisation).remove();
                    //$('#confirmationModal').modal({backdrop: 'static', keyboard: false});
                    $.notify(response.msg, "success");
                } else {

                    //$('#confirmationContent').html(response.msg);
                    //$('#confirmationModal').modal({backdrop: 'static', keyboard: false});
                    $.notify(response.msg, "warn");
                }
            }, error: function(response) {

                //$('#confirmationContent').html(response.msg);
                //$('#confirmationModal').modal({backdrop: 'static', keyboard: false});
                $.notify(response.msg, "error");
            }
        });
    }
    $('#table_localisations').on('click', '.deleteLocalisation', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteLocConfirmationModal').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeleteLocModalButton', function() {
                    $('#deleteLocConfirmationModal').hide();
                    deleteLocalisationById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });

    var libIndicateurSelected = '';
    $('.editSelectIndicateur').change(function() {
        libIndicateurSelected = $.trim($(this).val());
        getParamIndicateurByLiblle(libIndicateurSelected);
    });
//add valeur indicateur a un objet localisable 
    $('#addIndicateurLocalisationForm').submit(function(e) {
        var frm = $('#addIndicateurLocalisationForm');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue", "error");

            }
        });
    });

    //    ETAPE TABLE
    $('#table_etapes').dataTable({
        responsive: true,
        "aaSorting": [[0, "asc"]],
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Etapes par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ sur _PAGES_",
            sInfoFiltered: "(Filtrés sur _MAX_ projets)",
            sInfoEmpty: "Aucun Etape trouvée",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });

// Update Etape
    $('#editEtapeFormulaire').submit(function(e) {
        var frm = $('#editEtapeFormulaire');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification de l'étape", "error");
            }
        });
    });

    // ADD ETAPE   
    $('#submitEtapeForm').submit(function(e) {
        var frm = $('#submitEtapeForm');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant l'ajout de l'étape", "error");
            }
        });
    });


    //Code de chargement d'une étape selectionnée sur la liste des étapes
    $('#table_etapes').on('click', '.showEtape', function(e) {

        e.preventDefault();
        $('#etapeEditSuccess').css('display', 'none');
        $('#etapeEditFailed').css('display', 'none');
        var idEtape = $(this).attr('data-id');
        $('#viewEtapeModal').modal();
        $.ajax({
            url: 'getEtape',
            type: 'get',
            data: 'idActivite=' + idEtape,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editDescEtape').val(response.description);
                $('#editNomEtape').val(response.nomActivite);
                $('#editDateDebPrevuEtape').val(response.dateDebPrevu);
                $('#editDateFinPrevuEtape').val(response.dateFinPrevu);
                $('#editBudgetPrevuEtape').val(response.budgetPrevu);
                $('#editNbreEmploiPrevuEtape').val(response.nbreEmploiPrevu);
                $('#editPonderationEtape').val(response.ponderation);
                $('#editTauxRealisationEtape').val(response.tauxRealisation);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });

    //Code de chargement d'un étape selectionnée sur la fiche d'une étape
    $('.showEtape').on('click', function(e) {

        e.preventDefault();
        $('#etapeEditSuccess').css('display', 'none');
        $('#etapeEditFailed').css('display', 'none');
        var idEtape = $(this).attr('data-id');
        $('#viewEtapeModal').modal();
        $.ajax({
            url: 'getEtape',
            type: 'get',
            data: 'idActivite=' + idEtape,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editDescEtape').val(response.description);
                $('#editNomEtape').val(response.nomActivite);
                $('#editDateDebPrevuEtape').val(response.dateDebPrevu);
                $('#editDateFinPrevuEtape').val(response.dateFinPrevu);
                $('#editPonderationEtape').val(response.ponderation);
                $('#editTauxRealisationEtape').val(response.tauxRealisation);
                $('#editBudgetPrevuEtape').val(response.budgetPrevu);
                $('#editNbreEmploiPrevuEtape').val(response.nbreEmploiPrevu);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });

    //DELETE ETAPE
    function deleteEtapeById(idEtape) {

        $.ajax({
            url: 'etape/deleteEtape?idActivite=' + idEtape,
            type: 'delete',
            success: function(response) {
                if (response.resultat === 200) {

                    $('#' + idEtape).remove();
                    $.notify(response.msg, "success");
                } else {

                    $.notify(response.msg, "warn");
                }
            }, error: function(response) {

                $.notify(response.msg, "error");
            }
        });
    }

    //CONFIRMATION DELETE ETAPE

    $('#table_etapes').on('click', '.deleteEtape', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteEtapeConfirmation').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeleteEtapeConfirmation', function() {
                    $('#deleteEtapeConfirmation').hide();
                    deleteEtapeById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });

    $('#list_etapes').on('click', '.deleteEtape', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteEtapeConfirmation').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeleteEtapeConfirmation', function() {
                    $('#deleteEtapeConfirmation').hide();
                    deleteEtapeById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });


    // ADD PROGRAMME   
    $('#submitProgrammeForm').submit(function(e) {
        var frm = $('#submitProgrammeForm');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant l'ajout du projet", "error");
            }
        });
    });


    // ADD PROJET   

    $('#submitJournalForm').submit(function(e) {
        var frm = $('#submitJournalForm');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant l'ajout du projet", "error");
            }
        });
    });


    //    PROJET PROGRAMME
    $('#table_programmes').dataTable({
        responsive: true,
        "aaSorting": [[0, "asc"]],
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Projets par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ sur _PAGES_",
            sInfoFiltered: "(Filtrés sur _MAX_ projets)",
            sInfoEmpty: "Aucun projet trouvé",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });


    //DELETE programme
    function deleteProgrammeById(idProgramme) {

        $.ajax({
            url: 'programme/deleteProgramme?idActivite=' + idProgramme,
            type: 'delete',
            success: function(response) {
                if (response.resultat === 200) {

                    $('#' + idProgramme).remove();
                    $.notify(response.msg, "success");
                } else {

                    $.notify(response.msg, "warn");
                }
            }, error: function(response) {

                $.notify(response.msg, "error");
            }
        });
    }

    //CONFIRMATION DELETE PROGRAMME

    $('#table_programmes').on('click', '.deleteProgramme', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteProgrammeConfirmation').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeleteProgrammeConfirmation', function() {
                    $('#deleteProgrammeConfirmation').hide();
                    deleteProgrammeById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });


    //    INDICATEUR TABLE RECHERCHE
    $('#table_indicateurs').dataTable({
        responsive: true,
        "aaSorting": [[0, "asc"]],
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Indicateurs par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ sur _PAGES_",
            sInfoFiltered: "(Filtrés sur _MAX_ projets)",
            sInfoEmpty: "Aucun projet trouvé",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });

    //DELETE INDICATEUR
    function deleteIndicateurById(id) {
        $.ajax({
            url: 'indicateur/deleteIndicateur?id=' + id,
            type: 'delete',
            success: function(response) {
                if (response.resultat === 200) {
                    $('#' + id).remove();
                    $.notify(response.msg, "success");
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);

                } else {

                    $.notify(response.msg, "warn");
                }
            }, error: function(response) {

                $.notify(response.msg, "error");
            }
        });
    }



    //Code de chargement d'un journal selectionné sur la liste des journal
    $('#listJournal').on('click', '.showJournal', function(e) {

        e.preventDefault();
        var idJournal = $(this).attr('data-id');
        $('#viewJournalModal').modal();
        $.ajax({
            url: 'getJournalProgramme',
            type: 'get',
            data: 'idJournal=' + idJournal,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editDescJournal').val(response.description);
                $('#editLibelleJournal').val(response.libelle);
                $('#editIdJournal').val(idJournal);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });

    // Update projet
    $('#editJournalFormulaire').submit(function(e) {
        var frm = $('#editJournalFormulaire');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification du projet", "error");
            }
        });
    });






    //CONFIRMATION DELETE INDICATEUR

    $('#table_indicateurs').on('click', '.deleteIndicateur', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteIndConfirmationModal').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeleteIndModalButton', function() {
                    $('#deleteIndConfirmationModal').hide();
                    deleteIndicateurById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });


    //    ADD INDICATEUR

    $('#submitIndicateurForm').submit(function(e) {
        var frm = $('#submitIndicateurForm');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    hideModal('CreateIndicateur');
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant l'ajout de l'indicateur", "error");
            }
        });
    });



    //Code de chargement d'un programme selectionné sur la liste des programmess
    $('#table_programmes').on('click', '.showProgramme', function(e) {

        e.preventDefault();
        $('#programmeEditSuccess').css('display', 'none');
        $('#programmeEditFailed').css('display', 'none');
        var idProgramme = $(this).attr('data-id');
        $('#viewProgrammeModal').modal();
        $.ajax({
            url: 'getProgramme',
            type: 'get',
            data: 'idActivite=' + idProgramme,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editDescProgramme').val(response.description);
                $('#editNomProgramme').val(response.nomActivite);
                $('#editDateDebPrevuProgramme').val(response.dateDebPrevu);
                $('#editDateFinPrevuProgramme').val(response.dateFinPrevu);

                $('#editBudgetPrevuProgramme').val(response.budgetPrevu);
                $('#editNbreEmploiPrevuProgramme').val(response.nbreEmploiPrevu);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });

    // Update programme
    $('#editProgrammeFormulaire').submit(function(e) {
        var frm = $('#editProgrammeFormulaire');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification du projet", "error");
            }
        });
    });


    //CONFIRMATION DELETE JOURNAL

    $('#listJournal').on('click', '.deleteJournal', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteJournalConfirmation').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeleteJournalConfirmation', function() {
                    $('#deleteJournalConfirmation').hide();
                    deleteJournalById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });

    //DELETE JOURNAL
    function deleteJournalById(idJournal) {

        $.ajax({
            url: '/gesimmo/deleteJournal?idJournal=' + idJournal,
            type: 'delete',
            success: function(response) {
                if (response.resultat === 200) {

                    $('#' + idJournal).remove();
                    $.notify(response.msg, "success");
                } else {

                    $.notify(response.msg, "warn");
                }
            }, error: function(response) {

                $.notify(response.msg, "error");
            }
        });
    }


    //Code de chargement d'un valeur indicateur  
    $('#listindicateurLocalisation').on('click', '.showValueIndicateur', function(e) {

        e.preventDefault();
        var idIndicateurLocalisation = $(this).attr('data-id');
        $('#viewValueIndicateurModal').modal();
        $.ajax({
            url: 'getIndicateurLocalisation',
            type: 'get',
            data: 'idIndicateurLocalisation=' + idIndicateurLocalisation,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editValeurIndicateur').val(response.valeur);
                $('#editId').val(idIndicateurLocalisation);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });

    // Update valeur indicateur
    $('#editValueIndicateurFormulaire').submit(function(e) {
        var frm = $('#editValueIndicateurFormulaire');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification du projet", "error");
            }
        });
    });

    //affectation projets a une localite 
    $('#affecterProjetLocalite').submit(function(e) {
        var frm = $('#affecterProjetLocalite');

        e.preventDefault();

        var data = {nom: "nom", description: "desc", type: "type", idLocalisation: document.getElementById('addProjetLocalisationInput').value};
        //Gather Data also remove undefined keys(buttons)
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue", "error");

            }
        });
    });



    //Code de chargement d'un indicateur selectionné sur la liste des objets localisables
    $('#table_indicateurs').on('click', '.showIndicateur', function(e) {
        e.preventDefault();
        var id = $(this).attr('data-id');
        $('#viewIdicateurModal').modal();
        $.ajax({
            url: 'getIndicateur',
            type: 'get',
            data: 'id=' + id,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editNomIndicateur').val(response.nomIndicateur);
                $('#editDescIndicateur').val(response.libelleIndicateur);
                $('#editUniteIndicateur').val(response.uniteIndicateur);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });

    //CONFIRMATION DELETE IndicateurLocalisation
    $('#listindicateurLocalisation').on('click', '.deleteIndicateurLocalisation', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteIndicateurLocalisationConfirmation').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeleteIndicateurLocalisationConfirmation', function() {
                    $('#deleteIndicateurLocalisationConfirmation').hide();
                    deleteIndicateurLocalisationById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });

    //DELETE IndicateurLocalisation
    function deleteIndicateurLocalisationById(idIndicateurLocalisation) {

        $.ajax({
            url: '/gesimmo/deleteIndicateurLocalisation?idIndicateurLocalisation=' + idIndicateurLocalisation,
            type: 'get',
            success: function(response) {
                if (response.resultat === 200) {

                    $('#' + idIndicateurLocalisation).remove();
                    $.notify(response.msg, "success");
                } else {

                    $.notify(response.msg, "warn");
                }
            }, error: function(response) {

                $.notify(response.msg, "error");
            }
        });
    }


    //Update objet localisable
    $('#editIndicateurFormulaire').submit(function(e) {
        var frm = $('#editIndicateurFormulaire');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant la modification de l'indicateur", "error");

            }
        });
    });

    //Recherche dans liste localite
    $('#menuLocalisation').load(encodeURI('localisationListFilter/load/localite/categorie?categorie=LOCALITE'));
    $(document).on('change', '#selectDtypeRecherche', function() {
        var dTypteSelected = $.trim($(this).val());
        var contextPath = window.location.pathname;
        $('#menuLocalisation').load(encodeURI('localisationListFilter/load/localite/categorie?categorie=' + dTypteSelected));
    });

    //affceter projet a un programme  
    $('#affectProjettoProgrammmeFormulaire').submit(function(e) {
        var frm = $('#affectProjettoProgrammmeFormulaire');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $.notify(callback.msg, "success");
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant l'affectation du projet au programme", "error");

            }
        });
    });
    
    //    ADD USERACTIVITE

    $('#affecterProjetPersonneForm').submit(function(e) {
        var frm = $('#affecterProjetPersonneForm');
        e.preventDefault();

        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {

                    $.notify(callback.msg, "success");
                    hideModal('AffecterProjetPersonne');
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                } else {
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $.notify("Une erreur est survenue pendant l'affectation", "error");
            }
        });
    });
});




//Partie du modal edit  projet
//
//1:Information
// 2:Dates
//3:Budget
//4:Emplois
//
//======================================================================================================================================================================================
//Fonction qui cache les partie du modal qui sont pas concerne par la modification
function cacherDiv(idDiv) {

    for (var i = 1; i <= 4; i++) {
        $('#' + i).show();
    }

    for (var i = 1; i <= 4; i++) {
        if (i !== idDiv) {
            $('#' + i).hide();
        }
    }

}

//Fonction qui modifie une partie des info du projet 
function modifier(idDiv) {

    $('.btnModifierPartieProjet').on('click', function(e) {

        e.preventDefault();
        $('#projetEditSuccess').css('display', 'none');
        $('#projetEditFailed').css('display', 'none');
        var idProjet = $(this).attr('data-id');
        cacherDiv(idDiv);

        $('#viewProjetModal').modal();
        $.ajax({
            url: 'getProjet',
            type: 'get',
            data: 'idActivite=' + idProjet,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editDescProjet').val(response.description);
                $('#editNomProjet').val(response.nomActivite);
                $('#editDateDebPrevuProjet').val(response.dateDebPrevu);
                $('#editDateFinPrevuProjet').val(response.dateFinPrevu);

                $('#editBudgetPrevuProjet').val(response.budgetPrevu);
                $('#editNbreEmploiPrevuProjet').val(response.nbreEmploiPrevu);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });
}



//Partie du modal edit  etape
//
//1:Information
//2:Dates
//3:Budget
//4:Emplois
//5:Taux
//
//
//Fonction qui cache les partie du modal qui sont pas concerne par la modification etape
function cacherDivEtape(idDiv) {

    for (var i = 1; i <= 6; i++) {
        $('#' + i).show();
    }

    for (var i = 1; i <= 6; i++) {
        if (i !== idDiv) {
            $('#' + i).hide();
        }
    }

}
//Fonction qui modifie une partie des info de l'etape 
function modifierEtape(idDiv) {

    //Code de chargement d'un étape selectionnée sur la fiche d'une étape
    $('.btnModifierPartieEtape').on('click', function(e) {

        e.preventDefault();
        $('#etapeEditSuccess').css('display', 'none');
        $('#etapeEditFailed').css('display', 'none');
        var idEtape = $(this).attr('data-id');
        cacherDivEtape(idDiv);

        $('#viewEtapeModal').modal();
        $.ajax({
            url: 'getEtape',
            type: 'get',
            data: 'idActivite=' + idEtape,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editDescEtape').val(response.description);
                $('#editNomEtape').val(response.nomActivite);
                $('#editDateDebPrevuEtape').val(response.dateDebPrevu);
                $('#editDateFinPrevuEtape').val(response.dateFinPrevu);
                $('#editPonderationEtape').val(response.ponderation);
                $('#editTauxRealisationEtape').val(response.tauxRealisation);
                $('#editBudgetPrevuEtape').val(response.budgetPrevu);
                $('#editNbreEmploiPrevuEtape').val(response.nbreEmploiPrevu);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });
}

//Recuperation des parametre des iondicateurs

function getParamIndicateurByLiblle(nomIndicateur) {

    $.ajax({
        type: "GET",
        url: "getParamIndicateur",
        data: 'nomIndicateur=' + nomIndicateur,
        success: function(params) {
            $('#chargerParametres').html('');

            $('#chargerParametres').append('<label class="pull-left" >' + params.nomIndicateur + '( ' + params.uniteIndicateur + ' ):</label>' +
                    '<input  class="form-control" name = "valeur" required="required" placeholder="la valeur de l indicateur">');
        }
    });
}

