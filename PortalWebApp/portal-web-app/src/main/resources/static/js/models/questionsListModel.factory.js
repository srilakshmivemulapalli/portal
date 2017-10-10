app.factory('QuestionsListModel', [ "QuestionModel", function(questionModel) {
	function QuestionList() {
		var _self = this;
		this.questions = {};
		this.questions.totalQuestions=null;
		this.questions.totalUsers=null;
		this.questions.questionDetails=[];
		
		this.addquestion=function(obj){
			_self.questions.totalQuestions=obj.totalQuestions;
			_self.questions.totalUsers=obj.totalUsers;
		}
		this.addquestionDetails = function(obj) {
			
			var questionDetail= questionModel.clone(obj);
				
			if (_self.questions.questionDetails.length > 0) {
				var check = true;
				_self.questions.questionDetails.map(function(innerQuestion) 
						{
					if (innerQuestion.questionId === questionDetail.questionId) {
						check = false;
					}
				})
				if (check) {
					_self.questions.questionDetails.push(questionDetail);
				}

			} else {
				_self.questions.questionDetails.push(questionDetail);
			}
		};

	}
	return {

		newQuestionListInstance : function() {
			return new QuestionList();
		}

	}

} ]);
