var mainApp=angular.module('questionsApp', [ 'ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('questions',{
		url:'/questions',
		templateUrl:'js/questions/questions.html',
		controller: 'questionsController'
	})
})    
		