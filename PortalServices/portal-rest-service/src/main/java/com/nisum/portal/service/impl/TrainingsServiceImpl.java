package com.nisum.portal.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nisum.portal.data.dao.api.TrainingsDAO;
import com.nisum.portal.data.domain.TrainingToUser;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.service.api.EmailAccount;
import com.nisum.portal.service.api.TrainingsService;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.TrainingToUserDTO;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;
import com.nisum.portal.service.dto.TrainingsDTO;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.MailSender;
import com.nisum.portal.util.TrainingFeedBackUtil;
import com.nisum.portal.util.TrainingRequestUtil;
import com.nisum.portal.util.TrainingsServiceUtil;
import org.apache.commons.lang3.StringUtils;

@Service
public class TrainingsServiceImpl implements TrainingsService {
	private static Logger logger = LoggerFactory.getLogger(TrainingsServiceImpl.class);

	@Autowired
	private TrainingsDAO trainingsDAO;
	
	private static EmailAccount emailAccount;
	
	@Override
	public TrainingsDTO saveTrainings(TrainingsDTO trainingsDTO) {
		
		logger.info("TrainingsServiceImpl::saveTrainings");
	   Trainings trainings=	TrainingsServiceUtil.convertDtoToDao(trainingsDTO);
				return TrainingsServiceUtil.convertTrainingsDaoTODto(trainingsDAO.saveTrainings(trainings));
		
	}

	
	/*@Override
	public List<TrainingsDTO> upComingTrainings(String trainingType,String email) {
		
		logger.info("TrainingsServiceImpl::upComingTrainings");
		
	List<Trainings> upcomeList=	trainingsDAO.upcomingTraining();
    LinkedHashSet<Trainings> lst=new LinkedHashSet<Trainings>(trainingsDAO.upcomingTraining());
	Timestamp createDate = new Timestamp(System.currentTimeMillis());
	if(trainingType.equals("classroom"))
	{
	   for (Trainings trainings : lst) {
		if(trainings.getTrainingType().equals("classroom"))
		{
		    if(trainings.getTrainingDate().before(createDate))
			upcomeList.remove(trainings);
		}
		else 
			upcomeList.remove(trainings);
	 }
	}else
	{
		for (Trainings trainings : lst) {
			if(trainings.getTrainingType().equals("online"))
			{
			    if(trainings.getTrainingDate().before(createDate))
				upcomeList.remove(trainings);
			}
			else 
				upcomeList.remove(trainings);
		 }
		
	}
	Collections.sort(upcomeList, new Comparator<Trainings>() 
	{

		@Override
		public int compare(Trainings t1, Trainings t2) {
			
			return t1.getTrainingDate().compareTo(t2.getTrainingDate());
		}
		
	});
	
		return TrainingsServiceUtil.convertDaoTODto(upcomeList);
	} */
	
	@Override
	public List<TrainingsDTO> upComingTrainings(String trainingType,String emailId,UserService userService) {
		logger.info("TrainingsServiceImpl::upComingTrainings");
	//	List<Trainings> upcomeList=trainingsDAO.upcomingTraining( trainingType);
		
	//	List<TrainingsDTO> TrainingsDTOs=TrainingsServiceUtil.convertDaoTODto(upcomeList);
		List<TrainingsDTO> upcomeList=TrainingsServiceUtil.convertDaoTODto(trainingsDAO.upcomingTraining( trainingType));
		 Integer presence;
		 List<Object[]> noOfStudents;
		 long durationInSeconds;
		for(TrainingsDTO trainingsDTO:upcomeList)
		{
			if(emailId.compareTo(trainingsDTO.getTrainerEmailId())==0)
			{
				trainingsDTO.setTrainingPresence(2);
			}
			else
			{
			     presence= trainingsDAO.checkTrainingPresence(emailId, trainingsDTO.getTrainingId());
			    if(presence!=null)
				   trainingsDTO.setTrainingPresence(presence);
			    else
				   trainingsDTO.setTrainingPresence(0);
			}
		   
		    noOfStudents=trainingsDAO.noOfStudents(trainingsDTO.getTrainingId());
		    if(noOfStudents.size()>0 && noOfStudents!=null )
		    {
			  // if(noOfStudents!=null )
				   trainingsDTO.setNoOfStudents(noOfStudents.size());
			   
			  // else
				  
			   for(Object[] trainingTouser:noOfStudents)
			   {
				   
				   if(trainingTouser[0]!=null &&(trainingTouser[0].toString()).compareTo(emailId)==0)
					   trainingsDTO.setTrainingToUserId(Integer.valueOf(trainingTouser[1].toString()));
			  }
		    }else
		    {
		    	    trainingsDTO.setNoOfStudents(0);
		    }
			if(trainingsDTO.getTrainingStartTime()!=null && trainingsDTO.getTrainingEndTime()!=null)
			{
				long diff=  trainingsDTO.getTrainingEndTime().getTime()-trainingsDTO.getTrainingStartTime().getTime();
				long diffHours = diff / (60 * 60 * 1000) % 24;
				//long diffDays = diff / (24 * 60 * 60 * 1000);
				long diffMinutes = diff / (60 * 1000) % 60;
		        long diffSeconds = diff / 1000 % 60;
		        durationInSeconds=(diffHours*60*60)+(diffMinutes*60)+(diffSeconds);
				trainingsDTO.setDuration(durationInSeconds);
			  }
			UserDTO user=userService.getUsers().get(trainingsDTO.getTrainerEmailId());
			if(user!=null && StringUtils.isNotEmpty(user.getImage())&&user.getUserName()!=null) {
				trainingsDTO.setDisplayImage(user.getImage());
				trainingsDTO.setTrainerName(user.getUserName());
			}
			/*List<TrainingFeedBackDTO> comments= this.getTrainingFeedBack(trainingsDTO.getTrainingId());
			if(comments!=null&&comments.size()>0)
			{
				trainingsDTO.setNoOfComments(comments.size());
				
			}*/
			trainingsDTO.setNoOfComments(0);
			   
		}

		return upcomeList;
	}


	@Override
	public List<TrainingsDTO> completedTrainings() {
		logger.info("TrainingsServiceImpl::completedTrainings");
		/*List<Trainings> upcomeList=	trainingsDAO.completedTraining();
	    LinkedHashSet<Trainings> lst=new LinkedHashSet<Trainings>(trainingsDAO.completedTraining());
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		for (Trainings trainings : lst) {
			if(trainings.getTrainingStartDate().after(createDate))
				upcomeList.remove(trainings);
			else
				trainings.setTrainingStatus("completed");
		}*/
		
			return TrainingsServiceUtil.convertDaoTODto(trainingsDAO.completedTraining());
	}
	@Override
	public TrainingFeedBackDTO addTrainingFeedBack(TrainingFeedBackDTO trainingFeedBackDTO) {
		// TODO Auto-generated method stub
		logger.info("TrainingsServiceImpl :: addTrainingFeedBack ::"+trainingFeedBackDTO.toString());
		Date date = new Date();
		Timestamp createdDate = new Timestamp(date.getTime());
		String feedback2 = trainingFeedBackDTO.getFeedback();
		Integer status;
		if(StringUtils.isNotEmpty(feedback2))
		{
			status=1;
		}
		else
		{
			status=0;
		}
		trainingFeedBackDTO.setCreateDate(createdDate);
		trainingFeedBackDTO.setFeedbackStatus(status);
		TrainingFeedBack trainingFeedBack = TrainingFeedBackUtil.convertDtoTODao(trainingFeedBackDTO);
		TrainingFeedBack feedBack = trainingsDAO.addTrainingsFeedBack(trainingFeedBack);
		return TrainingFeedBackUtil.convertDaoTODto(feedBack);
	}
	
	@Override
	public ServiceStatusDto addTrainingRequest(TrainingRequestDTO trainingRequestDTO) throws Exception {
		// TODO Auto-generated method stub
		logger.info("TrainingsServiceImpl :: addTrainingRequest ::"+trainingRequestDTO.toString());
		Date date = new Date();
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		Timestamp createdDate = new Timestamp(date.getTime());

		trainingRequestDTO.setRequestedDate(createdDate);
		TrainingRequest trainingRequest = TrainingRequestUtil.convertDtoTODao(trainingRequestDTO);
		logger.info("TrainingsServiceImpl :: addTrainingFeedBack ::"+trainingRequest.toString());
		
		Integer serviceStatus = trainingsDAO.addTrainingsRequest(trainingRequest);

		if (serviceStatus == 0) {
			serviceStatusDto.setStatus(false);
			serviceStatusDto.setMessage(Constants.TRAINING_REQUEST_EXISTS);
		} if (serviceStatus == 1) {
			String id = trainingRequestDTO.getEmailId();
			//Hard coded need to add admin login address
			String userName="mbheemanapalli@nisum.com";
			final String topic=trainingRequestDTO.getRequestTrainingTitle();
			final String description=trainingRequestDTO.getDescription();
			MailSender.sendEmail(emailAccount.getAdminemail(), emailAccount.getAdminpassword() ,
					userName, null, emailAccount.getSubtrainingreq(), MailSender.trainingReqestBody(topic,id,description));
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(Constants.MSG_RECORD_ADD);
		}

		return serviceStatusDto;
	}


	@Override
	public List<TrainingRequestDTO> getAllTrainingRequests() {
		// TODO Auto-generated method stub
		List<TrainingRequest> trainingRequests = trainingsDAO.getTrainingRequests();
		return TrainingRequestUtil.convertDaoListToDto(trainingRequests);
	}

	@Override
	public TrainingToUserDTO trainingToUser(TrainingToUserDTO trainingToUserDTO) {
		logger.info("TrainingsServiceImpl::trainingToUser");
		TrainingToUser trainingToUser=TrainingsServiceUtil.convertTrainingToUserDtoToDao(trainingToUserDTO);
		
		return TrainingsServiceUtil.convertTrainingToUserDaoToDto((trainingsDAO.trainingToUser(trainingToUser)));
	}


	@Override
	public List<TrainingFeedBackDTO> getAllTrainingFeedBacks() {
		// TODO Auto-generated method stub
		List<TrainingFeedBack> feedBacks = trainingsDAO.getTrainingFeedBacks();
		return TrainingFeedBackUtil.convertDaoListToDto(feedBacks);
	}


	@Override
	public List<TrainingFeedBackDTO> getTrainingFeedBack(Integer trainingId) {
		// TODO Auto-generated method stub
		List<TrainingFeedBack> list = trainingsDAO.getTrainingFeedBacksByTrainingId(trainingId);
		return TrainingFeedBackUtil.convertDaoListToDto(list);
	}

	@Autowired
	public void setEmailAccount(EmailAccount emailAccount) {
		TrainingsServiceImpl.emailAccount = emailAccount;
	}
}
