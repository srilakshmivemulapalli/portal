var mainApp=angular.module('mainApp', [ 'ngRoute' ])
	   .config(function($routeProvider) {
		   $routeProvider.when('/main',{
			   templateUrl: 'js/main/main.html',
			   controller: 'mainController'
		   })
})    
		