package com.nisum.portal.data.dao.api.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.impl.QuestionRepliesDAOImpl;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.repository.QuestionRepliesRepository;

@RunWith(MockitoJUnitRunner.class)
public class QuestionRepliesDAOImplTest {
	
	@Mock
	QuestionRepliesRepository questionRepliesRepository;
	
	@InjectMocks
	QuestionRepliesDAOImpl questionRepliesDAOImpl;
	@Test
	public void getMyReplyQuestions() {
		
		Questionaries questionaries = new Questionaries();
		questionaries.setDescription("description");
		questionaries.setEmailId("test@nisum.com");
		questionaries.setQuestion("What is java");
		questionaries.setQuestionId(1);
		
		List<Questionaries> questionsList = new ArrayList<Questionaries>();
		questionsList.add(questionaries);
		
		when(questionRepliesRepository.getMyReplyQuestions("test@nisum.com")).thenReturn(questionsList);
		
		List<Questionaries> expected = questionRepliesDAOImpl.getMyReplyQuestions("test@nisum.com");
		assertEquals(expected.size(), questionsList.size());
	}

}
