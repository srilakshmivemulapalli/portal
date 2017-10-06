package com.nisum.portal.service.api;

import java.util.List;


import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;
import com.nisum.portal.service.dto.TrainingsDTO;

public interface TrainingsService {
	public List<TrainingsDTO> upComingTrainings();

	public List<TrainingsDTO> completedTrainings();

	public Trainings saveTrainings(TrainingsDTO trainingsDTO);

	ServiceStatusDto addTrainingFeedBack(TrainingFeedBackDTO trainingFeedBackDTO);

	ServiceStatusDto addTrainingRequest(TrainingRequestDTO trainingRequestDTO);

	List<TrainingRequestDTO> getAllTrainingRequests();

}
