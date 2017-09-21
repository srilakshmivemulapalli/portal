package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.dto.QuestionRepliesDTO;
import com.nisum.portal.service.dto.QuestionariesDTO;

public class QuestionariesUtil {

	
	public static List<QuestionariesDTO> convertDaoToDto(List<Questionaries> questionariesList ) {
		
		List<QuestionariesDTO> questionariesDTOs = new ArrayList<QuestionariesDTO>();
		for (Questionaries questionaries : questionariesList) {
			QuestionariesDTO dto = new  QuestionariesDTO();
			dto.setCreatedDate(questionaries.getCreatedDate());
			dto.setDescription(questionaries.getDescription());
			dto.setQuestion(questionaries.getQuestion());
			dto.setQuestionId(questionaries.getQuestionId());
			dto.setCategoryName(questionaries.getCategoryId().getCategoryName());
			dto.setUserId(questionaries.getUserId().getUserId());
			dto.setEmailId(questionaries.getUserId().getEmailId());
			dto.setDisplayName(questionaries.getUserId().getName());
//			dto.setQuestionReplies(new HashSet<QuestionRepliesDTO>());
//			for (QuestionReplies questionReplies : questionaries.getQuestionReplies()) {
//				QuestionRepliesDTO repliesDTO = new QuestionRepliesDTO();
//				repliesDTO.setCreatedDate(questionReplies.getCreatedDate());
//				repliesDTO.setReplyDescription(questionReplies.getReplyDescription());
//				repliesDTO.setReplyId(questionReplies.getReplyId());
//				repliesDTO.setUserName(questionReplies.getUserId().getName());
//				repliesDTO.setUpdatedDate(questionReplies.getUpdatedDate());
//				dto.getQuestionReplies().add(repliesDTO);
//			}
			
			questionariesDTOs.add(dto);
		}
		return questionariesDTOs;
	}
}
