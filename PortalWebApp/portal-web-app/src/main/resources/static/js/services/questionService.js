app.factory('questionService', function($http, $q,commonService) {

	var qs = {};
	
	
	qs.getQuestions = function(categoryId,startindex,questioncount) {
		var deferred = $q.defer();
		$http.get('v1/questionaries/retrieve/allQuestions/'+categoryId+'?page='+startindex+'&size='+questioncount,{ headers:{'EmailId':commonService.emailId}}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	
	qs.getAllUnansweredQuestions = function(categoryId,startindex,questioncount) {
		var deferred = $q.defer();
		$http.get('v1/questionaries/retrieve/unanswQuestions/'+categoryId+'?page='+startindex+'&size='+questioncount).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	qs.retriveMyQuestionaries = function(emailId,categoryId,startindex,questioncount) {
		var deferred = $q.defer();
		$http.get('v1/questionaries/retrieve/myQuestions/'+emailId+'/'+categoryId+'?page='+startindex+'&size='+questioncount).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.retriveMyReplyQuestions = function(emailId,categoryId,startindex,questioncount) {
		var deferred = $q.defer();
		$http.get('v1/questionreply/retrieve/myReplyQuestions/'+emailId+'/'+categoryId+'?page='+startindex+'&size='+questioncount).success(function(response) {
			deferred.resolve(response);
			console.log('in retriveMyReplyQuestions' +response)
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.getQuestionsCount = function() {
		
		var deferred = $q.defer();
		$http.get('v1/questionaries/retrieveCount').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	
	qs.addQuestion = function(data) {
		
		var deferred = $q.defer();
		$http.post('v1/questionaries/save', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.editQuestion=function(data){
	
		var deferred = $q.defer();
		$http.post('v1/questionaries/update', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.deleteQuestion = function(questionid) {
		var deferred = $q.defer();
		$http.post('' + questionid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.sendReply = function(data) {
		var deferred = $q.defer();
		$http.post('v1/questionreply/save',data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.getQuestionById=function(id){
		var deferred = $q.defer();
		$http.get('v1/questionreply/retrieve/questionReply/'+id).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	
	
	return qs;
})