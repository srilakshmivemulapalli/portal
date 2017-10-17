package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.TrainingToUser;

public interface TrainingToUserRepository extends JpaRepository<TrainingToUser, Integer> {
	
@Transactional

@Query(value ="select tu.trainingPresence from TrainingToUser tu where tu.emailId=:emailId and tu.trainingId=:trainingId")
Integer fetchTrainingPresence(@Param("emailId") String emailId,@Param("trainingId") int trainingId);


@Transactional
@Query(value="SELECT distinct(tu.emailId),tu.trainingToUserId FROM TrainingToUser tu where tu.trainingId=:trainingId")
List<Object[]> fetchnoOfStudent(@Param("trainingId") int trainingId);
}
