package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.Trainings;

public interface TrainingRepository extends JpaRepository<Trainings,Integer>{
	
	@Transactional
    @Query(value = "select t from Trainings t where t.trainingType=:trainingType  and t.trainingStartDate>NOW() and t.trainingStatus=2 order by trainingStartDate asc")
	List<Trainings> fetchMyTrainings(@Param("trainingType") String trainingType);
	
	@Transactional
	@Query(value="SELECT t from Trainings t where t.trainingStatus=2 and t.trainingId IN (SELECT tu.trainingId from TrainingToUser tu  where "+
			"tu.emailId= :emailId and tu.trainingPresence=1 )order by t.trainingStartDate desc")
    List<Trainings> fetchCompletedTrainings(@Param("emailId") String emailId);
	
	@Transactional
	@Query(value="SELECT t from Trainings t where t.trainerEmailId=:trainerEmailId and t.trainingStatus=2 order by trainingStartDate asc")
	List<Trainings> getMyTrainings(@Param("trainerEmailId") String trainerEmailId);
}