package com.nisum.portal.service.impl;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nisum.portal.data.dao.api.TrainingsDAO;
import com.nisum.portal.data.domain.TrainingToUser;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.service.api.TrainingsService;
import com.nisum.portal.service.dto.TrainingToUserDTO;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;
import com.nisum.portal.service.dto.TrainingsDTO;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.TrainingFeedBackUtil;
import com.nisum.portal.util.TrainingRequestUtil;
import com.nisum.portal.util.TrainingsServiceUtil;

@Service
public class TrainingsServiceImpl implements TrainingsService {
	private static Logger logger = LoggerFactory.getLogger(TrainingsServiceImpl.class);

	@Autowired
	private TrainingsDAO trainingsDAO;
	
	@Override
	public Trainings saveTrainings(TrainingsDTO trainingsDTO) {
		
		logger.info("TrainingsServiceImpl::saveTrainings");
	   Trainings trainings=	TrainingsServiceUtil.convertDtoToDao(trainingsDTO);
				return trainingsDAO.saveTrainings(trainings);
		
	}

	
	@Override
	public List<TrainingsDTO> upComingTrainings(String trainingType) {
		
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
	}


	@Override
	public List<TrainingsDTO> completedTrainings() {
		logger.info("TrainingsServiceImpl::completedTrainings");
		List<Trainings> upcomeList=	trainingsDAO.upcomingTraining();
	    LinkedHashSet<Trainings> lst=new LinkedHashSet<Trainings>(trainingsDAO.upcomingTraining());
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		for (Trainings trainings : lst) {
			if(trainings.getTrainingDate().after(createDate))
				upcomeList.remove(trainings);
			else
				trainings.setTrainingStatus("completed");
		}
		
			return TrainingsServiceUtil.convertDaoTODto(upcomeList);
	}
	@Override
	public ServiceStatusDto addTrainingFeedBack(TrainingFeedBackDTO trainingFeedBackDTO) {
		// TODO Auto-generated method stub
		logger.info("TrainingsServiceImpl :: addTrainingFeedBack ::"+trainingFeedBackDTO.toString());
		Date date = new Date();
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		Timestamp createdDate = new Timestamp(date.getTime());

		trainingFeedBackDTO.setCreateDate(createdDate);
		TrainingFeedBack trainingFeedBack = TrainingFeedBackUtil.convertDtoTODao(trainingFeedBackDTO);
		logger.info("TrainingsServiceImpl :: addTrainingFeedBack ::"+trainingFeedBack.toString());
		Integer serviceStatus = trainingsDAO.addTrainingsFeedBack(trainingFeedBack);

		if (serviceStatus == 0) {
			serviceStatusDto.setStatus(false);
			serviceStatusDto.setMessage(Constants.TRAINING_FEEDBACK_EXISTS);
		} else if (serviceStatus == 1) {
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(Constants.MSG_RECORD_ADD);
		}

		return serviceStatusDto;
	}
	
	@Override
	public ServiceStatusDto addTrainingRequest(TrainingRequestDTO trainingRequestDTO) {
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
			serviceStatusDto.setMessage(Constants.TRAINING_FEEDBACK_EXISTS);
		} else if (serviceStatus == 1) {
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
	
}