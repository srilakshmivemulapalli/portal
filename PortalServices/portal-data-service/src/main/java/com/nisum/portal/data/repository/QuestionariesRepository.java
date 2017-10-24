package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.Questionaries;


public interface QuestionariesRepository extends JpaRepository<Questionaries,Integer>{

	@Transactional
    @Query(value = "SELECT q from Questionaries q where q.emailId = :emailId")
	List<Questionaries> fetchMyQuestionaries(@Param("emailId") String emailId);

	@Transactional
	@Query(value = "SELECT  distinct q FROM Questionaries q where q.questionId not in (SELECT  distinct qr.questId FROM QuestionReplies qr )")
	List<Questionaries> retriveAllUnansweredQuestionaries();
	
	@Transactional
    @Query(value = "SELECT q from Questionaries q where q.categoryId = :category")
	List<Questionaries> retrieveQuestionariesByCategory(@Param("category") Categories category, Pageable pageable);

	    
    @Modifying
	@Transactional
	@Query(value = "UPDATE Questionaries set categoryId =:#{#questionaries.categoryId},question =:#{#questionaries.question},description =:#{#questionaries.description},emailId =:#{#questionaries.emailId} where questionId =:#{#questionaries.questionId}")
	Integer update(@Param("questionaries") Questionaries questionaries);
	    
	
	


	
	
}

