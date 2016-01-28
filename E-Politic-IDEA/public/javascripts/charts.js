//var chart;

var chart;
var spider;
var chart2;
var chartArea;
var chartPie;
$(document).ready(function(){

    var options = {
        chart: {
            renderTo: 'graph',
            type: 'bar',
            backgroundColor: {
                linearGradient: [0, 0, 500, 500],
                stops: [
                    [0, 'rgb(255, 255, 255)'],
                    [1, 'rgb(240, 240, 255)']
                ]
            },
            borderWidth: 2,
            plotBackgroundColor: 'rgba(255, 255, 255, .9)',
            plotShadow: true,
            plotBorderWidth: 1
        },
        credits: {
            enabled: false
        },
        title: {
            text: 'Evolution Des Inscription Par Région'
        },
        xAxis: {
            categories: [] //['Dakar', 'Thies', 'Saint Louis']  // liste des regions
        },
        yAxis: {
            title: {
                text: 'Inscritions sur la population totale et celle des electeurs'
            }
        },
        series: []
    };
     requestData();
/// end graph population-nbinscrits-nbelecteurs


    /** DEBUT **** DIAGRAMME D EVOLUTIONS DES INSCRIPTIONS P/R AUX OBJECTIFS  */
    var optionsObjectifs = {
        chart: {
            renderTo: 'graph2',
            type: 'bar',
            backgroundColor: {
                linearGradient: [0, 0, 500, 500],
                stops: [
                    [0, 'rgb(255, 255, 255)'],
                    [1, 'rgb(240, 240, 255)']
                ]
            },
            borderWidth: 2,
            plotBackgroundColor: 'rgba(255, 255, 255, .9)',
            plotShadow: true,
            plotBorderWidth: 1
        },
        credits: {
            enabled: false
        },
        title: {
            text: 'Evolution Des Inscription P/R aux Objectifs Par Région'
        },
        xAxis: {
            categories: []  // liste des regions
        },
        yAxis: {
            title: {
                text: 'Inscritions et Objectifs'
            }
        },
        series: []
    };
    requestData2();

    /** FIN **** DIAGRAMME D EVOLUTIONS DES INSCRIPTIONS P/R AUX OBJECTIFS  */

    var optsPie = {
        chart: {
            renderTo: 'pie',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        credits: {
            enabled: false
        },
        title: {
            text: 'Repartition des Inscriptions Par Région Campagne 2015'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Répartirion des Inscriptions Par Région',
            data: []
        }]
    }
    requestPieData();


    function requestData() {
        $.ajax({
            url: '/admin/regions/',
            type: "GET",
            dataType: "json",
            success: function(response) {
                var tabRegions=[];
                var  tabInscrits=[];
                var tabElecteurs=[];
                var tabPop=[];
                var i=0;
                for (var key in response) {
                    if (response.hasOwnProperty(key)) {
                        tabRegions[i] = key;
                        tabPop[i] =  response[key][0];
                        tabInscrits[i] =  response[key][1];
                        tabElecteurs[i] =  response[key][2];
                        i++;
                    }
                }
                chart = new Highcharts.Chart(options);
                chart.xAxis[0].setCategories(tabRegions)
                chart.addSeries({
                    name: "Population Totale",
                    data: tabPop
                }, false);
                chart.addSeries( {
                    name: "Nombre Inscrits",
                    data: tabInscrits
                });
                chart.addSeries(  {
                    name: "Nombre Electeurs",
                    data: tabElecteurs
                });
            }
        });
    }

    function requestData2() {
        $.ajax({
            url: '/admin/objectifs/regions/',
            type: "GET",
            dataType: "json",
            success: function(response) {
                var tabRegions=[];
                var  tabInscrits=[];
                var tabObjectifs=[];
                var tabPop=[];
                var i=0;
                for (var key in response) {
                    if (response.hasOwnProperty(key)) {
                        tabRegions[i] = key;
                        tabPop[i] =  response[key][0];
                        tabInscrits[i] =  response[key][1];
                        tabObjectifs[i] =  response[key][2];
                        i++;
                    }
                }

                chart2 = new Highcharts.Chart(optionsObjectifs);
                chart2.xAxis[0].setCategories(tabRegions)
                /*chart2.addSeries({
                    name: "Population Totale",
                    data: tabPop
                }, false);*/
                chart2.addSeries( {
                    name: "Nombre Inscrits",
                    data: tabInscrits
                });
                chart2.addSeries(  {
                    name: "Objectifs Définis",
                    data: tabObjectifs
                });
            }
        });
    }
    function requestPieData() {
        $.ajax({
            url: '/admin/per/regions/',
            type: "GET",
            dataType: "json",
            success: function(response) {
                chartPie = new Highcharts.Chart(optsPie);
                chartPie.addSeries({
                    type: 'pie',
                    name: 'Part en %',
                    data: response
                });
            }
        });
    }


    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });


    var optionsSpider = {
        chart: {
            renderTo: 'courbe',
            polar: true,
            type: 'line'
        },

        title: {
            text: 'Couverture Territoriale',
            x: -80
        },

        pane: {
            size: '75%'
        },

        xAxis: {
            categories: [], //['Sales', 'Marketing', 'Development', 'Customer Support', 'Information Technology', 'Administration'],
            tickmarkPlacement: 'on',
            lineWidth: 0
        },

        yAxis: {
            gridLineInterpolation: 'polygon',
            lineWidth: 0,
            min: 0
        },

        tooltip: {
            shared: true,
            pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y:,.0f}</b><br/>'
        },

        legend: {
            align: 'right',
            verticalAlign: 'top',
            y: 70,
            layout: 'vertical'
        },

        series: []

    };


    requestSpiderData();

    function requestSpiderData() {
       /* $.ajax({
            url: '/admin/per/regions/',
            type: "GET",
            dataType: "json",
            success: function(response) {
                spider = new Highcharts.Chart(optionsSpider);
                spider.addSeries({
                    pointPlacement: 'on',
                    name: 'Objectifs',
                    data: [50000, 39000, 42000, 31000, 26000, 14000]
                });
            }
        });*/



        $.ajax({
            url: '/admin/objectifs/regions/',
            type: "GET",
            dataType: "json",
            success: function(response) {
                var tabRegions=[];
                var  tabInscrits=[];
                var tabObjectifs=[];
                var tabPop=[];
                var i=0;
                for (var key in response) {
                    if (response.hasOwnProperty(key)) {
                        tabRegions[i] = key;
                        tabPop[i] =  response[key][0];
                        tabInscrits[i] =  response[key][1];
                        tabObjectifs[i] =  response[key][2];
                        i++;
                    }
                }

                spider = new Highcharts.Chart(optionsSpider);
                spider.xAxis[0].setCategories(tabRegions)

                spider.addSeries( {
                    name: "Nombre Inscrits",
                    data: tabInscrits
                });
                spider.addSeries(  {
                    name: "Objectifs Définis",
                    data: tabObjectifs
                });
            }
        });
    }
    
    
});

