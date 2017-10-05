package com.nisum.portal.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.impl.BlogsDAOImpl;
import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.data.repository.BlogsRepository;

@RunWith(MockitoJUnitRunner.class)
public class BlogsDAOImplTest {
	
	@InjectMocks
	BlogsDAOImpl blogsDaoImpl;

	@Mock
	BlogsRepository blogsRepository;
	
	@Test
	public void getAllBlogsTest() {
		List<Blogs> allBlogs=new ArrayList<Blogs>();
		when(blogsDaoImpl.getAllBlogs()).thenReturn(allBlogs);
		List<Blogs> expMsg=blogsDaoImpl.getAllBlogs();
		assertEquals(expMsg,allBlogs);
	}

}
