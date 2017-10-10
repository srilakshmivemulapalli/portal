package com.nisum.portal.service.api;


import com.nisum.portal.service.dto.CountDTO;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
import com.nisum.portal.service.dto.QuestionsDTO;

public interface QuestionariesService {

	QuestionsDTO getQuestionaries();
	
	CountDTO getQuestionariesCount();
	
	String saveQuestions(String emailId, Integer categoryId, String question, String description);

	QuestionsDTO fetchMyQuestionaries(String emailId);

	QuestionsDTO retriveAllUnansweredQuestionaries();
	
	String saveQuestionComment(String emailId, QuestionariesCommentsDTO questionComment);
	
	boolean findQuestionById(int questionId);
	
}
