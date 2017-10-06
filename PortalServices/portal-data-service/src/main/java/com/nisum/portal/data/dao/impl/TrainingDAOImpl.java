package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.TrainingsDAO;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.data.repository.TrainingRepository;
import com.nisum.portal.data.repository.TrainingRequestRepository;
import com.nisum.portal.data.repository.TrainingsFeedBackRepository;

@Configuration
public class TrainingDAOImpl implements TrainingsDAO {
	private static Logger logger = LoggerFactory.getLogger(TrainingDAOImpl.class);

	@Autowired
	TrainingRepository trainingRepository;

	@Autowired
	TrainingsFeedBackRepository trainingFeedBackRepository;

	@Autowired
	TrainingRequestRepository trainingRequestRepository;

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
	public Integer addTrainingsFeedBack(TrainingFeedBack trainingFeedBack) {
		// TODO Auto-generated method stub
		logger.info("TrainingDAOImpl :: addTrainingsFeedBack ::" + trainingFeedBack.toString());

		Integer status;

		TrainingFeedBack feedBack = trainingFeedBackRepository
				.findByTrainingFeedBackId(trainingFeedBack.getTrainingFeedBackId());

		if (feedBack == null) {
			trainingFeedBackRepository.save(trainingFeedBack);
			status = 1;
		} else {
			status = 0;

		}
		return status;
	}

	@Override
	public Integer addTrainingsRequest(TrainingRequest trainingRequest) {
		// TODO Auto-generated method stub
		logger.info("TrainingDAOImpl :: addTrainingsRequest ::" + trainingRequest.toString());

		Integer status;
		logger.info("TrainingDAOImpl :: addTrainingsRequest ::" + trainingRequest.getTrainingRequestId());
		TrainingRequest request = trainingRequestRepository
				.findByTrainingRequestId(trainingRequest.getTrainingRequestId());

		if (request == null) {
			trainingRequestRepository.save(trainingRequest);
			status = 1;
		} else {
			status = 0;

		}
		return status;
	}

}
