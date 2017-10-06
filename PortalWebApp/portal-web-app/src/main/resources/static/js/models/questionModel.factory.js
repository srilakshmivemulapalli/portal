app.factory('QuestionModel', [ '$http', function($http) {
//	function Question(questionCopy) {
//		this.totalQuestions = null;
//		this.totalUsers = null;
//		this.questionDetails = [];
//
//		angular.extend(this, questionCopy);
//		this.clone = function(otherQuestion) {
//			return new User(otherQuestion);
//		};
//	}
	function QuestionDetail(questionDetailCopy) {
		this.categoryName = null;
		this.createdDate = null;
		this.description = null;
		this.displayImage = null;
		this.displayName = null;
		this.emailId = null;
		this.question = null;
		this.questionId = null;
		this.questionRepliesCount = null;
		angular.extend(this, questionDetailCopy);
		this.clone = function(otherQuestionDetail) {
			return new QuestionDetail(otherQuestionDetail);
		};
	}

	return  new QuestionDetail({})
	

} ]);