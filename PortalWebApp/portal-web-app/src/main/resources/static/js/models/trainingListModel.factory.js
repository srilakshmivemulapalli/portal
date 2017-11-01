app
		.factory(
				'TrainingListModel',
				[
						'TrainingModel',
						function(trainingModel) {

							function TrainingList() {
								var _self = this;
								this.trainings = [];

								this.addtrainings = function(obj) {
									var trainingDetail = trainingModel
											.clone(obj);
									if (_self.trainings.length > 0) {
										var check = true;
										_self.trainings
												.map(function(innerTraining) {
													if (innerTraining.trainingId === trainingDetail.trainingId) {
														check = false;
													}

												})
										if (check) {
											_self.trainings
													.push(trainingDetail);
										}
									} else {
										_self.trainings.push(trainingDetail);
									}

								}
								this.editTraining=function(obj){
									var i=-1;
									_self.trainings.find(function(innerTraining){
										i++;
										if(innerTraining.trainingId===obj.trainingId){
					      		  			_self.trainings[i].trainingPresence=innerTraining.trainingPresence;
					      		  		}
									})
								}
								this.editCommentStatus=function(obj){
									var i=-1;
									_self.trainings.find(function(innerTraining){
										i++;
										if(innerTraining.trainingId===obj.trainingId){
					      		  			_self.trainings[i].commentStatus=innerTraining.commentStatus;
					      		  		}
									})
								}
								this.deleteTraining=function(obj){
									 var i = -1;
						    			
										_self.trainings.find(function(innerTraining){
										i++;
										return innerTraining.trainingId ===obj.TrainingId;
										});
						      			
										_self.trainings.splice(i,1);
								}
							};

							return {
								newTrainingListInstance : function() {
									return new TrainingList();
								}
							}

						} ])