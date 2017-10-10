package com.nisum.portal.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.QuestionReplyComments;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.domain.QuestionariesComments;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.QuestionRepliesDTO;
import com.nisum.portal.service.dto.QuestionReplyCommentsDTO;
import com.nisum.portal.service.dto.QuestionReplysDTO;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.service.dto.UserDTO;

public class QuestionReplysUtil {
	
	
	public static QuestionReplysDTO convertDaoToDto(Questionaries questionaries,List<QuestionReplies> questionReplies, UserService userervice ) {
		
		QuestionReplysDTO replysDTO = new QuestionReplysDTO();
		if (questionaries!=null) {
			QuestionariesDTO dto = new  QuestionariesDTO();
			dto.setCreatedDate(questionaries.getCreatedDate());
			dto.setDescription(questionaries.getDescription());
			dto.setQuestion(questionaries.getQuestion());
			dto.setQuestionId(questionaries.getQuestionId());
			dto.setCategoryName("Java"+questionaries.getCategoryId()+"TODO Need to read from Cache");
			dto.setEmailId(questionaries.getEmailId());
			List<QuestionariesCommentsDTO> questionariesCommentsDto = new ArrayList<QuestionariesCommentsDTO>();
			List<QuestionariesComments> questionariesComments = questionaries.getQuestionariesComments();
			if(CollectionUtils.isNotEmpty(questionariesComments)) {
				for (QuestionariesComments comment : questionariesComments) {
					QuestionariesCommentsDTO questionariesCommentsDTO = new QuestionariesCommentsDTO();
					questionariesCommentsDTO.setcommentDescription(comment.getcommentDescription());
					questionariesCommentsDTO.setCommentId(comment.getCommentId());
					questionariesCommentsDTO.setCreatedDate(comment.getCreatedDate());
					questionariesCommentsDTO.setEmailId(comment.getEmailId());
					questionariesCommentsDTO.setquestionId(comment.getQuestionId());
					questionariesCommentsDto.add(questionariesCommentsDTO);
				}
			}
			dto.setQuestionComments(questionariesCommentsDto);
			UserDTO qUser = userervice.getUsers().get(questionaries.getEmailId());
			if(qUser!=null && StringUtils.isNotEmpty(qUser.getImage())) {
				dto.setDisplayImage(qUser.getImage());
			}
			
			replysDTO.setQuestionDetails(dto);
			List<QuestionRepliesDTO> questRepliesDTO = new ArrayList<QuestionRepliesDTO>();
			if(CollectionUtils.isNotEmpty(questionReplies)) {
				for (QuestionReplies reply : questionReplies) {
					QuestionRepliesDTO repliesDTO = new QuestionRepliesDTO();
					repliesDTO.setEmailId(reply.getEmailid());
					repliesDTO.setReplyDescription(reply.getReplyDescription());
					repliesDTO.setReplyId(reply.getReplyId());
					repliesDTO.setUpdatedDate(reply.getUpdatedDate());
					
					List<QuestionReplyCommentsDTO> questionReplyCommentsDTO = new ArrayList<QuestionReplyCommentsDTO>();
					List<QuestionReplyComments> questionReplyComments = reply.getQuestionReplyComments();
					if(CollectionUtils.isNotEmpty(questionReplyComments)) {
						for (QuestionReplyComments replyComment : questionReplyComments) {
							QuestionReplyCommentsDTO questionReplyCommentsDto = new QuestionReplyCommentsDTO();
							questionReplyCommentsDto.setcommentDescription(replyComment.getcommentDescription());
							questionReplyCommentsDto.setCommentId(replyComment.getCommentId());
							questionReplyCommentsDto.setCreatedDate(replyComment.getCreatedDate());
							questionReplyCommentsDto.setEmailId(replyComment.getEmailId());
							questionReplyCommentsDto.setreplyId(replyComment.getReplyId());
							questionReplyCommentsDTO.add(questionReplyCommentsDto);
						}
					}
					repliesDTO.setReplyComments(questionReplyCommentsDTO);
					
					UserDTO qrUser = userervice.getUsers().get(reply.getEmailid());
					if(qrUser!=null && StringUtils.isNotEmpty(qrUser.getImage())) {
						repliesDTO.setDisplayImage(qUser.getImage());
					}
					questRepliesDTO.add(repliesDTO);
				}
			}
			
			replysDTO.setReplyDetails(questRepliesDTO);
		}
		return replysDTO;
	}

	public static QuestionReplies convertDtoToDao(Integer questId, String emailId, String replyDescription) {
		return new QuestionReplies(replyDescription,new Timestamp(System.currentTimeMillis()),questId,emailId);
	}

	public static QuestionRepliesDTO convertReplyDaoToDto(QuestionReplies reply) {
		QuestionRepliesDTO repliesDTO = new QuestionRepliesDTO();
		repliesDTO.setEmailId(reply.getEmailid());
		repliesDTO.setReplyDescription(reply.getReplyDescription());
		repliesDTO.setReplyId(reply.getReplyId());
		repliesDTO.setUpdatedDate(reply.getUpdatedDate());
		repliesDTO.setReplyComments(new ArrayList<QuestionReplyCommentsDTO>());
		return repliesDTO;
	}
	
	public static QuestionReplyComments convertCommentDtoToDao(QuestionReplyCommentsDTO comment) {
		QuestionReplyComments questionReplyComments = new QuestionReplyComments();
		questionReplyComments.setEmailId(comment.getEmailId());
		questionReplyComments.setcommentDescription(comment.getcommentDescription());
		questionReplyComments.setReplyId(comment.getReplyId());
		return questionReplyComments;
	}
	
	public static QuestionReplyCommentsDTO convertCommentDaoToDto(QuestionReplyComments comment) {
		QuestionReplyCommentsDTO questionReplyCommentsDTO = new QuestionReplyCommentsDTO();
		questionReplyCommentsDTO.setEmailId(comment.getEmailId());
		questionReplyCommentsDTO.setcommentDescription(comment.getcommentDescription());
		questionReplyCommentsDTO.setCommentId(comment.getCommentId());
		questionReplyCommentsDTO.setCreatedDate(comment.getCreatedDate());
		questionReplyCommentsDTO.setreplyId(comment.getReplyId());
		return questionReplyCommentsDTO;
	}

}
