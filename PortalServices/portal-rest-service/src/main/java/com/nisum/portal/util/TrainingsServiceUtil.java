package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.TrainingToUser;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.service.dto.TrainingToUserDTO;
import com.nisum.portal.service.dto.TrainingsDTO;

public class TrainingsServiceUtil {
	public static List<TrainingsDTO> convertDaoTODto(List<Trainings> TrainingsList) {
		List<TrainingsDTO> trainingsDTOs = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(TrainingsList)) {
			for (Trainings trainings : TrainingsList) {
				TrainingsDTO trainingsDTO = new TrainingsDTO();
				trainingsDTO.setTrainingId(trainings.getTrainingId());
				trainingsDTO.setTrainingTitle(trainings.getTrainingTitle());
				trainingsDTO.setTrainerEmailId(trainings.getTrainerEmailId());
				trainingsDTO.setTrainingStartTime(trainings.getTrainingStartTime());
				trainingsDTO.setTrainingEndTime(trainings.getTrainingEndTime());
				trainingsDTO.setTrainingType(trainings.getTrainingType());
				trainingsDTO.setDescription(trainings.getDescription());
				trainingsDTO.setTrainingStartDate(trainings.getTrainingStartDate());
				trainingsDTO.setTrainingEndDate(trainings.getTrainingEndDate());
				trainingsDTO.setTrainingStatus(trainings.getTrainingStatus());
				
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
		trainings.setTrainerEmailId(trainingsDTO.getTrainerEmailId());
		trainings.setTrainingStartTime(trainingsDTO.getTrainingStartTime());
		trainings.setTrainingEndTime(trainingsDTO.getTrainingEndTime());
		trainings.setTrainingType(trainingsDTO.getTrainingType());
		trainings.setDescription(trainingsDTO.getDescription());
		trainings.setTrainingStartDate(trainingsDTO.getTrainingStartDate());
		trainings.setTrainingEndDate(trainingsDTO.getTrainingEndDate());
		trainings.setTrainingStatus(trainingsDTO.getTrainingStatus());
		return trainings;
		
	}
	
	public static TrainingToUser convertTrainingToUserDtoToDao(TrainingToUserDTO trainingToUserDTO)
	{
		TrainingToUser trainingToUser=new TrainingToUser();
		trainingToUser.setTrainingToUserId(trainingToUserDTO.getTrainingToUserId());
		trainingToUser.setTrainingId(trainingToUserDTO.getTrainingId());
		trainingToUser.setTrainingPresence(trainingToUserDTO.getTrainingPresence());
		trainingToUser.setEmailId(trainingToUserDTO.getEmailId());
		
		return trainingToUser;
	}
	public static TrainingToUserDTO convertTrainingToUserDaoToDto(TrainingToUser trainingToUser)
	{
		TrainingToUserDTO trainingToUserDTO=new TrainingToUserDTO();
		trainingToUserDTO.setTrainingToUserId(trainingToUser.getTrainingToUserId());
		trainingToUserDTO.setTrainingId(trainingToUser.getTrainingId());
		trainingToUserDTO.setTrainingPresence(trainingToUser.getTrainingPresence());
		trainingToUserDTO.setEmailId(trainingToUser.getEmailId());
		return trainingToUserDTO;
	}
	public static TrainingsDTO convertTrainingsDaoTODto(Trainings trainings) {
		TrainingsDTO trainingsDTO = new TrainingsDTO();
		
		trainingsDTO.setTrainingId(trainings.getTrainingId());
		trainingsDTO.setTrainingTitle(trainings.getTrainingTitle());
		trainingsDTO.setTrainerEmailId(trainings.getTrainerEmailId());
		trainingsDTO.setTrainingStartTime(trainings.getTrainingStartTime());
		trainingsDTO.setTrainingEndTime(trainings.getTrainingEndTime());
		trainingsDTO.setTrainingType(trainings.getTrainingType());
		trainingsDTO.setDescription(trainings.getDescription());
		trainingsDTO.setTrainingStartDate(trainings.getTrainingStartDate());
		trainingsDTO.setTrainingEndDate(trainings.getTrainingEndDate());
		trainingsDTO.setTrainingStatus(trainings.getTrainingStatus());
		return trainingsDTO;

	}

}
