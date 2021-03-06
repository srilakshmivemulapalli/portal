package com.nisum.portal.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.domain.QuestionariesComments;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
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
		}
		questionsDTO.setQuestionDetails(questionariesDTOs);
		return questionsDTO;
	}
    public static QuestionsDTO convertQuestionariesDTOToQuestionsDTO(List<QuestionariesDTO> questionariesDTOList,QuestionsDTO questionsDTO, UserService userervice) {
	    Collections.reverse(questionariesDTOList); 
		questionsDTO.setQuestionDetails(questionariesDTOList);
		return questionsDTO;
	}
    
    public static QuestionariesDTO convertQuestionariesToQuestionariesDTO(Questionaries questionaries,UserService userervice) {
    	if (questionaries !=null) {
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
			return dto;
		}
		return null;
	}

	public static Questionaries convertDtoToDao(String emailId, Categories categoryId, String question, String description) {
		return new Questionaries(categoryId,question,description,new Timestamp(System.currentTimeMillis()),emailId);
	}
	
	public static Questionaries convertDtoToDao(Integer questionId, Categories categoryId, String question, String description,String emailId) {
		return new Questionaries(questionId,categoryId,question,description,new Timestamp(System.currentTimeMillis()),emailId);
	}
	
	
	public static QuestionariesComments convertDtoToDao(String emailId, QuestionariesCommentsDTO questionComments) {
		QuestionariesComments questionariesComments = new QuestionariesComments();
		questionariesComments.setcommentDescription(questionComments.getcommentDescription());
		questionariesComments.setQuestionId(questionComments.getid());
		questionariesComments.setEmailId(emailId);
		return questionariesComments;
		
	}
	
	public static QuestionariesCommentsDTO convertDaoToDto(QuestionariesComments questionComments) {
		QuestionariesCommentsDTO questionariesCommentsDTO = new QuestionariesCommentsDTO();
		questionariesCommentsDTO.setcommentDescription(questionComments.getcommentDescription());
		questionariesCommentsDTO.setCommentId(questionComments.getCommentId());
		questionariesCommentsDTO.setCreatedDate(questionComments.getCreatedDate());
		questionariesCommentsDTO.setEmailId(questionComments.getEmailId());
		questionariesCommentsDTO.setid(questionComments.getQuestionId());
		return questionariesCommentsDTO;
		
	}
}
