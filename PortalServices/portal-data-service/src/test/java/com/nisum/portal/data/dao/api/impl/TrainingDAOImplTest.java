package com.nisum.portal.data.dao.api.impl;

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

import com.nisum.portal.data.dao.impl.TrainingDAOImpl;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.data.repository.TrainingRepository;
import com.nisum.portal.data.repository.TrainingRequestRepository;
import com.nisum.portal.data.repository.TrainingsFeedBackRepository;

@RunWith(MockitoJUnitRunner.class)
public class TrainingDAOImplTest {
	@InjectMocks
	TrainingDAOImpl trainingDAOImpl;
	@Mock
	TrainingRepository trainingRepository;
	@Mock
	TrainingRequestRepository trainingRequestRepository;
	@Mock
	TrainingsFeedBackRepository trainingsFeedBackRepository;
	
	@Test
	public void addTrainingRequestTest(){
		Integer status=1;
		TrainingRequest trainingRequest=new TrainingRequest();
		trainingRequest.setTrainingRequestId(1);
		trainingRequest.setEmailid("mahesh@gmail.com");
		trainingRequest.setRequestTrainingTitle("Java_Thread");
		trainingRequest.setDescription("I have Basic Idea On Java Thread, Technical Training");
		when(trainingRequestRepository.findByTrainingRequestId(1)).thenReturn(null);
		when(trainingRequestRepository.save(trainingRequest)).thenReturn(new TrainingRequest());
		assertEquals(status, trainingDAOImpl.addTrainingsRequest(trainingRequest));
	}
	@Test
	public void addTrainingRequestFailureTest() {
		Integer status=0;
		TrainingRequest trainingRequest=new TrainingRequest();
		trainingRequest.setTrainingRequestId(1);
		trainingRequest.setEmailid("mahesh@gmail.com");
		trainingRequest.setRequestTrainingTitle("Java_Thread");
		trainingRequest.setDescription("I have Basic Idea On Java Thread, Technical Training");
		when(trainingRequestRepository.findByTrainingRequestId(1)).thenReturn(new TrainingRequest());
		assertEquals(status, trainingDAOImpl.addTrainingsRequest(trainingRequest));
	}
	@Test
	public void addTrainingFeedBackTest() {
		Integer status=1;
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		TrainingFeedBack feedBack=new TrainingFeedBack();
		feedBack.setTrainingFeedBackId(1);
		Trainings trainings=new Trainings();
		trainings.setTrainingId(1);
		feedBack.setTrainings(trainings);
		feedBack.setFeedback("Very Good");
		feedBack.setRating("Very Nice");
		feedBack.setCreateDate(timestamp);
		when(trainingsFeedBackRepository.findByTrainingFeedBackId(1)).thenReturn(null);
		when(trainingsFeedBackRepository.save(feedBack)).thenReturn(new TrainingFeedBack());
		assertEquals(status,trainingDAOImpl.addTrainingsFeedBack(feedBack));
	}
	@Test
	public void addTrainingFeedBackFailureTest() {
		Integer status=0;
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		TrainingFeedBack feedBack=new TrainingFeedBack();
		feedBack.setTrainingFeedBackId(1);
		Trainings trainings=new Trainings();
		trainings.setTrainingId(1);
		feedBack.setTrainings(trainings);
		feedBack.setFeedback("Very Good");
		feedBack.setRating("Very Nice");
		feedBack.setCreateDate(timestamp);
		when(trainingsFeedBackRepository.findByTrainingFeedBackId(1)).thenReturn(new TrainingFeedBack());
		assertEquals(status,trainingDAOImpl.addTrainingsFeedBack(feedBack));
	}
	@Test
	public void getAllTrainingRequests()
	{
		TrainingRequest trainingRequest = new TrainingRequest();
		List<TrainingRequest> requestList=new ArrayList<TrainingRequest>();
		trainingRequest.setTrainingRequestId(1);
		trainingRequest.setEmailid("mahesh@gmail.com");
		trainingRequest.setRequestTrainingTitle("Java_Thread");
		trainingRequest.setDescription("I have Basic Idea On Java Thread, Technical Training");
		requestList.add(trainingRequest);
		Mockito.when(trainingRequestRepository.findAll()).thenReturn(requestList);
		List<TrainingRequest> actual = trainingDAOImpl.getTrainingRequests();
		assertEquals(requestList, actual);
	}
}
