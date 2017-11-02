meetingApp.controller('addMeetingController', function($scope,
		LocationListModel, meetingService) {
	
	$scope.meeting={};
	$scope.locationsList = LocationListModel.newLocationListInstance();
	$scope.dateOptions = {
			format : 'D/MM/YYYY',
			minDate : new Date(),
			defaultDate: new Date()
	}
	$scope.timeOptions = {
			format : 'LT',
		}
	$scope.getAllLocations = function() {
		meetingService.getLocations().then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage;
			} else {
				
				response.map(function(location) {
					$scope.locationsList.addLocations(location);

				})
			}
		}, function(response) {
			console.log(response);
		})
	}

	$scope.meetingsList = [ {
		"name" : "for  meetings",
		"description" : "surumthree",
		"meetingRoomId" : 112,
		"locationId" : 102
	} ]
	$scope.getAllLocations();
});
