package com.nisum.portal.data.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.TrainingFeedBack;

public interface TrainingsFeedBackRepository extends JpaRepository<TrainingFeedBack,Integer>{
	TrainingFeedBack findByTrainingFeedBackId(Integer trainingFeedBackId);
	@Query(value="Select f from TrainingFeedBack f where f.trainings.trainingId= :trainingId")
	List<TrainingFeedBack> findByTrainingId(@Param("trainingId") Integer trainingId);
}
