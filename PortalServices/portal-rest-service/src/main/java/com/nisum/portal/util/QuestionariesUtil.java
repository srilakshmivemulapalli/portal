package com.nisum.portal.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.service.dto.QuestionsDTO;

public class QuestionariesUtil {

	
	public static QuestionsDTO convertDaoToDto(List<Questionaries> questionariesList,QuestionsDTO questionsDTO ) {
		
		List<QuestionariesDTO> questionariesDTOs = new ArrayList<QuestionariesDTO>();
		for (Questionaries questionaries : questionariesList) {
			QuestionariesDTO dto = new  QuestionariesDTO();
			dto.setCreatedDate(questionaries.getCreatedDate());
			dto.setDescription(questionaries.getDescription());
			dto.setQuestion(questionaries.getQuestion());
			dto.setQuestionId(questionaries.getQuestionId());
			dto.setCategoryName(questionaries.getCategoryId()+"");
			dto.setEmailId(questionaries.getEmailId());
			dto.setQuestionRepliesCount(questionaries.getQuestionReplies()!=null ? questionaries.getQuestionReplies().size() : 0);
			questionariesDTOs.add(dto);
		}
		questionsDTO.setQuestionDetails(questionariesDTOs);
		return questionsDTO;
	}

	public static Questionaries convertDtoToDao(String emailId, Integer categoryId, String question, String description) {
		return new Questionaries(categoryId,question,description,new Timestamp(System.currentTimeMillis()),emailId);
	}
}
