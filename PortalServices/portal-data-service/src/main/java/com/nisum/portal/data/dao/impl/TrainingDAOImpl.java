package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.TrainingsDAO;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.data.repository.TrainingRepository;

@Configuration
public class TrainingDAOImpl implements TrainingsDAO {
	private static Logger logger = LoggerFactory.getLogger(TrainingDAOImpl.class);
	
	@Autowired
	TrainingRepository trainingRepository;

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

	

}
