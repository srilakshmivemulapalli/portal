package com.nisum.portal.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.service.dto.UserDTO;

public class QuestionariesUtil {

	
	public static QuestionsDTO convertDaoToDto(List<Questionaries> questionariesList,QuestionsDTO questionsDTO, UserService userervice) {
		
		List<QuestionariesDTO> questionariesDTOs = new ArrayList<QuestionariesDTO>();
		if (CollectionUtils.isNotEmpty(questionariesList)) {
			for (Questionaries questionaries : questionariesList) {
				QuestionariesDTO dto = new  QuestionariesDTO();
				dto.setCreatedDate(questionaries.getCreatedDate());
				dto.setDescription(questionaries.getDescription());
				dto.setQuestion(questionaries.getQuestion());
				dto.setQuestionId(questionaries.getQuestionId());
				dto.setCategoryName(questionaries.getCategoryId().getCategoryName());
				dto.setEmailId(questionaries.getEmailId());
				dto.setQuestionRepliesCount(questionaries.getQuestionReplies()!=null ? questionaries.getQuestionReplies().size() : 0);
				UserDTO user = userervice.getUsers().get(questionaries.getEmailId());
				if(user!=null && StringUtils.isNotEmpty(user.getImage())) {
					dto.setDisplayImage(user.getImage());
				}
				questionariesDTOs.add(dto);
			}
			Collections.reverse(questionariesDTOs); 
			questionsDTO.setQuestionDetails(questionariesDTOs);
		}
		return questionsDTO;
	}

	public static Questionaries convertDtoToDao(String emailId, Categories categoryId, String question, String description) {
		return new Questionaries(categoryId,question,description,new Timestamp(System.currentTimeMillis()),emailId);
	}
}
