package com.nisum.portal.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.api.QuestionariesDAO;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.service.dto.CountDTO;

@RunWith(MockitoJUnitRunner.class)
public class QuestionariesServiceImplTest {
	@Mock
	QuestionariesDAO questionariesDAO;
	
	@Mock
	UserDAO userDAO;
	
	@InjectMocks
	QuestionariesServiceImpl questionariesServiceImpl;
	
	@Test
	public void getQuestionariesCountTestSuccess() {
		long count=0;
		CountDTO countDTO=new CountDTO();
		countDTO.setQuestionCount(count);
		countDTO.setUserCount(count);
		when(questionariesDAO.getQuestionariesCount()).thenReturn(count);
		when(userDAO.getUserCount()).thenReturn(count);
		assertThat(countDTO).isEqualToComparingFieldByField(questionariesServiceImpl.getQuestionariesCount());
	}
}
