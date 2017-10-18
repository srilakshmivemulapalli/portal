package com.nisum.portal.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.service.api.TrainingsService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;
import com.nisum.portal.service.exception.TrainingsServiceException;
import com.nisum.portal.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class TrainingRestServiceTest {
	@InjectMocks
	TrainingsRestService trainingsRestService;
	@Mock
	TrainingsService trainingsService;
	@Test
	public void addTrainingFeedBackTest() {
		TrainingFeedBackDTO dto=new TrainingFeedBackDTO();
		ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
		statusDtoExpected.setStatus(true);
		statusDtoExpected.setMessage(Constants.MSG_RECORD_ADD);
		when(trainingsService.addTrainingFeedBack(dto)).thenReturn(statusDtoExpected);
		ResponseEntity<?> statusDtoActual = trainingsRestService.addTrainingFeedBack(dto);
		assertEquals(statusDtoExpected, statusDtoActual.getBody());
	}
	@Test
	public void addTrainingFeedBackFailureTest() {
		TrainingFeedBackDTO dto=new TrainingFeedBackDTO();
		ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
		statusDtoExpected.setStatus(false);
		statusDtoExpected.setMessage(Constants.TRAINING_FEEDBACK_EXISTS);
		Errors error = new Errors();
		error.setErrorCode("Errors-TrainingFeedBack");
		error.setErrorMessage("Training feedback to respective training already submitted !!");
		when(trainingsService.addTrainingFeedBack(dto)).thenReturn(statusDtoExpected);
		ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		ResponseEntity<Errors> actual = (ResponseEntity<Errors>) trainingsRestService.addTrainingFeedBack(dto);
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertEquals(expected.getBody().getErrorCode(), actual.getBody().getErrorCode());
		assertEquals(expected.getBody().getErrorMessage(), actual.getBody().getErrorMessage());
	}
	@Test
	public void addTrainingRequestTest() {
		TrainingRequestDTO dto=new TrainingRequestDTO();
		ServiceStatusDto statusDtoExpected = new ServiceStatusDto();
		statusDtoExpected.setStatus(true);
		statusDtoExpected.setMessage(Constants.MSG_RECORD_ADD);
		when(trainingsService.addTrainingRequest(dto)).thenReturn(statusDtoExpected);
		ResponseEntity<?> statusDtoActual = trainingsRestService.addTrainingRequest(dto);
		assertEquals(statusDtoExpected, statusDtoActual.getBody());
	}
	@Test
	public void addTrainingRequestFailureTest()
	{
		TrainingRequestDTO dto = new TrainingRequestDTO();
		ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
		statusDtoExpected.setStatus(false);
		statusDtoExpected.setMessage(Constants.TRAINING_REQUEST_EXISTS);
		Errors error = new Errors();
		error.setErrorCode("Errors-TrainingRequest");
		error.setErrorMessage("Training Request Already Raised !!");
		when(trainingsService.addTrainingRequest(dto)).thenReturn(statusDtoExpected);
		ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		ResponseEntity<Errors> actual = (ResponseEntity<Errors>) trainingsRestService.addTrainingRequest(dto);
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertEquals(expected.getBody().getErrorCode(), actual.getBody().getErrorCode());
		assertEquals(expected.getBody().getErrorMessage(), actual.getBody().getErrorMessage());
	}
	@Test
	public void getAllTrainingRequestsTest() throws TrainingsServiceException {
			List<TrainingRequestDTO> expected=new ArrayList<TrainingRequestDTO>();
			when(trainingsRestService.getAllTrainingRequests()).thenReturn(expected);
			List<TrainingRequestDTO> actual = (List<TrainingRequestDTO>) trainingsRestService.getAllTrainingRequests();
			Assert.assertEquals(expected, actual);
	}
	@Test
	public void getAllTrainingRequestsFailureTest() throws TrainingsServiceException {
		when(trainingsService.getAllTrainingRequests()).thenThrow(Exception.class);
		Errors error=new Errors();
		error.setErrorCode("Error-All Trainings Requests");
		error.setErrorMessage("No Data Exist In DataBase !!!");
		ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error, HttpStatus.OK);
		ResponseEntity<Errors> actual = (ResponseEntity<Errors>) trainingsRestService.getAllTrainingRequests();
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertEquals(expected.getBody().getErrorMessage(),actual.getBody().getErrorMessage());
		assertEquals(expected.getBody().getErrorCode(),actual.getBody().getErrorCode());
	}
}
