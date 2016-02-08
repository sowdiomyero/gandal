var app = angular.module("masters");
app.directive("themenu", function() {
    return {
        restrict : 'EA',
        replace : true,
        transclude : true,
        templateUrl:"./views/partials/themenu.html"
    }
});