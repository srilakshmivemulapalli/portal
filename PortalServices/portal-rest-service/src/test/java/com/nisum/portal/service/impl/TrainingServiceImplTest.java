package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.impl.TrainingDAOImpl;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.repository.TrainingRepository;
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
	TrainingDAOImpl trainingDAOImpl;
	@Mock
	TrainingRequestRepository trainingRequestRepository;
	@Test
	public void addTrainingFeedBackTest(){
		Boolean status=false;
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		TrainingFeedBackDTO trainingFeedBackDTO = new TrainingFeedBackDTO();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		trainingFeedBackDTO.setTrainingFeedBackId(1);
		trainingFeedBackDTO.setTrainingId(1);
		trainingFeedBackDTO.setFeedback("Very Good");
		trainingFeedBackDTO.setRating("Very Nice");
		trainingFeedBackDTO.setCreateDate(timestamp);
		TrainingFeedBack trainingFeedBack = TrainingFeedBackUtil.convertDtoTODao(trainingFeedBackDTO);
		System.out.println(trainingFeedBack.toString());
		when(trainingDAOImpl.addTrainingsFeedBack(trainingFeedBack)).thenReturn(0);
		serviceStatusDto= trainingsServiceImpl.addTrainingFeedBack(trainingFeedBackDTO);
		assertEquals(serviceStatusDto.isStatus(), status);
	}
	@Test
	public void addTrainingRequestTest(){
		Boolean status=false;
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		TrainingRequestDTO trainingRequestDTO = new TrainingRequestDTO();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		trainingRequestDTO.setRequestedDate(timestamp);
		TrainingRequest dao = TrainingRequestUtil.convertDtoTODao(trainingRequestDTO);
		when(trainingDAOImpl.addTrainingsRequest(dao)).thenReturn(0);
		serviceStatusDto= trainingsServiceImpl.addTrainingRequest(trainingRequestDTO);
		assertEquals(serviceStatusDto.isStatus(), status);
	}
	@Test
	public void addTrainingRequestFailureTest() {
		Boolean status1=true;
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		TrainingRequestDTO trainingRequestDTO = new TrainingRequestDTO();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		trainingRequestDTO.setTrainingRequestId(2);
		trainingRequestDTO.setRequestTrainingTitle("Java Threads");
		trainingRequestDTO.setEmailid("mahesh@gmail.com");
		trainingRequestDTO.setDescription("Basic Info Needed");
		trainingRequestDTO.setRequestedDate(timestamp);
		TrainingRequest dao = TrainingRequestUtil.convertDtoTODao(trainingRequestDTO);
		dao.setTrainingRequestId(2);
		dao.setRequestTrainingTitle("Java Threads");
		dao.setEmailid("mahesh@gmail.com");
		dao.setDescription("Basic Info Needed");
		dao.setRequestedDate(timestamp);
		when(trainingRequestRepository.findByTrainingRequestId(2)).thenReturn(null);
		when(trainingDAOImpl.addTrainingsRequest(dao)).thenReturn(1);
		serviceStatusDto= trainingsServiceImpl.addTrainingRequest(trainingRequestDTO);
		assertEquals(serviceStatusDto.isStatus(), status1);
	}
}
