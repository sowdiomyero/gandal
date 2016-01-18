var routeController = {
    user : {
      deleteUserById : function(idUser){
          $.ajax({
            url: getContextPath()+'/user/deleteUser?idUser=' + idUser,
            type: 'delete',
            success: function(response) {
                if (response.resultat === 200) {
                    setTimeout(function() {
                        location.reload();
                    }, 1000);
                    $('#' + idUser).remove();
                    $.notify(response.msg, "success");
                } else {
                    $.notify(response.msg, "warn");
                }
            }, error: function(response) {
                $.notify(response.msg, "error");
            }
        });
      },
      
      getUserById : function (idUser, callback){
          
          $.ajax({
            url: getContextPath()+'/user/getUser',
            type: 'get',
            data: 'idUser=' + idUser,
            success: callback,
            error: function(err) {
                alert('Une erreur est survenu pendant le traitement de votre requete');
            }
        });
      }
    },
    localisation : {          
        loadFicheLocalisation : function(idElement, itemId){
            $(idElement).load(getContextPath() + "/localisation/" + itemId, function(responseText, textStatus, req){
                // callback function
                if (textStatus === "error") {
                    //creer un element avec un z-index haut pour afficher cette erreur de chargement
                }
            });
        },
        reloadFormAfterDtypeChanged : function(idElement, lng, lat, dtypeSelected, localiteName){
            $(idElement).load(encodeURI(getContextPath() + '/load/localite/type?lon=' + lng + '&lat=' + lat + '&add=' + localiteName + '&type=' + dtypeSelected));
        },
        
        reloadFormRechercheAfterDtypeChanged : function(idElement, dtypeSelected){
            $(idElement).load(encodeURI(getContextPath() + '/localisationListFilterCarte/load/localite/categorie?categorie=' + dtypeSelected));
        },
        filterCarteResultatRecherche : function(method, action, data, callback){
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
        getLocalisationByType : function(type, callback){
            $.ajax({
            url: getContextPath()+'/charger?type=' + type,
            type: 'get',
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
    routeController.localisation.loadFicheLocalisation("#localisationItemArea",itemId);
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
/*
 * Ne sert qu'à rendre un modal visible
 */
function showModal(elementId) {
     $('#'+elementId).modal();
}
/*
 * Chaque element input à inclure devra definir un attribut name
 */
function extractDataForm(frm){           
        var data = {};
        $.each(frm, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        
        return data;
 }



