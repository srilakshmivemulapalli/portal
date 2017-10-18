package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.Trainings;

public interface TrainingRepository extends JpaRepository<Trainings,Integer>{
	
	@Transactional
    @Query(value = "select t from Trainings t where t.trainingType=:trainingType  and t.trainingStartDate>CURDATE() order by trainingStartDate asc")
	List<Trainings> fetchMyTrainings(@Param("trainingType") String trainingType);
	
	@Transactional
	@Query(value="select t from Trainings t where  t.trainingStartDate<CURDATE() order by trainingStartDate asc")
    List<Trainings> fetchCompletedTrainings();
}