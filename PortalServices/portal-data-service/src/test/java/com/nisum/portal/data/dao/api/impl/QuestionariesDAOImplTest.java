package com.nisum.portal.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.PageAdapter;

import com.nisum.portal.data.dao.impl.QuestionariesDAOImpl;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.repository.QuestionariesRepository;

@RunWith(MockitoJUnitRunner.class)
public class QuestionariesDAOImplTest {

	@InjectMocks
	QuestionariesDAOImpl questionariesDAOImpl;
	
	@Mock
	QuestionariesRepository questionariesRepository;
	
	
	@Test
	public void retrieveQuestionByCategoryTest() {
		
		Questionaries questionaries = new Questionaries();
			questionaries.setDescription("description");
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("What is java");
			questionaries.setQuestionId(1);

		List<Questionaries> questionariesExpected = new ArrayList<>();
			questionariesExpected.add(questionaries);

		Categories category = new Categories();
			category.setCategoryId(1);

		when(questionariesRepository.retrieveQuestionariesByCategory(category, new PageRequest(0, 3))).thenReturn(questionariesExpected);
				

		List<Questionaries> actual = questionariesDAOImpl.retrieveQuestionByCategory(category, new PageRequest(0, 3));

		assertEquals(questionariesExpected, actual);
	}
	
	
	@Test
	public void retrieveQuestionByPaginationTest() {
		

		Categories category = new Categories();
		category.setCategoryId(1);

		Questionaries questionaries = new Questionaries();
		questionaries.setDescription("description");
		questionaries.setEmailId("test@nisum.com");
		questionaries.setQuestion("What is java");
		questionaries.setQuestionId(1);
		questionaries.setCategoryId(category);

		List<Questionaries> questionariesExpected = new ArrayList<>();
		questionariesExpected.add(questionaries);
		PageImpl<Questionaries> pageQuestionaries = new PageImpl<>(questionariesExpected);

		when(questionariesRepository.findAll(new PageRequest(0, 3))).thenReturn(pageQuestionaries);

		List<Questionaries> actual = questionariesDAOImpl.retrieveQuestionByPagination(new PageRequest(0, 3));

		assertEquals(questionariesExpected, actual);
		
	}
	
	@Test
	public void retrieveAllUnansweredQuestionariesByCategoryTest() {
		
		Questionaries questionaries = new Questionaries();
			questionaries.setDescription("description");
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("What is java");
			questionaries.setQuestionId(1);

		List<Questionaries> questionariesExpected = new ArrayList<>();
			questionariesExpected.add(questionaries);

		Categories category = new Categories();
			category.setCategoryId(1);

		when(questionariesRepository.retriveAllUnansweredQuestionariesByCategory(category, new PageRequest(0, 3)))
				.thenReturn(questionariesExpected);

		List<Questionaries> actual = questionariesDAOImpl.retrieveAllUnansweredQuestionariesByCategory(category, new PageRequest(0, 3));

		assertEquals(questionariesExpected, actual);
	}
	
	@Test
	public void retrieveAllUnansweredQuestionariesByPaginationTest() {
		

		Categories category = new Categories();
		category.setCategoryId(1);

		Questionaries questionaries = new Questionaries();
		questionaries.setDescription("description");
		questionaries.setEmailId("test@nisum.com");
		questionaries.setQuestion("What is java");
		questionaries.setQuestionId(1);
		questionaries.setCategoryId(category);

		List<Questionaries> questionariesExpected = new ArrayList<>();
		questionariesExpected.add(questionaries);
		PageImpl<Questionaries> pageQuestionaries = new PageImpl<>(questionariesExpected);

		when(questionariesRepository.retriveAllUnansweredQuestionariesByPagination(new PageRequest(0, 3))).thenReturn(questionariesExpected);

		List<Questionaries> actual = questionariesDAOImpl.retrieveAllUnansweredQuestionariesByPagination(new PageRequest(0, 3));

		assertEquals(questionariesExpected, actual);
		
	}
	
	@Test
	public void fetchMyQuestionariesByCategoryTest() {
		
		Questionaries questionaries = new Questionaries();
			questionaries.setDescription("description");
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("What is java");
			questionaries.setQuestionId(1);

		List<Questionaries> questionariesExpected = new ArrayList<>();
			questionariesExpected.add(questionaries);

		Categories category = new Categories();
			category.setCategoryId(1);

		when(questionariesRepository.fetchMyQuestionariesByCategory("test@nisum.com", category, new PageRequest(0, 3)))
				.thenReturn(questionariesExpected);

		List<Questionaries> actual = questionariesDAOImpl.fetchMyQuestionariesByCategory("test@nisum.com", category, new PageRequest(0, 3));

		assertEquals(questionariesExpected, actual);
	}
	
	@Test
	public void fetchMyQuestionariesByPaginationTest() {
		

		Categories category = new Categories();
		category.setCategoryId(1);

		Questionaries questionaries = new Questionaries();
		questionaries.setDescription("description");
		questionaries.setEmailId("test@nisum.com");
		questionaries.setQuestion("What is java");
		questionaries.setQuestionId(1);
		questionaries.setCategoryId(category);

		List<Questionaries> questionariesExpected = new ArrayList<>();
		questionariesExpected.add(questionaries);
		PageImpl<Questionaries> pageQuestionaries = new PageImpl<>(questionariesExpected);

		when(questionariesRepository.fetchMyQuestionariesByPagination("test@nisum.com", new PageRequest(0, 3))).thenReturn(questionariesExpected);

		List<Questionaries> actual = questionariesDAOImpl.fetchMyQuestionariesByPagination("test@nisum.com", new PageRequest(0, 3));

		assertEquals(questionariesExpected, actual);
		
	}
	
	@Test
	public void retrieveAllUnansweredQuestionariesCountByCategoryTest() {
		
		Questionaries questionaries = new Questionaries();
			questionaries.setDescription("description");
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("What is java");
			questionaries.setQuestionId(1);

		List<Questionaries> questionariesExpected = new ArrayList<>();
			questionariesExpected.add(questionaries);

		Categories category = new Categories();
			category.setCategoryId(1);

		when(questionariesRepository.retriveAllUnansweredQuestionariesCountByCategory(category))
				.thenReturn(questionariesExpected);

		List<Questionaries> actual = questionariesDAOImpl.retrieveAllUnansweredQuestionariesCountByCategory(category);

		assertEquals(questionariesExpected, actual);
	}
	
	@Test
	public void fetchMyQuestionariesCountByCategoryTest() {
		
		Questionaries questionaries = new Questionaries();
			questionaries.setDescription("description");
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("What is java");
			questionaries.setQuestionId(1);

		List<Questionaries> questionariesExpected = new ArrayList<>();
			questionariesExpected.add(questionaries);

		Categories category = new Categories();
			category.setCategoryId(1);

		when(questionariesRepository.fetchMyQuestionariesCountByCategory("test@nisum.com", category))
				.thenReturn(questionariesExpected);

		List<Questionaries> actual = questionariesDAOImpl.fetchMyQuestionariesCountByCategory("test@nisum.com", category);

		assertEquals(questionariesExpected, actual);
	}
	
	
}
