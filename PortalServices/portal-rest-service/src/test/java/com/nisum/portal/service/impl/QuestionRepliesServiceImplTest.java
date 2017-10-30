package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals; 
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.dao.api.QuestionRepliesDAO;
import com.nisum.portal.data.dao.api.QuestionReplyCommentsDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.QuestionReplyComments;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.QuestionReplyCommentsDTO;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.QuestionReplysUtil;
import com.nisum.portal.util.QuestionariesUtil;


@RunWith(PowerMockRunner.class) 
@PrepareForTest({QuestionariesUtil.class,QuestionReplysUtil.class})
public class QuestionRepliesServiceImplTest {
	
	
	@Mock
	QuestionReplyCommentsDAO questionReplyCommentsDAO;
	
	@InjectMocks
	QuestionRepliesServiceImpl questionRepliesServiceImpl;
	
	@Mock
	QuestionRepliesDAO questionRepliesDAO;
	
	@Mock
	CategoriesDAO categoriesDAO;
	
	@Mock
	UserService userervice;

	
	@Test
	public void saveQuestionComment() {
		QuestionReplyCommentsDTO questionReplyCommentsDTOExpected = new QuestionReplyCommentsDTO();
		questionReplyCommentsDTOExpected.setcommentDescription("asasasaa");
		questionReplyCommentsDTOExpected.setid(1);
		questionReplyCommentsDTOExpected.setCreatedDate(new Timestamp(new Date(0).getTime()));
		questionReplyCommentsDTOExpected.setEmailId("test@nisum.com");
		questionReplyCommentsDTOExpected.setCommentId(1);
		
		QuestionReplyCommentsDTO request = new QuestionReplyCommentsDTO();
		request.setcommentDescription("asasasaa");
		request.setid(1);
		request.setCommentId(1);
		
		QuestionReplyComments comments=new QuestionReplyComments();
				comments.setcommentDescription("asasasaa");
				
				comments.setCreatedDate(new Timestamp(new Date(0).getTime()));
				comments.setEmailId("test@nisum.com");
				comments.setReplyId(1);
				
		
		QuestionReplyComments questionReplyCommentsMock = new QuestionReplyComments();
		questionReplyCommentsMock.setcommentDescription("asasasaa");
		questionReplyCommentsMock.setCommentId(1);
		questionReplyCommentsMock.setCreatedDate(new Timestamp(new Date(0).getTime()));
		questionReplyCommentsMock.setEmailId("test@nisum.com");
		questionReplyCommentsMock.setReplyId(1);
		
		PowerMockito.mockStatic(QuestionReplysUtil.class); 
		PowerMockito.when(QuestionReplysUtil.convertCommentDtoToDao(request)).thenReturn(questionReplyCommentsMock);
		
		//when(questionReplyCommentsDAO.saveReplyComments(questionReplyCommentsMock)).thenReturn(questionReplyCommentsMock);
		PowerMockito.when(QuestionReplysUtil.convertCommentDaoToDto(questionReplyCommentsMock)).thenReturn(questionReplyCommentsDTOExpected);
		
		when(questionReplyCommentsDAO.saveReplyComments(comments)).thenReturn(comments);
		
		QuestionReplyCommentsDTO actual = questionRepliesServiceImpl.saveReplyComment("test@nisum.com", request);
		
		System.out.println("dsssssdsds"+actual);
		assertEquals(questionReplyCommentsDTOExpected.getid(), actual.getid());
	}
	
	@Test
	public void findreplyById() {
		int replyId = 1;
		QuestionReplies reply = new QuestionReplies();
		reply.setEmailid("test@nisum.com");
		reply.setReplyDescription("hsjkhbsd");
		reply.setReplyId(1);
		
		when(questionRepliesDAO.getReply(replyId)).thenReturn(reply);
		boolean actual = questionRepliesServiceImpl.findReplyById(replyId);
		assertEquals(true, actual);
	}
	
	@Test
	public void findReplyByIdFail() {
		int replyId = 1;
		
		when(questionRepliesDAO.getReply(replyId)).thenReturn(null);
		boolean actual = questionRepliesServiceImpl.findReplyById(replyId);
		assertEquals(false, actual);
	}
	
	@Test
	public void fetchMyReplyQuestions() {
		
		List<QuestionariesDTO> questionList = new ArrayList<QuestionariesDTO>();
		
		QuestionariesDTO questionariesDTO = new QuestionariesDTO();
		
			questionariesDTO.setCategoryName("category1");
			questionariesDTO.setDescription("description");
			questionariesDTO.setDisplayImage("DisplayImage");
			questionariesDTO.setDisplayName("display name");
			questionariesDTO.setEmailId("test@nisum.com");
			questionariesDTO.setQuestion("Question111");
			questionariesDTO.setQuestionId(1);
			questionariesDTO.setQuestionRepliesCount(0);

		questionList.add(questionariesDTO);

		
		
		Categories category = new Categories();
		category.setCategoryId(1);
		category.setCategoryName("JAVA");
		
		List<Questionaries> questionariesList=new ArrayList<>();
		
		Questionaries questionaries = new Questionaries();
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("what is abc");
			questionaries.setQuestionId(1);
			questionaries.setCategoryId(category);
			questionaries.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			questionaries.setDescription("description");
	
		
		questionariesList.add(questionaries);
		
		
		QuestionsDTO expected = new QuestionsDTO();
		expected.setTotalQuestions(questionariesList.size());
		expected.setTotalUsers(1); 
		
		when(questionRepliesDAO.getMyReplyQuestionsByPagination("test@nisum.com", new PageRequest(0, 3))).thenReturn(questionariesList);
		when(questionRepliesDAO.getMyReplyQuestions("test@nisum.com")).thenReturn(questionariesList);
		QuestionsDTO actual = questionRepliesServiceImpl.fetchMyReplyQuestionsByPagination("test@nisum.com", new PageRequest(0, 3));
		
		assertEquals(expected.getTotalQuestions(), actual.getTotalQuestions());
		
	}
	
	
	@Test
	public void fetchMyQuestionariesByCategoryTest() {
		
List<QuestionariesDTO> questionList = new ArrayList<QuestionariesDTO>();
		
		QuestionariesDTO questionariesDTO = new QuestionariesDTO();
		
			questionariesDTO.setCategoryName("category1");
			questionariesDTO.setDescription("description");
			questionariesDTO.setDisplayImage("DisplayImage");
			questionariesDTO.setDisplayName("display name");
			questionariesDTO.setEmailId("test@nisum.com");
			questionariesDTO.setQuestion("Question111");
			questionariesDTO.setQuestionId(1);
			questionariesDTO.setQuestionRepliesCount(0);

		questionList.add(questionariesDTO);

		
		
		Categories category = new Categories();
		category.setCategoryId(1);
		category.setCategoryName("JAVA");
		
		List<Questionaries> questionariesList=new ArrayList<>();
		
		Questionaries questionaries = new Questionaries();
			questionaries.setEmailId("test@nisum.com");
			questionaries.setQuestion("what is abc");
			questionaries.setQuestionId(1);
			questionaries.setCategoryId(category);
			questionaries.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			questionaries.setDescription("description");
	
		
		questionariesList.add(questionaries);
		
		
		QuestionsDTO expected = new QuestionsDTO();
		expected.setTotalQuestions(questionariesList.size());
		expected.setTotalUsers(1); 
		
		when(categoriesDAO.getCategory(1)).thenReturn(category);
		
		when(questionRepliesDAO.getMyReplyQuestionsByCategoryThroughPagination("test@nisum.com", category, new PageRequest(0, 3))).thenReturn(questionariesList);
		when(questionRepliesDAO.getMyReplyQuestionsByCategory("test@nisum.com", category)).thenReturn(questionariesList);
		QuestionsDTO actual = questionRepliesServiceImpl.fetchMyReplyQuestionsByCategory( "test@nisum.com", 1, new PageRequest(0, 3));
		
		assertEquals(expected.getTotalQuestions(), actual.getTotalQuestions());
		
	} 

}
