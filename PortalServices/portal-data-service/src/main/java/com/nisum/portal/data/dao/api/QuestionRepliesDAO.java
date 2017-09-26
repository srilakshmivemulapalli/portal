package com.nisum.portal.data.dao.api;

import java.util.List;


import com.nisum.portal.data.domain.QuestionReplies;

public interface QuestionRepliesDAO {

	List<QuestionReplies> getQuestionReplies();
	Integer getQuestionariesReplyCount(int questId);
	List<QuestionReplies> getQuestionariesReply(int questId);
	QuestionReplies saveQuestionReplies(QuestionReplies questionReplies);
}
