package com.nisum.portal.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.service.api.QuestionariesService;
import com.nisum.portal.service.dto.CountDTO;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.QuestionariesServiceException;
import com.nisum.portal.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class QuestionariesRestServiceTest {
	
	@Mock
	QuestionariesService questionariesService;
	
	@InjectMocks
	QuestionariesRestService questionariesRestService;
	
	@InjectMocks
	QuestionariesServiceException questionariesServiceException;
	
	
	@Test
	public void retrieveCountTestSuccess() throws QuestionariesServiceException {
		CountDTO countDTO=new CountDTO();
		countDTO.setQuestionCount(0);
		countDTO.setUserCount(0);
		when(questionariesService.getQuestionariesCount()).thenReturn(countDTO);
		
		ResponseEntity<CountDTO> responseEntity=new ResponseEntity<CountDTO>(questionariesService.getQuestionariesCount(), HttpStatus.OK);
		
		assertEquals(responseEntity, questionariesRestService.retrieveCount());
	}
	
	@Test
	public void retrieveCountTestFailure() throws QuestionariesServiceException {
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Questionaries");
		//errors.setErrorMessage("Could not open JPA EntityManager for transaction; nested exception is org.hibernate.exception.JDBCConnectionException: Unable to acquire JDBC Connection");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		
		when(questionariesService.getQuestionariesCount()).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>) questionariesRestService.retrieveCount();
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void saveQuestionComment() throws QuestionariesServiceException {
		QuestionariesCommentsDTO questionariesCommentsDTO = new QuestionariesCommentsDTO();
		questionariesCommentsDTO.setcommentDescription("asasasaa");
		questionariesCommentsDTO.setCommentId(1);
		questionariesCommentsDTO.setCreatedDate(new Timestamp(new Date(0).getTime()));
		questionariesCommentsDTO.setEmailId("test@nisum.com");
		questionariesCommentsDTO.setid(1);
		
		when(questionariesService.findQuestionById(questionariesCommentsDTO.getid())).thenReturn(true);
		when(questionariesService.saveQuestionComment(questionariesCommentsDTO.getEmailId(), questionariesCommentsDTO)).thenReturn(questionariesCommentsDTO);
		ResponseEntity<?> actual = questionariesRestService.saveQuestionComment(questionariesCommentsDTO.getEmailId(), questionariesCommentsDTO);
		ResponseEntity<QuestionariesCommentsDTO> entity = new ResponseEntity<QuestionariesCommentsDTO>(questionariesCommentsDTO,HttpStatus.OK);
		System.out.println(actual.getStatusCode());
		assertEquals(entity.getStatusCode(), actual.getStatusCode());
	}
	
	@Test
	public void questionNotFound() throws QuestionariesServiceException {
		QuestionariesCommentsDTO questionariesCommentsDTO = new QuestionariesCommentsDTO();
		questionariesCommentsDTO.setcommentDescription("asasasaa");
		questionariesCommentsDTO.setCommentId(1);
		questionariesCommentsDTO.setCreatedDate(new Timestamp(new Date(0).getTime()));
		questionariesCommentsDTO.setEmailId("test@nisum.com");
		questionariesCommentsDTO.setid(10000);
		
		when(questionariesService.findQuestionById(questionariesCommentsDTO.getid())).thenReturn(false);
		ResponseEntity<?> actual = questionariesRestService.saveQuestionComment(questionariesCommentsDTO.getEmailId(), questionariesCommentsDTO);
		ServiceStatusDto expected = new ServiceStatusDto();
		expected.setMessage(Constants.QUESTION_NOT_EXIST);
		expected.setStatus(false);
		ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(expected,HttpStatus.EXPECTATION_FAILED);
		assertEquals(entity.getStatusCode(), actual.getStatusCode());
	}
	
	@Test(expected = Exception.class)
	public void questionCommentException() throws QuestionariesServiceException {
		when(questionariesRestService.saveQuestionComment(null,null)).thenThrow(questionariesServiceException);
	}

}
