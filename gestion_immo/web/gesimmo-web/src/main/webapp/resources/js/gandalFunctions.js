var routeController = {
    user: {
        deleteUserById: function(idUser) {
            $.ajax({
                url: getContextPath() + '/user/delete?idUser=' + idUser,
                type: 'delete',
                success: notifySuccessAndReload,
                error: function(response) {
                    $.notify(response.msg, "error");
                }
            });
        },
        blockUserById: function(idUser) {
            $.ajax({
                url: getContextPath() + '/user/api/bloquer?idUser=' + idUser,
                type: 'get',
                success: notifySuccessAndReload,
                error: function(response) {
                    $.notify(response.msg, "error");
                }
            });
        },
        unblockUserById: function(idUser) {
            $.ajax({
                url: getContextPath() + '/user/api/debloquer?idUser=' + idUser,
                type: 'get',
                success: notifySuccessAndReload,
                error: function(response) {
                    $.notify(response.msg, "error");
                }
            });
        },
        getUserById: function(idUser, callback) {
            $.ajax({
                url: getContextPath() + '/user/api/get',
                type: 'get',
                data: 'idUser=' + idUser,
                success: callback,
                error: function(err) {
                    alert('Une erreur est survenu pendant le traitement de votre requete');
                }
            });
        },
        viewProfil: function(login, callback) {
            $.ajax({
                url: getContextPath() + '/user/profil/view',
                type: 'get',
                data: 'login=' + login,
                success: callback,
                error: function(response) {
                    $.notify(response.msg, "error");
                }
            });
        }
    },
    role: {
        view : function(idRole, callback){
           $.ajax({
                url: getContextPath() + '/role/api/get',
                type: 'get',
                data :"idRole="+idRole,
                success: callback,
                error: function(response) {
                    $.notify(response.msg, "error");
                }
            }); 
        },
        
        delete : function(idRole){
           $.ajax({
                url: getContextPath() + '/role/delete?idRole='+idRole,
                type: 'delete',
                //data :"idRole="+idRole,
                success: notifySuccessAndReload,
                error: function(response) {
                    $.notify(response.msg, "error");
                }
            }); 
        }
    },
    localisation: {
        loadFicheLocalisation: function(idElement, itemId) {
            $(idElement).load(getContextPath() + "/localisation/view/" + itemId, function(responseText, textStatus, req) {
                // callback function
                if (textStatus === "error") {
                    //creer un element avec un z-index haut pour afficher cette erreur de chargement
                }
            });
        },
        reloadFormAfterDtypeChanged: function(idElement, lng, lat, dtypeSelected, localiteName) {
            $(idElement).load(encodeURI(getContextPath() + '/localisation/load/localite/type?lon=' + lng + '&lat=' + lat + '&add=' + localiteName + '&type=' + dtypeSelected), function(){               
                $("#cleLocalite").val(extractKey(localiteName,3," "));
                $("#nomLocaliteCorrige").focus();
            
            });
            
        },
        reloadFormRechercheAfterDtypeChanged: function(idElement, dtypeSelected) {
            $(idElement).load(encodeURI(getContextPath() + '/localisationListFilterCarte/load/localite/categorie?categorie=' + dtypeSelected));
        },
        filterCarteResultatRecherche: function(method, action, data, callback) {
            // pasencore defini 
            $.ajax({
                contentType: 'application/json; charset=utf-8',
                type: method,
                url: action,
                dataType: 'json',
                data: JSON.stringify(data),
                success: callback,
                error: function(response) {
                    alert("Une erreur est survenue pendant le chargement des coordonnees geo ....");
                }
            });
        },
        getLocalisationByType: function(type, callback) {
            $.ajax({
                url: getContextPath() + '/charger?type=' + type,
                type: 'get',
                success: callback,
                error: function(response) {
                    alert("Global Functions : Une erreur est survenue pendant le chargement des coordonnees geo ....");
                }
            });
        },
        update: function(data, frm) {            
           // var data = extractDataForm(frm);
            $.ajax({
                contentType: 'application/json; charset=utf-8',
                type: frm.attr('method'),
                url: frm.attr('action'),
                dataType: 'json',
                data: JSON.stringify(data),
                success: notifySuccessAndReload,
                error: function(response) {
                    alert("Global Functions : Une erreur est survenue pendant le chargement des coordonnees geo ....");
                }
            });
        },
        verifyKey: function(key, callback) {            
           // var data = extractDataForm(frm);
            $.ajax({
                contentType: 'application/json; charset=utf-8',
                type: 'GET',
                url: getContextPath() + '/localisation/verify/key/'+key,
                dataType: 'json',
                success: callback,
                error: function(response) {
                    alert("Global Functions : Une erreur est survenue pendant le chargement des coordonnees geo ....");
                }
            });
        }
    }
};
function loadLocalisationItem(itemId) {
    console.log(getContextPath());
    //$("#localisationItemArea").load(getContextPath() + "/localisation/" + itemId);
    routeController.localisation.loadFicheLocalisation("#localisationItemArea", itemId);
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
/*
 * Ne sert qu'à rendre un modal visible
 */
function showModal(elementId) {
    $('#' + elementId).modal();
}
/*
 * Chaque element input à inclure devra definir un attribut name
 */
function extractDataForm(frm) {
    var data = {};
    $.each(frm, function(i, v) {
        var input = $(v);
        data[input.attr("name")] = input.val();
        delete data["undefined"];
    });

    return data;
}

function notifySuccessAndReload(response) {
    if (response.resultat === 200) {        
        $.notify(response.msg, "success");
        setTimeout(function() {
            location.reload();
        }, 1000);
    } else {
        $.notify(response.msg, "warn");
    }
}

function extractKey(caractere, taille, delimiter){
    
    var chaine = caractere.split(delimiter);
        
        if(chaine.length > 1){
           var clef ="";
           for(var i=0; i < chaine.length; i++){
               clef += chaine[i].charAt(0).toUpperCase();
               if(i === taille-1) // le tableau commence par 0
                   return clef;
           }
          return clef; 
           
        }else{
            var res = chaine[0].substring(0,taille).toUpperCase();
            return res;
        }
}



