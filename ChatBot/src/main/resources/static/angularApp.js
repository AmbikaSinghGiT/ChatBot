var app = angular.module('chatbotApp', []);

app.controller('myCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.receiveDataFromJS = function(data) {
		//alert(data);
		$http.post('/api/v1/saveData', data).then(function(response) {
			console.log("Data saved successfully");
		}, function(error) {
			alert(JSON.stringify(error));
			console.log("Error saving data", error);
		});

	};
}]);