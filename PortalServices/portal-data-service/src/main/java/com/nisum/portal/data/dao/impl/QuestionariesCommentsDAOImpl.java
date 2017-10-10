package com.nisum.portal.data.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.nisum.portal.data.dao.api.QuestionariesCommentsDAO;
import com.nisum.portal.data.domain.QuestionariesComments;
import com.nisum.portal.data.repository.QuestionariesCommentsRepository;

@Configuration
public class QuestionariesCommentsDAOImpl implements QuestionariesCommentsDAO{

	private static Logger logger = LoggerFactory.getLogger(QuestionariesCommentsDAOImpl.class);
	@Autowired
	QuestionariesCommentsRepository questionariesCommentsRepository;
	
	@Override
	public QuestionariesComments saveQuestionComments(QuestionariesComments questionComments) {
		logger.info("QuestionariesCommentsDAOImpl :: saveQuestionComments :: saving question comment");
		return questionariesCommentsRepository.save(questionComments);
	}
}
