package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

import com.nisum.portal.data.dao.api.TrainingsDAO;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.data.repository.TrainingRequestRepository;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;
import com.nisum.portal.util.TrainingFeedBackUtil;
import com.nisum.portal.util.TrainingRequestUtil;
//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({TrainingFeedBackUtil.class,TrainingRequestUtil.class})
public class TrainingServiceImplTest {
	@InjectMocks
	TrainingsServiceImpl trainingsServiceImpl;
	@Mock
	TrainingsDAO trainingsDAO;
	@Mock
	TrainingRequestRepository trainingRequestRepository;
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
				feedback.setTrainings(new Trainings(1));
				feedback.setTrainingFeedBackId(1);
		PowerMockito.when(TrainingFeedBackUtil.convertDtoTODao(trainingFeedBackDTO)).thenReturn(feedback);
		
		Integer status=1;
		when(trainingsDAO.addTrainingsFeedBack(feedback)).thenReturn(status);
		ServiceStatusDto actualStatus = trainingsServiceImpl.addTrainingFeedBack(trainingFeedBackDTO);
		ServiceStatusDto expectedStatus = new ServiceStatusDto();
		expectedStatus.setStatus(true);
		expectedStatus.setMessage("Record Added Successfully !!");
		assertEquals(expectedStatus.isStatus(), actualStatus.isStatus());
		assertEquals(expectedStatus.getMessage(),actualStatus.getMessage());
	}
	@Test
	public void addTrainingFeedBackFailureTest() {
		TrainingFeedBackDTO trainingFeedBackDTO = new TrainingFeedBackDTO();
		TrainingFeedBack dao = TrainingFeedBackUtil.convertDtoTODao(trainingFeedBackDTO);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setStatus(false);
		serviceStatusDto.setMessage("Training feedback to respective training already submitted !!");
		when(trainingsDAO.addTrainingsFeedBack(dao)).thenReturn(0);
		ServiceStatusDto actual = trainingsServiceImpl.addTrainingFeedBack(trainingFeedBackDTO);
		assertEquals(serviceStatusDto.isStatus(), actual.isStatus());
		assertEquals(serviceStatusDto.getMessage(), actual.getMessage());
	}
	@Test
	public void addTrainingRequesSuccesstTest(){
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
	public void addTrainingRequestFailureTest() {
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
}
