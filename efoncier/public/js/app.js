angular.module('utilisateurs', ['ngRoute', 'masters'])
.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider
        .when('/', {
            templateUrl : 'views/connexion.html',
            controller : 'connexionCtrl',
        })
        .when('/createUser', {
            templateUrl : 'views/createuser.html',
            controller : 'createUserCtrl',
        })
        .otherwise({
            redirectTo : '/'
        });
    }
]);