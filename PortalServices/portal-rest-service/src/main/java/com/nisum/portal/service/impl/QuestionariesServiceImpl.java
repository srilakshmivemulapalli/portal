package com.nisum.portal.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.dao.api.QuestionariesDAO;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.QuestionariesService;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.CountDTO;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.QuestionariesUtil;

@Service
public class QuestionariesServiceImpl implements QuestionariesService{

	private static Logger logger = LoggerFactory.getLogger(QuestionariesServiceImpl.class);
	@Autowired
	private QuestionariesDAO questionariesDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserService userervice;
	
	@Autowired
	private CategoriesDAO categoriesDAO;

	
	
	@Override
	public QuestionsDTO getQuestionaries() {
		List<Questionaries> questionariesList = questionariesDAO.fetchAllQuestionaries();
		QuestionsDTO questionsDTO = new QuestionsDTO();
		questionsDTO.setTotalQuestions(questionariesDAO.getQuestionariesCount());
		questionsDTO.setTotalUsers(userDAO.getUserCount());
		return QuestionariesUtil.convertDaoToDto(questionariesList,questionsDTO,userervice);
	}

	@Override
	public CountDTO getQuestionariesCount() {
		long questionCount= questionariesDAO.getQuestionariesCount();
		long userCount=userDAO.getUserCount();
		CountDTO countDTO=new CountDTO();
		countDTO.setQuestionCount(questionCount);
		countDTO.setUserCount(userCount);
		return countDTO;
	}

	@Override
	public String saveQuestions(String emailId, Integer categoryId, String question, String description) {
		Questionaries questionaries = QuestionariesUtil.convertDtoToDao(emailId, categoriesDAO.getCategory(categoryId), question, description);
		questionariesDAO.saveQuestionaries(questionaries);
		return Constants.MSG_RECORD_ADD;
	}

	@Override
	public QuestionsDTO fetchMyQuestionaries(String emailId) {
		emailId = emailId.substring(0, emailId.indexOf("@"))+"@nisum.com";
		List<Questionaries> questionariesList = questionariesDAO.fetchMyQuestionaries(emailId);
		QuestionsDTO questionsDTO = new QuestionsDTO();
		return QuestionariesUtil.convertDaoToDto(questionariesList,questionsDTO,userervice);
	}

	@Override
	public QuestionsDTO retriveAllUnansweredQuestionaries() {
		return QuestionariesUtil.convertDaoToDto(questionariesDAO.retriveAllUnansweredQuestionaries(),new QuestionsDTO(),userervice);
	}

}
