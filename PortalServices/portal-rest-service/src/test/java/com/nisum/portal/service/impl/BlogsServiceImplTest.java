package com.nisum.portal.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nisum.portal.data.dao.api.BlogsDAO;
import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.service.dto.BlogsDTO;

@RunWith(MockitoJUnitRunner.class)
public class BlogsServiceImplTest {
	
	private static Logger logger = LoggerFactory.getLogger(BlogsServiceImplTest.class);
	
	@Mock
	BlogsDAO blogsDAO;

	@InjectMocks
	BlogServiceImpl blogsServiceImpl;
	
	@Test
	public void getAllBlogsTest() {
		logger.info("BlogsServiceImplTest :: getAllBlogsTest");
		List<BlogsDTO> blogsDTO=new ArrayList<BlogsDTO>();
		when(blogsServiceImpl.getAllBlogs()).thenReturn(blogsDTO);
		List<BlogsDTO> expMsg=blogsServiceImpl.getAllBlogs();
		assertEquals(expMsg,blogsDTO);
	}
	@Test
	public void getBlogTest() {
		logger.info("BlogsServiceImplTest :: getBlogTest");
		Integer id=new Integer(1);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setCategoryId(101);
		blog.setDescription("aaaaaa");
		blog.setPath("bbbbbbb");
		blog.setUserId(101);
		
		
		when(blogsDAO.getBlog(id)).thenReturn(blog);
		
		BlogsDTO actMsg=(BlogsDTO) blogsServiceImpl.getBlog(id);
		
		assertThat(actMsg).isEqualToComparingFieldByField(blog);
		
	}
}
