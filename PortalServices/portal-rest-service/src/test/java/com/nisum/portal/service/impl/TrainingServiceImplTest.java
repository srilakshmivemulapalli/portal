package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nisum.portal.data.dao.api.TrainingsDAO;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.domain.TrainingToUser;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.data.repository.TrainingRequestRepository;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;
import com.nisum.portal.service.dto.TrainingToUserDTO;
import com.nisum.portal.service.dto.TrainingsDTO;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.util.TrainingFeedBackUtil;
import com.nisum.portal.util.TrainingRequestUtil;
import com.nisum.portal.util.TrainingsServiceUtil;
//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({TrainingFeedBackUtil.class,TrainingRequestUtil.class,TrainingsServiceUtil.class})
public class TrainingServiceImplTest {
	@InjectMocks
	TrainingsServiceImpl trainingsServiceImpl;
	@Mock
	TrainingsDAO trainingsDAO;
	@Mock
	TrainingRequestRepository trainingRequestRepository;
	
	@Mock
	UserService userService;
	@Test
	public void addTrainingFeedBackSuccessTest(){
		TrainingFeedBackDTO trainingFeedBackDTO = new TrainingFeedBackDTO();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		trainingFeedBackDTO.setTrainingFeedBackId(1);
		trainingFeedBackDTO.setTrainingId(1);
		trainingFeedBackDTO.setFeedback("Very Good");
		trainingFeedBackDTO.setEmailId("mbheemanapalli@nisum.com");
		trainingFeedBackDTO.setCreateDate(timestamp);
		PowerMockito.mockStatic(TrainingFeedBackUtil.class);
		TrainingFeedBack feedback=new TrainingFeedBack();
				feedback.setTrainingId(1);
				feedback.setTrainingFeedBackId(1);
		PowerMockito.when(TrainingFeedBackUtil.convertDtoTODao(trainingFeedBackDTO)).thenReturn(feedback);
		
		Integer status=1;
		//when(trainingsDAO.addTrainingsFeedBack(feedback)).thenReturn(status);
		//ServiceStatusDto actualStatus = trainingsServiceImpl.addTrainingFeedBack(trainingFeedBackDTO);
		ServiceStatusDto expectedStatus = new ServiceStatusDto();
		expectedStatus.setStatus(true);
		expectedStatus.setMessage("Record Added Successfully !!");
		//assertEquals(expectedStatus.isStatus(), actualStatus.isStatus());
		//assertEquals(expectedStatus.getMessage(),actualStatus.getMessage());
	}
	@Test
	public void addTrainingFeedBackFailureTest() {
		TrainingFeedBackDTO trainingFeedBackDTO = new TrainingFeedBackDTO();
		TrainingFeedBack dao = TrainingFeedBackUtil.convertDtoTODao(trainingFeedBackDTO);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setStatus(false);
		serviceStatusDto.setMessage("Training feedback to respective training already submitted !!");
		//when(trainingsDAO.addTrainingsFeedBack(dao)).thenReturn(0);
		//ServiceStatusDto actual = trainingsServiceImpl.addTrainingFeedBack(trainingFeedBackDTO);
		//assertEquals(serviceStatusDto.isStatus(), actual.isStatus());
		//assertEquals(serviceStatusDto.getMessage(), actual.getMessage());
	}
	@Test
	public void addTrainingRequesSuccesstTest() throws Exception{
		TrainingRequestDTO trainingRequestDTO = new TrainingRequestDTO();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		trainingRequestDTO.setRequestedDate(timestamp);
		trainingRequestDTO.setEmailId("mahesh@gmail.com");
		trainingRequestDTO.setRequestTrainingTitle("Java8 Features");
		trainingRequestDTO.setTrainingRequestId(1);
		trainingRequestDTO.setDescription("It is latest version");
		PowerMockito.mockStatic(TrainingRequestUtil.class);
		TrainingRequest request = new TrainingRequest();
		request.setTrainingRequestId(1);
		PowerMockito.when(TrainingRequestUtil.convertDtoTODao(trainingRequestDTO)).thenReturn(request);
		Integer status=1;
		when(trainingsDAO.addTrainingsRequest(request)).thenReturn(status);
		ServiceStatusDto actualStatus = new ServiceStatusDto();
		actualStatus= trainingsServiceImpl.addTrainingRequest(trainingRequestDTO);
		ServiceStatusDto expectedStatus = new ServiceStatusDto();
		expectedStatus.setStatus(true);
		expectedStatus.setMessage("Record Added Successfully !!");
		assertEquals(expectedStatus.isStatus(), actualStatus.isStatus());
		assertEquals(expectedStatus.getMessage(), actualStatus.getMessage());
	}
	@Test
	public void addTrainingRequestFailureTest() throws Exception {
		TrainingRequestDTO dto = new TrainingRequestDTO();
		TrainingRequest dao = TrainingRequestUtil.convertDtoTODao(dto);
		ServiceStatusDto expectedStatus = new ServiceStatusDto();
		expectedStatus.setStatus(false);
		expectedStatus.setMessage("Training Request Already Raised !!");
		when(trainingsDAO.addTrainingsRequest(dao)).thenReturn(0);
		ServiceStatusDto actualStatus = trainingsServiceImpl.addTrainingRequest(dto);
		assertEquals(expectedStatus.isStatus(), actualStatus.isStatus());
		assertEquals(expectedStatus.getMessage(), actualStatus.getMessage());
	}
	@Test
	public void getAllTrainingRequestsTest() {
		List<TrainingRequest> expected=new ArrayList<TrainingRequest>();
		when(trainingsDAO.getTrainingRequests()).thenReturn(expected);
		List<TrainingRequestDTO> actual = trainingsServiceImpl.getAllTrainingRequests();
		assertEquals(expected, actual);
	}
	
	@Test
	public void saveTrainingsTest(){
		
		TrainingsDTO trainingDto=new  TrainingsDTO();
		trainingDto.setDescription("JAVA ADvance");
		trainingDto.setTrainingId(1);
		trainingDto.setTrainingStatus("Created");
		trainingDto.setTrainerEmailId("trainer1@nisum.com");
		trainingDto.setTrainingTitle("JAVA");
		trainingDto.setTrainingType("TECH");
		trainingDto.setTrainingStartDate(new Timestamp(new Date(2017-10-30).getTime()));
		trainingDto.setTrainingEndDate(new Timestamp(new Date(2017-11-30).getTime()));
		trainingDto.setTrainingEndTime(new Timestamp(new Date(2017-10-30).getTime()));
		trainingDto.setTrainingStartTime(new Timestamp(new Date(2017-10-30).getTime()));
		
		PowerMockito.mockStatic(TrainingsServiceUtil.class);
		
		Trainings training=new Trainings();
		training.setTrainingId(1);
		
		PowerMockito.when(TrainingsServiceUtil.convertDtoToDao(trainingDto)).thenReturn(training);
		when(trainingsDAO.saveTrainings(training)).thenReturn(training);
		PowerMockito.when(TrainingsServiceUtil.convertTrainingsDaoTODto(training)).thenReturn(trainingDto);
		
		TrainingsDTO actual=trainingsServiceImpl.saveTrainings(trainingDto); 
		
		System.out.println(actual.getTrainingTitle());
		assertEquals(trainingDto.getTrainingId(), actual.getTrainingId());
	
	}

	@Test
	public void upComingTrainingsTest(){
		List<TrainingsDTO> upcomeList=new ArrayList<TrainingsDTO>();
		UserDTO user=new UserDTO();
		String email=user.getEmailId();
		
		PowerMockito.mockStatic(TrainingsServiceUtil.class);
		PowerMockito.when(TrainingsServiceUtil.convertDaoTODto(trainingsDAO.upcomingTraining("classroom"))).thenReturn(upcomeList);
		List<TrainingsDTO> actual=trainingsServiceImpl.upComingTrainings("classroom", email, userService);
		assertEquals(upcomeList,actual);
	}
	
	@Test
	public void completedTrainingsTest(){
		List<TrainingsDTO> dtoList=new ArrayList<TrainingsDTO>();
		List<Trainings>trainingList=new ArrayList<Trainings>();
		UserDTO user=new UserDTO();
		String email=user.getEmailId();
		PowerMockito.mockStatic(TrainingsServiceUtil.class);
		PowerMockito.when(TrainingsServiceUtil.convertDaoTODto(trainingList)).thenReturn(dtoList);
		List<TrainingsDTO> actualList=trainingsServiceImpl.completedTrainings(email, userService);
		assertEquals(dtoList,actualList);
	}
	
	@Test
	public void trainingToUser(){
		TrainingToUserDTO expected=new TrainingToUserDTO();
		TrainingToUser trainingToUser=new TrainingToUser();
		PowerMockito.mockStatic(TrainingsServiceUtil.class);
		PowerMockito.when(TrainingsServiceUtil.convertTrainingToUserDaoToDto(trainingsDAO.trainingToUser(trainingToUser))).thenReturn(expected);
		TrainingToUserDTO actual=trainingsServiceImpl.trainingToUser(expected);
		assertEquals(expected,actual);
	}

}
