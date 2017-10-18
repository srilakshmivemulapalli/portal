app.factory('TrainingModel',[function(){
	function Training(trainingCopy) {
		  this.trainingId = null;
		  this.trainingTitle= null;
		  this.description = null;
		  this.trainerName = null;
		  this.trainingDate = null;
		  this.trainingStatus = null;
		  this.trainingType = null;
		  this.trainingStartDate=null;
		  this.trainingEndDate=null;
		  this.trainingStartTime=null;
		  this.trainingEndTime=null;
		  this.trainingPresence = null;
		  thistrainerEmailId=null;
		  angular.extend(this, trainingCopy);
		  this.clone = function(otherTraining) {
			 return new Training(otherTraining);
		 };
	  }
	  
	  return new Training({});
}])


