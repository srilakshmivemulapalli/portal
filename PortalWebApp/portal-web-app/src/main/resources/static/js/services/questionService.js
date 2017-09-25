app.factory('questionService', function($http, $q) {

	var qs = {};
	var deferred = $q.defer();
	qs.text = "hello";
	qs.getQuestions = function() {
		$http.get('v1/questionaries/retrieve').success(function(response) {

			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	qs.postQuestion = function(data) {
		$http.post('', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.deleteQuestion = function(questionid) {
		$http.post('' + questionid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.editQuestion = function(data) {
		$http.put(',data').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.getQuestionById=function(id){
		$http.get('v1/questionreply/retrieveQuestionReply/'+id).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}

	return qs;
})