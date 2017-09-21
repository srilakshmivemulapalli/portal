/**
 * 
 */
var app = angular.module('admin.services', []);
app.factory("services", [ '$http',  function($http) {

	return {

		addCateogy : function(empObject, callback) {

			$http({
				url : 'v1/category/addCategory',
				method : "POST",
				data : empObject
			}).then(function(response) {
				callback(response);
			}, function(response) { // optional
				callback(response);

			});

		},
	}
} ]);