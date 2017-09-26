var questionApp=angular.module('questionsApp', [ 'ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('questions',{
		url:'/questions',
		templateUrl:'js/questions/questions.html',
		controller: 'questionsController'
	}).state('question',{
		url:'/question/:questionid',
		templateUrl:'js/questions/questionreply.html',
		controller: 'questionReplyController'
	})
})    
		