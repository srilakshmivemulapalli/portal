package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.service.dto.QuestionariesDTO;

public interface QuestionariesService {

	List<QuestionariesDTO> getQuestionaries();
	
	long getQuestionariesCount();
}
