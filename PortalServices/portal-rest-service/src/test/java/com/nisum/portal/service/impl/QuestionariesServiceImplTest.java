package com.nisum.portal.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import com.nisum.portal.data.dao.api.QuestionariesCommentsDAO;
import com.nisum.portal.data.dao.api.QuestionariesDAO;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.domain.QuestionariesComments;
import com.nisum.portal.service.dto.CountDTO;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
import com.nisum.portal.util.QuestionariesUtil;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class) 
@PrepareForTest(QuestionariesUtil.class)
public class QuestionariesServiceImplTest {
	
	@Mock
	QuestionariesDAO questionariesDAO;
	
	@Mock
	UserDAO userDAO;
	
	@InjectMocks
	QuestionariesServiceImpl questionariesServiceImpl;
	
	@Mock
	QuestionariesCommentsDAO questionariesCommentsDAO;

	

	
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
}
