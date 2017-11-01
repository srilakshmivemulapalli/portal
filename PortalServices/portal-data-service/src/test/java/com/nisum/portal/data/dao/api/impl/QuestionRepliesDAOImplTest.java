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
import org.springframework.data.domain.PageRequest;

import com.nisum.portal.data.dao.impl.QuestionRepliesDAOImpl;
import com.nisum.portal.data.domain.Categories;
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
		assertEquals(expected, questionsList);
	}
	@Test
	public void getMyReplyQuestionsByPagination() {
		
		Questionaries questionaries = new Questionaries();
		questionaries.setDescription("description");
		questionaries.setEmailId("test@nisum.com");
		questionaries.setQuestion("What is java");
		questionaries.setQuestionId(1);
	
		List<Questionaries> questionsList = new ArrayList<Questionaries>();
		questionsList.add(questionaries);
		
		when(questionRepliesRepository.getMyReplyQuestionsByPagination("test@nisum.com", new PageRequest(0, 3))).thenReturn(questionsList);
		
		List<Questionaries> expected = questionRepliesDAOImpl.getMyReplyQuestionsByPagination("test@nisum.com", new PageRequest(0, 3));
		assertEquals(expected, questionsList);
	}
	
	
	@Test
	public void getMyReplyQuestionsByCategoryThroughPagination() {
		
		Questionaries questionaries = new Questionaries();
		questionaries.setDescription("description");
		questionaries.setEmailId("test@nisum.com");
		questionaries.setQuestion("What is java");
		questionaries.setQuestionId(1);
	
		List<Questionaries> questionsList = new ArrayList<Questionaries>();
		questionsList.add(questionaries);
		
		Categories category = new Categories();
		category.setCategoryId(1);
		
		when(questionRepliesRepository.getMyReplyQuestionsByCategoryThroughPagination("test@nisum.com", category, new PageRequest(0, 3))).thenReturn(questionsList);
		
		List<Questionaries> expected = questionRepliesDAOImpl.getMyReplyQuestionsByCategoryThroughPagination("test@nisum.com", category, new PageRequest(0, 3));
		assertEquals(expected, questionsList);
	}
	
	@Test
	public void getMyReplyQuestionsByCategory() {
		
		Questionaries questionaries = new Questionaries();
		questionaries.setDescription("description");
		questionaries.setEmailId("test@nisum.com");
		questionaries.setQuestion("What is java");
		questionaries.setQuestionId(1);
	
		List<Questionaries> questionsList = new ArrayList<Questionaries>();
		questionsList.add(questionaries);
		
		Categories category = new Categories();
		category.setCategoryId(1);
		
		when(questionRepliesRepository.getMyReplyQuestionsByCategory("test@nisum.com", category)).thenReturn(questionsList);
		
		List<Questionaries> expected = questionRepliesDAOImpl.getMyReplyQuestionsByCategory("test@nisum.com", category);
		assertEquals(expected, questionsList);
	}

}
