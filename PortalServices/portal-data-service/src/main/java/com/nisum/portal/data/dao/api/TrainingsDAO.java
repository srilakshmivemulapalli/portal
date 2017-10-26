package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.TrainingToUser;
import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.domain.Trainings;

public interface TrainingsDAO {

	
	public List<Object[]> noOfStudents(Integer trainingId);
	

	public List<Trainings> upcomingTraining(String trainingType);

	public List<Trainings> completedTraining(String emailId);

	public Trainings saveTrainings(Trainings trainings);

	public TrainingToUser trainingToUser(TrainingToUser trainingToUser);

	TrainingFeedBack addTrainingsFeedBack(TrainingFeedBack trainingFeedBack);

	Integer addTrainingsRequest(TrainingRequest trainingRequest);

	List<TrainingRequest> getTrainingRequests();

	Integer checkTrainingPresence(String emailId, Integer trainingId);

	List<TrainingFeedBack> getTrainingFeedBacks();

	List<TrainingFeedBack> getTrainingFeedBacksByTrainingId(Integer trainingId);

}
