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
	.state('addquestion',{
		url:'/addquestion',
		templateUrl:'js/questions/addquestion.html',
		controller: 'addQuestionController'
	})
	.state('editquestion',{
		url:'/editquestion',
		params: { question: null },
		templateUrl:'js/questions/editquestion.html',
		controller: 'editQuestionController'
	})
})    
		