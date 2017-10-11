app.factory('TrainingListModel',['TrainingModel',function(trainingModel){
	
	function TrainingList(){
	var _self=this;
	this.trainings=[];
	
	
	this.addtrainings=function(obj){
		var trainingDetail=trainingModel.clone(obj);
		if(_self.trainings.length>0){
			var check=true;
			_self.trainings.map(function(innerTraining){
				if(innerTraining.trainingId===trainingDetail.trainingId){
					check=false;
				}
				
			})
			if(check){
				_self.trainings.push(trainingDetail);
			}
		}
		else{
			_self.trainings.push(trainingDetail);
		}
		
	}
	};
	return{
		newTrainingListInstance: function(){
			return new TrainingList();
		}
	}
	
}])