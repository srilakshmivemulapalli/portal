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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.api.TrainingsDAO;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.repository.TrainingRequestRepository;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;
import com.nisum.portal.util.TrainingFeedBackUtil;
import com.nisum.portal.util.TrainingRequestUtil;
@RunWith(MockitoJUnitRunner.class)
public class TrainingServiceImplTest {
	@InjectMocks
	TrainingsServiceImpl trainingsServiceImpl;
	@Mock
	TrainingsDAO trainingsDAO;
	@Mock
	TrainingRequestRepository trainingRequestRepository;
	@Test
	public void addTrainingFeedBackSuccessTest(){
		ServiceStatusDto expectedStatus = new ServiceStatusDto();
		expectedStatus.setStatus(true);
		TrainingFeedBackDTO trainingFeedBackDTO = new TrainingFeedBackDTO();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		trainingFeedBackDTO.setTrainingFeedBackId(1);
		trainingFeedBackDTO.setTrainingId(1);
		trainingFeedBackDTO.setFeedback("Very Good");
		trainingFeedBackDTO.setRating("Very Nice");
		trainingFeedBackDTO.setCreateDate(timestamp);
		TrainingFeedBack trainingFeedBack = TrainingFeedBackUtil.convertDtoTODao(trainingFeedBackDTO);
		System.out.println(trainingFeedBack.toString());
		Mockito.when(trainingsDAO.addTrainingsFeedBack(trainingFeedBack)).thenReturn(1);
		ServiceStatusDto actualStatus = trainingsServiceImpl.addTrainingFeedBack(trainingFeedBackDTO);
		assertEquals(expectedStatus.isStatus(), actualStatus.isStatus());
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
		Boolean status=false;
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		TrainingRequestDTO trainingRequestDTO = new TrainingRequestDTO();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		trainingRequestDTO.setRequestedDate(timestamp);
		TrainingRequest dao = TrainingRequestUtil.convertDtoTODao(trainingRequestDTO);
		when(trainingsDAO.addTrainingsRequest(dao)).thenReturn(0);
		serviceStatusDto= trainingsServiceImpl.addTrainingRequest(trainingRequestDTO);
		assertEquals(serviceStatusDto.isStatus(), status);
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
