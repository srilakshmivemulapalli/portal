package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.api.BlogsDAO;
import com.nisum.portal.service.dto.BlogsDTO;

@RunWith(MockitoJUnitRunner.class)
public class BlogsServiceImplTest {
	@Mock
	BlogsDAO blogsDAO;

	@InjectMocks
	BlogServiceImpl blogsServiceImpl;
	
	@Test
	public void getAllBlogsTest() {
		List<BlogsDTO> blogsDTO=new ArrayList<BlogsDTO>();
		when(blogsServiceImpl.getAllBlogs()).thenReturn(blogsDTO);
		List<BlogsDTO> expMsg=blogsServiceImpl.getAllBlogs();
		assertEquals(expMsg,blogsDTO);
	}
}
