package com.nisum.portal.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.dao.api.QuestionariesCommentsDAO;
import com.nisum.portal.data.dao.api.QuestionariesDAO;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.domain.QuestionariesComments;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.CountDTO;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.QuestionariesUtil;

@RunWith(PowerMockRunner.class) 
@PrepareForTest(QuestionariesUtil.class)
public class QuestionariesServiceImplTest {
	
	@Mock
	QuestionariesDAO questionariesDAO;
	
	@Mock
	UserDAO userDAO;
	
	@Mock
	CategoriesDAO categoriesDAO;
	
	@InjectMocks
	QuestionariesServiceImpl questionariesServiceImpl;
	
	@Mock
	QuestionariesCommentsDAO questionariesCommentsDAO;

	@Mock
	UserService userService;
 
	
	@Test
	public void getQuestionariesCountTestSuccess() {
		long count=0;
		CountDTO countDTO=new CountDTO();
		countDTO.setQuestionCount(count);
		countDTO.setUserCount(count);
		when(questionariesDAO.getQuestionariesCount()).thenReturn(count);
		when(userDAO.getUserCount()).thenReturn(count);
		assertThat(countDTO).isEqualToComparingFieldByField(questionariesServiceImpl.getQuestionariesCount());
	}
	
	@Test
	public void saveQuestionComment() {
		QuestionariesCommentsDTO questionariesCommentsDTOExpected = new QuestionariesCommentsDTO();
		questionariesCommentsDTOExpected.setcommentDescription("asasasaa");
		questionariesCommentsDTOExpected.setid(1);
		questionariesCommentsDTOExpected.setCreatedDate(new Timestamp(new Date(0).getTime()));
		questionariesCommentsDTOExpected.setEmailId("test@nisum.com");
		questionariesCommentsDTOExpected.setCommentId(1);
		
		QuestionariesCommentsDTO request = new QuestionariesCommentsDTO();
		request.setcommentDescription("asasasaa");
		request.setid(1);
		
		QuestionariesComments questionariesCommentsMock = new QuestionariesComments();
		questionariesCommentsMock.setcommentDescription("asasasaa");
		questionariesCommentsMock.setCommentId(1);
		questionariesCommentsMock.setCreatedDate(new Timestamp(new Date(0).getTime()));
		questionariesCommentsMock.setEmailId("test@nisum.com");
		questionariesCommentsMock.setQuestionId(1);
		
		PowerMockito.mockStatic(QuestionariesUtil.class); 
		PowerMockito.when(QuestionariesUtil.convertDtoToDao("test@nisum.com", request)).thenReturn(questionariesCommentsMock);
		when(questionariesCommentsDAO.saveQuestionComments(questionariesCommentsMock)).thenReturn(questionariesCommentsMock);
		PowerMockito.when(QuestionariesUtil.convertDaoToDto(questionariesCommentsMock)).thenReturn(questionariesCommentsDTOExpected);
		
		QuestionariesCommentsDTO actual = questionariesServiceImpl.saveQuestionComment("test@nisum.com", request);
		System.out.println("dsssssdsds"+actual);
		assertEquals(questionariesCommentsDTOExpected.getid(), actual.getid());
	}
	
	@Test
	public void findQuestionById() {
		int questionId = 1;
		Questionaries question = new Questionaries();
		question.setEmailId("test@nisum.com");
		question.setQuestion("what is abx");
		question.setQuestionId(1);
		
		when(questionariesDAO.getQuestionaries(questionId)).thenReturn(question);
		boolean actual = questionariesServiceImpl.findQuestionById(questionId);
		assertEquals(true, actual);
	}
	
	@Test
	public void findQuestionByIdFail() {
		int questionId = 1;
		
		when(questionariesDAO.getQuestionaries(questionId)).thenReturn(null);
		boolean actual = questionariesServiceImpl.findQuestionById(questionId);
		assertEquals(false, actual); 
	}
	
	@Test
	public void getQuestionariesByCategoryTest() {
		
		List<QuestionariesDTO> questionList = new ArrayList<QuestionariesDTO>();
		
		QuestionariesDTO questionariesDTO = new QuestionariesDTO();
		
			questionariesDTO.setCategoryName("category1");
			questionariesDTO.setDescription("description");
			questionariesDTO.setDisplayImage("DisplayImage");
			questionariesDTO.setDisplayName("display name");
			questionariesDTO.setEmailId("test@nisum.com");
			questionariesDTO.setQuestion("Question111");
			questionariesDTO.setQuestionId(1);
			questionariesDTO.setQuestionRepliesCount(0);

		questionList.add(questionariesDTO);

		
		
		Categories category = new Categories();
		category.setCategoryId(1);
		category.setCategoryName("JAVA");
		
		List<Questionaries> questionariesList=new ArrayList<>();
		
		Questionaries questionaries = new Questionaries();
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("what is abc");
			questionaries.setQuestionId(1);
			questionaries.setCategoryId(category);
			questionaries.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			questionaries.setDescription("description");
	
		
		questionariesList.add(questionaries);
		
		
		QuestionsDTO expected = new QuestionsDTO();
		expected.setTotalQuestions(questionariesList.size());
		expected.setTotalUsers(1); 
		
		when(userDAO.getUserCount()).thenReturn((long) 1);
		when(categoriesDAO.getCategory(1)).thenReturn(category);
		when(questionariesDAO.retrieveQuestionCountByCategory(category)).thenReturn(questionariesList);
		when(questionariesDAO.retrieveQuestionByCategory(category, new PageRequest(0, 3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT))).thenReturn(questionariesList);
		
		QuestionsDTO actual=questionariesServiceImpl.getQuestionariesByCategory(1, new PageRequest(0,3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT)); 
		
		assertEquals(expected.getTotalQuestions(), actual.getTotalQuestions());
	} 
	
	
	@Test
	public void getQuestionariesByPaginationTest() {
		
		List<QuestionariesDTO> questionList = new ArrayList<QuestionariesDTO>();
		
		QuestionariesDTO questionariesDTO = new QuestionariesDTO();
		
			questionariesDTO.setCategoryName("category1");
			questionariesDTO.setDescription("description");
			questionariesDTO.setDisplayImage("DisplayImage");
			questionariesDTO.setDisplayName("display name");
			questionariesDTO.setEmailId("test@nisum.com");
			questionariesDTO.setQuestion("Question111");
			questionariesDTO.setQuestionId(1);
			questionariesDTO.setQuestionRepliesCount(0);

		questionList.add(questionariesDTO);

		
		
		Categories category = new Categories();
		category.setCategoryId(1);
		category.setCategoryName("JAVA");
		
		List<Questionaries> questionariesList=new ArrayList<>();
		
		Questionaries questionaries = new Questionaries();
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("what is abc");
			questionaries.setQuestionId(1);
			questionaries.setCategoryId(category);
			questionaries.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			questionaries.setDescription("description");
	
		
		questionariesList.add(questionaries);
		
		
		QuestionsDTO expected = new QuestionsDTO();
		expected.setTotalQuestions(questionariesList.size());
		expected.setTotalUsers(1); 
		
		when(userDAO.getUserCount()).thenReturn((long) 1);
		when(questionariesDAO.getQuestionariesCount()).thenReturn((long) 1);
		when(questionariesDAO.retrieveQuestionByPagination(new PageRequest(0, 3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT))).thenReturn(questionariesList);
		
		QuestionsDTO actual=questionariesServiceImpl.getQuestionariesByPagination(new PageRequest(0,3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT)); 
		
		assertEquals(expected.getTotalQuestions(), actual.getTotalQuestions());
	} 
	
	
	@Test
	public void retrieveAllunansweredQuestionariesByCategoryTest() {
		
List<QuestionariesDTO> questionList = new ArrayList<QuestionariesDTO>();
		
		QuestionariesDTO questionariesDTO = new QuestionariesDTO();
		
			questionariesDTO.setCategoryName("category1");
			questionariesDTO.setDescription("description");
			questionariesDTO.setDisplayImage("DisplayImage");
			questionariesDTO.setDisplayName("display name");
			questionariesDTO.setEmailId("test@nisum.com");
			questionariesDTO.setQuestion("Question111");
			questionariesDTO.setQuestionId(1);
			questionariesDTO.setQuestionRepliesCount(0);

		questionList.add(questionariesDTO);

		
		
		Categories category = new Categories();
		category.setCategoryId(1);
		category.setCategoryName("JAVA");
		
		List<Questionaries> questionariesList=new ArrayList<>();
		
		Questionaries questionaries = new Questionaries();
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("what is abc");
			questionaries.setQuestionId(1);
			questionaries.setCategoryId(category);
			questionaries.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			questionaries.setDescription("description");
	
		
		questionariesList.add(questionaries);
		
		
		QuestionsDTO expected = new QuestionsDTO();
		expected.setTotalQuestions(questionariesList.size());
		expected.setTotalUsers(1); 
		
		when(userDAO.getUserCount()).thenReturn((long) 1);
		when(categoriesDAO.getCategory(1)).thenReturn(category);
		when(questionariesDAO.retrieveAllUnansweredQuestionariesByCategory(category, new PageRequest(0, 3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT))).thenReturn(questionariesList);
		when(questionariesDAO.retrieveAllUnansweredQuestionariesCountByCategory(category)).thenReturn(questionariesList);
		QuestionsDTO actual=questionariesServiceImpl.retrieveAllUnansweredQuestionariesByCategory(1, new PageRequest(0,3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT)); 
		
		assertEquals(expected.getTotalQuestions(), actual.getTotalQuestions());
	} 
	
	
	@Test
	public void retrieveAllunansweredQuestionariesByPaginationTest() {
		
		List<QuestionariesDTO> questionList = new ArrayList<QuestionariesDTO>();
		
		QuestionariesDTO questionariesDTO = new QuestionariesDTO();
		
			questionariesDTO.setCategoryName("category1");
			questionariesDTO.setDescription("description");
			questionariesDTO.setDisplayImage("DisplayImage");
			questionariesDTO.setDisplayName("display name");
			questionariesDTO.setEmailId("test@nisum.com");
			questionariesDTO.setQuestion("Question111");
			questionariesDTO.setQuestionId(1);
			questionariesDTO.setQuestionRepliesCount(0);

		questionList.add(questionariesDTO);

		
		
		Categories category = new Categories();
		category.setCategoryId(1);
		category.setCategoryName("JAVA");
		
		List<Questionaries> questionariesList=new ArrayList<>();
		
		Questionaries questionaries = new Questionaries();
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("what is abc");
			questionaries.setQuestionId(1);
			questionaries.setCategoryId(category);
			questionaries.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			questionaries.setDescription("description");
	
		
		questionariesList.add(questionaries);
		
		
		QuestionsDTO expected = new QuestionsDTO();
		expected.setTotalQuestions(questionariesList.size());
		expected.setTotalUsers(1); 
		
		when(userDAO.getUserCount()).thenReturn((long) 1);
		when(questionariesDAO.getQuestionariesCount()).thenReturn((long) 1);
		when(questionariesDAO.retriveAllUnansweredQuestionaries()).thenReturn(questionariesList);
		when(questionariesDAO.retrieveAllUnansweredQuestionariesByPagination(new PageRequest(0, 3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT))).thenReturn(questionariesList);
		
		QuestionsDTO actual=questionariesServiceImpl.retrieveAllUnansweredQuestionariesByPagination(new PageRequest(0,3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT)); 
		
		assertEquals(expected.getTotalQuestions(), actual.getTotalQuestions());
	} 
	
	@Test
	public void fetchMyQuestionariesByCategoryTest() {
		
		List<QuestionariesDTO> questionList = new ArrayList<QuestionariesDTO>();
		
		QuestionariesDTO questionariesDTO = new QuestionariesDTO();
		
			questionariesDTO.setCategoryName("category1");
			questionariesDTO.setDescription("description");
			questionariesDTO.setDisplayImage("DisplayImage");
			questionariesDTO.setDisplayName("display name");
			questionariesDTO.setEmailId("test@nisum.com");
			questionariesDTO.setQuestion("Question111");
			questionariesDTO.setQuestionId(1);
			questionariesDTO.setQuestionRepliesCount(0);

		questionList.add(questionariesDTO);

		
		
		Categories category = new Categories();
		category.setCategoryId(1);
		category.setCategoryName("category1");
		
		List<Questionaries> questionariesList=new ArrayList<>();
		
		Questionaries questionaries = new Questionaries();
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("Question111");
			questionaries.setQuestionId(1);
			questionaries.setCategoryId(category);
			questionaries.setDescription("description");
	
		
		questionariesList.add(questionaries);
		
		
		QuestionsDTO expected = new QuestionsDTO();
		expected.setTotalQuestions(questionariesList.size());
		expected.setTotalUsers(1); 
		expected.setQuestionDetails(questionList);
		
		when(userDAO.getUserCount()).thenReturn((long) 1);
		when(categoriesDAO.getCategory(1)).thenReturn(category);
		when(questionariesDAO.fetchMyQuestionariesByCategory("test@nisum.com", category, new PageRequest(0, 3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT))).thenReturn(questionariesList);
		when(questionariesDAO.fetchMyQuestionariesCountByCategory("test@nisum.com", category)).thenReturn(questionariesList);
		QuestionsDTO actual=questionariesServiceImpl.fetchMyQuestionariesByCategory("test@nisum.com", 1, new PageRequest(0,3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT)); 
		
		//assertThat(actual).isEqualToComparingFieldByField(expected);
		
		assertEquals(expected.getTotalQuestions(), actual.getTotalQuestions());
	} 
	
	@Test
	public void fetchMyQuestionariesByPaginationTest() {
		
		List<QuestionariesDTO> questionList = new ArrayList<QuestionariesDTO>();
		
		QuestionariesDTO questionariesDTO = new QuestionariesDTO();
		
			questionariesDTO.setCategoryName("category1");
			questionariesDTO.setDescription("description");
			questionariesDTO.setDisplayImage("DisplayImage");
			questionariesDTO.setDisplayName("display name");
			questionariesDTO.setEmailId("test@nisum.com");
			questionariesDTO.setQuestion("Question111");
			questionariesDTO.setQuestionId(1);
			questionariesDTO.setQuestionRepliesCount(0);

		questionList.add(questionariesDTO);

		
		
		Categories category = new Categories();
		category.setCategoryId(1);
		category.setCategoryName("JAVA");
		
		List<Questionaries> questionariesList=new ArrayList<>();
		
		Questionaries questionaries = new Questionaries();
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("what is abc");
			questionaries.setQuestionId(1);
			questionaries.setCategoryId(category);
			questionaries.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			questionaries.setDescription("description");
	
		
		questionariesList.add(questionaries);
		
		
		QuestionsDTO expected = new QuestionsDTO();
		expected.setTotalQuestions(questionariesList.size());
		expected.setTotalUsers(1); 
		
		when(userDAO.getUserCount()).thenReturn((long) 1);
		when(questionariesDAO.fetchMyQuestionariesByPagination("test@nisum.com", new PageRequest(0, 3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT))).thenReturn(questionariesList);
		when(questionariesDAO.fetchMyQuestionaries("test@nisum.com")).thenReturn(questionariesList);
		
		QuestionsDTO actual=questionariesServiceImpl.fetchMyQuestionariesByPagination("test@nisum.com", new PageRequest(0,3, Sort.Direction.ASC, Constants.SORT_BY_ELEMENT)); 
		
		assertEquals(expected.getTotalQuestions(), actual.getTotalQuestions());
	} 
	
}
