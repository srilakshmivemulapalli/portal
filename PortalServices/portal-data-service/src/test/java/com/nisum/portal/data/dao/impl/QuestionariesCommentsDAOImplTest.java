package com.nisum.portal.data.dao.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.domain.QuestionariesComments;
import com.nisum.portal.data.repository.QuestionariesCommentsRepository;
@RunWith(MockitoJUnitRunner.class)
public class QuestionariesCommentsDAOImplTest {
	@Mock
	QuestionariesComments questionComments;
	
	@InjectMocks
	QuestionariesCommentsDAOImpl questionariesCommentsDAOImpl;
	
	@Mock
	QuestionariesCommentsRepository questionariesCommentsRepository;
	@Test
	public void test_saveQuestionComments() {
		QuestionariesComments questionCommentsMock=new QuestionariesComments();
		questionCommentsMock.setcommentDescription("jmsxcdfhv");
		questionCommentsMock.setCommentId(1);
		questionCommentsMock.setEmailId("adjhn@nisum.com");
		questionCommentsMock.setQuestionId(8);
		when(questionariesCommentsRepository.save(questionCommentsMock)).thenReturn(questionCommentsMock);
		QuestionariesComments actual = questionariesCommentsDAOImpl.saveQuestionComments(questionCommentsMock);
		assertEquals(actual.getEmailId(), questionCommentsMock.getEmailId());
		

	}

}
