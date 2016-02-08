var app = angular.module("masters", []);
app.directive("theheader", function() {
    return {
        restrict : 'EA',
        replace : true,
        transclude : true,
    
        template : '<div class="header" >' +
                        '<div class="general">' +
                            '<span class="structure">Foncier</span><br/>' +

                            // '<span class="exp">Application de gestion du foncier</span>' +
                        '</div>' +
                        '<div class="barre"></div>' +
                    '</div>'
    }
});