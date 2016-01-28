var chart;
var barChart;
var doubleBarChart;

$(document)
		.ready(
				function() {

					var optLine = {
						chart : {
							type : 'line',
							renderTo : 'graph2',
							borderWidth : 2,
							plotBackgroundColor : 'rgba(255, 255, 255, .9)'
						},
						title : {
							text : 'Evolution des inscriptions par mois'
						},
						xAxis : {
							categories : []
						},
						yAxis : {
							title : {
								text : 'Inscription'
							}
						},
						plotOptions : {
							line : {
								dataLabels : {
									enabled : true
								},
								enableMouseTracking : false
							}
						},
						series : []
					}

					getDataFromCtrl_1();

					function getDataFromCtrl_1() {
						$
								.ajax({
									url : '/localite/dashboard/inscription',
									type : "GET",
									dataType : "json",
									success : function(response) {

										var tabInscrits = [];
										var tabMois = [];
										var i = 0;
										for ( var key in response) {
											if (response.hasOwnProperty(key)) {
												tabMois[i] = key;
												tabInscrits[i] = response[key];
												i++;
											}
										}

										chart = new Highcharts.Chart(optLine);
										chart.xAxis[0].setCategories(tabMois
												.reverse());
										chart
												.addSeries({
													name : "Evolution des inscriptions",
													data : tabInscrits.reverse(),
													color: 'blue'
												});

									}
								});
					}

					/** ************************************************************* */

					var optBar = {
						chart : {
							type : 'pie',
							options3d : {
								enabled : true,
								alpha : 45
							},
							borderWidth : 2,
							plotBackgroundColor : 'rgba(255, 255, 255, .9)',
							renderTo : 'graph1'
						},
						title : {
							text : 'Part des inscriptions par agent'
						},
						plotOptions : {
							pie : {
								innerSize : 100,
								depth : 45
							}
						},
						series : [ {
							name : 'Delivered amount',
							data : []
						} ]

					}

					getDataFromCtrl_2();

					function getDataFromCtrl_2() {
						$.ajax({
							url : '/localite/dashboard/agent',
							type : "GET",
							dataType : "json",
							success : function(response) {
								barChart = new Highcharts.Chart(optBar);
								barChart.addSeries({
									name : "Nombre d'inscrits",
									data : response
								});

							}
						});
					}

					/** *********************************************************************************************** */

					var otpDoubleBar = {
						chart : {
							type : 'column',
							renderTo : 'graph3'
						},
						title : {
							text : 'Cout de realisations des projets'
						},
						xAxis : {
							categories : []
						},
						yAxis : [ {
							min : 0,
							title : {
								text : 'Coût'
							}
						}, {
							title : {
								text : 'Coût'
							},
							opposite : true
						} ],
						legend : {
							shadow : false
						},
						tooltip : {
							shared : true
						},
						plotOptions : {
							column : {
								grouping : false,
								shadow : false,
								borderWidth : 0
							}
						},
						series : []
					}

					getDataFromCtrl_3();

					function getDataFromCtrl_3() {
						$.ajax({
							url : '/localite/dashboard/projet/cout',
							type : "GET",
							dataType : "json",
							success : function(response) {
								var tabProjet = [];
								var tabCoutReel = [];
								var tabPrev = [];

								var i = 0;
								for ( var key in response) {
									if (response.hasOwnProperty(key)) {
										tabProjet[i] = key;
										tabPrev[i] = response[key][0];
										tabCoutReel[i] = response[key][1];
										i++;
									}
								}

								doubleBarChart = new Highcharts.Chart(
										otpDoubleBar);
								doubleBarChart.xAxis[0]
										.setCategories(tabProjet);
								doubleBarChart.addSeries({
									name : "Coût prévisionnel",
									data : tabPrev,
									color : 'rgba(248,161,63,1)',
									pointPadding : 0.3,
									pointPlacement : 0.2,
									yAxis : 1
								});
								doubleBarChart.addSeries({
									name : "Coût réel",
									data : tabCoutReel,
									color : 'rgba(186,60,61,.9)',
									pointPadding : 0.4,
									pointPlacement : 0.2,
									yAxis : 1
								});

							}
						});
					}

					/** *********************************************************************************************** */

					var otpDoubleBarre = {
						chart : {
							type : 'bar',
							renderTo : 'graph4'
						},
						title : {
							text : 'Nombres d\'emplois générés par projets'
						},
						xAxis : {
							categories : [],
							title : {
								text : null
							}
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Emplois générés',
								align : 'high'
							},
							labels : {
								overflow : 'justify'
							}
						},
						plotOptions : {
							bar : {
								dataLabels : {
									enabled : true
								}
							}
						},
						legend : {
							layout : 'vertical',
							align : 'right',
							verticalAlign : 'top',
							x : -40,
							y : 80,
							floating : true,
							borderWidth : 1,
							backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
							shadow : true
						},
						credits : {
							enabled : false
						},
						series : []
					}

					getDataFromCtrl_4();

					function getDataFromCtrl_4() {
						$.ajax({
							url : '/localite/dashboard/projet/Emploi',
							type : "GET",
							dataType : "json",
							success : function(response) {
								var tabProjet = [];
								var tabCoutReel = [];
								var tabPrev = [];

								var i = 0;
								for ( var key in response) {
									if (response.hasOwnProperty(key)) {
										tabProjet[i] = key;
										tabPrev[i] = response[key][0];
										tabCoutReel[i] = response[key][1];
										i++;
									}
								}

								doubleBarChart = new Highcharts.Chart(
										otpDoubleBarre);
								doubleBarChart.xAxis[0]
										.setCategories(tabProjet);
								doubleBarChart.addSeries({
									name : "Nb emploi prévisionnel",
									data : tabPrev,
									color: 'grey'
								});
								doubleBarChart.addSeries({
									name : "Nb emploi réel",
									color : 'rgba(165,170,217,1)',
									data : tabCoutReel
								});

							}
						});
					}
					/*********************************************************/
					
					
					var optbarpourcent = {
					        chart: {
					            type: 'column',
					        	renderTo : 'graph5'
					        },
					        title: {
					            text: 'Niveau d\'exécution des projets'
					        },
					       
					        xAxis: {
					            type: 'category'
					        },
					        yAxis: {
					            title: {
					                text: 'Pourcentage de réalisation'
					            }

					        },
					        legend: {
					            enabled: false
					        },
					        plotOptions: {
					            series: {
					                borderWidth: 0,
					                dataLabels: {
					                    enabled: true,
					                    format: '{point.y:.1f}%'
					                }
					            }
					        },

					        tooltip: {
					            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b><br/>'
					        },

					        series: []
						}

						getDataFromCtrl_5();

						function getDataFromCtrl_5() {
							$.ajax({
								url : '/localite/dashboard/projet/tauxAvancement',
								type : "GET",
								dataType : "json",
								success : function(response) {
									
									doubleBarChart = new Highcharts.Chart(
											optbarpourcent);
									
									doubleBarChart.addSeries({
										name : "Niveau d'exécution",
										data : response,
										color : 'rgb(191,36,94)',
										pointPadding : 0.4,
										pointPlacement : 0.1
										
									});
									

								}
							});
						}
					 
					 /*********************************************************************/
						
					
//						var optbartaux = {
//						        chart: {
//						            type: 'column',
//						        	renderTo : 'graph6'
//						        },
//						        title: {
//						            text: 'Taux de décaissement des projets'
//						        },
//						       
//						        xAxis: {
//						            type: 'category'
//						        },
//						        yAxis: {
//						            title: {
//						                text: 'Pourcentage de réalisation'
//						            }
//
//						        },
//						        legend: {
//						            enabled: false
//						        },
//						        plotOptions: {
//						            series: {
//						                borderWidth: 0,
//						                dataLabels: {
//						                    enabled: true,
//						                    format: '{point.y:.1f}%'
//						                }
//						            }
//						        },
//
//						        tooltip: {
//						            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
//						            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b><br/>'
//						        },
//
//						        series: []
//							}
//
//							getDataFromCtrl_6();
//
//							function getDataFromCtrl_6() {
//								$.ajax({
//									url : '/localite/dashboard/projet/tauxDecaissement',
//									type : "GET",
//									dataType : "json",
//									success : function(response) {
//										
//										doubleBarChart = new Highcharts.Chart(
//												optbartaux);
//										
//										doubleBarChart.addSeries({
//											name : "Taux de décaissement",
//											data : response,
//											color : 'rgb(209,182,6)',
//											pointPadding : 0.4,
//											pointPlacement : 0.1
//											
//										});
//										
//
//									}
//								});
//							}
//						 

				});
