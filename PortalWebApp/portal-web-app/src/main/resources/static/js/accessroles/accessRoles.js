var accessRolesApp=angular.module('accessRolesApp', ['ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('accessroles',{
		url:'/accessroles',
		templateUrl:'js/accessroles/acccessroles.html',
		controller: 'accessRolesController'
	})
	
})	  
  
		