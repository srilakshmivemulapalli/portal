app.factory('LocationListModel',["LocationModel", function( locationModel){
    	function LocationList() {
    	   var _self = this;
    	   this.locations = [];
    	   
    	   this.addLocations = function( obj){
				var location = locationModel.clone(obj);
				if(_self.locations.length > 0){
					var check = true;
					_self.locations.map(function(innerLocation){
			    		 if(innerLocation.locationId === location.locationId){
			    			check = false;
			    		 }
					})
					if(check){
						_self.locations.push(location);
					}
					
				}else{
					_self.locations.push(location);
				}
      	  };
      	  
         this.deleteCatgory =function( location){
        	 var i = -1;
    			
				_self.locations.find(function(innerLocation){
				i++;
				return innerLocation.locationId === location.locationId;
				});
      			
				_self.locations.splice(i,1);
         }
         
      	  
      	  this.getLocations = function(){
      		  return _self.locations;
      	  };
      	  
      	  this.editLocation=function(location)
      	  {
      		  	var i= -1;
      		  	_self.locations.find(function(innerLocation){
      		  		i++;
      		  		if(innerLocation.locationId===location.locationId){
      		  			_self.locations[i]=location;
      		  		}
      		  	})
      		  	
      	  }
      	 
      	  
    	}
    	return {
    		
    		
	      	newLocationListInstance: function() {
	  			 return new LocationList();
	  		 }  
    		
    	}
    	
    }]);
