var app = angular.module("siteApp", []);

app.controller("annoncesController", function($scope) {

   
   $scope.annonces =[ 
                    {
                        description :"Appartement en bordure de mer", 
                        superficie:"25m²", 
                        prix:"125 000XOF", 
                        image:"appartement.jpg", 
                        locaux:"2 chambres salon et salle de bain", 
                        etat:"DISPONIBLE",
                        exclusif:'/images/bien-exclusif.png', 
                        lat:"", 
                        lon:""
                   },
                   {
                        description :"Appartement de luxe", 
                        superficie:"250m²", 
                        prix:"900 000XOF", 
                        image:"appartement.jpg", 
                        locaux:"3 chambres salon et salle de bain balcon", 
                        etat:"DISPONIBLE", 
                        exclusif:'bien-exclusif.png', 
                        lat:"", 
                        lon:""
                   },
                   {
                        description :"Appartement dans un quartier calme", 
                        superficie:"53m²", 
                        prix:"325 000XOF", 
                        image:"appartement.jpg", 
                        locaux:"3 chambres salon et salle de bain", 
                        etat:"DISPONIBLE", 
                        exclusif:'bien-exclusif.png', 
                        lat:"", 
                        lon:""
                   },
                   {
                        description :"Residence les Mamounes Appartement Neuf", 
                        superficie:"45m²", 
                        prix:"225 000XOF", 
                        image:"appartement.jpg", 
                        locaux:"2 chambres salon et salle de bain", 
                        etat:"DISPONIBLE", 
                        exclusif:'bien-exclusif.png', 
                        lat:"", 
                        lon:""
                   }

    ];

 $scope.nbAnnonces ="200";
 $scope.typedName ="Diom Yero";
});

