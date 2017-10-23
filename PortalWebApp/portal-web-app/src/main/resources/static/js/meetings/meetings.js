var mainApp=angular.module('meetingsApp', [ 'ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('meetings',{
		url:'/meetings',
		templateUrl:'js/meetings/meetings.html',
		controller: 'meetingsController'
	})
})    
		