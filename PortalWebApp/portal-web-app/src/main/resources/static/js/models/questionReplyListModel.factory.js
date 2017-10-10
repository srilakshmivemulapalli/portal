app
		.factory(
				'QuestionReplyListModel',
				[
						"QuestionModel",
						"CommentListModel","ReplyListModel",
						function(questionModel, CommentListModel,ReplyListModel) {
							function QuestionReplyList() {
								var replyList=ReplyListModel.newReplyListInstance();
								var questionCommentList = CommentListModel
										.newCommentListInstance();
								var _self = this;
								
								this.questionDetails = {};
								this.questionDetails.questionComments=[];
								this. replyDetails = [];
								this.addReplyListDetails = function(obj) {
									_self. questionDetails = questionModel
											.clone(obj.questionDetails);
									if (obj.questionDetails.questionComments.length > 0) {
										obj.questionDetails.questionComments
												.map(function(innerComment) {
													questionCommentList
															.addComments(innerComment)
												});
										_self. questionDetails.questionComments = questionCommentList.comments;

									}
									if(obj.replyDetails.length>0){
										obj.replyDetails.map(function(innerReply){
											replyList.addReplies(innerReply);
										})
									}
									_self. replyDetails=replyList.replies;
									
								};

							}
							return {

								newQuestionReplyListInstance : function() {
									return new QuestionReplyList();
								}

							}

						} ]);
