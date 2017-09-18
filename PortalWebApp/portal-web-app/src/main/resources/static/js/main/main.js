var mainApp=angular.module('mainApp', [ 'ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('main',{
		url:'/main',
		templateUrl:'js/main/main.html',
		controller: 'mainController'
	})
})    
		