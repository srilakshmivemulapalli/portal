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
	
	/**
	 * 
	 * @param category
	 * @param pageable
	 * @return list of questionaries based on category through pagination
	 */
	
	@Transactional
    @Query(value = "SELECT q from Questionaries q where q.categoryId = :category")
	List<Questionaries> retrieveQuestionariesCountByCategory(@Param("category") Categories category);
	/**
	 * 
	 * @param category
	 * @param pageable
	 * @return list of questionaries based on category through pagination
	 */
	
	@Transactional
    @Query(value = "SELECT q from Questionaries q where q.categoryId = :category")
	List<Questionaries> retrieveQuestionariesByCategory(@Param("category") Categories category, Pageable pageable);

	    
    /*@Modifying
	@Transactional
	@Query(value = "UPDATE Questionaries set categoryId =:#{#questionaries.categoryId},question =:#{#questionaries.question},description =:#{#questionaries.description},emailId =:#{#questionaries.emailId} where questionId =:#{#questionaries.questionId}")
	Integer update(@Param("questionaries") Questionaries questionaries);
	*/    
	
	
    /**
	 * retrieves all unanswered questionaries based on category
	 * @param category
	 * @param pageable
	 * @return list of questionaries
	 */
	
	@Transactional
	@Query(value = "SELECT  distinct q FROM Questionaries q where q.questionId not in (SELECT  distinct qr.questId FROM QuestionReplies qr ) and q.categoryId = :category")
	List<Questionaries> retriveAllUnansweredQuestionariesCountByCategory(@Param("category") Categories category);
	/**
	 * retrieves all unanswered questionaries based on category through pagination
	 * @param category
	 * @param pageable
	 * @return list of questionaries
	 */
	
	@Transactional
	@Query(value = "SELECT  distinct q FROM Questionaries q where q.questionId not in (SELECT  distinct qr.questId FROM QuestionReplies qr ) and q.categoryId = :category")
	List<Questionaries> retriveAllUnansweredQuestionariesByCategory(@Param("category") Categories category, Pageable pageable);
	
	/**
	 *  retrieves all unanswered questionaries
	 * @param pageable
	 * @return list of questionaries
	 */
	@Transactional
	@Query(value = "SELECT  distinct q FROM Questionaries q where q.questionId not in (SELECT  distinct qr.questId FROM QuestionReplies qr )")
	List<Questionaries> retriveAllUnansweredQuestionariesByPagination(Pageable pageable);
	
	
	/**
	 * retrieve questionaries for given category and particular emailId 
	 * @param emailId
	 * @param category
	 * @param pageable
	 * @return list of questionaries
	 */
	@Transactional
    @Query(value = "SELECT q from Questionaries q where q.emailId = :emailId and q.categoryId = :category")
	List<Questionaries> fetchMyQuestionariesCountByCategory(@Param("emailId") String emailId, @Param("category") Categories category);
	
	/**
	 * retrieve questionaries for given category and particular emailId through pagination
	 * @param emailId
	 * @param category
	 * @param pageable
	 * @return list of questionaries
	 */
	@Transactional
    @Query(value = "SELECT q from Questionaries q where q.emailId = :emailId and q.categoryId = :category")
	List<Questionaries> fetchMyQuestionariesByCategory(@Param("emailId") String emailId, @Param("category") Categories category, Pageable pageable);
	
	/**
	 * retrieve questionaries for given particular emailId
	 * @param emailId
	 * @param pageable
	 * @return list of questionaries 
	 */
	@Transactional
    @Query(value = "SELECT q from Questionaries q where q.emailId = :emailId")
	List<Questionaries> fetchMyQuestionariesByPagination(@Param("emailId") String emailId, Pageable pageable);
	
}

