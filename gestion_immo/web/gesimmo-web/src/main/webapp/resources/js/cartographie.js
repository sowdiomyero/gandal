$(document).ready(function() {
    var lat;
    var lng;
    var add;        
    google.maps.event.addDomListener(window, 'load', initialize);
    
    function initialize() {
        addlocationsInMap("ALL");
    }

    function addlocationsInMap(type) {
        routeController.localisation.getLocalisationByType(type, remplirCarte);
    }

    function buttonControl(options) {
        var control = document.createElement('DIV');
        control.innerHTML = options.name;
        control.style.fontSize="medium";
        control.className = 'button';
        control.index = 1;

        // Add the control to the map
        options.gmap.controls[options.position].push(control);

        // When the button is clicked pan to sydney
        google.maps.event.addDomListener(control, 'click', options.action);
        return control;
    }

    function addMenus(map) {
        
        var buttonHomeOptions = {
            gmap: map,
            name: 'Toutes',
            position: google.maps.ControlPosition.TOP_CENTER,
            action: function() {
                addlocationsInMap("ALL");
                map.panTo(new google.maps.LatLng(14.627401,
                        -14.452361999999994));
            }
        };                    
        var buttonSiteOptions = {
            gmap: map,
            name: 'Sites',
            position: google.maps.ControlPosition.TOP_CENTER,
            action: function() {

                addlocationsInMap("SITE");
                map.panTo(new google.maps.LatLng(14.627401,
                        -14.452361999999994));
            }
        };
        // Vue Batiment
        var buttonBatimentOptions = {
            gmap: map,
            name: 'Batiments',
            position: google.maps.ControlPosition.TOP_CENTER,
            action: function() {
                addlocationsInMap("BATIMENT");
                map.panTo(new google.maps.LatLng(14.627401,-14.452361999999994));
                        
            }
        };
        var homeButton = new buttonControl(buttonHomeOptions);  
        var siteButton = new buttonControl(buttonSiteOptions);
        var batimentButton = new buttonControl(buttonBatimentOptions);
        
        addLegend(map);
    }

    function addLegend(map){
        map.controls[google.maps.ControlPosition.LEFT_TOP].push(
           document.getElementById('legend')); 
    }


    function remplirCarte(locations) {

        var latlng = new google.maps.LatLng(14.2739111,
                -13.6823329);

        var map = new google.maps.Map(document
                .getElementById('map-canvas'), {
            zoom: 7,
            title: "Title",
            center: new google.maps.LatLng(14.627401,
                    -14.452361999999994),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });


        //--------------------------------
        addMenus(map);
        var infowindow = new google.maps.InfoWindow();

        var marker, i;
        var contentString = [];

        for (i = 0; i < locations.length; i++) { //ficheLocalisation?idLocalisation=
            var linkLocalisationRef = window.location.pathname+'ficheLocalisation?idLocalisation='+ locations[i][4]+'&dtype='+ locations[i][6];
            var linkDemandes = window.location.pathname+'demandes?idLocalisation='+locations[i][4];
            var linkProjet = window.location.pathname+'projets?idLocalisation='+locations[i][4];
            var linkFicheLocaliteRef = window.location.pathname+'ficheLocalisation?idLocalisation='+ locations[i][4];
           
            contentString[i] = '<div id="content" style="min-width:160px; min-height:180px;">'
                    + '<div id="siteNotice">'
                    + '</div>'
                    + '<h3 id="firstHeading" class="firstHeading">'
                    + locations[i][6]
                    + ' : '
                    + locations[i][0]
                    + '</h3>'
                    + '<div  id="bodyContent">'
                    +
                    '<div>'
                    + '<label>Nom Responsable : '
                    + locations[i][5]
                    + ' </label>'
                    + '</div>'
                    + '<div>'
                    + '<label>Nombre d\'objets (id) : <span class="label label-danger">'
                    + locations[i][4]
                    + '</span> Batiments - Niveaux </label> '
                    + '</div>'
                    + '<div>'
                    + '<label>Nombre de Demandes : <span class="label label-info">'
                    + locations[i][4]
                    + '</span> Interventions </label> '
                    + '</div>'
                    + '<div>'
                    + '<label>Type : '
                    + locations[i][3]
                    + ' </label> '
                    + '</div>'
                    +
                    '<div>'
                    + '<a class="btn btn-default" href='
                    + linkLocalisationRef
                    + ' role="button">Fiche Localisation </a> '
                    +
                    '<a class="btn btn-default" href='
                    + linkProjet
                    + ' role="button">Liste des Projets</a>'
                    + '<a class="btn btn-default" href='
                    + linkDemandes
                    + ' role="button">Liste des Demandes</a>'
                    + '<a class="btn btn-default" href='
                    + linkFicheLocaliteRef
                    + ' role="button" style="margin-left:3px; padding-left:3px">Fiche Zone</a>'
                    + '</div>' + '</div>' +
                    '</div>' + '</div>';
            
            var markerColor = "http://maps.google.com/mapfiles/ms/icons/red-dot.png";
            if (locations[i][6] == 'BATIMENT') {
               // markerColor = "http://maps.google.com/mapfiles/ms/icons/green-dot.png";
                markerColor = window.location.pathname+"/img/markers/villa.png";
            } else if (locations[i][6] == 'SITE') { 
              //  markerColor = "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png";
              markerColor = window.location.pathname+"/img/markers/hotel_0star.png";
            }

            marker = new google.maps.Marker({
                position: new google.maps.LatLng(
                        locations[i][1], locations[i][2]),
                icon: markerColor,
                map: map
            });

            google.maps.event.addListener(
                            marker,
                           'click',
                           (function(marker, i) {
                                return function() {
                                    //appeler la fonction qui va construire le windowInfo depuis le serveur
                                    infowindow.setContent(contentString[i]);
                                    infowindow.open(map, marker);
                                }
                            })(marker, i));

        }


        var geocoder;
        geocoder = new google.maps.Geocoder();
        //var infowindow2 = new google.maps.InfoWindow();
        google.maps.event.addListener(map, 'click', function(e) {
            geocoder.geocode({'latLng': e.latLng}, function(results, status) {

                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[1]) {
                        map.setZoom(9);

                        console.info(results[0].formatted_address);

                        var loc = results[0].formatted_address;
                        var contextPath = window.location.pathname;
                        lat = e.latLng.lat();
                        lng = e.latLng.lng();
                        add = results[0].formatted_address;
                        var arr = loc.split(',');
                        var nomCorrige = arr[arr.length - 2];
                        $('#ficheLocalite').load(encodeURI(contextPath + 'load/localite?lon=' + lng + '&lat=' + lat + '&add=' + add));

                        $('#addLocalization').modal('show');
                    } else {
                        alert('No results found');
                    }
                } else {
                    alert('Geocoder failed due to: '
                            + status);
                }
            });

        });
    }

    $(document).on('change', '#selectDtype', function() {
        var dTypteSelected = $.trim($(this).val());        
        routeController.localisation.reloadFormAfterDtypeChanged('#ficheLocalite', lng, lat, dTypteSelected, add);    
    });

    $(document).on('change', '#selectDtypeRecherche', function() {
        var dTypteSelected = $.trim($(this).val());
        routeController.localisation.reloadFormAfterDtypeChanged('#panelRechercheLocalisationCarte',dTypteSelected); 
        //$('#panelRechercheLocalisationCarte').load(encodeURI('localisationListFilterCarte/load/localite/categorie?categorie=' + dTypteSelected));
    });

    $(document).on('submit', '#localisationFilterFormulaireCarte', function(e) {
        
       e.preventDefault();
       var frm = $('#localisationFilterFormulaireCarte');
       var data =extractDataForm(frm);
       routeController.localisation.filterCarteResultatRecherche(frm.attr('method'), frm.attr('action'), data, remplirCarte);

    });

 
});
 