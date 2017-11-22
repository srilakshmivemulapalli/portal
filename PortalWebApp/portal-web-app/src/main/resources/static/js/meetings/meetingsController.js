meetingApp.controller('meetingsController', function($scope, meetingService) {
  meetingService.getAllMeetings(null,new Date().toISOString(),null).then(function(response) {
		$scope.AllMeetings = response;
	});
});
