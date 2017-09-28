package com.nisum.portal.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.QuestionRepliesDAO;
import com.nisum.portal.data.dao.api.QuestionariesDAO;
import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.api.QuestionRepliesService;
import com.nisum.portal.service.dto.QuestionRepliesDTO;
import com.nisum.portal.service.dto.QuestionReplysDTO;
import com.nisum.portal.util.QuestionReplysUtil;

@Service
public class QuestionRepliesServiceImpl implements QuestionRepliesService{

	
	@Autowired
	private QuestionRepliesDAO repliesDAO;
	
	@Autowired
	private QuestionariesDAO questionariesDAO;
	
	@Override
	public Integer getQuestionariesReplyCount(int questId) {
		return repliesDAO.getQuestionariesReplyCount(questId);
	}

	@Override
	public QuestionReplysDTO getQuestionariesReply(int questId) {
		QuestionReplysDTO dto = new QuestionReplysDTO();
		Questionaries questionaries = questionariesDAO.getQuestionaries(questId);
		QuestionReplysDTO replysDTO = QuestionReplysUtil.convertDaoToDto(questionaries, repliesDAO.getQuestionariesReply(questId),dto);
		replysDTO.getQuestionDetails().setCategoryName(questionaries.getCategoryId().getCategoryName());
		return replysDTO;
	}
	
	@Override
	public QuestionRepliesDTO saveQuestionariesReply(Integer questId, String emailId, String replyDescription) {
		QuestionReplies questionaries = QuestionReplysUtil.convertDtoToDao(questId, emailId, replyDescription);
		return QuestionReplysUtil.convertReplyDaoToDto(repliesDAO.saveQuestionReplies(questionaries));
	}
}
