package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.QuestionariesDAO;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.repository.QuestionariesRepository;

@Configuration
public class QuestionariesDAOImpl implements QuestionariesDAO {

	private static Logger logger = LoggerFactory.getLogger(CategoriesDAOImpl.class);
	@Autowired
	QuestionariesRepository questionariesRepository;
	@Override
	public List<Questionaries> fetchAllQuestionaries() {
		return questionariesRepository.findAll();
	}
	@Override
	public long getQuestionariesCount() {
		return questionariesRepository.count();
	}
	@Override
	public Questionaries getQuestionaries(int questionId) {
		logger.info("QuestionariesDAOImpl :: getQuestionaries :: get question by id");
		return questionariesRepository.findOne(questionId);
	}
	
	@Override
	public Questionaries saveQuestionaries(Questionaries questionaries) {
		return questionariesRepository.save(questionaries);
	}
	@Override
	public List<Questionaries> fetchMyQuestionaries(String emailId) {
		return questionariesRepository.fetchMyQuestionaries(emailId);
	}
	@Override
	public List<Questionaries> retriveAllUnansweredQuestionaries() {
		return questionariesRepository.retriveAllUnansweredQuestionaries();
	}
	

}
