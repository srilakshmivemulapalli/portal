package com.nisum.portal.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
import com.nisum.portal.service.exception.QuestionariesServiceException;

@RunWith(MockitoJUnitRunner.class)
public class QuestionariesRestServiceTest {
	
	@Mock
	QuestionariesService questionariesService;
	
	@InjectMocks
	QuestionariesRestService questionariesRestService;
	
	
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

}
