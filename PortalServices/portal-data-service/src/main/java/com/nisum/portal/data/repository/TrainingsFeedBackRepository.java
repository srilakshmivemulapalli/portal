package com.nisum.portal.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.portal.data.domain.TrainingFeedBack;

public interface TrainingsFeedBackRepository extends JpaRepository<TrainingFeedBack,Integer>{
	TrainingFeedBack findByTrainingFeedBackId(Integer trainingFeedBackId);
}
