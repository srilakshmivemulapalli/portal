meetingApp.controller('addMeetingController', function($scope,
		LocationListModel, meetingService,commonService) {

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
		 setTimeout(function(){
			 document.querySelector('.btn-group').style.width = "182px";
			 document.querySelector('#location button').style.width = "100%";
			 document.querySelector('#location ul').style.width = "182px";
			 document.querySelector('#meetingroom .btn-group').style.width = "182px";
			 document.querySelector('#meetingroom button').style.width = "100%";
			 document.querySelector('#meetingroom ul').style.width = "182px";
		 },100);
		 
		
		$scope.meeting.startingTime = $scope.startingTime ? new Date($scope.startingTime).toISOString() : null;

		$scope.meeting.bookingDate = $scope.bookingDate ? new Date($scope.bookingDate).toISOString() : null;

		meetingService.getAllMeetings($scope.meeting.locationId, $scope.meeting.bookingDate,
				$scope.meeting.startingTime).then(function(response) {
					$scope.AllMeetings = response;
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
	$scope.meeting.emailId=commonService.emailId;

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
