package com.nisum.portal.service.api;


import com.nisum.portal.service.dto.QuestionReplysDTO;

public interface QuestionRepliesService {

	public Integer getQuestionariesReplyCount(int questId);
	QuestionReplysDTO getQuestionariesReply(int questId);
	String saveQuestionariesReply(Integer questId, String emailId, String description);

}
