package com.nisum.portal.rest.api;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.api.QuestionRepliesService;
import com.nisum.portal.service.dto.QuestionReplyCommentsDTO;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.QuestionariesRepliesServiceException;
import com.nisum.portal.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class QuestionRepliesRestServiceTest {
	
	@InjectMocks
	QuestionRepliesRestService questionRepliesRestService;
	
	@Mock
	QuestionRepliesService questionRepliesService;
	
	@InjectMocks
	QuestionariesRepliesServiceException questionariesRepliesServiceException;
	
	@Test
	public void saveReplyComment() throws QuestionariesRepliesServiceException {
		
		QuestionReplyCommentsDTO questionReplyCommentsDTO = new QuestionReplyCommentsDTO();
		questionReplyCommentsDTO.setcommentDescription("asasasaa");
		questionReplyCommentsDTO.setCommentId(1);
		questionReplyCommentsDTO.setCreatedDate(new Timestamp(new Date(0).getTime()));
		questionReplyCommentsDTO.setEmailId("test@nisum.com");
		questionReplyCommentsDTO.setid(1);
		
		when(questionRepliesService.findReplyById(questionReplyCommentsDTO.getid())).thenReturn(true);
		when(questionRepliesService.saveReplyComment(questionReplyCommentsDTO.getEmailId(), questionReplyCommentsDTO)).thenReturn(questionReplyCommentsDTO);
		ResponseEntity<?> actual = questionRepliesRestService.saveReplyComment(questionReplyCommentsDTO.getEmailId(), questionReplyCommentsDTO);
		ResponseEntity<QuestionReplyCommentsDTO> expected = new ResponseEntity<QuestionReplyCommentsDTO>(questionReplyCommentsDTO, HttpStatus.OK);
		assertEquals(actual.getStatusCode(), expected.getStatusCode());
	}
	
	
	@Test
	public void replyNotFound() throws QuestionariesRepliesServiceException {
		QuestionReplyCommentsDTO questionReplyCommentsDTO = new QuestionReplyCommentsDTO();
		questionReplyCommentsDTO.setcommentDescription("asasasaa");
		questionReplyCommentsDTO.setCommentId(1);
		questionReplyCommentsDTO.setCreatedDate(new Timestamp(new Date(0).getTime()));
		questionReplyCommentsDTO.setEmailId("test@nisum.com");
		questionReplyCommentsDTO.setid(100);
		
		when(questionRepliesService.findReplyById(questionReplyCommentsDTO.getid())).thenReturn(false);
		ResponseEntity<?> actual = questionRepliesRestService.saveReplyComment(questionReplyCommentsDTO.getEmailId(), questionReplyCommentsDTO);
		ServiceStatusDto expected = new ServiceStatusDto();
		expected.setMessage(Constants.QUESTION_NOT_EXIST);
		expected.setStatus(false);
		ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(expected,HttpStatus.EXPECTATION_FAILED);
		assertEquals(actual.getStatusCode(), entity.getStatusCode());
	}
	
	@Test(expected = Exception.class)
	public void replyCommentException() throws QuestionariesRepliesServiceException {
		when(questionRepliesRestService.saveReplyComment(null,null)).thenThrow(questionariesRepliesServiceException);
	}
	
	@Test
	public void retriveMyReplyQuestions() throws QuestionariesRepliesServiceException {
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

		QuestionsDTO questionsDTO = new QuestionsDTO();
		questionsDTO.setTotalQuestions(0);
		questionsDTO.setTotalUsers(0);
		questionsDTO.setQuestionDetails(questionList);  
		
		ResponseEntity<QuestionsDTO> expected = new ResponseEntity<QuestionsDTO>(questionsDTO, HttpStatus.OK);
		
		when(questionRepliesService.fetchMyReplyQuestionsByPagination("test@nisum.com", new PageRequest(0, 3, Sort.Direction.DESC, Constants.SORT_BY_ELEMENT))).thenReturn(questionsDTO);
		
		
		ResponseEntity<QuestionsDTO> actual = questionRepliesRestService.retriveMyReplyQuestionsByCategory("test@nisum.com", 0, new PageRequest(0,  3));
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void retriveMyReplyQuestionsByCategory() throws QuestionariesRepliesServiceException {
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

		QuestionsDTO questionsDTO = new QuestionsDTO();
		questionsDTO.setTotalQuestions(0);
		questionsDTO.setTotalUsers(0);
		questionsDTO.setQuestionDetails(questionList);  
		
		ResponseEntity<QuestionsDTO> expected = new ResponseEntity<QuestionsDTO>(questionsDTO, HttpStatus.OK);
		
		when(questionRepliesService.fetchMyReplyQuestionsByCategory("test@nisum.com", 1, new PageRequest(0, 3, Sort.Direction.DESC, Constants.SORT_BY_ELEMENT))).thenReturn(questionsDTO);
			
		ResponseEntity<QuestionsDTO> actual = questionRepliesRestService.retriveMyReplyQuestionsByCategory("test@nisum.com", 1, new PageRequest(0,  3));
		
		assertEquals(expected, actual);
	} 
	
	@Test(expected = Exception.class)
	public void replyQuestionsException() throws QuestionariesRepliesServiceException {
		when(questionRepliesRestService.retriveMyReplyQuestionsByCategory(null,0, null)).thenThrow(questionariesRepliesServiceException);
	}

}
