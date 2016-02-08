var app = angular.module("utilisateurs");

app.controller("connexionCtrl", function($scope) {
        $scope.clickChampEmail= function(){
            $scope.mail= false;
            var regValidEmail= new RegExp("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$","gi");
            if(!$scope.email || !$scope.email.match(regValidEmail)){
                $scope.mail=true;
            }
        }
        $scope.clickChampPass= function(){
            $scope.pass= false;
            if(!$scope.password){
                $scope.pass=true;
            }
        }
        $scope.clickConnexion= function(){
            $scope.mail= false;
             $scope.pass= false;
            var regValidEmail= new RegExp("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$","gi");
             if(!$scope.email || !$scope.email.match(regValidEmail)){
                $scope.mail=true;
            }
            if(!$scope.password){
                $scope.pass=true;
            }
            
        }
});

