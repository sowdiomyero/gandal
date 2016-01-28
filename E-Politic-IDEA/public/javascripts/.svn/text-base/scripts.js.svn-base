$(document)
		.ready(
				function() {

					google.maps.event
							.addDomListener(window, 'load', initialize);

					function initialize() {
						var locations = [];
						addlocationsInMap(null);
					}

					$('#selectbasic').selectize();
					$('#selectRattachement').selectize();
					$('#selectResponsable').selectize();

					$('.selectize').selectize();
					/*
					 * $("[name='my-checkbox']").bootstrapSwitch();
					 * $('#dimension-switch').bootstrapSwitch('setSizeClass',
					 * 'switch-mini');
					 */

					$("#foyersActifs").click(function() {
						addlocationsInMap("COMMUNE");
					});
					function addlocationsInMap(type) {

						var urlToCall = '';
						if (type === null)
							urlToCall = '/locations'
						else
							urlToCall = '/locations/' + type;

						$
								.ajax({
									url : urlToCall,
									type : 'get',
									success : function(response) {
										locations = response;

										remplirCarte(locations);

									},
									error : function(response) {

										alert("Une erreur est survenue pendant le chargement des coordonnees geo ....");
									}
								});
						return "";
					}
					function buttonControl(options) {
						var control = document.createElement('DIV');
						control.innerHTML = options.name;
						control.className = 'button';
						control.index = 1;

						// Add the control to the map
						options.gmap.controls[options.position].push(control);

						// When the button is clicked pan to sydney
						google.maps.event.addDomListener(control, 'click',
								options.action);
						return control;
					}

					function addMenus(map) {
						// Vue Section
						var buttonHomeOptions = {
							gmap : map,
							name : 'Quartiers',
							position : google.maps.ControlPosition.TOP_RIGHT,
							action : function() {

								addlocationsInMap("QUARTIER");
								map.panTo(new google.maps.LatLng(14.627401,
										-14.452361999999994));
							}
						}
						var homeButton = new buttonControl(buttonHomeOptions);

						// Vue Commune
						var buttonCommuneOptions = {
							gmap : map,
							name : 'Commune',
							position : google.maps.ControlPosition.TOP_RIGHT,
							action : function() {

								addlocationsInMap("COMMUNE");
								map.panTo(new google.maps.LatLng(14.627401,
										-14.452361999999994));
							}
						}
						var communeButton = new buttonControl(
								buttonCommuneOptions);

						// Vue Département
						var buttonHomeOptions = {
							gmap : map,
							name : 'Departement',
							position : google.maps.ControlPosition.TOP_RIGHT,
							action : function() {
								// var
								// markerColor="http://maps.google.com/mapfiles/ms/icons/yellow-dot.png";
								addlocationsInMap("DEPARTEMENT");
								map.panTo(new google.maps.LatLng(14.627401,
										-14.452361999999994));
							}
						}
						var homeButton = new buttonControl(buttonHomeOptions);

						// Vue Région
						var buttonHomeOptions = {
							gmap : map,
							name : 'Région',
							position : google.maps.ControlPosition.TOP_RIGHT,
							action : function() {

								addlocationsInMap("REGION");

								map.panTo(new google.maps.LatLng(14.627401,
										-14.452361999999994));
							}
						}
						var homeButton = new buttonControl(buttonHomeOptions);

						// Vue Région
						var buttonHomeOptions = {
							gmap : map,
							name : 'Toutes',
							position : google.maps.ControlPosition.TOP_RIGHT,
							action : function() {

								addlocationsInMap("ALL");

								map.panTo(new google.maps.LatLng(14.627401,
										-14.452361999999994));
							}
						}
						var homeButton = new buttonControl(buttonHomeOptions);
					}

					function remplirCarte(locations) {

						var latlng = new google.maps.LatLng(14.497401,
								-14.452361999999994);

						var map = new google.maps.Map(document
								.getElementById('map-canvas'), {
							zoom : 7,
							title : "Title",
							center : new google.maps.LatLng(14.627401,
									-14.452361999999994),
							mapTypeId : google.maps.MapTypeId.ROADMAP
						});
						/**
						 * CREER UN MENU POUR ALLER DIRECTEMENT VERS LA PAGE
						 * NOUS CONCERNANT
						 */

						addMenus(map);
						var infowindow = new google.maps.InfoWindow();

						var marker, i;
						var contentString = [];
						for (i = 0; i < locations.length; i++) {
							var linkMilitantsRef = '/membres/'
									+ locations[i][10];
							if (locations[i][10] == ''
									|| locations[i][10] == null)
								var linkMilitantsRef = '/membres';
							var status = '<span class="label label-default">Hors Ligne</span>';
							if (locations[i][12] == 'ONLINE'
									|| locations[i][12] == "En Ligne")
								status = '<span class="label label-success">En Ligne</span>';

							contentString[i] = '<div id="content" style="min-width:160px; min-height:180px;">'
									+ '<div id="siteNotice">'
									+ '</div>'
									+ '<h3 id="firstHeading" class="firstHeading">'
									+ locations[i][4]
									+ ' : '
									+ locations[i][0]
									+ '</h3>'
									+ '<div  id="bodyContent">'
									+

									'<div>'
									+ '<label>Nom Responsable : '
									+ locations[i][6]
									+ ' </label>'
									+ status
									+ '</div>'
									+ '<div>'
									+ '<label>Population : <span class="label label-danger">'
									+ locations[i][9]
									+ '</span> Habitants </label> '
									+ '</div>'
									+ '<div>'
									+ '<label>Nombre Militants : <span class="label label-info">'
									+ locations[i][11]
									+ '</span> Militants </label> '
									+ '</div>'
									+ '<div>'
									+ '<label>Nombre Sections : '
									+ locations[i][8]
									+ ' Sections</label> '
									+ '</div>'
									+

									'<div>'
									+ '<a class="btn btn-default" href='
									+ linkMilitantsRef
									+ ' role="button">Liste Des Militants </a> '
									+

									'<a class="btn btn-default" href="#" role="button">Fiche Responsable</a>'
									+ '<a class="btn btn-default" href="#" role="button" style="margin-left:3px; padding-left:3px">Fiche Zone</a>'
									+ '</div>' + '</div>' +

									'</div>' + '</div>';
							var markerColor = "http://maps.google.com/mapfiles/ms/icons/red-dot.png";
							if (locations[i][4] == 'REGION') {
								markerColor = "http://maps.google.com/mapfiles/ms/icons/green-dot.png";
							} else if (locations[i][4] == 'DEPARTEMENT') {
								markerColor = "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png";
							}

							marker = new google.maps.Marker({
								position : new google.maps.LatLng(
										locations[i][1], locations[i][2]),
								icon : markerColor,
								map : map
							});

							google.maps.event
									.addListener(
											marker,
											'click',
											(function(marker, i) {
												return function() {
													infowindow
															.setContent(contentString[i]);
													infowindow
															.open(map, marker);
												}
											})(marker, i));

						}

						var geocoder;
						geocoder = new google.maps.Geocoder();
						var infowindow2 = new google.maps.InfoWindow();
						google.maps.event
								.addListener(
										map,
										'click',
										function(e) {

											geocoder
													.geocode(
															{
																'latLng' : e.latLng
															},
															function(results,
																	status) {
																if (status == google.maps.GeocoderStatus.OK) {
																	if (results[1]) {
																		map
																				.setZoom(9);
																		/*
																		 * marker =
																		 * new
																		 * google.maps.Marker({
																		 * position:
																		 * e.latLng,
																		 * map:
																		 * map
																		 * });
																		 */
																		console
																				.info(results[0].formatted_address);
																		// infowindow2.setContent(results[0].formatted_address);
																		// infowindow2.open(map,
																		// marker);
																		var loc = results[0].formatted_address;
																		$(
																				'#nomLocaliteGoogle')
																				.val(
																						results[0].formatted_address);
																		var arr = loc
																				.split(',');
																		$(
																				'#nomLocaliteCorrige')
																				.val(
																						arr[arr.length - 2]);

																		$(
																				'#gps')
																				.val(
																						e.latLng);
																		$(
																				'#addLocalization')
																				.modal(
																						'show');
																	} else {
																		alert('No results found');
																	}
																} else {
																	alert('Geocoder failed due to: '
																			+ status);
																}
															});
											// alert("Coordonnées Géo : "+
											// e.latLng);
										});

					}

					$('#btnSearch')
							.click(
									function(e) {
										var frm = $('#searchForm');
										e.preventDefault();
										$
												.ajax({
													// contentType:
													// 'application/json;
													// charset=utf-8',
													type : frm.attr('method'),
													url : frm.attr('action'),
													// dataType: 'json',
													data : frm.serialize(),
													success : function(callback) {
														remplirCarte(callback);
													},
													error : function() {
														alert("Erreur pendnt le chargement des données")
													}
												});
									});

					$('#btnSendSms')
							.click(
									function(e) {
										var frm = $('#searchForm');
										e.preventDefault();
										$
												.ajax({
													// contentType:
													// 'application/json;
													// charset=utf-8',
													type : frm.attr('method'),
													url : frm.attr('action'),
													// dataType: 'json',
													data : frm.serialize(),
													success : function(callback) {
														$
																.notify(
																		"Message envoyés aux membres concernés",
																		"success");
													},
													error : function() {
														$
																.notify(
																		"Une erreur est survenue pendant l'envoie du message",
																		"error");
													}
												});
									});

			
					/*$('#saveLocalite')
							.click(
									function(e) {
										var frm = $('#addLocaliteForm');

										$
												.ajax({
													// contentType:
													// 'application/json;
													// charset=utf-8',
													type : frm.attr('method'),
													url : frm.attr('action'),
													// dataType: 'json',
													data : frm.serialize(),
													success : function(callback) {

														remplirCarte(callback);
														$('#addLocalization')
																.modal('hide');
													},
													error : function() {
														alert("Erreur pendnt le chargement des données")
													}
												});
									});*/

                  
					$('#saveLocalite').click(
							function(e) {
															
								var frm = $('#addLocaliteForm');
							
								e.preventDefault();
								$.ajax({
  									type : frm.attr('method'),
									url : frm.attr('action'),
 									data : frm.serialize(),
									success : function(callback) {
										if(callback.codeRetour === 200){
											$.notify(callback.message,
											"success");
											
											$('#addLocalization').modal('hide');
											setTimeout(function(){
												location.reload();
												},500);
										}
										else{
											$.notify(callback.message,
											"error");
											
										}
										
									},
									error : function() {
										$.notify("Une erreur s'est produite lors de la modification.", "error");
									}
								});
							});

					$('#members_table').dataTable({
						responsive : true,

						"aaSorting" : [ [ 0, "asc" ] ],

						oLanguage : {
							sLengthMenu : "Afficher: _MENU_ Membres par page ",
							sSearch : "Rechercher : ",
							sZeroRecords : "Aucune valeur trouvee !!",
							sInfo : "Afficher page _PAGE_ de _PAGES_",
							sInfoFiltered: "(Filtrés sur _MAX_ membres)",
							sInfoEmpty: "Aucun membre trouvé",
							oPaginate : {
								sFirst : "Premier",
								sPrevious : "Précédent",
								sNext : "Suivant",
								sLast : "Dernier"
							}
						}

					});
					$('#localites_table').dataTable({
						responsive : true,

						"aaSorting" : [ [ 0, "asc" ] ],

						oLanguage : {
							sLengthMenu : "Afficher: _MENU_ Localites par page ",
							sSearch : "Rechercher : ",
							sZeroRecords : "Aucune valeur trouvee !!",
							sInfo : "Afficher page _PAGE_ de _PAGES_",
							sInfoFiltered: "(Filtrées sur _MAX_ localités)",
							sInfoEmpty: "Aucune localité trouvée",
							oPaginate : {
								sFirst : "Premier",
								sPrevious : "Précédent",
								sNext : "Suivant",
								sLast : "Dernier"
							}
						}

					});
					$('#activites_table').dataTable({
						responsive : true,

						"aaSorting" : [ [ 0, "asc" ] ],

						oLanguage : {
							sLengthMenu : "Afficher: _MENU_ Activités par page ",
							sSearch : "Rechercher : ",
							sZeroRecords : "Aucune valeur trouvee !!",
							sInfo : "Afficher page _PAGE_ de _PAGES_",
							sInfoFiltered: "(Filtrées sur _MAX_ activités)",
							sInfoEmpty: "Aucune activité trouvée",
							oPaginate : {
								sFirst : "Premier",
								sPrevious : "Précédent",
								sNext : "Suivant",
								sLast : "Dernier"
							}
						}

					});
					$('#projets_table').dataTable({
						responsive : true,

						"aaSorting" : [ [ 0, "asc" ] ],

						oLanguage : {
							sLengthMenu : "Afficher: _MENU_ Projets par page ",
							sSearch : "Rechercher : ",
							sZeroRecords : "Aucune valeur trouvee !!",
							sInfo : "Afficher page _PAGE_ de _PAGES_",
							sInfoFiltered: "(Filtrés sur _MAX_ projets)",
							sInfoEmpty: "Aucun projet trouvé",
							oPaginate : {
								sFirst : "Premier",
								sPrevious : "Précédent",
								sNext : "Suivant",
								sLast : "Dernier"
							}
						}

					});

					$('#messageValide_table').dataTable({
						responsive : true,

						"aaSorting" : [ [ 0, "asc" ] ],

						oLanguage : {
							sLengthMenu : "Afficher: _MENU_ Messages valides par page ",
							sSearch : "Rechercher : ",
							sZeroRecords : "Aucune valeur trouvee !!",
							sInfo : "Afficher page _PAGE_ de _PAGES_",
							sInfoFiltered: "(Filtrés sur _MAX_ messages valides)",
							sInfoEmpty: "Aucun message valide trouvé",
							oPaginate : {
								sFirst : "Premier",
								sPrevious : "Précédent",
								sNext : "Suivant",
								sLast : "Dernier"
							}
						}

					});
					$('#messagenotValide_table').dataTable({
						responsive : true,

						"aaSorting" : [ [ 0, "asc" ] ],

						oLanguage : {
							sLengthMenu : "Afficher: _MENU_ Messages non validés par page ",
							sSearch : "Rechercher : ",
							sZeroRecords : "Aucune valeur trouvee !!",
							sInfo : "Afficher page _PAGE_ de _PAGES_",
							sInfoFiltered: "(Filtrés sur _MAX_ messages non valides)",
							sInfoEmpty: "Aucun message non valide trouvé",
							oPaginate : {
								sFirst : "Premier",
								sPrevious : "Précédent",
								sNext : "Suivant",
								sLast : "Dernier"
							}
						}

					});
					$('#messageBad_table').dataTable({
						responsive : true,

						"aaSorting" : [ [ 0, "asc" ] ],

						oLanguage : {
							sLengthMenu : "Afficher: _MENU_ Messages erronnés par page ",
							sSearch : "Rechercher : ",
							sZeroRecords : "Aucune valeur trouvee !!",
							sInfo : "Afficher page _PAGE_ de _PAGES_",
							sInfoFiltered: "(Filtrés sur _MAX_ messages erronés)",
							sInfoEmpty: "Aucun message erroné trouvé",
							oPaginate : {
								sFirst : "Premier",
								sPrevious : "Précédent",
								sNext : "Suivant",
								sLast : "Dernier"
							}
						}

					});
					$('#messageDup_table').dataTable({
						responsive : true,

						"aaSorting" : [ [ 0, "asc" ] ],

						oLanguage : {
							sLengthMenu : "Afficher: _MENU_ Messages dupliqués par page ",
							sSearch : "Rechercher : ",
							sZeroRecords : "Aucune valeur trouvee !!",
							sInfo : "Afficher page _PAGE_ de _PAGES_",
							sInfoFiltered: "(Filtrés sur _MAX_ messages dupliqués)",
							sInfoEmpty: "Aucun message dupliqué trouvé",
							oPaginate : {
								sFirst : "Premier",
								sPrevious : "Précédent",
								sNext : "Suivant",
								sLast : "Dernier"
							}
						}

					});

					function drawSmallCircle(id, valeur) {

						var can = document.getElementById(id);
						var context = can.getContext('2d');
						valeur = valeur;
						var percentage = valeur; // no specific length
						var degrees = percentage * 360.0;
						var radians = degrees * (Math.PI / 180);

						var x = 20;
						var y = 20;
						var r = 15;
						var s = 1.5 * Math.PI;

						context.beginPath();
						context.lineWidth = 3;
						context.arc(x, y, r, s, radians + s, false);
						context.font = '8.5pt Calibri';
						context.fillText(percentage * 100 + "%", 13, 25);
						context.strokeStyle = 'green';
						context.stroke();

					}

					$(".canvas").each(function(index, canvas) {
						var pourcentage = parseFloat($(canvas).data('index'));
						var idElement = canvas.id;
						drawSmallCircle(idElement, pourcentage);
					});

					// modification profil

				
					/**
					 * *********************Tableau activite
					 * ********************
					 */

					$('#activites_table').on(
							'click',
							'#activityShow',
							function(e) {
								e.preventDefault();

								var nom = $(this).attr('data-nom_activity');
								var description = $(this).attr(
										'data-description_activity');
								var secteur = $(this).attr(
										'data-secteur_activity');
								var dateCreation = $(this).attr(
										'data-dateUpdate_activity');
								var dateUpdate = $(this).attr(
										'data-dateUpdate_activity');

								$('#nom_activity').html(nom);
								$('#secteur_activity').html(secteur);
								$('#description_activity').html(description);
								$('#dateCreation_activity').html(dateCreation);
								$('#dateUpdate_activity').html(dateUpdate);
								$('#ActivitiesShow').modal();
							});

					$('#activites_table')
							.on(
									'click',
									'#activityUpdate',
									function(e) {
										e.preventDefault();

										var nom = $(this).attr(
												'data-nomUp_activity');
										var description = $(this).attr(
												'data-descriptionUp_activity');
										var secteur = $(this).attr(
												'data-secteurUp_activity');
										var dateCreation = $(this).attr(
												'data-dateCreationUp_activity');
										var dateUpdate = $(this).attr(
												'data-dateUpdateUp_activity');
										var idAct = $(this).attr(
												'data-idAct_activity');
										var idLoc = $(this).attr(
												'data-idLoc_activity');

										$('#nomUp_activity').val(nom);
										$('#secteurUp_activity').val(secteur);
										$('#descriptionUp_activity').val(
												description);
										/*
										 * $('#dateCUp').val(dateCreation);
										 * $('#dateUp').val(dateUpdate);
										 */
										$('#idAct_activity').val(idAct);
										$('#idLoc_activity').val(idLoc);
										$('#ActivitiesUpdate').modal();
									});

					/**
					 * ******************Tableau
					 * agents****************************
					 */

					$('#agents_table')
							.on(
									'click',
									'#data',
									function(e) {
										e.preventDefault();

										var nom = $(this)
												.attr('data-nom_agent');
										var prenomUser = $(this).attr(
												'data-prenom_agent');
										var dateNaissance = $(this).attr(
												'data-dateNaissance_agent');

										var sexe = $(this).attr('data-sexe_agent');
										
										var email = $(this).attr(
												'data-email_agent');
										var carteIdent = $(this).attr(
												'data-cni_agent');
										var telephone = $(this).attr(
												'data-telephone_agent');
										var telephone1 = $(this).attr(
												'data-telephone1_agent');
										var profession = $(this).attr(
												'data-profession_agent');
										var idUser = $(this).attr(
												'data-user_agent');
										var idResponsable = $(this).attr(
												'data-idEquipe_agent');

										$('#editProfilNomInput1_agent')
												.val(nom);
										$('#editProfilPrenomInput1_agent').val(
												prenomUser);
										$('#editProfilNaissanceInput1_agent')
												.val(dateNaissance);
										$('#editProfilEmailInput1_agent').val(
												email);
										$('#dateN_agent').val(dateNaissance);
										
										$('#selectSexe_agent').val(sexe);
										$("#selectSexe_agent option[value=sexe]").attr('selected','selected');
										
										$('#editProfilCniInput1_agent').val(
												carteIdent);
										$('#editProfilTel1Input1_agent').val(
												telephone);
										$('#editProfilTel2Input2_agent').val(
												telephone1);
										$('#fonction1_agent').val(profession);
										$('#idAgent').val(idUser);
										$('#idEquipeResponsable_agent').val(
												idResponsable);
										$('#UpdateAgent').modal();
									});

					$('#agents_table').on(
							'click',
							'#dataShow',
							function(e) {
								e.preventDefault();

								var nomU = $(this).attr('data-nomU_agent');
								var prenomU = $(this)
										.attr('data-prenomU_agent');
								var sexeU = $(this).attr('data-sexeU_agent');
								var dateNaissanceU = $(this).attr(
										'data-dateU_agent');
								var emailU = $(this).attr('data-emailU_agent');
								var cniU = $(this).attr('data-cniU_agent');
								var tel1U = $(this).attr('data-tel1U_agent');
								var tel2U = $(this).attr('data-tel2U_agent');
								var professionU = $(this).attr(
										'data-professionU_agent');
								$('#nom_agent').html(nomU);
								$('#prenom_agent').html(prenomU);
								$('#dateNaissance_agent').html(dateNaissanceU);
								$('#email_agent').html(emailU);
								$('#sexe_agent').html(sexeU);
								$('#cni_agent').html(cniU);
								$('#tel1_agent').html(tel1U);
								$('#tel2_agent').html(tel2U);
								$('#profession_agent').html(professionU);
								$('#formShow').modal();
							});

					// send SMS
					var max = 140;
					$('#characterLeft').text(
							'Nombre de charactères maximal ' + max);
					$('#message')
							.keydown(
									function() {
										var len = $(this).val().length;
										if (len >= max) {
											$('#characterLeft')
													.text(
															'Vous avez atteint la taille maximale du message.');
											$('#characterLeft').addClass('red');
											$('#btnSendSmsAgent').addClass(
													'disabled');
										} else {
											var ch = max - len;
											$('#characterLeft').text(
													'charactères restant: '
															+ ch);
											$('#btnSendSmsAgent').removeClass(
													'disabled');
											$('#characterLeft').removeClass(
													'red');
											if(ch == max){
												$('#btnSendSmsAgent').addClass('disabled');
											}
										}
									});

					/**
					 * *********************Tableau
					 * projets******************************
					 */

					$('#projets_table').on(
							'click',
							'#Viewproject',
							function(e) {
								e.preventDefault();

								var nom = $(this).attr('data-nom_projet');
								var description = $(this).attr(
										'data-description_projet');
								var dateD = $(this).attr('data-dateD_projet');
								var dateF = $(this).attr('data-dateF_projet');
								var contact = $(this).attr(
										'data-contact_projet');
								var autorite = $(this).attr(
										'data-autorite_projet');
								var cout = $(this).attr('data-cout_previsionnel_projet');
								var emploi = $(this).attr('data-emploi_previsionnel_projet');
								var etat = $(this).attr('data-etat_projet');

								$('#nom_projet').html(nom);

								$('#description_projet').html(description);
								$('#dateD_projet').html(dateD);
								$('#dateF_projet').html(dateF);
								$('#contact_projet').html(contact);
								$('#autorite_projet').html(autorite);
								$('#cout_previsionnel_projet').html(cout);
								$('#emploi_previsionnel_projet').html(emploi);
								$('#etat_projet').html(etat);
								$('#projectShow').modal();
							});

					$('#projets_table').on(
							'click',
							'#updatePro',
							function(e) {
								e.preventDefault();

								var nom = $(this).attr('data-nomUpPro');
								var descriptionPro = $(this).attr(
										'data-descriptionUpPro');
								var dateDUpPro = $(this)
										.attr('data-dateDUpPro');
								var dateFUpPro = $(this)
										.attr('data-dateFUpPro');
								var contactUpPro = $(this).attr(
										'data-contactUpPro');
								var autoriteUpPro = $(this).attr(
										'data-autoriteUpPro');
								var idPro = $(this).attr('data-idPro');
								var idLoc = $(this).attr('data-idLoc');
								var cout = $(this).attr('data-cout_previsionnel_projetUp');
								var emploi = $(this).attr('data-emploi_previsionnel_projetUp');

								$('#nomProUp').val(nom);
								$('#descriptionProUp').val(descriptionPro);
								$('#dateDProUp').val(dateDUpPro);
								$('#dateFProUp').val(dateFUpPro);
								$('#contactProUp').val(contactUpPro);
								$('#autoriteProUp').val(autoriteUpPro);
								$('#idPro').val(idPro);
								$('#idLocPro').val(idLoc);
								$('#coutPrevisionnelProUp').val(cout);
								$('#emlpoiPrevisionnelProUp').val(emploi);
								$('#UpdateProject').modal();
							});
					// ***************************Modification du profil************************************************//
				

					$('.div-changePassword').hide();
					$('#password').attr("required", false);
					$('#ancienpassword').attr("required", false);
					$('#cpassword').attr("required", false);

					$('#changePassword').click(function() {

						$("#pwmatch").hide();

						$('.div-changePassword').fadeToggle('slow');
						$('#password').attr("required", true);
						$('#ancienpassword').attr("required", true);
						$('#cpassword').attr("required", true);
					});

					
					

						$("#span-error").hide();
				
					$('.bg-info').hide();

					$('#changePassword').click(function() {

						$('.bg-info').fadeToggle('slow');

					});

					// Notification modification profil

					$('#btn-modifierProfil').click(
							function(e) {
								var frm = $('#editProfilFormulaire');
								e.preventDefault();
								$.ajax({
 									type : frm.attr('method'),
									url : frm.attr('action'),
 									data : frm.serialize(),
									success : function(callback) {
										if(callback.codeRetour === 200){
											$.notify(callback.message, "success");
										}else {
											$.notify(callback.message, "error");
										}
										
										if(callback.codeRetour === -302){
									
											$("#span-error").show();
											$("#span-error").css("color", "#FF0004");
											$("#cpassword").css("color", "#FF0004");
											$("#cpassword").css("border-color", "#FF0004");
											
										}
										else{
											$("#span-error").hide();
											$("#span-error").css("color", "#FFFFFF");
											$("#cpassword").css("color", "#000000");
											$("#cpassword").css("border-color", "");
										}
										
										if(callback.codeRetour === -301){
											
										
											$("#editProfilTel1Input1").css("border-color", "#FF0004");
											$("#editProfilTel2Input2").css("border-color", "#FF0004");
											
										}
										if(callback.codeRetour === -303){
											
											
											$("#password").css("border-color", "#FF0004");
										
										}
										if(callback.codeRetour === -304){
											
											
											$("#editProfilCniInput1").css("border-color", "#FF0004");
										
										}
										
										
									},
									error : function() {
										$.notify("Une erreur ext survenue pendant le traitement de votre demande", "error");
									}
								});
							});
					
					// Notification sms

					$('#btnSendSmsAgent').click(
							function(e) {
								var idEquipe = $(this).attr('data-idEquipeSms');
								
								var frm = $('#notificationSms');
								e.preventDefault();
								$.ajax({
									// contentType: 'application/json;
									// charset=utf-8',
									type : frm.attr('method'),
									url : frm.attr('action'),
									// dataType: 'json',
									data : frm.serialize(),
									success : function(callback) {
										$.notify("Votre message a été envoyé avec succes",
												"success");
										$('#send')
										.modal('hide');
										
									},
									error : function() {
										$.notify("Une erreur s'est produite lors de l'ajout.", "error");
									}
								});
							});
					
					$('#btnAddAgent').click(
							function(e) {
								var idEquipe = $(this).attr('data-idEquipeAddAgent');							
								var frm = $('#AddAgentFormulaire');
								e.preventDefault();
								$.ajax({
									// contentType: 'application/json;
									// charset=utf-8',
									type : frm.attr('method'),
									url : frm.attr('action'),
									// dataType: 'json',
									data : frm.serialize(),
									success : function(callback) {
										if(callback.codeRetour === 200){
											$.notify("Agent ajouté avec succès.",
													"success");
											$('#CreateAgent')
											.modal('hide');
											setTimeout(function(){
												location.reload();
												},500);
										}
										if(callback.codeRetour === -101){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -300){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -301){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -304){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -100){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -305){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -306){
											$.notify(callback.message, "error");
										}
									},
									error : function() {
										$.notify("Une erreur s'est produite lors de l'ajout.", "error");
									
								}
							});
							});
					
					$('#bntUpdateAgent').click(
							function(e) {
															
								var frm = $('#UpdateAgentFormulaire');
								e.preventDefault();
								$.ajax({
									// contentType: 'application/json;
									// charset=utf-8',
									type : frm.attr('method'),
									url : frm.attr('action'),
									// dataType: 'json',
									data : frm.serialize(),
									success : function(callback) {
										if(callback.codeRetour === 200){
											$.notify("Agent modifié avec succès.",
													"success");
											$('#UpdateAgent')
											.modal('hide');
											setTimeout(function(){
												location.reload();
												},500);
										}
										if(callback.codeRetour === -101){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -300){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -301){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -304){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -100){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -305){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -306){
											$.notify(callback.message, "error");
										}
									},
									error : function() {
										$.notify("Une erreur s'est produite lors de la modification.", "error");
									}
								});
							});
					

					$('#bntSubmitCreateActivity').click(
							function(e) {
															
								var frm = $('#formCreateActivity');
								var idLoc = $(this).attr('data-idLocActivity');
								
								e.preventDefault();
								$.ajax({
									// contentType: 'application/json;
									// charset=utf-8',
									type : frm.attr('method'),
									url : frm.attr('action'),
									// dataType: 'json',
									data : frm.serialize(),
									success : function(callback) {
										if(callback.codeRetour === 200){
										$.notify(callback.message,
												"success");
										$('#CreateActivity')
										.modal('hide');
										setTimeout(function(){
											location.reload();
											},500);
										}
										if(callback.codeRetour === -101){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -100){
											$.notify(callback.message, "error");
										}
									},
									error : function() {
										$.notify("Une erreur s'est produite lors de l'ajout.", "error");
									}
								});
							});
					
					$('#bntSubmitUpdateActivity').click(
							function(e) {
															
								var frm = $('#formUpdateActivity');
							
								e.preventDefault();
								$.ajax({
									// contentType: 'application/json;
									// charset=utf-8',
									type : frm.attr('method'),
									url : frm.attr('action'),
									// dataType: 'json',
									data : frm.serialize(),
									success : function(callback) {
										if(callback.codeRetour === 200){
										$.notify(callback.message,
												"success");
										$('#ActivitiesUpdate')
										.modal('hide');
										setTimeout(function(){
											location.reload();
											},500);
										}
										if(callback.codeRetour === -101){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -100){
											$.notify(callback.message, "error");
										}
									},
									error : function() {
										$.notify("Une erreur s'est produite lors de la modification.", "error");
									}
								});
							});
					
					$('#bntSubmitCreateProjet').click(
							function(e) {
															
								var frm = $('#formCreateProjet');
								var idLoc = $(this).attr('data-idLocProjet');
								
								e.preventDefault();
								$.ajax({
									// contentType: 'application/json;
									// charset=utf-8',
									type : frm.attr('method'),
									url : frm.attr('action'),
									// dataType: 'json',
									data : frm.serialize(),
									success : function(callback) {
										if(callback.codeRetour === 200){
											$.notify(callback.message,
													"success");
											$('#CreateProject')
											.modal('hide');
											setTimeout(function(){
												location.reload();
												},500);
										}
										if(callback.codeRetour === -101){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -100){
											$.notify(callback.message, "error");
										}
									},
									error : function() {
										$.notify("Une erreur s'est produite lors de l'ajout.", "error");
									}
								});
							});
					
					$('#bntSubmitUpdateProjet').click(
							function(e) {
															
								var frm = $('#formUpdateProjet');
							
								e.preventDefault();
								$.ajax({
  									type : frm.attr('method'),
									url : frm.attr('action'),
 									data : frm.serialize(),
									success : function(callback) {
										if(callback.codeRetour === 200){
											$.notify(callback.message,
													"success");
											$('#UpdateProject')
											.modal('hide');
											setTimeout(function(){
												location.reload();
												},500);
										}
										if(callback.codeRetour === -101){
											$.notify(callback.message, "error");
										}
										if(callback.codeRetour === -100){
											$.notify(callback.message, "error");
										}
									},
									error : function() {
										$.notify("Une erreur s'est produite lors de la modification.", "error");
									}
								});
							});

					
					//Tableau agent
					
					$('#agents_table').dataTable({
						responsive : true,

						"aaSorting" : [ [ 0, "asc" ] ],

						oLanguage : {
							sLengthMenu : "Afficher: _MENU_ Membres par page ",
							sSearch : "Rechercher : ",
							sZeroRecords : "Aucune valeur trouvee !!",
							sInfo : "Afficher page _PAGE_ of _PAGES_",
							oPaginate : {
								sFirst : "Premier",
								sPrevious : "Precedent",
								sNext : "Suivant",
								sLast : "Dernier"
							}
						}

					});
					
					
					/********************************Visualisation du membre**************************/ 
					
					$('#members_table').on(
							'click',
							'#showMembre',
							function(e) {
								e.preventDefault();

								var nomU = $(this).attr('data-nomU');
								var prenomU = $(this)
										.attr('data-prenomU');
								var sexeU = $(this).attr('data-sexeU');
								var dateNaissanceU = $(this).attr(
										'data-dateU_agent');
								var emailU = $(this).attr('data-emailU');
								var cniU = $(this).attr('data-cniU');
								var tel1U = $(this).attr('data-telephoneU');
								var tel2U = $(this).attr('data-telephoneU1');
								var professionU = $(this).attr('data-professionU');
								var categorieU = $(this).attr('data-categorieU');
								var localiteU = $(this).attr('data-localiteU');
								$('#nom').html(nomU);
								$('#prenom').html(prenomU);
								$('#dateNaissance').html(dateNaissanceU);
								$('#email').html(emailU);
								$('#sexe').html(sexeU);
								$('#cni').html(cniU);
								$('#tel1').html(tel1U);
								$('#tel2').html(tel2U);
								$('#profession').html(professionU);
								$('#localite').html(localiteU);
								$('#categorie').html(categorieU);
								$('#ShowInfosMembre').modal();
							});
					
					
					$('#members_table')
					.on(
							'click',
							'#updateMembre',
							function(e) {
								e.preventDefault();
								var nom = $(this)
										.attr('data-nom');
								var prenomUser = $(this).attr(
										'data-prenom');
								var dateNaissance = $(this).attr(
										'data-dateNaissance');

								var optionValue = $(this).attr(
										'data-sexe');
								
								var email = $(this).attr(
										'data-email');
								var carteIdent = $(this).attr(
										'data-cni');
								var telephone = $(this).attr(
										'data-telephone');
								var telephone1 = $(this).attr(
										'data-telephone1');
								var profession = $(this).attr(
										'data-profession');
								var idUser = $(this).attr(
										'data-user');
								var idResponsable = $(this).attr(
										'data-idEquipe');

								$('#editMembreNomInput')
										.val(nom);
								$('#editMembrePrenomInput').val(
										prenomUser);
								$('#editMembreNaissanceInput')
										.val(dateNaissance);
								$('#editMembreEmailInput').val(
										email);
								$('#dateN_agent').val(dateNaissance);
								$('#selectSexe_agent option:eq(1)')
										.prop('selected', true);
								// $('#selectSexe').val(optionValue);
								$('#editMembreCniInput').val(
										carteIdent);
								$('#editMembreTel1Input').val(
										telephone);
								$('#editMembreTel2Input').val(
										telephone1);
								$('#editMembreFonctionInput').val(profession);
								$('#idMembre').val(idUser);
								$('#idEquipeResponsable').val(
										idResponsable);
								$('#ModifierMembre').modal();
							});

					
					$('#localites_table')
					.on(
							'click',
							'#updateLocalite',
							function(e) {
								e.preventDefault();
								
								
								var nomLocalite = $(this).attr('data-nomLocalite');
								var nbrPopulation = $(this).attr('data-nbrPopulation');
								var nbrElecteurs = $(this).attr('data-nbrElecteurs');
								var nbrObjectif = $(this).attr('data-objectif');
								var nbrSection = $(this).attr('data-nbrSection');
								var typeLocalite = $(this).attr('data-typeLocalite');
								var rattachement = $(this).attr('data-rattachement');
								var responsable = $(this).attr('data-responsable');		
								var idresponsable = $(this).attr('data-responsableId');		
								var idlocalite = $(this).attr('data-idLocalite');
								var Nomparent = $(this).attr('data-parent');
								var parentId = $(this).attr('data-parentId');
							 
								$('#nomLocalite').val(nomLocalite);
								$('#nbrHabitants').val(nbrPopulation);
								$('#nbrElecteurs').val(nbrElecteurs);
								$('#nbrSections').val(nbrSection);
								$('#typeLocalite').val(typeLocalite);
								$('#nbrObjectifs').val(nbrObjectif);
								 var monResp=responsable+":"+idresponsable;
								 var monRattachement=Nomparent+":"+parentId;
								
							
								$("#typeLocalite option[value=typeLocalite]").attr('selected','selected');
								
                                $('#nomResponsable').prepend(new Option(responsable, monResp, true, true));
                                
                              
								$('#nomResponsable').val(monResp);
								$('#idLocaliteModif').val(idlocalite);
								//ajout d'une option dans le select pour la valeur dans la BD
								 $('#nomRattachement').prepend(new Option(Nomparent, monRattachement, true, true));
								$('#nomRattachement').val(monRattachement);

								$('#updateLocalization').modal();
							});

					//*****************************gestion objectif***********************
					$("#objectif-nombre").hide();
					$("#objectif-nombre-text").hide();
					
					$( "#editObjectif" ).bind("change paste keyup",function() {
					
							
							var pourcentageObjectif=$("#editObjectif").val();
							var nbElecteur=$("#nbElecteur").val();
							var nbobjectif=parseInt(((nbElecteur*pourcentageObjectif)/100));
							$('#objectif-nombre').addClass('well well-sm');
							$("#objectif-nombre-text").text("Nouveaux inscrits").show();
							$("#objectif-nombre").text(nbobjectif).show();
							
							});
						
					$( "#editObjectifNombre" ).bind("change paste keyup",function() {
						
						
						var valeurObjectif=$("#editObjectifNombre").val();
						var nbElecteur=$("#nbElecteur").val();
						var nbobjectif=((100*valeurObjectif)/nbElecteur).toFixed(2);
					     $("#editObjectif").val(nbobjectif);
					 	$('#objectif-nombre').addClass('well well-sm');
					 	$("#objectif-nombre-text").text("Pourcentage en electeurs").show();
						$("#objectif-nombre").text(nbobjectif+"%").show();
						
						});
					$("#fermerModalObjectif").click(function(){
						  $("#editObjectif").val("");
						  $("#editObjectifNombre").val("");
						  $("#objectif-nombre").text("");
						  $('#objectif-nombre').removeClass('well well-sm');
						  $("#objectif-nombre-text").text("")
						
					});
					$("#btnAnnulerAddObjectif").click(function(){
						  
						  $("#objectif-nombre").text("");
						  $('#objectif-nombre').removeClass('well well-sm');
						  $("#objectif-nombre-text").text("")
						
					});
					
					
						
						$('#bntAddObjectif').click(
								function(e) {
																
									var frm = $('#editObjectifFormulaire');
								
									e.preventDefault();
									$.ajax({
										// contentType: 'application/json;
										// charset=utf-8',
										type : frm.attr('method'),
										url : frm.attr('action'),
										// dataType: 'json',
										data : frm.serialize(),
										success : function(callback) {
											
											if(callback.codeRetour === 200){
												$.notify(callback.message,"success");
												
												$('#CreateObjectif').modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
												}
										
										if(callback.codeRetour === 500){
											
											$.notify(callback.message, "error");
											
										}
										
										if(callback.codeRetour === 501){
											
											$.notify(callback.message, "error");
										
											
											
										}
											
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});

						

						/**
						 * *************************************Ajout d'un
						 * militant****************************
						 */
						
						$('#bntAddMembres').click(
								function(e) {						
									var frm = $('#addMembreForm');
									e.preventDefault();
									$.ajax({
										// contentType: 'application/json;
										// charset=utf-8',
										type : frm.attr('method'),
										url : frm.attr('action'),
										// dataType: 'json',
										data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#CreateMembre')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},1000);
											}
											else{
												$.notify(callback.message, "error");
												
											}
											
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de l'ajout.", "error");
										
									}
								});
								});
						
						$('#bntModifierMembre').click(
								function(e) {
																
									var frm = $('#UpdateMembreFormulaire');
									e.preventDefault();
									$.ajax({
										// contentType: 'application/json;
										// charset=utf-8',
										type : frm.attr('method'),
										url : frm.attr('action'),
										// dataType: 'json',
										data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify("Agent modifié avec succès.",
														"success");
												$('#ModifierMembre')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											else{
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
						$('#all_projets_table').dataTable({
							responsive : true,

							"aaSorting" : [ [ 0, "asc" ] ],

							oLanguage : {
								sLengthMenu : "Afficher: _MENU_ Projet par page ",
								sSearch : "Rechercher : ",
								sZeroRecords : "Aucune valeur trouvee !!",
								sInfo : "Afficher page _PAGE_ of _PAGES_",
								oPaginate : {
									sFirst : "Premier",
									sPrevious : "Precedent",
									sNext : "Suivant",
									sLast : "Dernier"
								}
							}

						});
						
						
						/************************** Tableau all projet**************************/
						

						$('#all_projets_table').on(
								'click',
								'#ViewForAllproject',
								function(e) {
									e.preventDefault();

									var nom = $(this).attr('data-nom_projet');
									var description = $(this).attr(
											'data-description_projet');
									var dateD = $(this).attr('data-dateD_projet');
									var dateF = $(this).attr('data-dateF_projet');
									var contact = $(this).attr(
											'data-contact_projet');
									var autorite = $(this).attr(
											'data-autorite_projet');

									$('#nom_all_projet').html(nom);

									$('#description_all_projet').html(description);
									$('#dateD_all_projet').html(dateD);
									$('#dateF_all_projet').html(dateF);
									$('#contact_all_projet').html(contact);
									$('#autorite_all_projet').html(autorite);
									$('#allProjectShow').modal();
								});
						
						/***********************Fermer modal visualiser****************/
						
//						$('#fermerModalViewAllPoject').click(
//								function(e) {						
//									$('#allProjectShow')
//									.modal('hide');
//								});
//						
						
						//*****************************tableau etape
						$('#etape_table').dataTable({
							responsive : true,

							"aaSorting" : [ [ 0, "asc" ] ],

							oLanguage : {
								sLengthMenu : "Afficher: _MENU_ Membres par page ",
								sSearch : "Rechercher : ",
								sZeroRecords : "Aucune valeur trouvee !!",
								sInfo : "Afficher page _PAGE_ of _PAGES_",
								oPaginate : {
									sFirst : "Premier",
									sPrevious : "Precedent",
									sNext : "Suivant",
									sLast : "Dernier"
								}
							}

						});
						
						$('#bntSubmitCreateEtape').click(
								function(e) {
																
									var frm = $('#formCreateEtape');
									var idLoc = $(this).attr('data-idLocProjet');
									
									e.preventDefault();
									$.ajax({
										// contentType: 'application/json;
										// charset=utf-8',
										type : frm.attr('method'),
										url : frm.attr('action'),
										// dataType: 'json',
										data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#CreateEtape')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -101){
												$.notify(callback.message, "error");
											}
											if(callback.codeRetour === -100){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de l'ajout.", "error");
										}
									});
								});
						
						

						$('.Viewetape').on(
								'click',
								function(e) {
									e.preventDefault();

									var nom = $(this).attr('data-nom_etape');
									var description = $(this).attr(
											'data-description_etape');
									var dateD = $(this).attr('data-dateD_etape');
									var dateF = $(this).attr('data-dateF_etape');
									var contact = $(this).attr(
											'data-contact_etape');
									var autorite = $(this).attr(
											'data-autorite_etape');
									var cout = $(this).attr('data-cout_previsionnel_etape');
									var emploi = $(this).attr('data-emploi_previsionnel_etape');
									var coutReel = $(this).attr('data-cout_reel_etape');
									var emploiReel = $(this).attr('data-emploi_reel_etape');
									var etat = $(this).attr('data-etat_etape');

									$('#nom_etape').html(nom);

									$('#description_etape').html(description);
									$('#dateD_etape').html(dateD);
									$('#dateF_etape').html(dateF);
									$('#contact_etape').html(contact);
									$('#autorite_etape').html(autorite);
									$('#cout_previsionnel_etape').html(cout);
									$('#emploi_previsionnel_etape').html(emploi);
									$('#cout_reel_etape').html(coutReel);
									$('#emploi_reel_etape').html(emploiReel);
									$('#etat_etape').html(etat);
									
									$('#etapeShow').modal();
								});
						
						$('.updateEtape').on(
								'click',
								 
								function(e) {
									e.preventDefault();

									var nom = $(this).attr('data-nomUpetape');
									var descriptionPro = $(this).attr(
											'data-descriptionUpetape');
									var dateDUpPro = $(this)
											.attr('data-dateDUpetape');
									var dateFUpPro = $(this)
											.attr('data-dateFUpetape');
									var contactUpPro = $(this).attr(
											'data-contactUpetape');
									var autoriteUpPro = $(this).attr(
											'data-autoriteUpetape');
									 
									var cout = $(this).attr('data-cout_previsionnel_etapeUp');
									var emploi = $(this).attr('data-emploi_previsionnel_etapeUp');
									var coutReel = $(this).attr('data-cout_reel_etapeUp');
									var emploiReel = $(this).attr('data-emploi_reel_etapeUp');
									var idEtape = $(this).attr('data-idEtape');

									$('#nomEtapeUp').val(nom);
									$('#descriptionEtapeUp').val(descriptionPro);
									$('#dateDEtapeUp').val(dateDUpPro);
									$('#dateFEtapeUp').val(dateFUpPro);
									$('#contactEtapeUp').val(contactUpPro);
									$('#autoriteEtapeUp').val(autoriteUpPro);
									$('#coutPrevisionnelEtapeUp').val(cout);
									$('#emlpoiPrevisionnelEtapeUp').val(emploi);
									$('#coutReelEtapeUp').val(coutReel);
									$('#emlpoiReelEtapeUp').val(emploiReel);
									$('#idEtape').val(idEtape);
									$('#UpdateEtape').modal();
								});
						
						$('#bntSubmitUpdateEtape').click(
								function(e) {
																
									var frm = $('#formUpdateEtape');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateEtape')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
												
												
											}
											if(callback.codeRetour === -101){
												$.notify(callback.message, "error");
											}
											if(callback.codeRetour === -100){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
									
									
								});
						
						/*************************update localite**************************/
						
						$('#btnUpdateLocalite').click(
								function(e) {
																
									var frm = $('#updateLocaliteForm');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#updateLocalization')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											else{
												$.notify(callback.message,
												"error");
												
											}
											
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
						
						//Modifier la fiche projet
						
						$('#bntSubmitUpdateFicheProjet').click(
								function(e) {
																
									var frm = $('#formUpdateFicheProjet');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateFicheProjet')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								}); 
						
						
						//Modifier PErsonne projet
						
						$('#bntSubmitUpdatePersonne').click(
								function(e) {
																
									var frm = $('#formUpdatePersonne');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdatePersonnesProjet')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
//Modifier Details projet
						
						$('#bntSubmitUpdateDetails').click(
								function(e) {
																
									var frm = $('#formUpdateDetails');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateDetailsProjet')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
						
//Modifier Date projet
						
						$('#bntSubmitUpdateDate').click(
								function(e) {
																
									var frm = $('#formUpdateDate');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateDatesProjet')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
											if(callback.codeRetour === -101){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
//Modifier Budgets projet
						
						$('#bntSubmitUpdateBudgets').click(
								function(e) {
																
									var frm = $('#formUpdateBudgets');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateBudgetsProjet')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
//Modifier Budgets projet
						
						$('#bntSubmitUpdateEmplois').click(
								function(e) {
																
									var frm = $('#formUpdateEmplois');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateEmploisProjet')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
											
						
						
						
						
						
						
/*********************************************************************modification etape**************************************************************/						
						
						//Etape modification niveau execution
						
						$('#bntSubmitUpdateNiveauEtape').click(
								function(e) {
																
									var frm = $('#formUpdateNiveauEtape');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateNiveauEtape')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
						
						//Modifier PErsonne projet
						
						$('#bntSubmitUpdatePersonneEtape').click(
								function(e) {
																
									var frm = $('#formUpdatePersonneEtape');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdatePersonnesEtape')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
//Modifier Details projet
						
						$('#bntSubmitUpdateDetailsEtape').click(
								function(e) {
																
									var frm = $('#formUpdateDetailsEtape');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateDetailsEtape')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
						
//Modifier Date projet
						
						$('#bntSubmitUpdateDateEtape').click(
								function(e) {
																
									var frm = $('#formUpdateDateEtape');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateDatesEtape')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
											if(callback.codeRetour === -101){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
//Modifier Budgets projet
						
						$('#bntSubmitUpdateBudgetsEtape').click(
								function(e) {
																
									var frm = $('#formUpdateBudgetsEtape');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateBudgetsEtape')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
											if(callback.codeRetour === -101){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
						
//Modifier Budgets projet
						
						$('#bntSubmitUpdateEmploisEtape').click(
								function(e) {
																
									var frm = $('#formUpdateEmploisEtape');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateEmploisEtape')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
											if(callback.codeRetour === -101){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
											
											
						
						
						
//	-------------------------------------------------------------------------------------------------------------------------------------------					
						
						
						$('#panelDetailsProjet').click(
								function(e) {
									
									var val = $( "#spanDetailsProjet" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanDetailsProjet').removeAttr("class");
		 								$('#spanDetailsProjet').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanDetailsProjet').removeAttr("class");
		 								$('#spanDetailsProjet').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						$('#panelEtapesProjet').click(
								function(e) {
									
									var val = $( "#spanEtapesProjet" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanEtapesProjet').removeAttr("class");
		 								$('#spanEtapesProjet').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanEtapesProjet').removeAttr("class");
		 								$('#spanEtapesProjet').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						$('#panelPersonnesProjet').click(
								function(e) {
									
									var val = $( "#spanPersonnesProjet" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanPersonnesProjet').removeAttr("class");
		 								$('#spanPersonnesProjet').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanPersonnesProjet').removeAttr("class");
		 								$('#spanPersonnesProjet').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						$('#panelDatesProjet').click(
								function(e) {
									
									var val = $( "#spanDatesProjet" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanDatesProjet').removeAttr("class");
		 								$('#spanDatesProjet').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanDatesProjet').removeAttr("class");
		 								$('#spanDatesProjet').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						$('#panelBudgetsProjet').click(
								function(e) {
									
									var val = $( "#spanBudgetsProjet" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanBudgetsProjet').removeAttr("class");
		 								$('#spanBudgetsProjet').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanBudgetsProjet').removeAttr("class");
		 								$('#spanBudgetsProjet').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						$('#panelEmploisProjet').click(
								function(e) {
									
									var val = $( "#spanEmploisProjet" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanEmploisProjet').removeAttr("class");
		 								$('#spanEmploisProjet').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanEmploisProjet').removeAttr("class");
		 								$('#spanEmploisProjet').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						$('#panelTimesProjet').click(
								function(e) {
									
									var val = $( "#spanTimesProjet" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanTimesProjet').removeAttr("class");
		 								$('#spanTimesProjet').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanTimesProjet').removeAttr("class");
		 								$('#spanTimesProjet').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						
						//********************************************************************************
						
						$('#panelTimesEtape').click(
								function(e) {
									
									var val = $( "#spanTimesEtape" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanTimesEtape').removeAttr("class");
		 								$('#spanTimesEtape').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanTimesEtape').removeAttr("class");
		 								$('#spanTimesEtape').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
					 
						
						
						$('#panelDetailsEtape').click(
								function(e) {
									
									var val = $( "#spanDetailsEtape" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanDetailsEtape').removeAttr("class");
		 								$('#spanDetailsEtape').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanDetailsEtape').removeAttr("class");
		 								$('#spanDetailsEtape').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						$('#panelPersonnesEtape').click(
								function(e) {
									
									var val = $( "#spanPersonnesEtape" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanPersonnesEtape').removeAttr("class");
		 								$('#spanPersonnesEtape').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanPersonnesEtape').removeAttr("class");
		 								$('#spanPersonnesEtape').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						$('#panelDatesEtape').click(
								function(e) {
									
									var val = $( "#spanDatesEtape" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanDatesEtape').removeAttr("class");
		 								$('#spanDatesEtape').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanDatesEtape').removeAttr("class");
		 								$('#spanDatesEtape').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						$('#panelBudgetsEtape').click(
								function(e) {
									
									var val = $( "#spanBudgetsEtape" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanBudgetsEtape').removeAttr("class");
		 								$('#spanBudgetsEtape').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanBudgetsEtape').removeAttr("class");
		 								$('#spanBudgetsEtape').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						$('#panelEmploisEtape').click(
								function(e) {
									
									var val = $( "#spanEmploisEtape" ).attr( "class" );	
									
									if(val== 'glyphicon glyphicon-chevron-up'){
										$('#spanEmploisEtape').removeAttr("class");
		 								$('#spanEmploisEtape').attr("class","glyphicon glyphicon-chevron-down");	
									}
									else
										{
										$('#spanEmploisEtape').removeAttr("class");
		 								$('#spanEmploisEtape').attr("class","glyphicon glyphicon-chevron-up");
										}
									});
						
						
						
						
						
						//delete activite confirmation


						$('#activites_table').on('click', '#deleteActivite',

						function(e) {
							e.preventDefault();

							var idAct = $(this).attr('data-idAct');
							var idLoc = $(this).attr('data-idLoc');

							$('#idActDel').val(idAct);
							$('#idLocDel').val(idLoc);
							$('#confirmDelAct').modal();
						});
						
						$('#yesDelAct').click(
								function(e) {
																
									var frm = $('#formDelAct');
									
									
									e.preventDefault();
									$.ajax({
										type : frm.attr('method'),
										url : frm.attr('action'),
										data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#confirmDelAct')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.CodeRetour === -100){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la suppresion.", "error");
										}
									});
								});
						
						
						//delete agent confirmation


						$('#agents_table').on('click', '#deleteAgent',

						function(e) {
							e.preventDefault();

							var idAgent = $(this).attr('data-idAgent');
							
							$('#idAgentDel').val(idAgent);
							$('#confirmDelAgent').modal();
						});
						
						
						$('#yesDelAgent').click(
								function(e) {
																
									var frm = $('#formDelAgent');
									
									e.preventDefault();
									$.ajax({
										type : frm.attr('method'),
										url : frm.attr('action'),
										data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#confirmDelAgent')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.CodeRetour === -100){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la suppresion.", "error");
										}
									});
								});
						
						
						//delete sms confirmation
						$('#delsms').click(

						function(e) {
							e.preventDefault();
							
							var idSms = $(this).attr('data-idsms');

							$('#idsms').val(idSms);
							$('#confirmDelsms').modal();
						});
						
						
						$('#yesDelsms').click(
								function(e) {
																
									var frm = $('#formDelsms');
									
									e.preventDefault();
									$.ajax({
										type : frm.attr('method'),
										url : frm.attr('action'),
										data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#confirmDelsms')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la suppresion.", "error");
										}
									});
								});
						
						//delete project confirmation
						$('#projets_table').on('click', '#deleteProject',

						function(e) {
							e.preventDefault();
													
							var idPro = $(this).attr('data-idPro');
							var idLoc = $(this).attr('data-idLoc');

							$('#idProDel').val(idPro);
							$('#idLocDel').val(idLoc);
							$('#confirmDelPro').modal();
						});
						
						
						$('#yesDelPro').click(
								function(e) {
																
									var frm = $('#formDelPro');
									
									e.preventDefault();
									$.ajax({
										type : frm.attr('method'),
										url : frm.attr('action'),
										data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#confirmDelPro')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.CodeRetour === -100){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la suppresion.", "error");
										}
									});
								});
						
						//delete Etape confirmation
						$('.deleteEtape').click(

						function(e) {
							e.preventDefault();
													
							var idEtape = $(this).attr('data-idEtape');
						
							$('#idEtapeDel').val(idEtape);
							
							$('#confirmDelEtape').modal();
						});
						
						
						$('#yesDelEtape').click(
								function(e) {
																
									var frm = $('#formDelEtape');
									
									e.preventDefault();
									$.ajax({
										type : frm.attr('method'),
										url : frm.attr('action'),
										data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#confirmDelEtape')
												.modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.CodeRetour === -100){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la suppresion.", "error");
										}
									});
								});
						
						
						
						
						//**********************supprimer l'option select ajouter par append*****//
						
						$("#btnModifLocalite").click(function(){
							  
						
							 $('#nomResponsable option:selected').remove();
							 $('#nomRattachement option:selected').remove();
						});
						
						
						$('#bntSubmitUpdateCoutReel').click(
								function(e) {
																
									var frm = $('#formUpdateCoutReel');
								
									e.preventDefault();
									$.ajax({
	  									type : frm.attr('method'),
										url : frm.attr('action'),
	 									data : frm.serialize(),
										success : function(callback) {
											if(callback.codeRetour === 200){
												$.notify(callback.message,
														"success");
												$('#UpdateCoutReel').modal('hide');
												setTimeout(function(){
													location.reload();
													},500);
											}
											if(callback.codeRetour === -400){
												$.notify(callback.message, "error");
											}
										},
										error : function() {
											$.notify("Une erreur s'est produite lors de la modification.", "error");
										}
									});
								});
									
						
				});



function blockerMembre(idMembre) {

	$.ajax({
		// contentType: 'application/json;
		// charset=utf-8',
		type : "GET",
		url : " /membres/block/" + idMembre,
		// dataType: 'json',
		success : function(callback) {
			if (callback.codeRetour === 200) {
				$.notify(callback.message, "success");
				setTimeout(function(){
					location.reload();
					},1000);
			} else {
				$.notify(callback.message, "error");
				setTimeout(function(){
					location.reload();
					},1000);
			}

		},
		error : function() {
			$.notify("Une erreur s'est produite lors du blocage du membre",
					"error");

		}

	});
}

function changeStateProjet(idProjet, etat) {

	$.ajax({
		// contentType: 'application/json;
		// charset=utf-8',
		type : "GET",
		url : "/projets/UpdateStaTeProjet/"+idProjet+"/"+etat,
		// dataType: 'json',
		success : function(callback) {
			if (callback.codeRetour === 200) {
				$.notify(callback.message, "success");
				
				setTimeout(function(){
					location.reload();
					},500);
				
			} else {
				$.notify(callback.message, "error");
				setTimeout(function(){
					location.reload();
					},500);
			}

		},
		error : function() {
			$.notify("Une erreur s'est produite ",
					"error");

		}

	});
}


function changeStateEtape(idEtape, etat) {

	$.ajax({
		// contentType: 'application/json;
		// charset=utf-8',
		type : "GET",
		url : "/projets/UpdateStaTeEtape/"+idEtape+"/"+etat,
		// dataType: 'json',
		success : function(callback) {
			if (callback.codeRetour === 200) {
				$.notify(callback.message, "success");
				if(etat!="terminer"){
				setTimeout(function(){
					location.reload();
					},500);
				}
				else{
					
					$('#UpdateCoutReel').modal('show');
				}
			} else {
				$.notify(callback.message, "error");
				setTimeout(function(){
					location.reload();
					},500);
			}

		},
		error : function() {
			$.notify("Une erreur s'est produite ",
					"error");

		}

	});
}


