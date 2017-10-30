package com.nisum.portal.data.dao.api;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.Questionaries;

public interface QuestionRepliesDAO {

	List<QuestionReplies> getQuestionReplies();
	Integer getQuestionariesReplyCount(int questId);
	List<QuestionReplies> getQuestionariesReply(int questId);
	QuestionReplies saveQuestionReplies(QuestionReplies questionReplies);
	QuestionReplies getReply(int replyId);
	List<Questionaries> getMyReplyQuestions(String emailId);
	List<Questionaries> getMyReplyQuestionsByCategory(String emailId, Categories category);
	List<Questionaries> getMyReplyQuestionsByPagination(String emailId, Pageable pageable);
	List<Questionaries> getMyReplyQuestionsByCategoryThroughPagination(String emailId, Categories category, Pageable pageable);
}
