package com.nisum.portal.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.portal.data.domain.Trainings;

public interface TrainingRepository extends JpaRepository<Trainings,Integer>{
	
	//Trainings findByCategoryId(Integer trainingId);

}