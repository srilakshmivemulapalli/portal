package com.nisum.portal.data.dao.api.impl;

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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.impl.TrainingDAOImpl;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.domain.TrainingToUser;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.data.repository.TrainingRepository;
import com.nisum.portal.data.repository.TrainingRequestRepository;
import com.nisum.portal.data.repository.TrainingToUserRepository;
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
	
	@Mock
	TrainingToUserRepository TrainingToUserRepository;
	
	@Test
	public void addTrainingRequestTest(){
		Integer status=1;
		TrainingRequest trainingRequest=new TrainingRequest();
		trainingRequest.setTrainingRequestId(1);
		trainingRequest.setEmailid("mahesh@gmail.com");
		trainingRequest.setRequestTrainingTitle("Java_Thread");
		trainingRequest.setDescription("I have Basic Idea On Java Thread, Technical Training");
		when(trainingRequestRepository.findByTrainingRequestId(1)).thenReturn(null);
		when(trainingRequestRepository.save(trainingRequest)).thenReturn(trainingRequest);
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
		when(trainingRequestRepository.findByTrainingRequestId(1)).thenReturn(trainingRequest);
		assertEquals(status, trainingDAOImpl.addTrainingsRequest(trainingRequest));
	}
	@Test
	public void addTrainingFeedBackTest() {
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		TrainingFeedBack feedBack=new TrainingFeedBack();
		feedBack.setTrainingFeedBackId(1);
		feedBack.setTrainingId(1);
		feedBack.setFeedback("Very Good");
		feedBack.setEmailId("mbheemanapalli@nisum.com");
		feedBack.setCreateDate(timestamp);
		when(trainingsFeedBackRepository.save(feedBack)).thenReturn(feedBack);
		TrainingFeedBack feedBack2 = trainingDAOImpl.addTrainingsFeedBack(feedBack);
		assertEquals(feedBack,feedBack2);
	}
	
	@Test
	public void getAllTrainingRequestsTest()
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
	

	@Test
	public void saveTrainingsTest(){
		
		Trainings trainings=new Trainings();
		
		trainings.setDescription("JAVA ADvance");
		trainings.setTrainingId(1);
		//trainings.setTrainingStatus("Created");
		trainings.setTrainerEmailId("trainer1@nisum.com");
		trainings.setTrainingTitle("JAVA");
		trainings.setTrainingType("TECH");
		trainings.setTrainingStartDate(new Timestamp(new Date(2017-10-30).getTime()));
		trainings.setTrainingEndDate(new Timestamp(new Date(2017-11-30).getTime()));
		trainings.setTrainingEndTime(new Timestamp(new Date(2017-10-30).getTime()));
		trainings.setTrainingStartTime(new Timestamp(new Date(2017-10-30).getTime()));
		Mockito.when(trainingRepository.save(trainings)).thenReturn(trainings);
		Trainings actual=trainingDAOImpl.saveTrainings(trainings);
		assertEquals(trainings,actual);
	
		
	}
	
	@Test
	public void upcomingTrainingTest(){
							
		List<Trainings> expectedList=new ArrayList<Trainings>();
		Trainings  trainings=new  Trainings();
		
		Mockito.when(trainingRepository.fetchMyTrainings("Classroom")).thenReturn(expectedList);
		List<Trainings> actualList=trainingDAOImpl.upcomingTraining("Classroom");
		assertEquals(expectedList,actualList);
		
	}
	
	@Test
	public void completedTrainingTest(){
		List<Trainings> expectedList=new ArrayList<Trainings>();
		String email="dbhattacharya@nisum.com";
		Trainings  trainings=new  Trainings();
		Mockito.when(trainingRepository.fetchCompletedTrainings(email)).thenReturn(expectedList);
		List<Trainings> actualList=trainingDAOImpl.completedTraining(email);
		assertEquals(expectedList,actualList);
	}
	
	@Test
	public void checkTrainingPresenceTest(){
		Integer trainingId=2;
		String email="dbhattacharya@nisum.com";
		Mockito.when(TrainingToUserRepository.fetchTrainingPresence(email,trainingId)).thenReturn(trainingId);
		Integer actual=trainingDAOImpl.checkTrainingPresence(email, trainingId);
		assertEquals(trainingId,actual);
	}
	
	@Test
	public void noOfStudentsTest(){
		Integer trainingId=2;
		List<Object[]> list=new ArrayList<Object[]>();
		Mockito.when(TrainingToUserRepository.fetchnoOfStudent(trainingId)).thenReturn(list);
		List<Object[]> actualList=trainingDAOImpl.noOfStudents(trainingId);
		assertEquals(list,actualList);
	}
	
	
	@Test
	public void trainingToUser(){
		
		TrainingToUser trainingToUser=new TrainingToUser();
		trainingToUser.setEmailId("acb@gmail.com");
		trainingToUser.setTrainingId(5);
		trainingToUser.setTrainingPresence(40);
		trainingToUser.setTrainingToUserId(5);
		//trainingToUser.setUserId(5);
		Mockito.when(TrainingToUserRepository.save(trainingToUser)).thenReturn(trainingToUser);
		TrainingToUser actual=trainingDAOImpl.trainingToUser(trainingToUser);
		assertEquals(trainingToUser,actual);
	}
	
	@Test
	public void getTrainingFeedBacksByTrainingIdTest(){
		List<TrainingFeedBack> list=new ArrayList<TrainingFeedBack>();
		Integer trainingId=2;
		Mockito.when(trainingsFeedBackRepository.findByTrainingId(trainingId)).thenReturn(list);
		List<TrainingFeedBack> actual=trainingDAOImpl.getTrainingFeedBacksByTrainingId(trainingId);
		assertEquals(list,actual);
	}
	
	@Test
	public void getMyTrainingsTest(){
		List<Trainings> expected=new ArrayList<Trainings>();
		String trainerEmailId="trainer1@nisum.com";
		Mockito.when(trainingRepository.getMyTrainings(trainerEmailId)).thenReturn(expected);
		List<Trainings> actual=trainingDAOImpl.getMyTrainings(trainerEmailId);
		assertEquals(expected,actual);
	}
}
