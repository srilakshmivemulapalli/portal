package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.TrainingFeedBack;
import com.nisum.portal.data.domain.Trainings;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;

public class TrainingFeedBackUtil {
	public static List<TrainingFeedBackDTO> convertDaoListToDto(List<TrainingFeedBack> trainingFeedBack) {
		List<TrainingFeedBackDTO> trainingFeedBackDTO = new ArrayList<TrainingFeedBackDTO>();
		
		if (CollectionUtils.isNotEmpty(trainingFeedBack)) {
			for(TrainingFeedBack trainingFeedBacks : trainingFeedBack){
				TrainingFeedBackDTO trainingFeedBackDto = new TrainingFeedBackDTO();
				trainingFeedBackDto.setTrainingFeedBackId(trainingFeedBacks.getTrainingFeedBackId());
				trainingFeedBackDto.setTrainingId(trainingFeedBacks.getTrainings().getTrainingId());
				trainingFeedBackDto.setFeedback(trainingFeedBacks.getFeedback());
				trainingFeedBackDto.setEmailId(trainingFeedBacks.getEmailId());
				trainingFeedBackDto.setCreateDate(trainingFeedBacks.getCreateDate());
				trainingFeedBackDTO.add(trainingFeedBackDto);
			}
		}
		return trainingFeedBackDTO;
	}
	public static TrainingFeedBack convertDtoTODao(TrainingFeedBackDTO trainingFeedBackDTO) {
		TrainingFeedBack trainingFeedBack = new TrainingFeedBack();
		trainingFeedBack.setTrainingFeedBackId(trainingFeedBackDTO.getTrainingFeedBackId());
		trainingFeedBack.setTrainings(new Trainings(trainingFeedBackDTO.getTrainingId()));
		trainingFeedBack.setFeedback(trainingFeedBackDTO.getFeedback());
		trainingFeedBack.setEmailId(trainingFeedBackDTO.getEmailId());
		trainingFeedBack.setCreateDate(trainingFeedBackDTO.getCreateDate());
		return trainingFeedBack;
	}
	public static TrainingFeedBackDTO convertDaoTODto(TrainingFeedBack trainingFeedBack) {
		TrainingFeedBackDTO trainingFeedBackDTO=new TrainingFeedBackDTO();
		trainingFeedBackDTO.setTrainingFeedBackId(trainingFeedBack.getTrainingFeedBackId());
		trainingFeedBackDTO.setTrainingId(trainingFeedBack.getTrainings().getTrainingId());
		trainingFeedBackDTO.setFeedback(trainingFeedBack.getFeedback());
		trainingFeedBackDTO.setEmailId(trainingFeedBack.getEmailId());
		trainingFeedBackDTO.setCreateDate(trainingFeedBack.getCreateDate());
		return trainingFeedBackDTO;
	}
}
