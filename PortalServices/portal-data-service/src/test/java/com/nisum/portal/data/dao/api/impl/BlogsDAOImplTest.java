package com.nisum.portal.data.dao.api.impl;

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

import com.nisum.portal.data.dao.impl.BlogsDAOImpl;
import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.data.repository.BlogsRepository;

@RunWith(MockitoJUnitRunner.class)
public class BlogsDAOImplTest {
	
	private static Logger logger = LoggerFactory.getLogger(BlogsDAOImplTest.class);
	
	@InjectMocks
	BlogsDAOImpl blogsDaoImpl;

	@Mock
	BlogsRepository blogsRepository;
	
	@Test
	public void getAllBlogsTest() {
		logger.info("BlogsDAOImplTest :: getAllBlogsTest");
		List<Blogs> blogs=new ArrayList<Blogs>();
		
		Integer id=new Integer(18);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("aaaaaa");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18");
		blog.setUserId(101);
		blogs.add(blog);
		when(blogsDaoImpl.getAllBlogs()).thenReturn(blogs);
		List<Blogs> expMsg=blogsDaoImpl.getAllBlogs();
		assertEquals(expMsg,blogs);
	}
	
	@Test
	public void getBlogTest() {
		logger.info("BlogsDAOImplTest :: getBlogTest");
		Integer id=new Integer(1);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("aaaaaa");
		blog.setPath("bbbbbbb");
		blog.setUserId(101);
		
		
		when(blogsDaoImpl.getBlog(id)).thenReturn(blog);
		
		Blogs actMsg=blogsDaoImpl.getBlog(id);
		assertEquals(blog,actMsg);
	}
	
	@Test
	public void removeBlogTest() {
		logger.info("BlogsDAOImplTest :: removeBlogTest");
		/*doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				return null;
			}
		}).when(blogsRepository).delete(1);*/
		
		blogsDaoImpl.removeBlog(1);
		
	}
	
	@Test
	public void updateBlogTest() {
		logger.info("BlogsDAOImplTest :: updateBlogTest");
		Blogs blog=new Blogs();
		when(blogsDaoImpl.updateBlog(blog)).thenReturn(blog);
		Blogs actMsg=blogsDaoImpl.updateBlog(blog);
		assertEquals(blog,actMsg);
	}
	
	@Test
	public void blogExistsTest() {
		logger.info("BlogsDAOImplTest :: blogExistsTest");
		boolean msg=true;
		when(blogsDaoImpl.blogExists(1)).thenReturn(true);
		boolean actMsg=blogsDaoImpl.blogExists(1);
		assertEquals(msg,actMsg);
	}
	
	@Test
	public void addBlogTest() {
		logger.info("BlogsDAOImplTest :: addBlogTest");
		Blogs blog=new Blogs();
		blog.setBlogsId(101);
		when(blogsDaoImpl.addBlog(blog)).thenReturn(blog);
		Blogs actMsg=blogsDaoImpl.addBlog(blog);
		assertEquals(blog,actMsg);
	}
	
	@Test
	public void getAllBlogsByUserMailIdTest() {
		logger.info("BlogsDAOImplTest :: getAllBlogsByUserMailIdTest");
		List<Blogs> blogs=new ArrayList<Blogs>();
		
		Integer id=new Integer(18);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("aaaaaa");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18");
		blog.setUserId(101);
		blogs.add(blog);
		when(blogsDaoImpl.getAllBlogsByUserMailId("sjbasha@nisum.com")).thenReturn(blogs);
		List<Blogs> expMsg=blogsDaoImpl.getAllBlogsByUserMailId("sjbasha@nisum.com");
		assertEquals(expMsg,blogs);
	}
	
	@Test
	public void getAllBlogsPaginationTest() {
		logger.info("BlogsDAOImplTest :: getAllBlogsPaginationTest");
		List<Blogs> blogs=new ArrayList<Blogs>();
		
		Integer id=new Integer(18);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("aaaaaa");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18");
		blog.setUserId(101);
		blogs.add(blog);
		
		when(blogsDaoImpl.getAllBlogsPagination(3, 5)).thenReturn(blogs);
		List<Blogs> expMsg=blogsDaoImpl.getAllBlogsPagination(3, 5);
		assertEquals(expMsg,blogs);
	}
	
	@Test
	public void getAllBlogsPaginationByMailIdTest() {
		logger.info("BlogsDAOImplTest :: getAllBlogsPaginationTest");
		
		List<Blogs> blogs=new ArrayList<Blogs>();
		
		Integer id=new Integer(18);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("aaaaaa");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18");
		blog.setUserId(101);
		blogs.add(blog);
		
		when(blogsDaoImpl.getAllBlogsPaginationByMailId("sjbasha@nisum.com", 3, 5)).thenReturn(blogs);
		List<Blogs> expMsg=blogsDaoImpl.getAllBlogsPaginationByMailId("sjbasha@nisum.com", 3, 5);
		assertEquals(expMsg,blogs);
	}
	
	@Test
	public void getAllBlogsCountTest() {
		logger.info("BlogsDAOImplTest :: getAllBlogsCountTest");
		
		Long lng=10L;
		
		when(blogsDaoImpl.getAllBlogsCount()).thenReturn(lng);
		
		Long expLng=blogsDaoImpl.getAllBlogsCount();
		
		assertEquals(expLng,lng);
	}
}
