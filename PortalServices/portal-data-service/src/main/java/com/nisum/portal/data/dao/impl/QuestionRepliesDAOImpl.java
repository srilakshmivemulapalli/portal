package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import com.nisum.portal.data.dao.api.QuestionRepliesDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.Questionaries;
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
	@Override
	public QuestionReplies getReply(int replyId) {
		logger.info("QuestionRepliesDAOImpl :: getReply :: finding reply by Id");
		return questionRepliesRepository.findOne(replyId);
	}
	@Override
	public List<Questionaries> getMyReplyQuestionsByPagination(String emailId, Pageable pageable) {
		logger.info("QuestionRepliesDAOImpl :: getMyReplyQuestionsByPagination :: finding reply questions");
		return questionRepliesRepository.getMyReplyQuestionsByPagination(emailId, pageable);
	}
	@Override
	public List<Questionaries> getMyReplyQuestionsByCategoryThroughPagination(String emailId, Categories category, Pageable pageable) {
		logger.info("QuestionRepliesDAOImpl :: getMyReplyQuestionsByCategoryThroughPagination :: finding reply questions by category"+category);
		return questionRepliesRepository.getMyReplyQuestionsByCategoryThroughPagination(emailId, category, pageable);
	}
	@Override
	public List<Questionaries> getMyReplyQuestions(String emailId) {
		logger.info("QuestionRepliesDAOImpl :: getMyReplyQuestions :: finding reply questions for given emailId"+emailId);
		return questionRepliesRepository.getMyReplyQuestions(emailId);
	}
	@Override
	public List<Questionaries> getMyReplyQuestionsByCategory(String emailId, Categories category) {
		logger.info("QuestionRepliesDAOImpl :: getMyReplyQuestionsByCategory :: finding reply questions by category");
		return questionRepliesRepository.getMyReplyQuestionsByCategory(emailId, category);
	}

}
