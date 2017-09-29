package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.Questionaries;


public interface QuestionariesRepository extends JpaRepository<Questionaries,Integer>{

	@Transactional
    @Query(value = "SELECT q from Questionaries q where q.emailId = :emailId")
	List<Questionaries> fetchMyQuestionaries(@Param("emailId") String emailId);

	@Transactional
	//@Query(value = "SELECT q from Questionaries q,QuestionReplies qr WHERE q.questionId != qr.questId group by q.questionId")
	@Query(value = "SELECT q FROM Questionaries as q, QuestionReplies as qr where q.questionId != qr.questId")
	List<Questionaries> retriveAllUnansweredQuestionaries();

}

