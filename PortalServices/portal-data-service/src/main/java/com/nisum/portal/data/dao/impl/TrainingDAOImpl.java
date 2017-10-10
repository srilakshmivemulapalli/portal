package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.TrainingsDAO;
import com.nisum.portal.data.domain.TrainingToUser;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.data.repository.TrainingRepository;
import com.nisum.portal.data.repository.TrainingToUserRepository;

@Configuration
public class TrainingDAOImpl implements TrainingsDAO {
	private static Logger logger = LoggerFactory.getLogger(TrainingDAOImpl.class);
	
	@Autowired
	TrainingRepository trainingRepository;
	
	@Autowired
	TrainingToUserRepository trainingToUserRepository;

	@Override
	public Trainings saveTrainings(Trainings trainings) {
		logger.info("TrainingDAOImpl::saveTrainings");
		return trainingRepository.save(trainings);
		
	}
	
	@Override
	public List<Trainings> upcomingTraining() {
		logger.info("TrainingDAOImpl::upcomingTraining");
		return trainingRepository.findAll();		 
	}

	@Override
	public List<Trainings> completedTraining() {
		logger.info("TrainingDAOImpl::completedTraining");
		return trainingRepository.findAll();	
	}

	@Override
	public TrainingToUser trainingToUser(TrainingToUser trainingToUser) {
		logger.info("TrainingDAOImpl::trainingToUser");
		TrainingToUser	trainingToUser1;
		if(trainingToUser.getTrainingToUserId()!=null)
			{
			trainingToUser1=	trainingToUserRepository.findOne(trainingToUser.getTrainingToUserId());
				if(trainingToUser1!=null)
				{
					trainingToUser1.setTrainingPresence(trainingToUser.getTrainingPresence());
					trainingToUserRepository.save(trainingToUser1);
				}
				else
				{
					TrainingToUser trainingToUser2=new TrainingToUser();
					trainingToUser2.setTrainingId(trainingToUser.getTrainingId());
					trainingToUser2.setTrainingPresence(trainingToUser.getTrainingPresence());
					trainingToUser2.setUserId(trainingToUser.getUserId());
					
					trainingToUserRepository.save(trainingToUser2);
					
				}
					
		  }
		     return trainingToUser;
	}
}


