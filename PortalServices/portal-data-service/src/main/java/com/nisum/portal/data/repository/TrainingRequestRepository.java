package com.nisum.portal.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.portal.data.domain.TrainingRequest;
import java.lang.Integer;

public interface TrainingRequestRepository extends JpaRepository<TrainingRequest, Integer>{
	TrainingRequest findByTrainingRequestId(Integer trainingRequestId);
}
