meetingApp.controller('meetingsController', function($scope) {
	.config(function($stateProvider){
		$stateProvider.state('meetings',{
			url:'/meetings',
			templateUrl:'js/meetings/meetings.html',
			controller: 'meetingsController'
		})
		
	
});
