package com.nisum.portal.data.dao.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.domain.QuestionReplyComments;
import com.nisum.portal.data.domain.QuestionariesComments;
import com.nisum.portal.data.repository.QuestionReplyCommentsRepository;
@RunWith(MockitoJUnitRunner.class)
public class QuestionReplyCommentsDAOImplTest {
	@Mock
	QuestionReplyComments questionReplyComments;
	
	@InjectMocks
	QuestionReplyCommentsDAOImpl questionReplyCommentsDAOImpl;
	
	@Mock
	QuestionReplyCommentsRepository questionReplyCommentsRepository;

	@Test
	public void test_saveReplyComments() {
		QuestionReplyComments  replyCommentsMock=new QuestionReplyComments();
		replyCommentsMock.setcommentDescription("hvgxnh");
		replyCommentsMock.setCommentId(1);
		replyCommentsMock.setEmailId("nsudh@nisum.com");
		replyCommentsMock.setReplyId(1);
		
		when(questionReplyCommentsRepository.save(replyCommentsMock)).thenReturn(replyCommentsMock);
		QuestionReplyComments actual = questionReplyCommentsDAOImpl.saveReplyComments(replyCommentsMock);
		assertEquals(actual.getEmailId(), replyCommentsMock.getEmailId());
		
		
	}

}
