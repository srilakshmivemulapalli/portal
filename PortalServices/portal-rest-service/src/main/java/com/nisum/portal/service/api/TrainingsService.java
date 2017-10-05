package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.TrainingsDTO;

public interface TrainingsService {
	public List<TrainingsDTO> upComingTrainings();
	
	public List<TrainingsDTO> completedTrainings();
	
	public Trainings saveTrainings(TrainingsDTO trainingsDTO);

}
