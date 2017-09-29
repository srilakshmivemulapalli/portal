app.factory('CategoryListModel',["CategoryModel", function( categoryModel){
    	function CategoryList() {
    	   var _self = this;
    	   this.categories = [];
    	   
    	   this.addCategories = function( obj){
				var category = categoryModel.clone(obj);
				if(_self.categories.length > 0){
					var check = true;
					_self.categories.map(function(innerCategory){
			    		 if(innerCategory.categoryId === category.categoryId){
			    			check = false;
			    		 }
					})
					if(check){
						_self.categories.push(category);
					}
					
				}else{
					_self.categories.push(category);
				}
      	  };
      	  
         this.deleteCatgory =function( category){
        	 var i = -1;
    			
				_self.categories.find(function(innerCategory){
				i++;
				return innerCategory.categoryId === category.categoryId;
				});
      			
				_self.categories.splice(i,1);
         }
         
      	  
      	  this.getCategories = function(){
      		  return _self.categories;
      	  };
      	  
      	  this.editCategory=function(category)
      	  {
      		  	var i= -1;
      		  	_self.categories.find(function(innerCategory){
      		  		i++;
      		  		if(innerCategory.categoryId===category.categoryId){
      		  			_self.categories[i]=category;
      		  		}
      		  	})
      		  	
      	  }
      	 
      	  
    	}
    	return {
    		
    		
	      	newCategoryListInstance: function() {
	  			 return new CategoryList();
	  		 }  
    		
    	}
    	
    }]);
