package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.Questionaries;

public interface QuestionRepliesRepository extends JpaRepository<QuestionReplies,Integer>{

	@Transactional
    @Query(value = "SELECT count(*) from QuestionReplies qr where qr.questId = :questId")
	Integer getQuestionariesReplyCount(@Param("questId") int questId);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query(value = "SELECT qr from QuestionReplies qr where qr.questId = :questId")
	List<QuestionReplies> getQuestionariesReply(@Param("questId") int questId);
	
	@Transactional
	@Query(value = "SELECT q from Questionaries q where q.questionId IN (select questId from QuestionReplies where emailid =:emailId)")
	List<Questionaries> getMyReplyQuestions(@Param("emailId") String emailId, Pageable pageable);
	
	
	@Query(value="SELECT qst from Questionaries qst where qst.questionId =:questId")
	Questionaries findByuserEmail(@Param("questId")Integer questId);
	
	/**
	 * used to retrieve questionaries for given category and particular emailId
	 * @param emailId
	 * @param category
	 * @param pageable
	 * @return list of questionaries
	 */
	@Transactional
	@Query(value =" SELECT q FROM Questionaries q , QuestionReplies qr  where qr.emailid = :emailId  and q.categoryId = :category and q.questionId = qr.questId")
	List<Questionaries> getMyReplyQuestionsByCategory(@Param("emailId") String emailId, @Param ("category")Categories category,  Pageable pageable);
}
