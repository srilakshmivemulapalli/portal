app
		.factory(
				'QuestionReplyListModel',
				[
						"QuestionModel",
						"CommentListModel","ReplyListModel","CommentModel",
						function(questionModel, CommentListModel,ReplyListModel,CommentModel) {
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
								
								
								this.addQuestionComment=function(obj){
									var commentDetail=CommentModel.clone(obj);
									_self.questionDetails.questionComments.push(commentDetail);
								};
								
								
								this.addReplyComment=function(obj,replyId){
									var i = -1;

									var commentDetail=CommentModel.clone(obj);
									_self.replyDetails.find(function(innerReply){
										i++;
										if(innerReply.replyId=== replyId){
											_self.replyDetails[i].replyComments.push(commentDetail);
										}
									})
								};

							}
							return {

								newQuestionReplyListInstance : function() {
									return new QuestionReplyList();
								}

							}

						} ]);
