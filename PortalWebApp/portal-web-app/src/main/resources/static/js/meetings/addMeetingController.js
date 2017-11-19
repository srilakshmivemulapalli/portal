meetingApp.controller('addMeetingController', function($scope,
		LocationListModel, meetingService) {
	
	$scope.meeting={	};
	$scope.locationsList = LocationListModel.newLocationListInstance();
	$scope.meeting.locationId=null;
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
	};
	$scope.getAllMeetings = function(locationId,bookingDate,beginTime) {	

			beginTime=beginTime? new Date(beginTime).toISOString():null;
		
		
			bookingDate= bookingDate?new Date(bookingDate).toISOString():null;
		
		meetingService.getAllMeetings($scope.meeting.locationId,bookingDate,beginTime).then(function(response) {
			console.log(response,"res///");
	})
	};
	$scope.getallavailblemeetings = function(locationId,bookingDate,beginTime,endTime){
		
		
		if(locationId && bookingDate && beginTime && endTime){
			beginTime=beginTime? new Date(beginTime).toISOString():null;
			endTime=endTime? new Date(endTime).toISOString():null;
			bookingDate= bookingDate?new Date(bookingDate).toISOString():null;
		
		meetingService.getAvailableMeetingRoom(locationId,bookingDate,beginTime,endTime).then(function(response) {
			console.log(response,"responsedateeee///");
	})
		}
   }
	
	$scope.meetingsList = [ {
		"name" : "for  meetings",
		"description" : "surumthree", 
		"meetingRoomId" : 112,
		"locationId" : 102
	} ]
	$scope.getAllLocations();
});
