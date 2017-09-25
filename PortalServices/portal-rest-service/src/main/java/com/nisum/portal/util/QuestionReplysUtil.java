package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.dto.QuestionRepliesDTO;
import com.nisum.portal.service.dto.QuestionReplysDTO;
import com.nisum.portal.service.dto.QuestionariesDTO;

public class QuestionReplysUtil {
	
	
	public static QuestionReplysDTO convertDaoToDto(Questionaries questionaries,List<QuestionReplies> questionReplies ) {
		
		QuestionReplysDTO replysDTO = new QuestionReplysDTO();
		
		if(questionaries!=null) {
			QuestionariesDTO dto = new  QuestionariesDTO();
			dto.setCreatedDate(questionaries.getCreatedDate());
			dto.setDescription(questionaries.getDescription());
			dto.setQuestion(questionaries.getQuestion());
			dto.setQuestionId(questionaries.getQuestionId());
			dto.setCategoryName("Java"+questionaries.getCategoryId()+"TODO Need to read from Cache");
			dto.setEmailId(questionaries.getEmailId());
			replysDTO.setQuestionDetails(dto);
			List<QuestionRepliesDTO> questRepliesDTO = new ArrayList<QuestionRepliesDTO>();
			for (QuestionReplies reply : questionReplies) {
				QuestionRepliesDTO repliesDTO = new QuestionRepliesDTO();
				repliesDTO.setEmailId(reply.getEmailid());
				repliesDTO.setReplyDescription(reply.getReplyDescription());
				repliesDTO.setReplyId(reply.getReplyId());
				repliesDTO.setUpdatedDate(reply.getUpdatedDate());
				questRepliesDTO.add(repliesDTO);
			}
			
			replysDTO.setReplyDetails(questRepliesDTO);
		}
		return replysDTO;
	}

}
