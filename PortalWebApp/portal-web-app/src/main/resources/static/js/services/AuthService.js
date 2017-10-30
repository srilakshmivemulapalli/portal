(function() {
	'use strict';
	angular.module('app').factory('authService', Service);
	function Service($http) {
		var service = {}, deferred = $q.defer(), headerInfo = {
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		};
		// $http.defaults.headers.common.Authorization =
		// response.result.token_type + ' ' + response.result.access_token;
		var httpRequestArgs = {
			url : "",
			obj : "",
			headers : "",
			file : false,
			loading : "loading"
		};
		service.authentication = {
			isAuth : false,
			userName : "",
			useRefreshTokens : true
		};
		service.postRequest = function(httpRequestArgs, cb) {
			var tStamp = new Date();

			/* Checking if the url has any params and assigning the timestamp */
			var arr = httpRequestArgs.url.split('?');
			if (httpRequestArgs.url.length > 1 && arr[1] !== '') {
				if (arr.length > 1) {
					var url = httpRequestArgs.url + "&v=" + tStamp.getTime();
				} else {
					var url = httpRequestArgs.url + "?v=" + tStamp.getTime();
				}
			}
			var fileType = (httpRequestArgs.file !== (undefined || false)) ? ""
					: "file";
			var method = (httpRequestArgs.obj === undefined) ? "GET" : "POST";
			$http.defaults.cache = false;
			var httpObj = {};
			httpObj.url = url;
			httpObj.method = method;

			if (httpRequestArgs.headers !== undefined) {
				httpObj.headers = httpRequestArgs.headers;
			}
			if (httpRequestArgs.obj !== undefined) {
				httpObj.data = httpRequestArgs.obj;
			}

			if (fileType === "file") {
				httpObj.transformRequest = angular.identity;
				httpObj.headers = {
					'Content-Type' : undefined
				};
			}
			var rtn = $http(httpObj).success(
					function(data, status, headers, config) {
						commonService.log("htp response", data);
						// $rootScope.loading = false;
						if ((data !== undefined || data !== null)
								&& data.errorCode === 200) {
							if (cb)
								cb(data);
						} else {
							if (cb)
								cb(data);
						}
						// deferred.resolve(data);
					}).error(
					function(data, status, headers, config) {
						if (data !== undefined || data !== null) {
							if (cb)
								cb(data);
						} else {
							commonService.log(
									"Failed as the data response is failed",
									data);
						}
						// deferred.reject(data);
					});
			// return deferred.promise;
		}

		service.registerUserService = function(data, cb) {
			httpRequestArgs = {
				url : serviceBase + "api/Account/Register",
				obj : data

			};
			service.postRequest(httpRequestArgs, function(response) {
				if (cb)
					cb(response);
			});
		};
	}
})();
