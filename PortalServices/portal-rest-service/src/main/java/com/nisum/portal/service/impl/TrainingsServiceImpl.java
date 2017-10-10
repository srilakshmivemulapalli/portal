package com.nisum.portal.service.impl;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
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
import com.nisum.portal.service.dto.TrainingsDTO;
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
	public TrainingToUserDTO trainingToUser(TrainingToUserDTO trainingToUserDTO) {
		logger.info("TrainingsServiceImpl::trainingToUser");
		TrainingToUser trainingToUser=TrainingsServiceUtil.convertTrainingToUserDtoToDao(trainingToUserDTO);
		
		return TrainingsServiceUtil.convertTrainingToUserDaoToDto((trainingsDAO.trainingToUser(trainingToUser)));
	}

	
}
