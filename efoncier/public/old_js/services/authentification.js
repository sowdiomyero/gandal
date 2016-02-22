angular.module('utilisateurs')

.factory('authentification', function authentification($http) {
	return {
		identifier : function(donnees) {
			return $http.post('http://localhost:8080/api/user', donnees);
		},

		creer : function(donnees) {
			return $http.post('http://localhost:8080/api/user', donnees);
		}
	}
});