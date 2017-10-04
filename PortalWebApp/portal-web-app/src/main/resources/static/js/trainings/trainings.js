var trainingsApp=angular.module('trainingsApp', [ 'ui.router' ])
.config(function($stateProvider){
		$stateProvider.state('trainings',{
		url:'/trainings',
		templateUrl:'js/trainings/trainings.html',
		controller: 'trainingsController'
	})
}) 