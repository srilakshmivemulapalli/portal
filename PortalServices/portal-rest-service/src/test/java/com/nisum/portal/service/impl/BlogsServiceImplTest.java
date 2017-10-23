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

import com.nisum.portal.data.dao.impl.BlogsDAOImpl;
import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.service.dto.BlogsDTO;
import com.nisum.portal.service.exception.BlogServiceException;
import com.nisum.portal.util.BlogsServiceUtil;

@RunWith(MockitoJUnitRunner.class)
public class BlogsServiceImplTest {
	
	private static Logger logger = LoggerFactory.getLogger(BlogsServiceImplTest.class);
	
	@Mock
	BlogsDAOImpl blogsDAOImpl;
	
	@Mock
	BlogsServiceUtil blosgServiceUtil;

	@InjectMocks
	BlogServiceImpl blogsServiceImpl;
	
	
	@Test
	public void getAllBlogsTest() throws Exception {
		logger.info("BlogsServiceImplTest :: getAllBlogsTest");
		List<BlogsDTO> blogsDTO=new ArrayList<BlogsDTO>();
		List<Blogs> blogs=new ArrayList<Blogs>();
		List<String> listValue=new ArrayList<String>();
		listValue.add("epfuan_13Oct2017_03_38_54_PM.pdf");
		listValue.add("hdfcAuthImg_13Oct2017_03_38_54_PM.jpg");
		
		Integer id=new Integer(18);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		
		BlogsDTO blogDTO=new BlogsDTO();
		blogDTO.setBlogsId(id);
		blogDTO.setCreatedDate(createdDate);
		blogDTO.setDescription("aaaaaa");
		blogDTO.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18");
		blogDTO.setUserId(101);
		blogDTO.setFileNames(listValue);
		blogsDTO.add(blogDTO);
		
		
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("aaaaaa");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18");
		blog.setUserId(101);
		blogs.add(blog);
		
		when(blogsDAOImpl.getAllBlogs()).thenReturn(blogs);
		List<BlogsDTO> expMsg=blogsServiceImpl.getAllBlogs();
		BlogsDTO expObj=expMsg.get(0);
		BlogsDTO actObj=blogsDTO.get(0);
		assertThat(actObj).isEqualToComparingFieldByField(expObj);
	}
	@Test
	public void getBlogTestSuccess() throws Exception {
		logger.info("BlogsServiceImplTest :: getBlogTestSuccess");
		List<String> listValue=new ArrayList<String>();
		listValue.add("epfuan_13Oct2017_03_38_54_PM.pdf");
		listValue.add("hdfcAuthImg_13Oct2017_03_38_54_PM.jpg");
		Integer id=new Integer(18);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("aaaaaa");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18");
		blog.setUserId(101);
		
		BlogsDTO blogDTO=new BlogsDTO();
		blogDTO.setBlogsId(id);
		blogDTO.setCreatedDate(createdDate);
		blogDTO.setDescription("aaaaaa");
		blogDTO.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18");
		blogDTO.setUserId(101);
		blogDTO.setFileNames(listValue);
		
		when(blogsDAOImpl.getBlog(id)).thenReturn(blog);
		
		BlogsDTO actMsg=(BlogsDTO) blogsServiceImpl.getBlog(id);
		
		assertThat(actMsg).isEqualToComparingFieldByField(blogDTO);
		
	}
	
	@Test(expected=BlogServiceException.class)
	public void getBlogTestFailure() throws Exception {
		logger.info("BlogsServiceImplTest :: getBlogTestFailure");
		
		assertThat(blogsServiceImpl.getBlog(0)).asString();
	}
	
	@Test
	public void removeBlogSuccess() throws Exception {
		logger.info("BlogsServiceImplTest :: removeBlogSuccess");
		when(blogsDAOImpl.blogExists(18)).thenReturn(true);
		//when(BlogsServiceUtil.removeBlogAttachments("hdfcAuthImg_13Oct2017_03_38_54_PM.jpg")).thenReturn(true);
		blogsServiceImpl.removeBlog(18, "sjbasha@nisum.com");
	}
	
	@Test(expected=BlogServiceException.class)
	public void removeBlogFailure() throws Exception{
		logger.info("BlogsServiceImplTest :: removeBlogFailure ");
		blogsServiceImpl.removeBlog(10, "sjbasha@nisum.com");
	}
	
	@Test
	public void updateBlogSuccess() throws Exception {
		logger.info("BlogsServiceImplTest :: updateBlogSuccess ");
		List<String> listValue=new ArrayList<String>();
		listValue.add("epfuan_13Oct2017_03_38_54_PM.pdf");
		listValue.add("hdfcAuthImg_13Oct2017_03_38_54_PM.jpg");
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		BlogsDTO blogDTO=new BlogsDTO();
		blogDTO.setBlogsId(7);
		blogDTO.setTitle("titleOne");
		blogDTO.setCreatedDate(createdDate);
		blogDTO.setDescription("aaaaaa");
		blogDTO.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18");
		blogDTO.setUserId(101);
		blogDTO.setUserMailId("sjbasha@nisum.com");
		blogDTO.setFileNames(listValue);
		
		Blogs blog=new Blogs();
		blog.setBlogsId(7);
		blog.setTitle("titleOne");
		blog.setCreatedDate(createdDate);
		blog.setDescription("aaaaaa");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18");
		blog.setUserId(101);
		
		when(blogsDAOImpl.blogExists(7)).thenReturn(true);
		when(blogsDAOImpl.getBlog(7)).thenReturn(blog);
		when(blogsDAOImpl.updateBlog(blog)).thenReturn(blog);
		
		BlogsDTO actMsg=(BlogsDTO) blogsServiceImpl.updateBlog(blogDTO);
		assertThat(actMsg).isEqualToComparingFieldByField(blogDTO);
	}
	
	@Test(expected=BlogServiceException.class)
	public void updateBlogFailure() throws Exception {
		logger.info("BlogsServiceImplTest :: updateBlogFailure ");
		BlogsDTO blog=new BlogsDTO();
		blog.setBlogsId(7);
		blog.setTitle("titleOne");
		when(blogsDAOImpl.blogExists(7)).thenReturn(false);
		BlogsDTO actMsg=(BlogsDTO) blogsServiceImpl.updateBlog(blog);
	}
	
	@Test
	public void addBlogSuccess() throws Exception{
		logger.info("BlogsServiceImplTest :: addBlogSuccess ");
		
		Blogs blog=new Blogs();
		blog.setTitle("titleOne");
		blog.setDescription("desc");
		blog.setUserId(101);
		blog.setUserMailId("sjbasha@nisum.com");
		
		BlogsDTO blogDTO=new BlogsDTO();
		blogDTO.setTitle("titleOne");
		blogDTO.setDescription("desc");
		blogDTO.setUserId(101);
		blogDTO.setUserMailId("sjbasha@nisum.com");
		
		BlogsDTO expObj=blogsServiceImpl.addBlog(blogDTO);
		
		assertEquals(expObj,null);
	}
}
