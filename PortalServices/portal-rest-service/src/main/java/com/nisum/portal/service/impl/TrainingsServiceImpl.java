package com.nisum.portal.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
import com.nisum.portal.service.dto.TrainingsApproveDTO;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;
import com.nisum.portal.service.dto.TrainingsDTO;
import com.nisum.portal.service.dto.TrainingsDetails;
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
	public TrainingsDTO saveTrainings(TrainingsDTO trainingsDTO)
	{
		 logger.info("TrainingsServiceImpl::saveTrainings");
	     Trainings trainings=	TrainingsServiceUtil.convertDtoToDao(trainingsDTO);
	     return TrainingsServiceUtil.convertTrainingsDaoTODto(trainingsDAO.saveTrainings(trainings));
		
	}
	
	@Override
	public List<TrainingsDTO> upComingTrainings(String trainingType,String emailId,UserService userService) {
		 logger.info("TrainingsServiceImpl::upComingTrainings");
		 List<TrainingsDTO> upcomeList=TrainingsServiceUtil.convertDaoTODto(trainingsDAO.upcomingTraining( trainingType));
		 this.getTrainingList(emailId, upcomeList, userService);
		 return upcomeList;
	}


	@Override
	public List<TrainingsDTO> completedTrainings(String emailId,UserService userService) 
	{
		logger.info("TrainingsServiceImpl::completedTrainings");
		List<TrainingsDTO> completedList=TrainingsServiceUtil.convertDaoTODto(trainingsDAO.completedTraining(emailId));
		this.getTrainingList(emailId, completedList, userService);
		this.disableOptedTrainings(completedList);
		return completedList;
	}
	@Override
	public TrainingFeedBackDTO addTrainingFeedBack(TrainingFeedBackDTO trainingFeedBackDTO) {
		// TODO Auto-generated method stub
		logger.info("TrainingsServiceImpl :: addTrainingFeedBack ::"+trainingFeedBackDTO.toString());
		Timestamp createdDate = new Timestamp(System.currentTimeMillis());
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
		TrainingFeedBack trainingFeedBack = TrainingFeedBackUtil.convertDtoTODao(trainingFeedBackDTO);
		TrainingFeedBack feedBack = trainingsDAO.addTrainingsFeedBack(trainingFeedBack);
		TrainingFeedBackDTO feedBackDTO =  TrainingFeedBackUtil.convertDaoTODto(feedBack);
		return feedBackDTO;
	}
	
	@Override
	public ServiceStatusDto addTrainingRequest(TrainingRequestDTO trainingRequestDTO,UserService userService) throws Exception {
		// TODO Auto-generated method stub
		logger.info("TrainingsServiceImpl :: addTrainingRequest ::"+trainingRequestDTO.toString());
		
		Timestamp createdDate = new Timestamp(System.currentTimeMillis());

		trainingRequestDTO.setRequestedDate(createdDate);
		TrainingRequest trainingRequest = TrainingRequestUtil.convertDtoTODao(trainingRequestDTO);
		logger.info("TrainingsServiceImpl :: addTrainingFeedBack ::"+trainingRequest.toString());
		
		Integer serviceStatus = trainingsDAO.addTrainingsRequest(trainingRequest);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		 if (serviceStatus == 1) {
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(Constants.MSG_RECORD_ADD);
			UserDTO user=userService.getUsers().get(trainingRequestDTO.getEmailId());
			final String userName=user.getUserName();
			final String title=trainingRequestDTO.getRequestTrainingTitle();
			final String description=trainingRequestDTO.getDescription();
			String reqestBody = MailSender.trainingReqestBody(title, userName, description);	
			MailSender.sendEmail(emailAccount.getAdminemail(), emailAccount.getAdminpassword(),
					emailAccount.getAdminemailId(), trainingRequestDTO.getEmailId(), emailAccount.getSubtrainingreq(),reqestBody);
		}
		else if(serviceStatus == 0){
			serviceStatusDto.setStatus(false);
			serviceStatusDto.setMessage(Constants.TRAINING_REQUEST_EXISTS);
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
	public TrainingToUserDTO trainingToUser(TrainingToUserDTO trainingToUserDTO) 
	{
		logger.info("TrainingsServiceImpl::trainingToUser");
	    TrainingToUser trainingToUser=TrainingsServiceUtil.convertTrainingToUserDtoToDao(trainingToUserDTO);
	    return TrainingsServiceUtil.convertTrainingToUserDaoToDto((trainingsDAO.trainingToUser(trainingToUser)));
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
	public void getTrainingList(String emailId,List<TrainingsDTO> trainingsList,UserService userService)
	{
		 long durationInSeconds;
		 Integer presence;
		 List<Object[]> noOfStudents;
		for(TrainingsDTO trainingsDTO:trainingsList)
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
			
			UserDTO user=userService.getUsers().get(trainingsDTO.getTrainerEmailId());
			if(user!=null && StringUtils.isNotEmpty(user.getImage())&&user.getUserName()!=null) {
				trainingsDTO.setDisplayImage(user.getImage());
				trainingsDTO.setTrainerName(user.getUserName());
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
			List<TrainingFeedBackDTO> comments= this.getTrainingFeedBack(trainingsDTO.getTrainingId());
			LinkedHashMap<String,String> nameComment;
			ArrayList<LinkedHashMap<String,String>> commentsList =new ArrayList<LinkedHashMap<String,String>>();
			if(comments!=null&&comments.size()>0)
			{
				trainingsDTO.setNoOfComments(comments.size());
				for(TrainingFeedBackDTO trainingFeedBackDTO:comments)
				{
					
					nameComment=new LinkedHashMap<String,String>();
					 if(trainingsDTO.getTrainingStartDate().compareTo(new Date())>0)
					 {
						 trainingsDTO.setCommentStatus(2);
						 
					 }
					 else
					 {
						 if(trainingFeedBackDTO.getEmailId().contains(emailId))
						{
			
						         trainingsDTO.setCommentStatus(0);
						}else
						{
							     trainingsDTO.setCommentStatus(1);
						}
					 }
					if(emailId.compareTo(trainingsDTO.getTrainerEmailId())==0)
					{
						UserDTO commentedUser=userService.getUsers().get(trainingFeedBackDTO.getEmailId());
						if(commentedUser!=null &&commentedUser.getUserName()!=null) 
						{
							nameComment.put("name",commentedUser.getUserName());
							nameComment.put("description", trainingFeedBackDTO.getFeedback());
						}
						commentsList.add(nameComment);
					}
				}
				
				   trainingsDTO.setCommentDescriptions(commentsList);
				
			}else
			{
				 if(trainingsDTO.getTrainingStartDate().compareTo(new Date())>0)
					 trainingsDTO.setCommentStatus(2);
				 else
				     trainingsDTO.setCommentStatus(1);
				 trainingsDTO.setNoOfComments(0);
			}
			
			
		}
	}

	@Override
	public List<TrainingsDTO> getMyTrainings(String trainerEmailId,UserService userService) {
		logger.info("TrainingsServiceImpl :: getMyTrainings");
		 List<TrainingsDTO> myTrainingList=TrainingsServiceUtil.convertDaoTODto(trainingsDAO.getMyTrainings(trainerEmailId))	;	
		 this.getTrainingList(trainerEmailId, myTrainingList, userService); 
		 this.disableOptedTrainings(myTrainingList);
		return myTrainingList;
	}
	
	public void disableOptedTrainings(List<TrainingsDTO> trainingsList)
	{
		for(TrainingsDTO trainingsDTO:trainingsList)
		{
			if(trainingsDTO.getTrainingStartDate().compareTo(new Date())<0)
			trainingsDTO.setTrainingPresence(2); // disable Join option in MyTrainings Part
		}
		
	}

	@Override
	public Integer updateTrainingRequests(TrainingRequestDTO dto, String action) {
		// TODO Auto-generated method stub
		Integer status = 0;
		TrainingRequest request = TrainingRequestUtil.convertDtoTODao(dto);
		if(StringUtils.isNotEmpty(action)&&action.equalsIgnoreCase("approve"))
		{
			status=trainingsDAO.updateTrainingRequest(request, 1);
		}
		else if(StringUtils.isNotEmpty(action)&&action.equalsIgnoreCase("reject"))
		{
			status=trainingsDAO.updateTrainingRequest(request,2);
		}
		return status;
	}

	@Override
	public TrainingsApproveDTO getAllTrainings() {
		 logger.info("TrainingsServiceImpl::getAllTrainings");
		 List<TrainingsDTO> tainingsList=TrainingsServiceUtil.convertDaoTODto(trainingsDAO.getAllTrainings());
		// TrainingsDetails trainingDetails=new TrainingsDetails();
		 TrainingsApproveDTO trainingsApproveDTO=new TrainingsApproveDTO();
		/* int classCount=0;
		 int onlineCount=0;
		 int classPending=0;
		 int onlinePending=0;
		 int classApproval=0;
		 int onlineApproval=0;
		 int classRejected=0;
		 int onlineRejected=0;
		 for (TrainingsDTO trainingsDTO : tainingsList) {
			 if(trainingsDTO.getTrainingType()!=null && trainingsDTO.getTrainingType().equals("classroom"))
			 {
				 classCount++;
				 if(trainingsDTO.getTrainingStatus()!=null )
				 {
					 if(trainingsDTO.getTrainingStatus()==1)
						 classPending++;
					 if(trainingsDTO.getTrainingStatus()==2)
						 classApproval++;
					 if(trainingsDTO.getTrainingStatus()==0)
						 classRejected++;
				 }
			 }
			 if(trainingsDTO.getTrainingType()!=null && trainingsDTO.getTrainingType().equals("online"))
			 {
				 onlineCount++;
				 if(trainingsDTO.getTrainingStatus()!=null )
				 {
					 if(trainingsDTO.getTrainingStatus()==1)
						 onlinePending++;
					 if(trainingsDTO.getTrainingStatus()==2)
						 onlineApproval++;
					 if(trainingsDTO.getTrainingStatus()==0)
						 onlineRejected++;
				 }
			 }
			 
		}
		 trainingDetails.setNoOfClassTrainings(classCount);
		 trainingDetails.setNoOfClassPendings(classPending);
		 trainingDetails.setNoOfClassApprovals(classApproval);
		 trainingDetails.setNoOfClassRejected(classRejected);
		 
		 trainingDetails.setNoOfOnlineTrainings(onlineCount);
		 trainingDetails.setNoOfOnlinePendings(onlinePending);
		 trainingDetails.setNoOfOnlineApprovals(onlineApproval);
		 trainingDetails.setNoOfOnlineRejected(onlineRejected);*/
		 
		 TrainingsDetails trainingDetails=	this.getTrainingDetails(tainingsList);
		 trainingsApproveDTO.setTrainings(tainingsList);
		 trainingsApproveDTO.setTrainingsDetails(trainingDetails);
		 
		return trainingsApproveDTO;
	}

	@Override
	public TrainingsDTO updateTrainingStatus(TrainingsDTO trainingsDTO) {
		logger.info("TrainingsServiceImpl :: updateTrainingStatus");
		 Trainings trainings=TrainingsServiceUtil.convertDtoToDao(trainingsDTO);
		 List<TrainingsDTO> tainingsList=TrainingsServiceUtil.convertDaoTODto(trainingsDAO.getAllTrainings());
		 //int classApproval=0;
		// int onlineApproval=0;
		 /*for (TrainingsDTO trainingDTO : tainingsList) {
			 if(trainingDTO.getTrainingType()!=null && trainingDTO.getTrainingType().equals("classroom"))
			 {
				 if(trainingDTO.getTrainingStatus()!=null&&trainingDTO.getTrainingStatus()==2)
					 classApproval++;
			 }
			 if(trainingDTO.getTrainingType()!=null && trainingDTO.getTrainingType().equals("online"))
			 {
				 if(trainingDTO.getTrainingStatus()!=null&&trainingDTO.getTrainingStatus()==2)
					 onlineApproval++;
				 
			 }
		 }*/
           
		 TrainingsDTO training=TrainingsServiceUtil.convertTrainingsDaoTODto(trainingsDAO.updateTrainingStatus(trainings));
		 TrainingsDetails trainingsDetail=new TrainingsDetails();
		 TrainingsDetails trainingsDetails= this.getTrainingDetails(tainingsList);
		 if(training.getTrainingType()!=null && training.getTrainingType()!=null)
		 {
			  if(training.getTrainingType().equals("classroom") )
			  {
				  trainingsDetail.setNoOfClassPendings(trainingsDetails.getNoOfClassPendings()-1);
                     if(training.getTrainingStatus()==2)
                     {
				        trainingsDetail.setNoOfClassApprovals(trainingsDetails.getNoOfClassApprovals()+1);
				        trainingsDetail.setNoOfClassRejected(trainingsDetails.getNoOfClassRejected());
                     }
                     if(training.getTrainingStatus()==0)
                     {
                     	 trainingsDetail.setNoOfClassRejected(trainingsDetails.getNoOfClassRejected()+1);
                     	trainingsDetail.setNoOfClassApprovals(trainingsDetails.getNoOfClassApprovals());
                     }
			  }
		      else
		      {
		    	      trainingsDetail.setNoOfClassPendings(trainingsDetails.getNoOfClassPendings());
		    	      trainingsDetail.setNoOfClassApprovals(trainingsDetails.getNoOfClassApprovals());
		    	      trainingsDetail.setNoOfClassRejected(trainingsDetails.getNoOfClassRejected());
		      }
		     if(training.getTrainingType().equals("online") )
		     {
		          trainingsDetail.setNoOfOnlinePendings(trainingsDetails.getNoOfOnlinePendings()-1);
		         	 if( training.getTrainingStatus()==2)
		         	 {
				     	  trainingsDetail.setNoOfOnlineApprovals(trainingsDetails.getNoOfOnlineApprovals()+1);
				     	 trainingsDetail.setNoOfOnlineRejected(trainingsDetails.getNoOfOnlineRejected());
		         	 }
		         	 if( training.getTrainingStatus()==0)
		         	 {
		         		trainingsDetail.setNoOfOnlineRejected(trainingsDetails.getNoOfOnlineRejected()+1);
		         		trainingsDetail.setNoOfOnlineApprovals(trainingsDetails.getNoOfOnlineApprovals());
		         	 }
		     	  
		     }
		     else
		     {    trainingsDetail.setNoOfOnlinePendings(trainingsDetails.getNoOfOnlinePendings());
		    	      trainingsDetail.setNoOfOnlineApprovals(trainingsDetails.getNoOfOnlineApprovals());
		    	      trainingsDetail.setNoOfOnlineRejected(trainingsDetails.getNoOfOnlineRejected());
		     }
		 }
			    	  trainingsDetail.setNoOfClassTrainings(trainingsDetails.getNoOfClassTrainings());
				  trainingsDetail.setNoOfOnlineTrainings(trainingsDetails.getNoOfOnlineTrainings());
				  training.setTrainingsDetails(trainingsDetail);

		 return training;
	}
	
	public TrainingsDetails getTrainingDetails(List<TrainingsDTO> tainingsList)
	{
		TrainingsDetails trainingDetails=new TrainingsDetails();
		 int classCount=0;
		 int onlineCount=0;
		 int classPending=0;
		 int onlinePending=0;
		 int classApproval=0;
		 int onlineApproval=0;
		 int classRejected=0;
		 int onlineRejected=0;
		for (TrainingsDTO trainingsDTO : tainingsList) {
			 if(trainingsDTO.getTrainingType()!=null && trainingsDTO.getTrainingType().equals("classroom"))
			 {
				 classCount++;
				 if(trainingsDTO.getTrainingStatus()!=null )
				 {
					 if(trainingsDTO.getTrainingStatus()==1)
						 classPending++;
					 if(trainingsDTO.getTrainingStatus()==2)
						 classApproval++;
					 if(trainingsDTO.getTrainingStatus()==0)
						 classRejected++;
				 }
			 }
			 if(trainingsDTO.getTrainingType()!=null && trainingsDTO.getTrainingType().equals("online"))
			 {
				 onlineCount++;
				 if(trainingsDTO.getTrainingStatus()!=null )
				 {
					 if(trainingsDTO.getTrainingStatus()==1)
						 onlinePending++;
					 if(trainingsDTO.getTrainingStatus()==2)
						 onlineApproval++;
					 if(trainingsDTO.getTrainingStatus()==0)
						 onlineRejected++;
				 }
			 }
			 
		}
		 trainingDetails.setNoOfClassTrainings(classCount);
		 trainingDetails.setNoOfClassPendings(classPending);
		 trainingDetails.setNoOfClassApprovals(classApproval);
		 trainingDetails.setNoOfClassRejected(classRejected);
		 
		 trainingDetails.setNoOfOnlineTrainings(onlineCount);
		 trainingDetails.setNoOfOnlinePendings(onlinePending);
		 trainingDetails.setNoOfOnlineApprovals(onlineApproval);
		 trainingDetails.setNoOfOnlineRejected(onlineRejected);
		return trainingDetails;
		
	}

}
