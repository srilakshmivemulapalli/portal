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
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.repository.TrainingRequestRepository;
import com.nisum.portal.data.repository.TrainingsFeedBackRepository;

@Configuration
public class TrainingDAOImpl implements TrainingsDAO {
	private static Logger logger = LoggerFactory.getLogger(TrainingDAOImpl.class);

	@Autowired
	TrainingRepository trainingRepository;
	
	@Autowired
	TrainingToUserRepository trainingToUserRepository;

	@Autowired
	TrainingsFeedBackRepository trainingFeedBackRepository;

	@Autowired
	TrainingRequestRepository trainingRequestRepository;

	@Override
	public Trainings saveTrainings(Trainings trainings) {
		logger.info("TrainingDAOImpl::saveTrainings");
		return trainingRepository.save(trainings);

	}

	/*@Override
	public List<Trainings> upcomingTraining() {
		logger.info("TrainingDAOImpl::upcomingTraining");
		return trainingRepository.findAll();
	} */
	
	@Override
	public List<Trainings> upcomingTraining(String trainingType) {
		logger.info("TrainingDAOImpl::upcomingTraining");
		return trainingRepository.fetchMyTrainings(trainingType);
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
	public TrainingToUser trainingToUser(TrainingToUser trainingToUser) {
		logger.info("TrainingDAOImpl::trainingToUser");
		TrainingToUser	trainingToUser1;
		   if(trainingToUser.getTrainingToUserId()==null)
			{
				TrainingToUser trainingToUser2=new TrainingToUser();
				trainingToUser2.setTrainingId(trainingToUser.getTrainingId());
				trainingToUser2.setTrainingPresence(trainingToUser.getTrainingPresence());
				trainingToUser2.setUserId(trainingToUser.getUserId());
				trainingToUser2.setEmailId(trainingToUser.getEmailId());
			
				trainingToUserRepository.save(trainingToUser2);
			
			}else
			{
				
				trainingToUser1=	trainingToUserRepository.findOne(trainingToUser.getTrainingToUserId());
				if(trainingToUser1!=null)
				{
					trainingToUser1.setTrainingPresence(trainingToUser.getTrainingPresence());
					trainingToUserRepository.save(trainingToUser1);
				}
					
		  }
		     return trainingToUser;
	}
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
		} else{
			status = 0;

		}
		return status;
	}

	@Override
	public List<TrainingRequest> getTrainingRequests() {
		// TODO Auto-generated method stub
		logger.info("TrainingDAOImpl::getTrainingRequests");
		return trainingRequestRepository.findAll();
	}

	@Override
	public Integer checkTrainingPresence(String emailId, Integer trainingId) {
		return trainingToUserRepository.fetchTrainingPresence(emailId, trainingId);
	}

	@Override
	public List<Object[]> noOfStudents(Integer trainingId) {
		
		return trainingToUserRepository.fetchnoOfStudent(trainingId);
	}

	@Override
	public List<TrainingFeedBack> getTrainingFeedBacks() {
		// TODO Auto-generated method stub
		return trainingFeedBackRepository.findAll();
	}

	@Override
	public TrainingFeedBack getTrainingFeedBackByTrainingId(Integer trainingId) {
		// TODO Auto-generated method stub
		return trainingFeedBackRepository.findOne(trainingId);
	}

}


