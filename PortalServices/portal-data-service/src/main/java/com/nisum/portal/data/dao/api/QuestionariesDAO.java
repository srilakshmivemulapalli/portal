package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.Questionaries;

public interface QuestionariesDAO {

	List<Questionaries> fetchAllQuestionaries();
	List<Questionaries> fetchMyQuestionaries(String emailId);
	Questionaries getQuestionaries(int questionId);
	long getQuestionariesCount();
	Questionaries saveQuestionaries(Questionaries questionaries);
}
