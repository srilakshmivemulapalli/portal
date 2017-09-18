var loginApp=angular.module('loginApp', [ 'ngRoute' ])
	   .config(function($routeProvider) {
		   $routeProvider.when('/login',{
			   templateUrl: 'js/login/login.html',
			   controller: 'loginController'
		   })
})    
		