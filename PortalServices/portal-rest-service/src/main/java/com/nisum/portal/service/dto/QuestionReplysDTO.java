package com.nisum.portal.service.dto;

import java.util.List;

public class QuestionReplysDTO {
	
	QuestionariesDTO questionDetails;
	List<QuestionRepliesDTO> replyDetails;
	public List<QuestionRepliesDTO> getReplyDetails() {
		return replyDetails;
	}
	public void setReplyDetails(List<QuestionRepliesDTO> replyDetails) {
		this.replyDetails = replyDetails;
	}
	public QuestionariesDTO getQuestionDetails() {
		return questionDetails;
	}
	public void setQuestionDetails(QuestionariesDTO questionDetails) {
		this.questionDetails = questionDetails;
	}
	
	
	

}
