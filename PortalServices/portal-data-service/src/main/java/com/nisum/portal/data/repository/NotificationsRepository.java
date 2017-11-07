package com.nisum.portal.data.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nisum.portal.data.domain.Notifications;

@Repository
@Transactional
public interface NotificationsRepository extends JpaRepository<Notifications,Integer>{
	/**
	 * 
	 * @param questionId
	 * @return max of questionaries
	 */
	
	@Transactional
    @Query(value = "SELECT max(questionId) from Questionaries")
	int fetchMaxQuestionTd();
}
