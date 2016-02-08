angular.module('utilisateurs')
.controller('createUserCtrl', function($scope, $location, authentification) {
	$scope.telephone = '';
	$scope.cni = '';
	$scope.fax = '';
	$scope.cniCorrect = function() {
		var cniValide = new RegExp("^[0-9]{12}[0-9]+");
		return ($scope.actionValider == undefined) ? true : $scope.cni.match(cniValide);
	};
	$scope.telephoneCorrect = function() {
		var numeroValide = new RegExp("^[0-9]{8}[0-9]+");
		return ($scope.actionValider == undefined) ? true : $scope.telephone.match(numeroValide);
	};
	$scope.faxCorrect = function() {
		var numeroValide = new RegExp("^[0-9]{8}[0-9]+");
		return ($scope.actionValider == undefined) ? true : (($scope.fax == '') || $scope.fax.match(numeroValide));
	};
	$scope.annulation = function() {
		$location.path('');
	};
	function validation() {
		return ($scope.faxCorrect() && $scope.telephoneCorrect() && $scope.cniCorrect());
	}
	$scope.envoi = function() {
		if(!validation())
			return;

		var userData = {
			'user ' : {
				'prenom' : $scope.prenom,
				'nom' : $scope.nom,
				'telephone' : $scope.telephone,
				'fax' : $scope.fax,
				'adresse' : $scope.adresse,
				'cni': $scope.cni,
				'email' : $scope.email,
				'role' : $scope.statut
			}
		};
		authentification.creer(userData)
		.success(function(data) {
			$scope.codeReponse = data.code;
		})
		.error(function(data) {
			$scope.codeReponse = data.code;
			$scope.messageReponse = data.error.username;
		});
		$location.path('');
	};
});