package com.nisum.portal.service.api;


import com.nisum.portal.service.dto.QuestionRepliesDTO;
import com.nisum.portal.service.dto.QuestionReplyCommentsDTO;
import com.nisum.portal.service.dto.QuestionReplysDTO;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.service.dto.QuestionsDTO;

public interface QuestionRepliesService {

	public Integer getQuestionariesReplyCount(int questId);
	QuestionReplysDTO getQuestionariesReply(int questId);
	QuestionRepliesDTO saveQuestionariesReply(Integer questId, String emailId, String description);
	QuestionReplyCommentsDTO saveReplyComment(String emailId, QuestionReplyCommentsDTO questionComment);
	boolean findReplyById(int replyId);
	QuestionsDTO fetchMyReplyQuestions(String emailId);

}
