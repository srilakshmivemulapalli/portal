app.factory('userservice', function($http, $q) {
	var deferred = $q.defer();
	
	function _getQuestions() {
		$http.get('').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	function _postQuestion(data) {
		$http.post('', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	function _deleteQuestion(questionid) {
		$http.post('' + questionid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	function _editQuestion(data) {
		$http.put(',data').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	$scope.getQuestions=_getQuestions;
	$scope.postQuestion=_postQuestion;
	$scope.deleteQuestion=_deleteQuestion;
	$scope.editQuestion=_editQuestion;
	
})