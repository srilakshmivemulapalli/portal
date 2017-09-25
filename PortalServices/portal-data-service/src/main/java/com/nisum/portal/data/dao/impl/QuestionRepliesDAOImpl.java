package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.dao.api.QuestionRepliesDAO;
import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.repository.QuestionRepliesRepository;

@Configuration
public class QuestionRepliesDAOImpl implements QuestionRepliesDAO{

	private static Logger logger = LoggerFactory.getLogger(QuestionRepliesDAOImpl.class);
	@Autowired
	QuestionRepliesRepository questionRepliesRepository;
	
	@Override
	public List<QuestionReplies> getQuestionReplies() {
		return questionRepliesRepository.findAll();
	}
	@Override
	public Integer getQuestionariesReplyCount(int questId) {
		return questionRepliesRepository.getQuestionariesReplyCount(questId);
	}
	
	@Override
	public List<QuestionReplies> getQuestionariesReply(int questId){
		return questionRepliesRepository.getQuestionariesReply(questId);
	}
	@Override
	public QuestionReplies saveQuestionReplies(QuestionReplies questionReplies) {
		return questionRepliesRepository.save(questionReplies);
	}

}
