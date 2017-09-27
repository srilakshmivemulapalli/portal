package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.Questionaries;


public interface QuestionariesRepository extends JpaRepository<Questionaries,Integer>{

	@Transactional
    @Query(value = "SELECT qr from Questionaries qr where qr.emailId = :emailId")
	List<Questionaries> fetchMyQuestionaries(@Param("emailId") String emailId);
}
