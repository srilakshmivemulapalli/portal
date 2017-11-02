app.factory('LocationModel', [ function() {

	function Location(locationCopy) {
		this.locationId = null;
		this.locationName = null;
		angular.extend(this, locationCopy);
		this.clone = function(otherLocation) {
			return new Location(otherLocation);
		};
	}

	return new Location({})

} ]);