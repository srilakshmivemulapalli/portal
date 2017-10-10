app
		.factory(
				'CommentListModel',
				[
						'$http',
						'CommentModel',
						function($http, CommentModel) {
							function CommentList() {
								var _self = this;
								this.comments = [];

								this.addComments = function(obj) {
									var commentDetail = CommentModel.clone(obj);
									if (_self.comments.length > 0) {
										var check = true;
										_self.comments
												.map(function(innerComment) {

													if (innerComment.commentId === commentDetail.commentId) {
														check = false;
													}

												});
										if (check) {
											_self.comments.push(commentDetail);
										}
									} else {
										_self.comments.push(commentDetail);
									}

								};
							}
							return {

								newCommentListInstance : function() {
									return new CommentList();
								}
							}

						} ]);