app.factory('TrainingModel',[function(){
	function Training(trainingCopy) {
		  this.trainingId = null;
		  this.trainingTitle= null;
		  this.description = null;
		  this.trainerName = null;
		  this.trainingDate = null;
		  this.trainingStatus = null;
		  this.trainingType = null;
		  this.trainingPresence = null;
		  angular.extend(this, trainingCopy);
		  this.clone = function(otherTraining) {
			 return new Training(otherTraining);
		 };
	  }
	  
	  return new Training({});
}])


