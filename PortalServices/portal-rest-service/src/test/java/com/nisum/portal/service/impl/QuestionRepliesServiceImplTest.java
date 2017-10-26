package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.PageRequest;

import com.nisum.portal.data.dao.api.QuestionRepliesDAO;
import com.nisum.portal.data.dao.api.QuestionReplyCommentsDAO;
import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.QuestionReplyComments;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.QuestionReplyCommentsDTO;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.util.QuestionReplysUtil;
import com.nisum.portal.util.QuestionariesUtil;

@RunWith(PowerMockRunner.class) 
@PrepareForTest({QuestionariesUtil.class,QuestionReplysUtil.class})
public class QuestionRepliesServiceImplTest {
	
	
	@Mock
	QuestionReplyCommentsDAO questionReplyCommentsDAO;
	
	@InjectMocks
	QuestionRepliesServiceImpl questionRepliesServiceImpl=new QuestionRepliesServiceImpl();
	
	@Mock
	QuestionRepliesDAO repliesDAO;
	
	@Mock
	UserService userervice;
	
	/*@Test
	public void saveQuestionComment() {
		QuestionReplyCommentsDTO questionReplyCommentsDTOExpected = new QuestionReplyCommentsDTO();
		questionReplyCommentsDTOExpected.setcommentDescription("asasasaa");
		questionReplyCommentsDTOExpected.setid(1);
		questionReplyCommentsDTOExpected.setCreatedDate(new Timestamp(new Date(0).getTime()));
		questionReplyCommentsDTOExpected.setEmailId("test@nisum.com");
		questionReplyCommentsDTOExpected.setCommentId(1);
		
		QuestionReplyCommentsDTO request = new QuestionReplyCommentsDTO();
		request.setcommentDescription("asasasaa");
		request.setid(1);
		
		QuestionReplyComments questionReplyCommentsMock = new QuestionReplyComments();
		questionReplyCommentsMock.setcommentDescription("asasasaa");
		questionReplyCommentsMock.setCommentId(1);
		questionReplyCommentsMock.setCreatedDate(new Timestamp(new Date(0).getTime()));
		questionReplyCommentsMock.setEmailId("test@nisum.com");
		questionReplyCommentsMock.setReplyId(1);
		
		PowerMockito.mockStatic(QuestionReplysUtil.class); 
		PowerMockito.when(QuestionReplysUtil.convertCommentDtoToDao(request)).thenReturn(questionReplyCommentsMock);
		when(questionReplyCommentsDAO.saveReplyComments(questionReplyCommentsMock)).thenReturn(questionReplyCommentsMock);
		PowerMockito.when(QuestionReplysUtil.convertCommentDaoToDto(questionReplyCommentsMock)).thenReturn(questionReplyCommentsDTOExpected);
		
		QuestionReplyCommentsDTO actual = questionRepliesServiceImpl.saveReplyComment("test@nisum.com", request);
		System.out.println("dsssssdsds"+actual);
		assertEquals(questionReplyCommentsDTOExpected.getid(), actual.getid());
	}
	
	@Test
	public void findreplyById() {
		int replyId = 1;
		QuestionReplies reply = new QuestionReplies();
		reply.setEmailid("test@nisum.com");
		reply.setReplyDescription("hsjkhbsd");
		reply.setReplyId(1);
		
		when(questionRepliesDAO.getReply(replyId)).thenReturn(reply);
		boolean actual = questionRepliesServiceImpl.findReplyById(replyId);
		assertEquals(true, actual);
	}
	
	@Test
	public void findReplyByIdFail() {
		int replyId = 1;
		
		when(questionRepliesDAO.getReply(replyId)).thenReturn(null);
		boolean actual = questionRepliesServiceImpl.findReplyById(replyId);
		assertEquals(false, actual);
	}*/
	
	@Test
	public void fetchMyReplyQuestions() {
		
		Questionaries questionaries = new Questionaries();
		questionaries.setDescription("description");
		questionaries.setEmailId("test@nisum.com");
		questionaries.setQuestion("What is java");
		questionaries.setQuestionId(1);
		
		List<Questionaries> questionsList = new ArrayList<Questionaries>();
		questionsList.add(questionaries);
		
		QuestionariesDTO questionariesDTO = new QuestionariesDTO();
		questionariesDTO.setDescription("ghjhjj");
		questionariesDTO.setEmailId("test@nisum.com");
		questionariesDTO.setQuestion("What is java");
		questionariesDTO.setQuestionId(1);
		
		List<QuestionariesDTO> questionariesList = new ArrayList<QuestionariesDTO>();
		questionariesList.add(questionariesDTO);
		
		QuestionsDTO expected = new QuestionsDTO();
		expected.setTotalQuestions(1);
		expected.setTotalUsers(0);
		expected.setQuestionDetails(questionariesList);
		
		QuestionsDTO questionsDTO = new QuestionsDTO();
		questionsDTO.setTotalQuestions(questionsList.size());
		
		when(repliesDAO.getMyReplyQuestions("test@nisum.com", new PageRequest(0, 3))).thenReturn(questionsList); 
		
		PowerMockito.mockStatic(QuestionariesUtil.class); 
		PowerMockito.when(QuestionariesUtil.convertDaoToDto(questionsList,questionsDTO,userervice)).thenReturn(expected);
		
		System.out.println(questionRepliesServiceImpl.fetchMyReplyQuestions("test@nisum.com", new PageRequest(0, 3))+",dfbg "+repliesDAO.getMyReplyQuestions("test@nisum.com", new PageRequest(0, 3)));
		
		QuestionsDTO actual = questionRepliesServiceImpl.fetchMyReplyQuestions("test@nisum.com", new PageRequest(0, 3));
		
		assertEquals(expected.getTotalQuestions(), actual.getTotalQuestions());
		
	}

}
