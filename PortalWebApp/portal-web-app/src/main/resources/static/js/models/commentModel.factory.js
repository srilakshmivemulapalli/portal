app.factory('CommentModel', [ '$http', function($http) {

	function Comment(commentCopy) {
		
		this.commentId = null;
		this.createdDate = null;
		this.emailId = null;
		this.commentdescription = null;
		angular.extend(this, commentCopy);
		this.clone = function(otherComment) {
			return new Comment(otherComment);
		};
	}

	return  new Comment({})
	

} ]);