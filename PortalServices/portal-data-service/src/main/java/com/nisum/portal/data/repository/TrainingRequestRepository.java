package com.nisum.portal.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nisum.portal.data.domain.TrainingRequest;
import java.lang.Integer;

import javax.transaction.Transactional;
@Repository
public interface TrainingRequestRepository extends JpaRepository<TrainingRequest, Integer>{
	TrainingRequest findByTrainingRequestId(Integer trainingRequestId);
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE TrainingRequest t SET t.requestStatus=:i WHERE t.trainingRequestId=:trainingRequestId")
	Integer updateRequest(@Param("trainingRequestId") int trainingRequestId, @Param("i") int i);
}
