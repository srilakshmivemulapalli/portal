package com.nisum.portal.data.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nisum.portal.data.domain.TrainingFeedBack;

public interface TrainingsFeedBackRepository extends JpaRepository<TrainingFeedBack,Integer>{
	List<TrainingFeedBack> findByTrainingId(Integer trainingId);
}
