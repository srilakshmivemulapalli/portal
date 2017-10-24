package com.nisum.portal.service.api;


import org.springframework.data.domain.Pageable;

import com.nisum.portal.service.dto.CountDTO;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
import com.nisum.portal.service.dto.QuestionsDTO;

public interface QuestionariesService {

	QuestionsDTO getQuestionaries();
	
	CountDTO getQuestionariesCount();
	
	String saveQuestions(String emailId, Integer categoryId, String question, String description);
	
	String updateQuestion(Integer questionId,Integer categoryId,String question,String description,String emailId);

	QuestionsDTO fetchMyQuestionaries(String emailId);

	QuestionsDTO retriveAllUnansweredQuestionaries();
	
	QuestionariesCommentsDTO saveQuestionComment(String emailId, QuestionariesCommentsDTO questionComment);
	
	boolean findQuestionById(int questionId);
	
	QuestionsDTO getQuestionariesByPagination(Pageable pageable);
	
	QuestionsDTO getQuestionariesByCategory(Integer categoryId, Pageable pageable);
	
	
}
