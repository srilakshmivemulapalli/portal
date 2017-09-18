var adminApp=angular.module('adminApp', [ 'ngRoute' ])
	   .config(function($routeProvider) {
		   $routeProvider.when('/admin',{
			   templateUrl: 'js/admin/admin.html',
			   controller: 'adminController'
		   })
})    
		