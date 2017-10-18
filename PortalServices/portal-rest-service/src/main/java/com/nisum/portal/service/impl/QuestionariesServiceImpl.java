package com.nisum.portal.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.dao.api.QuestionariesCommentsDAO;
import com.nisum.portal.data.dao.api.QuestionariesDAO;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.domain.QuestionariesComments;
import com.nisum.portal.service.api.QuestionariesService;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.CountDTO;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
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

	@Autowired
	private QuestionariesCommentsDAO questionariesCommentsDAO;
	
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
		questionsDTO.setTotalQuestions(questionariesList.size());
		questionsDTO.setTotalUsers(userDAO.getUserCount());
		return QuestionariesUtil.convertDaoToDto(questionariesList,questionsDTO,userervice);
	}

	@Override
	public QuestionsDTO retriveAllUnansweredQuestionaries() {
		List<Questionaries> questionariesList=questionariesDAO.retriveAllUnansweredQuestionaries();
		QuestionsDTO questionsDTO=new QuestionsDTO();
		questionsDTO.setTotalQuestions(questionariesList.size());
		questionsDTO.setTotalUsers(userDAO.getUserCount());
		return QuestionariesUtil.convertDaoToDto(questionariesList,questionsDTO,userervice);
	}

	@Override
	public QuestionariesCommentsDTO saveQuestionComment(String emailId, QuestionariesCommentsDTO questionComment) {
		logger.info("QuestionariesServiceImpl :: saveQuestionComment :: saving question comment");
		QuestionariesComments comments = QuestionariesUtil.convertDtoToDao(emailId, questionComment);
		Date date = new Date();
		Timestamp createdDate = new Timestamp(date.getTime());
		comments.setCreatedDate(createdDate);
		QuestionariesComments questionariesComments = questionariesCommentsDAO.saveQuestionComments(comments);
		QuestionariesCommentsDTO questionariesCommentsDTO = QuestionariesUtil.convertDaoToDto(questionariesComments);
		if (questionariesCommentsDTO != null) {
			return questionariesCommentsDTO;
		} else {
			return null;
		}
	}

	@Override
	public boolean findQuestionById(int questionId) {
		logger.info("QuestionariesServiceImpl :: findQuestionById :: Finding question by id");
		Questionaries question = questionariesDAO.getQuestionaries(questionId);
		if (question == null) {
			return false;
		} else {
			return true;
		}
	}

	/*retrieving the questionaries based on category through pagination
	 * (non-Javadoc)
	 * @see com.nisum.portal.service.api.QuestionariesService#getQuestionariesByCategory(java.lang.Integer, org.springframework.data.domain.Pageable)
	 */
	@Override
	public QuestionsDTO getQuestionariesByCategory(Integer categoryId, Pageable pageable) {
		
		logger.info("QuestionariesServiceImpl:: getQuestionariesByCategory(categoryId: "+categoryId+"PageNumber: "+pageable.getPageNumber()+", PageSize: "+pageable.getPageSize()+", "+pageable.getSort());
		Categories category=categoriesDAO.getCategory(categoryId);
		List<Questionaries> allQuestionariesList=questionariesDAO.retrieveQuestionByCategory(category, pageable);
		QuestionsDTO questionsDTO = new QuestionsDTO();
		questionsDTO.setTotalQuestions(allQuestionariesList.size());
		questionsDTO.setTotalUsers(userDAO.getUserCount());
		
		return QuestionariesUtil.convertDaoToDto(allQuestionariesList, questionsDTO,userervice);
	}
	
	/*retrieving all questionaries through pagination 
	 * (non-Javadoc)
	 * @see com.nisum.portal.service.api.QuestionariesService#getQuestionariesByPagination(org.springframework.data.domain.Pageable)
	 */
	@Override
	public QuestionsDTO getQuestionariesByPagination(Pageable pageable) {
		logger.info("QuestionariesServiceImpl:: getQuestionariesByPagination(PageNumber: "+pageable.getPageNumber()+", "+pageable.getPageSize()+", "+pageable.getSort());
		List<Questionaries> questionariesList=questionariesDAO.retrieveQuestionByPagination(pageable);
		
		QuestionsDTO questionsDTO = new QuestionsDTO();
		questionsDTO.setTotalQuestions(questionariesDAO.getQuestionariesCount());
		questionsDTO.setTotalUsers(userDAO.getUserCount());
		
		return QuestionariesUtil.convertDaoToDto(questionariesList, questionsDTO, userervice);
	}
}
