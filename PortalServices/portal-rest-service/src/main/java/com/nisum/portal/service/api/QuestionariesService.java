package com.nisum.portal.service.api;


import org.springframework.data.domain.Pageable;

import com.nisum.portal.service.dto.CountDTO;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
import com.nisum.portal.service.dto.QuestionsDTO;

public interface QuestionariesService {

	QuestionsDTO getQuestionaries();
	
	CountDTO getQuestionariesCount();
	
	String saveQuestions(String emailId, Integer categoryId, String question, String description);

	QuestionsDTO fetchMyQuestionaries(String emailId);

	QuestionsDTO retriveAllUnansweredQuestionaries();
	
	QuestionariesCommentsDTO saveQuestionComment(String emailId, QuestionariesCommentsDTO questionComment);
	
	boolean findQuestionById(int questionId);
	
	QuestionsDTO getQuestionariesByPagination(Pageable pageable);
	
	QuestionsDTO getQuestionariesByCategory(Integer categoryId, Pageable pageable);
	
	QuestionsDTO retrieveAllUnansweredQuestionariesByCategory(Integer categoryId, Pageable pageable);
	
	QuestionsDTO retrieveAllUnansweredQuestionariesByPagination(Pageable pageable);
	
	QuestionsDTO fetchMyQuestionariesByCategory(String emailId, Integer categoryId, Pageable pageable);
	
	QuestionsDTO fetchMyQuestionariesByPagination(String emailId, Pageable pageable);

}
