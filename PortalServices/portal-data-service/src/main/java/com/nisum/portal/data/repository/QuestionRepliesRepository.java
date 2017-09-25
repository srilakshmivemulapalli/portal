package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.QuestionReplies;

public interface QuestionRepliesRepository extends JpaRepository<QuestionReplies,Integer>{

	@Transactional
    @Query(value = "SELECT count(*) from QuestionReplies qr where qr.questId = :questId")
	Integer getQuestionariesReplyCount(@Param("questId") int questId);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query(value = "SELECT qr from QuestionReplies qr where qr.questId = :questId")
	List<QuestionReplies> getQuestionariesReply(@Param("questId") int questId);
	
}
