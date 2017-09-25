package com.nisum.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.QuestionariesDAO;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.api.QuestionariesService;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.util.KeyConstants;
import com.nisum.portal.util.QuestionariesUtil;

@Service
public class QuestionariesServiceImpl implements QuestionariesService{

	private static Logger logger = LoggerFactory.getLogger(QuestionariesServiceImpl.class);
	@Autowired
	private QuestionariesDAO questionariesDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public QuestionsDTO getQuestionaries() {
		List<Questionaries> questionariesList = questionariesDAO.fetchAllQuestionaries();
		QuestionsDTO questionsDTO = new QuestionsDTO();
		questionsDTO.setTotalQuestions(questionariesDAO.getQuestionariesCount());
		questionsDTO.setTotalUsers(userDAO.getUserCount());
		return QuestionariesUtil.convertDaoToDto(questionariesList,questionsDTO);
	}

	@Override
	public long getQuestionariesCount() {
		return questionariesDAO.getQuestionariesCount();
	}

	@Override
	public String saveQuestions(String emailId, Integer categoryId, String question, String description) {
		Questionaries questionaries = QuestionariesUtil.convertDtoToDao(emailId, categoryId, question, description);
		questionariesDAO.saveQuestionaries(questionaries);
		return KeyConstants.SUCCESS_MESSAGE;
	}

}
