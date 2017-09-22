package com.nisum.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.QuestionRepliesDAO;
import com.nisum.portal.service.api.QuestionRepliesService;

@Service
public class QuestionRepliesServiceImpl implements QuestionRepliesService{

	
	@Autowired
	private QuestionRepliesDAO repliesDAO;

	@Override
	public Integer getQuestionariesReplyCount(int questId) {
		return repliesDAO.getQuestionariesReplyCount(questId);
	}
}
