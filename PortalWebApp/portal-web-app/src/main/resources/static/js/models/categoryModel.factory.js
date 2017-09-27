angular.module('nisumApp')
    .factory('CategoryModel', ['$http', function($http) {
    		function Category(categoryCopy) {
    		  this.categoryId = null;
    		  this.categoryName= null;
    		  this.description = null;
      		  angular.extend(this, categoryCopy);
      		  this.clone = function(otherCategory) {
      			 return new Category(otherCategory);
      		 };
      	  }
      	  
      	  return new Category({});

    }]);