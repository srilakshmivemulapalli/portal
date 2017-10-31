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
import com.nisum.portal.data.dao.api.QuestionRepliesDAO;
import com.nisum.portal.data.dao.api.QuestionReplyCommentsDAO;
import com.nisum.portal.data.dao.api.QuestionariesDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.QuestionReplyComments;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.api.QuestionRepliesService;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.QuestionRepliesDTO;
import com.nisum.portal.service.dto.QuestionReplyCommentsDTO;
import com.nisum.portal.service.dto.QuestionReplysDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.util.QuestionReplysUtil;
import com.nisum.portal.util.QuestionariesUtil;

@Service
public class QuestionRepliesServiceImpl implements QuestionRepliesService{

	private static Logger logger = LoggerFactory.getLogger(QuestionRepliesServiceImpl.class); 
	
	@Autowired
	private QuestionRepliesDAO repliesDAO;
	
	@Autowired
	private QuestionariesDAO questionariesDAO;
	
	@Autowired
	private UserService userervice;
	
	@Autowired
	private QuestionReplyCommentsDAO questionReplyCommentsDAO;
	
	@Autowired
	private QuestionRepliesDAO questionRepliesDAO;
	
	@Autowired
	private CategoriesDAO categoriesDAO;

	
	@Override
	public Integer getQuestionariesReplyCount(int questId) {
		return repliesDAO.getQuestionariesReplyCount(questId); 
	}

	@Override
	public QuestionReplysDTO getQuestionariesReply(int questId) {
		Questionaries questionaries = questionariesDAO.getQuestionaries(questId);
		QuestionReplysDTO replysDTO = QuestionReplysUtil.convertDaoToDto(questionaries, repliesDAO.getQuestionariesReply(questId),userervice);
		replysDTO.getQuestionDetails().setCategoryName(questionaries.getCategoryId().getCategoryName());
		return replysDTO;
	}
	
	@Override
	public QuestionRepliesDTO saveQuestionariesReply(Integer questId, String emailId, String replyDescription) {
		QuestionReplies questionaries = QuestionReplysUtil.convertDtoToDao(questId, emailId, replyDescription);
		return QuestionReplysUtil.convertReplyDaoToDto(repliesDAO.saveQuestionReplies(questionaries));
	}

	@Override
	public QuestionReplyCommentsDTO saveReplyComment(String emailId, QuestionReplyCommentsDTO replyComment) {
		logger.info("QuestionRepliesServiceImpl :: saveReplyComment :: saving reply comment");
		replyComment.setEmailId(emailId);
		QuestionReplyComments comments = QuestionReplysUtil.convertCommentDtoToDao(replyComment);
		Date date = new Date();
		Timestamp createdDate = new Timestamp(date.getTime());
		comments.setCreatedDate(createdDate);
		QuestionReplyComments questionariesComments = questionReplyCommentsDAO.saveReplyComments(comments);
		QuestionReplyCommentsDTO questionReplyCommentsDTO = QuestionReplysUtil.convertCommentDaoToDto(questionariesComments);
		if (questionReplyCommentsDTO != null) {
			return questionReplyCommentsDTO;
		} else {
			return null;
		}
	}

	@Override
	public boolean findReplyById(int replyId) {
		logger.info("QuestionRepliesServiceImpl :: findReplyById :: finding reply by id");
		QuestionReplies reply = questionRepliesDAO.getReply(replyId);
		if (reply == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public QuestionsDTO fetchMyReplyQuestionsByPagination(String emailId, Pageable pageable) {
		logger.info("QuestionRepliesServiceImpl :: fetchMyReplyQuestionsByPagination :: finding reply questions");
		emailId = emailId.substring(0, emailId.indexOf("@"))+"@nisum.com";
		List<Questionaries> questionariesList = repliesDAO.getMyReplyQuestionsByPagination(emailId, pageable);
		QuestionsDTO questionsDTO = new QuestionsDTO();
		questionsDTO.setTotalQuestions(repliesDAO.getMyReplyQuestions(emailId).size());
		return QuestionariesUtil.convertDaoToDto(questionariesList,questionsDTO,userervice);
	}

	@Override
	public QuestionsDTO fetchMyReplyQuestionsByCategory(String emailId, Integer categoryId, Pageable pageable) {
		logger.info("QuestionRepliesServiceImpl :: fetchMyReplyQuestionsByCategory :: finding reply questions by category");
		emailId = emailId.substring(0, emailId.indexOf("@"))+"@nisum.com";
		Categories category=categoriesDAO.getCategory(categoryId);
		List<Questionaries> questionariesList = repliesDAO.getMyReplyQuestionsByCategoryThroughPagination(emailId, category, pageable);
		QuestionsDTO questionsDTO = new QuestionsDTO();
		questionsDTO.setTotalQuestions(repliesDAO.getMyReplyQuestionsByCategory(emailId, category).size());
		return QuestionariesUtil.convertDaoToDto(questionariesList,questionsDTO,userervice);
	}
}
