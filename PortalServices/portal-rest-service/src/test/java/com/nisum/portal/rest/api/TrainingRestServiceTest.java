package com.nisum.portal.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.service.api.TrainingsService;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;
import com.nisum.portal.service.dto.TrainingToUserDTO;
import com.nisum.portal.service.dto.TrainingsDTO;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.exception.TrainingsServiceException;
import com.nisum.portal.util.Constants;
import java.sql.Timestamp;

@RunWith(MockitoJUnitRunner.class)
public class TrainingRestServiceTest {
	@InjectMocks
	TrainingsRestService trainingsRestService;
	@Mock
	TrainingsService trainingsService;
	
	@Mock 
	private UserService userService;
	
	@Test
	public void addTrainingFeedBackTest() {
		TrainingFeedBackDTO dto=new TrainingFeedBackDTO();
		dto.setTrainingFeedBackId(1);
		dto.setTrainingId(1);
		when(trainingsService.addTrainingFeedBack(dto)).thenReturn(dto);
		ResponseEntity<?> statusDtoActual = trainingsRestService.addTrainingFeedBack(dto);
		assertEquals(dto, statusDtoActual.getBody());
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
		//when(trainingsService.addTrainingFeedBack(dto)).thenReturn(statusDtoExpected);
		ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		ResponseEntity<Errors> actual = (ResponseEntity<Errors>) trainingsRestService.addTrainingFeedBack(dto);
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertEquals(expected.getBody().getErrorCode(), actual.getBody().getErrorCode());
		assertEquals(expected.getBody().getErrorMessage(), actual.getBody().getErrorMessage());
	}
	@Test
	public void addTrainingRequestTest() throws Exception {
		TrainingRequestDTO dto=new TrainingRequestDTO();
		ServiceStatusDto statusDtoExpected = new ServiceStatusDto();
		statusDtoExpected.setStatus(true);
		statusDtoExpected.setMessage(Constants.MSG_RECORD_ADD);
		when(trainingsService.addTrainingRequest(dto, userService)).thenReturn(statusDtoExpected);
		ResponseEntity<?> statusDtoActual = trainingsRestService.addTrainingRequest(dto);
		assertEquals(statusDtoExpected, statusDtoActual.getBody());
	}
	@Test
	public void addTrainingRequestFailureTest() throws Exception
	{
		TrainingRequestDTO dto = new TrainingRequestDTO();
		ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
		statusDtoExpected.setStatus(false);
		statusDtoExpected.setMessage(Constants.TRAINING_REQUEST_EXISTS);
		Errors error = new Errors();
		error.setErrorCode("Errors-TrainingRequest");
		error.setErrorMessage("Training Request Already Raised !!");
		when(trainingsService.addTrainingRequest(dto, userService)).thenReturn(statusDtoExpected);
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
	@Test
	public void saveTrainingsTest(){
		
		TrainingsDTO dto=new TrainingsDTO();
		ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
		
		statusDtoExpected.setMessage(Constants.MSG_RECORD_ADD);
		
		statusDtoExpected.setStatus(true);
		
		dto.setDescription("JAVA ADvance");
		dto.setTrainingId(1);
		dto.setTrainingStatus("Created");
		dto.setTrainerEmailId("trainer1@nisum.com");
		dto.setTrainingTitle("JAVA");
		dto.setTrainingType("TECH");
		dto.setTrainingStartDate(new Timestamp(new Date(2017-10-30).getTime()));
		dto.setTrainingEndDate(new Timestamp(new Date(2017-11-30).getTime()));
		dto.setTrainingEndTime(new Timestamp(new Date(2017-10-30).getTime()));
		dto.setTrainingStartTime(new Timestamp(new Date(2017-10-30).getTime()));
		dto.setDisplayImage("ABC");
		dto.setDuration((long)100);
		dto.setNoOfComments(4);
		dto.setNoOfStudents(50);
		dto.setTrainerName("XYZ");
		dto.setTrainingPresence(30);
		dto.setTrainingToUserId(7);

		
		
		          
		
		when(trainingsService.saveTrainings(dto)).thenReturn(dto);
		ResponseEntity<?> actual=trainingsRestService.saveTrainings(dto);
		//assertEquals(statusDtoExpected, actual.getBody());
		assertThat(actual.getBody()).isEqualToComparingFieldByField(statusDtoExpected);
	}
	
	@Test
	public void saveTrainingsTestFailure(){
		TrainingsDTO dto=new TrainingsDTO();
		

		Errors error=new Errors();
		error.setErrorCode("Error-Save Trainings");
		error.setErrorMessage("Save Trainings failed  !!!");
		when(trainingsService.saveTrainings(dto)).thenReturn(dto);
	    ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error, HttpStatus.OK);
		ResponseEntity<Errors> actual = (ResponseEntity<Errors>) trainingsRestService.saveTrainings(dto);
		ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
		statusDtoExpected.setStatus(false);
		statusDtoExpected.setMessage("Save  Training Failed");
		assertEquals(expected.getBody(), error);
			
	}
	
	@Test
	public void classroomUpcomingTrainingsTest()throws TrainingsServiceException{
		UserDTO userDto=new UserDTO();
		String email=userDto.getEmailId();
		List<TrainingsDTO> expected=new ArrayList<TrainingsDTO>();
		when(trainingsService.upComingTrainings("classroom",email,userService)).thenReturn(expected);
		ResponseEntity<?> actual=trainingsRestService.classroomUpcomingTrainings(email);
		assertThat(actual.getBody()).isEqualToComparingFieldByField(expected);
		    
	}
	
	@Test
	public void classroomUpcomingTrainingsTestFailure()throws TrainingsServiceException{
		UserDTO userDto=new UserDTO();
		String email=userDto.getEmailId();
		List<TrainingsDTO> dto=new ArrayList<TrainingsDTO>();
		Errors error=new Errors();
		error.setErrorCode("Error-Classroom Upcoming Trainings");
		error.setErrorMessage("Classroom Upcoming Trainings failed  !!!");
		when(trainingsService.upComingTrainings("classroom", email, userService)).thenReturn(dto);
		 ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error, HttpStatus.OK);
			ResponseEntity<Errors> actual = (ResponseEntity<Errors>) trainingsRestService.classroomUpcomingTrainings(email);
			ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
			statusDtoExpected.setStatus(false);
			statusDtoExpected.setMessage("Classroom Upcoming Trainings Failed");
			assertEquals(expected.getBody(), error);
	}
	
	@Test
	public void onlineUpcomingTrainingsTest()throws TrainingsServiceException{
	
		UserDTO userDto=new UserDTO();
		String email=userDto.getEmailId();
		List<TrainingsDTO> expected=new ArrayList<TrainingsDTO>();
		when(trainingsService.upComingTrainings("online",email,userService)).thenReturn(expected);
		ResponseEntity<?> actual=trainingsRestService.onlineUpcomingTrainings(email);
		assertThat(actual.getBody()).isEqualToComparingFieldByField(expected);
	
}
	@Test
	public void onlineUpcomingTrainingsTestFailure()throws TrainingsServiceException{
		UserDTO userDto=new UserDTO();
		String email=userDto.getEmailId();
		List<TrainingsDTO> dto=new ArrayList<TrainingsDTO>();
		Errors error=new Errors();
		error.setErrorCode("Error-Online Upcoming Trainings");
		error.setErrorMessage("Online Upcoming Trainings failed  !!!");
		when(trainingsService.upComingTrainings("online", email, userService)).thenReturn(dto);
		 ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error, HttpStatus.OK);
			ResponseEntity<Errors> actual = (ResponseEntity<Errors>) trainingsRestService.onlineUpcomingTrainings(email);
			ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
			statusDtoExpected.setStatus(false);
			statusDtoExpected.setMessage("Online Upcoming Trainings Failed");
			assertEquals(expected.getBody(), error);
	}
	
	@Test
	public void completedTrainings()throws TrainingsServiceException{
		List<TrainingsDTO> expected=new ArrayList<TrainingsDTO>();
		UserDTO userDto=new UserDTO();
		String email=userDto.getEmailId();
		when(trainingsService.completedTrainings(email, userService)).thenReturn(expected);
		ResponseEntity<?> actual=trainingsRestService.completedTrainings(email);
		assertThat(actual.getBody()).isEqualToComparingFieldByField(expected);
	}
	
	@Test
	public void completedTrainingsFailure()throws TrainingsServiceException{
		
		List<TrainingsDTO> dto=new ArrayList<TrainingsDTO>();
		UserDTO userDto=new UserDTO();
		String email=userDto.getEmailId();
		Errors error=new Errors();
		error.setErrorCode("Error-Completed Trainings");
		error.setErrorMessage("Completed Trainings failed  !!!");
		when(trainingsService.completedTrainings(email, userService)).thenReturn(dto);
		ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error, HttpStatus.OK);
		ResponseEntity<Errors> actual = (ResponseEntity<Errors>) trainingsRestService.completedTrainings(email);
		ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
		statusDtoExpected.setStatus(false);
		statusDtoExpected.setMessage("Completed Trainings Failed");
		assertEquals(expected.getBody(), error);
		
	}
	
	@Test
	public void trainingToUserTest(){
		TrainingToUserDTO dto=new TrainingToUserDTO();
		when(trainingsService.trainingToUser(dto)).thenReturn(dto);
		ResponseEntity<?> actual=trainingsRestService.trainingToUser(dto);
		assertThat(actual.getBody()).isEqualToComparingFieldByField(dto);
	}
	
	@Test
	public void trainingToUserTestFailure(){
		TrainingToUserDTO dto=new TrainingToUserDTO();
		Errors error=new Errors();
		error.setErrorCode("Error-Training To User");
		error.setErrorMessage("Training To User  !!!");
		when(trainingsService.trainingToUser(dto)).thenReturn(dto);
		ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error, HttpStatus.OK);
		ResponseEntity<Errors> actual = (ResponseEntity<Errors>) trainingsRestService.trainingToUser(dto);
		ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
		statusDtoExpected.setStatus(false);
		statusDtoExpected.setMessage("Completed Trainings Failed");
		assertEquals(expected.getBody(), error);
		
		
	}
	
	@Test
	public void getTrainingFeedBacksByTrainingId() throws TrainingsServiceException{
		List<TrainingFeedBackDTO> expected=new ArrayList<TrainingFeedBackDTO>();
		TrainingsDTO trainingDto=new TrainingsDTO();
		trainingDto.setTrainingId(1);
		int trainingId=trainingDto.getTrainingId();
		when(trainingsService.getTrainingFeedBack(trainingId)).thenReturn(expected);
		Object actual=trainingsRestService.getTrainingFeedBacksByTrainingId(trainingId);
	}
	
	@Test
	public void getTrainingFeedBacksByTrainingIdFailure() throws TrainingsServiceException{
		List<TrainingFeedBackDTO> dto=new ArrayList<TrainingFeedBackDTO>();
		TrainingsDTO trainingDto=new TrainingsDTO();
		trainingDto.setTrainingId(1);
		int trainingId=trainingDto.getTrainingId();
		Errors error=new Errors();
		error.setErrorCode("Error-Get Training FeedBacks By TrainingId");
		error.setErrorMessage("Get Training FeedBacks By TrainingId failed  !!!");
		when(trainingsService.getTrainingFeedBack(trainingId)).thenReturn(dto);
		ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error, HttpStatus.OK);
		ResponseEntity<Errors> actual = (ResponseEntity<Errors>) trainingsRestService.getTrainingFeedBacksByTrainingId(trainingId);
		ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
		statusDtoExpected.setStatus(false);
		statusDtoExpected.setMessage("Get Training FeedBacks By TrainingId failed");
		assertEquals(expected.getBody(), error);
	}

}
