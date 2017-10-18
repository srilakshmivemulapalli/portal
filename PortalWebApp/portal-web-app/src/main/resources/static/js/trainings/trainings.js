var trainingsApp=angular.module('trainingsApp', [ 'ui.router'])
.config(function($stateProvider){
		$stateProvider.state('trainings',{
		url:'/trainings',
		templateUrl:'js/trainings/trainings.html',
		controller: 'trainingsController'
	}).state('onlineTrainings',{
		url:'/onlineTrainings',
		templateUrl:'js/trainings/onlineTrainings.html',
		controller: 'onlineTrainingsController'
	}).state('classRoomTrainings',{
		url:'/classRoomTrainings',
		templateUrl:'js/trainings/classRoomTrainings.html',
		controller: 'classRoomTrainingsController'
	}).state('myTrainings',{
		url:'/myTrainings',
		templateUrl:'js/trainings/myTrainings.html',
		controller: 'myTrainingsController'
	})
}) 

