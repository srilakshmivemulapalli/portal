meetingApp.controller('addMeetingController', function($scope,
		LocationListModel, meetingService) {

	$scope.meeting = {};
	$scope.locationsList = LocationListModel.newLocationListInstance();
	$scope.meeting.locationId = null;
	$scope.availableMeetingRooms=[];
	$scope.dateOptions = {
		format : 'D/MM/YYYY',
		minDate : new Date(),
		defaultDate : new Date()
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
	};
	$scope.getAllMeetings = function() {
		

		$scope.meeting.startingTime = $scope.startingTime ? new Date($scope.startingTime).toISOString() : null;

		$scope.meeting.bookingDate = $scope.bookingDate ? new Date($scope.bookingDate).toISOString() : null;

		meetingService.getAllMeetings($scope.meeting.locationId, $scope.meeting.bookingDate,
				$scope.meeting.startingTime).then(function(response) {
			console.log(response, "res///");
		})
	};
	$scope.getallavailblemeetings = function() {

		if ($scope.meeting.locationId && $scope.bookingDate && $scope.startingTime && $scope.endingTime)
		{
			$scope.meeting.startingTime = $scope.startingTime ? new Date($scope.startingTime).toISOString() : null;
			$scope.meeting.endingTime = $scope.endingTime ? new Date($scope.endingTime).toISOString() : null;
			$scope.bookingDate1 = $scope.bookingDate ? new Date($scope.bookingDate).toISOString() : null;
			meetingService.getAvailableMeetingRoom($scope.meeting.locationId, $scope.meeting.bookingDate,
					$scope.meeting.startingTime, $scope.meeting.endingTime).then(function(response) {
				$scope.availableMeetingRooms= response;
				console.log($scope.availableMeetingRooms,"$scope.availableMeetingRooms")
				
			});
		}

	};
var meetingDetails = {}
	$scope.bookMeetingRoom = function() {
		console.log("jkjkj");
		var bookMeetingObj = {
			"description" : "for meetings",
			"startingTime" : "2017-11-15T13:04:32.838-0600Z",
			"emailId" : "radhi@nisum.com",
			"endingTime" : "2017-11-15T13:04:32.838-0600Z",
			"headCount" : 20,
			"meetingTitle" : "meeting",
			"locationId" : 4,
			"meetingRoomId" : 4
		};

		meetingService.bookMeetingRoom($scope.meeting).then(function(response) {
			console.log(response, "BookMeeting///");
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
