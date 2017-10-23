package com.nisum.portal.data.dao.api;

import java.util.List;


import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.Questionaries;

public interface QuestionRepliesDAO {

	List<QuestionReplies> getQuestionReplies();
	Integer getQuestionariesReplyCount(int questId);
	List<QuestionReplies> getQuestionariesReply(int questId);
	QuestionReplies saveQuestionReplies(QuestionReplies questionReplies);
	QuestionReplies getReply(int replyId);
	List<Questionaries> getMyReplyQuestions(String emailId);
}
