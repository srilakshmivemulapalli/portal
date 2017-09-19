var adminApp=angular.module('adminApp', ['ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('admin',{
		url:'/admin',
		templateUrl:'js/admin/admin.html',
		controller: 'adminController'
	})
	
})	  
  
		