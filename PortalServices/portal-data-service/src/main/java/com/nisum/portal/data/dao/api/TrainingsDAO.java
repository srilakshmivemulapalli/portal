package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.data.domain.Trainings;

public interface TrainingsDAO {

	public List<Trainings> upcomingTraining();

	public List<Trainings> completedTraining();

	public Trainings saveTrainings(Trainings trainings);

	Integer addTrainingsFeedBack(TrainingFeedBack trainingFeedBack);

	Integer addTrainingsRequest(TrainingRequest trainingRequest);
}
