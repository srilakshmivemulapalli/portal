package com.nisum.portal.data.dao.api;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.Questionaries;

public interface QuestionariesDAO {

	List<Questionaries> fetchAllQuestionaries();
	List<Questionaries> fetchMyQuestionaries(String emailId);
	Questionaries getQuestionaries(int questionId);
	long getQuestionariesCount();
	Questionaries saveQuestionaries(Questionaries questionaries);
	Questionaries updateQuestionaries(Questionaries questionaries);
	List<Questionaries> retriveAllUnansweredQuestionaries();
	List<Questionaries> retrieveQuestionCountByCategory(Categories category);
	List<Questionaries> retrieveQuestionByPagination(Pageable pageable);
	List<Questionaries> retrieveQuestionByCategory(Categories category, Pageable pageable);
	List<Questionaries> retrieveAllUnansweredQuestionariesCountByCategory(Categories category);
	List<Questionaries> retrieveAllUnansweredQuestionariesByCategory(Categories category, Pageable pageable);
	List<Questionaries> retrieveAllUnansweredQuestionariesByPagination(Pageable pageable);
	List<Questionaries> fetchMyQuestionariesCountByCategory(String emailId, Categories category);
	List<Questionaries> fetchMyQuestionariesByCategory(String emailId, Categories category, Pageable pageable);
	List<Questionaries> fetchMyQuestionariesByPagination(String emailId, Pageable pageable);
}
