package com.nisum.portal.data.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.QuestionReplyCommentsDAO;
import com.nisum.portal.data.domain.QuestionReplyComments;
import com.nisum.portal.data.repository.QuestionReplyCommentsRepository;

@Configuration
public class QuestionReplyCommentsDAOImpl implements QuestionReplyCommentsDAO{

	private static Logger logger = LoggerFactory.getLogger(QuestionReplyCommentsDAOImpl.class);
	@Autowired
	QuestionReplyCommentsRepository questionReplyCommentsRepository;
	
	public QuestionReplyComments saveReplyComments(QuestionReplyComments replyComments) {
		logger.info("QuestionReplyCommentsDAOImpl :: saveReplyComments :: saving reply comment");
		return questionReplyCommentsRepository.save(replyComments);
	}
}
