package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nisum.portal.data.dao.api.TrainingsDAO;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.domain.TrainingToUser;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.data.repository.TrainingRequestRepository;
import com.nisum.portal.service.api.EmailAccount;
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
import com.nisum.portal.util.MailSender;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TrainingsServiceUtil.class,TrainingFeedBackUtil.class,TrainingRequestUtil.class,MailSender.class})
public class TrainingServiceImplTest {
	
	@InjectMocks
	TrainingsServiceImpl trainingsServiceImpl;
	
	@Mock
	TrainingsDAO trainingsDAO;
	
	@Mock
	TrainingRequestRepository trainingRequestRepository;
	
	@Mock
	UserService userService;

	@InjectMocks
	Map<String, UserDTO> map=new HashMap<String,UserDTO>();
	@Mock
	EmailAccount emailAccount;
	
	@Test
	public void addTrainingFeedBackSuccessTest(){
		TrainingFeedBackDTO trainingFeedBackDTO = new TrainingFeedBackDTO();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		trainingFeedBackDTO.setTrainingFeedBackId(1);
		trainingFeedBackDTO.setTrainingId(1);
		trainingFeedBackDTO.setFeedback("Very Good");
		trainingFeedBackDTO.setEmailId("mbheemanapalli@nisum.com");
		trainingFeedBackDTO.setCreateDate(timestamp);
		TrainingFeedBack feedback=new TrainingFeedBack();
		feedback.setTrainingFeedBackId(1);
		feedback.setTrainingId(1);
		feedback.setFeedback("Very Good");
		feedback.setEmailId("mbheemanapalli@nisum.com");
		feedback.setCreateDate(timestamp);
		PowerMockito.mockStatic(TrainingFeedBackUtil.class);
		PowerMockito.when(TrainingFeedBackUtil.convertDtoTODao(trainingFeedBackDTO)).thenReturn(feedback);
		Mockito.when(trainingsDAO.addTrainingsFeedBack(feedback)).thenReturn(feedback);
		PowerMockito.when(TrainingFeedBackUtil.convertDaoTODto(feedback)).thenReturn(trainingFeedBackDTO);
		TrainingFeedBackDTO actual = trainingsServiceImpl.addTrainingFeedBack(trainingFeedBackDTO);
		assertEquals(trainingFeedBackDTO.getTrainingFeedBackId(), actual.getTrainingFeedBackId());
		assertEquals(trainingFeedBackDTO.getTrainingId(), actual.getTrainingId());
		assertEquals(trainingFeedBackDTO.getFeedback(), actual.getFeedback());
		assertEquals(trainingFeedBackDTO.getEmailId(), actual.getEmailId());
		assertEquals(trainingFeedBackDTO.getCreateDate(), actual.getCreateDate());
	}
	
	@Test
	public void addTrainingRequesSuccesstTest() throws Exception{
		//Assigning system time
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//Created Training DTO Object 
		TrainingRequestDTO trainingRequestDTO = new TrainingRequestDTO();
		trainingRequestDTO.setTrainingRequestId(1);
		trainingRequestDTO.setEmailId("mbheemanapalli@nisum.com");
		trainingRequestDTO.setRequestTrainingTitle("Java8 Features");
		trainingRequestDTO.setDescription("It is latest version");
		trainingRequestDTO.setRequestedDate(timestamp);
		trainingRequestDTO.setRequestStatus(0);
		TrainingRequest request = new TrainingRequest();
		request.setTrainingRequestId(1);
		request.setEmailid("mbheemanapalli@nisum.com");
		request.setRequestTrainingTitle("Java8 Features");
		request.setDescription("It is latest version");
		request.setRequestedDate(timestamp);  
		request.setRequestStatus(0);
		PowerMockito.mockStatic(TrainingRequestUtil.class);
		PowerMockito.when(TrainingRequestUtil.convertDtoTODao(trainingRequestDTO)).thenReturn(request);
		Integer status=1;
		Mockito.when(trainingsDAO.addTrainingsRequest(request)).thenReturn(status);
		PowerMockito.when(TrainingRequestUtil.convertDaoTODto(request)).thenReturn(trainingRequestDTO);
		ServiceStatusDto actualStatus = new ServiceStatusDto();
		//Get TrainingDTO data
		String id = trainingRequestDTO.getEmailId();
		//when(trainingRequestDTO.getEmailId()).thenReturn(id);
		String title = trainingRequestDTO.getRequestTrainingTitle();
		//when(trainingRequestDTO.getRequestTrainingTitle()).thenReturn(title);
		String description = trainingRequestDTO.getDescription();
		//when(trainingRequestDTO.getDescription()).thenReturn(description);
		//Create UserDTO
		UserDTO dto=new UserDTO();
		dto.setActiveStatus("Yes");
		dto.setCreateDate(timestamp);
		dto.setEmailId(id);
		dto.setLoginDate(timestamp);
		dto.setNotifications("Yes");
		dto.setProfileName("user");
		dto.setUserName("Mahesh");
		map.put(id, dto);
		
		Mockito.when(userService.getUsers()).thenReturn(map);
		//Mockito.when(map.get(trainingRequestDTO.getEmailId())).thenReturn(dto);
		UserDTO dto2 = map.get(trainingRequestDTO.getEmailId());
		String userName = dto.getUserName();
		assertEquals(userName, dto2.getUserName());
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head></head><title></title>");
		sb.append("<body style='font-size:12px;font-family:Trebuchet MS;'>");
		sb.append("<i><b>Hi ,</b></i>");
		sb.append("<br><br>");
		sb.append("<i> I am Mahesh, Looking for training on "+"<b><font color=red>Java8 Features</font></b></i>");
		sb.append("<br><br>");
		sb.append("<i>Description :</i>");
		sb.append("<br><br>");
		sb.append("<i>"+description+"</i>");
		sb.append("<br><br>");
		sb.append("<i>Please do Needful and If There is a plan to arrange training on this, Java8 Features Please Let me Know</i>");
		sb.append("<br><br>");
		sb.append("<i>This is an auto generated e-mail. Please check this in Nisum portal. Thanking you.</i>");
		sb.append("<br><br>");
		sb.append("<br><br>");
		sb.append("<i>Regards,</i>");
		sb.append("<br><br>");
		sb.append("<font color=red><i>Nisum Portal Application.</i></font>");
		String requestBody = sb.toString();
		PowerMockito.mockStatic(MailSender.class);
		PowerMockito.when(MailSender.trainingReqestBody(title, userName, description)).thenReturn(requestBody);
		String userId = "portalnisum@gmail.com";
		when(emailAccount.getAdminemail()).thenReturn("portalnisum@gmail.com");
		String password="nisum@123";
		when(emailAccount.getAdminpassword()).thenReturn(password);
		when(emailAccount.getSubtrainingreq()).thenReturn("New Training Request Raised from Nisum Portal");
		MailSender.sendEmail(emailAccount.getAdminemail(), emailAccount.getAdminpassword(), id, id, emailAccount.getSubtrainingreq(), requestBody);
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Authenticator authenticator = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userId, password);
			}
		};
		Session session = Session.getInstance(props,authenticator);
		Store store = session.getStore("pop3");
		store.connect("gmail.com", emailAccount.getAdminemail().substring(0, emailAccount.getAdminemail().indexOf('@')), password);
		Folder folder = store.getFolder("inbox");
		folder.open(Folder.READ_ONLY);
		  Message[] msg = folder.getMessages();
		  
		  assertTrue(msg.length == 1);
		  assertEquals(emailAccount.getSubtrainingreq(), msg[0].getSubject());
		  assertEquals(requestBody, msg[0].getContent());
		  folder.close(true);
		  store.close();
		actualStatus= trainingsServiceImpl.addTrainingRequest(trainingRequestDTO, userService);
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
		ServiceStatusDto actualStatus = trainingsServiceImpl.addTrainingRequest(dto, userService);
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
