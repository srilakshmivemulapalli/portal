package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.service.dto.TrainingsDTO;

public class TrainingsServiceUtil {
	public static List<TrainingsDTO> convertDaoTODto(List<Trainings> TrainingsList) {
		List<TrainingsDTO> trainingsDTOs = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(TrainingsList)) {
			for (Trainings trainings : TrainingsList) {
				TrainingsDTO trainingsDTO = new TrainingsDTO();
				trainingsDTO.setTrainingId(trainings.getTrainingId());
				trainingsDTO.setTrainingTitle(trainings.getTrainingTitle());
				trainingsDTO.setTrainerName(trainings.getTrainerName());
				trainingsDTO.setTrainingDate(trainings.getTrainingDate());
				trainingsDTO.setTrainingStatus(trainings.getTrainingStatus());
				trainingsDTO.setTrainingType(trainings.getTrainingType());
				trainingsDTO.setDescription(trainings.getDescription());
				
				trainingsDTOs.add(trainingsDTO);
			}
		}
		return trainingsDTOs;

	}
	
	public static Trainings convertDtoToDao(TrainingsDTO trainingsDTO)
	{
		Trainings trainings=new Trainings();
		trainings.setTrainingId(trainingsDTO.getTrainingId());
		trainings.setTrainingTitle(trainingsDTO.getTrainingTitle());
		trainings.setTrainerName(trainingsDTO.getTrainerName());
		trainings.setTrainingDate(trainingsDTO.getTrainingDate());
		trainings.setTrainingStatus(trainingsDTO.getTrainingStatus());
		trainings.setTrainingType(trainingsDTO.getTrainingType());
		trainings.setDescription(trainingsDTO.getDescription());
		
		return trainings;
		
	}


}
