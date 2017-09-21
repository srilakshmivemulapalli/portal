package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nisum.portal.data.dao.api.QuestionRepliesDAO;
import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.repository.QuestionRepliesRepository;

public class QuestionRepliesDAOImpl implements QuestionRepliesDAO{

	private static Logger logger = LoggerFactory.getLogger(QuestionRepliesDAOImpl.class);
	@Autowired
	QuestionRepliesRepository questionRepliesRepository;
	
	@Override
	public List<QuestionReplies> getQuestionReplies() {
		return questionRepliesRepository.findAll();
	}

}
