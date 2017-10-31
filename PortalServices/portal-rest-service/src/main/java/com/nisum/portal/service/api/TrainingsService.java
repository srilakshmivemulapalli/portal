package com.nisum.portal.service.api;

import java.util.List;


import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.service.dto.TrainingToUserDTO;
import com.nisum.portal.service.dto.TrainingsDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;

public interface TrainingsService {
	
	public List<TrainingsDTO> upComingTrainings(String trainingType,String emailId,UserService userService);

	public List<TrainingsDTO> completedTrainings(String emailId, UserService userService);

	public TrainingsDTO saveTrainings(TrainingsDTO trainingsDTO);
	
	public TrainingToUserDTO trainingToUser(TrainingToUserDTO trainingToUserDTO);

	TrainingFeedBackDTO addTrainingFeedBack(TrainingFeedBackDTO trainingFeedBackDTO);

	ServiceStatusDto addTrainingRequest(TrainingRequestDTO trainingRequestDTO) throws Exception;

	List<TrainingRequestDTO> getAllTrainingRequests();

	List<TrainingFeedBackDTO> getAllTrainingFeedBacks();

	public List<TrainingFeedBackDTO> getTrainingFeedBack(Integer trainingId);
	
	public List<TrainingsDTO> getMyTrainings(String emailId,UserService userService);

}
