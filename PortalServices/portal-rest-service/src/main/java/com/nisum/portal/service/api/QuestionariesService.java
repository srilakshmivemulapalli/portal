package com.nisum.portal.service.api;


import com.nisum.portal.service.dto.QuestionsDTO;

public interface QuestionariesService {

	QuestionsDTO getQuestionaries();
	
	long getQuestionariesCount();
}
