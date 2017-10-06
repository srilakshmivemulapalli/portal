package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.TrainingRequest;
import com.nisum.portal.service.dto.TrainingRequestDTO;

public class TrainingRequestUtil {
	public static List<TrainingRequestDTO> convertDaoListToDto(List<TrainingRequest> trainingRequest) {
		List<TrainingRequestDTO> trainingRequestDTO = new ArrayList<TrainingRequestDTO>();
		
		if (CollectionUtils.isNotEmpty(trainingRequest)) {
			for(TrainingRequest trainingRequests : trainingRequest){
				TrainingRequestDTO trainingRequestDto=new TrainingRequestDTO();
				trainingRequestDto.setTrainingRequestId(trainingRequests.getTrainingRequestId());
				trainingRequestDto.setEmailid(trainingRequests.getEmailid());
				trainingRequestDto.setRequestTrainingTitle(trainingRequests.getRequestTrainingTitle());
				trainingRequestDto.setDescription(trainingRequests.getDescription());
				trainingRequestDto.setRequestedDate(trainingRequests.getRequestedDate());
				trainingRequestDTO.add(trainingRequestDto);
			}
		}
		return trainingRequestDTO;
	}
	public static TrainingRequest convertDtoTODao(TrainingRequestDTO trainingRequestDTO) {

		TrainingRequest trainingRequest = new TrainingRequest();
		trainingRequest.setTrainingRequestId(trainingRequestDTO.getTrainingRequestId());
		trainingRequest.setEmailid(trainingRequestDTO.getEmailid());
		trainingRequest.setRequestTrainingTitle(trainingRequestDTO.getRequestTrainingTitle());
		trainingRequest.setDescription(trainingRequest.getDescription());
		trainingRequest.setRequestedDate(trainingRequestDTO.getRequestedDate());
		return trainingRequest;
	}
	public static TrainingRequestDTO convertDaoTODto(TrainingRequest trainingRequest) {
		TrainingRequestDTO trainingRequestDTO=new TrainingRequestDTO();
		trainingRequestDTO.setTrainingRequestId(trainingRequest.getTrainingRequestId());
		trainingRequestDTO.setEmailid(trainingRequest.getEmailid());
		trainingRequestDTO.setRequestTrainingTitle(trainingRequest.getRequestTrainingTitle());
		trainingRequestDTO.setDescription(trainingRequest.getDescription());
		trainingRequestDTO.setRequestedDate(trainingRequestDTO.getRequestedDate());
		return trainingRequestDTO;
	}
}
