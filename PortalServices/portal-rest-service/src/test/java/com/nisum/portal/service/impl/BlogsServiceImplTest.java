package com.nisum.portal.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import com.nisum.portal.data.dao.impl.BlogsDAOImpl;
import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.service.dto.BlogsDTO;
import com.nisum.portal.service.exception.BlogServiceException;
import com.nisum.portal.util.BlogsServiceUtil;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
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
		listValue.add("epfuan_17Oct2017_04_44_00_PM.pdf");
		listValue.add("hdfcAuthImg_17Oct2017_04_44_00_PM.jpg");
		
		Integer id=new Integer(22);
		long millis=1508238840000L;
		Timestamp createdDate=new Timestamp(millis);
		
		BlogsDTO blogDTO=new BlogsDTO();
		blogDTO.setBlogsId(id);
		blogDTO.setCreatedDate(createdDate);
		blogDTO.setDescription("123dddddddddd");
		blogDTO.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blogDTO.setUserId(103);
		blogDTO.setFileNames(listValue);
		blogsDTO.add(blogDTO);
		
		
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("123dddddddddd");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blog.setUserId(103);
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
		listValue.add("epfuan_17Oct2017_04_44_00_PM.pdf");
		listValue.add("hdfcAuthImg_17Oct2017_04_44_00_PM.jpg");
		Integer id=new Integer(22);
		long millis=1508238840000L;
		Timestamp createdDate=new Timestamp(millis);
		
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("123dddddddddd");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blog.setUserId(103);
		
		BlogsDTO blogDTO=new BlogsDTO();
		blogDTO.setBlogsId(id);
		blogDTO.setCreatedDate(createdDate);
		blogDTO.setDescription("123dddddddddd");
		blogDTO.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blogDTO.setUserId(103);
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
	public void removeBlog() throws Exception {
		logger.info("BlogsServiceImplTest :: removeBlogSuccess");
		Integer id=18;
		when(blogsDAOImpl.blogExists(id)).thenReturn(true);
		//when(BlogsServiceUtil.removeBlogAttachments("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18")).thenReturn(true);
		//when(blosgServiceUtil.removeBlogAttachments("null/sjbasha@nisum.com/18")).thenReturn(true);
		
		blogsServiceImpl.removeBlog(id, "/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com");
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
		listValue.add("epfuan_17Oct2017_04_44_00_PM.pdf");
		listValue.add("hdfcAuthImg_17Oct2017_04_44_00_PM.jpg");
		long millis=1508238840000L;
		Timestamp createdDate=new Timestamp(millis);
		BlogsDTO blogDTO=new BlogsDTO();
		blogDTO.setBlogsId(22);
		blogDTO.setTitle("titleOne");
		blogDTO.setCreatedDate(createdDate);
		blogDTO.setDescription("123dddddddddd");
		blogDTO.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blogDTO.setUserId(103);
		blogDTO.setUserMailId("sjbasha@nisum.com");
		blogDTO.setFileNames(listValue);
		
		Blogs blog=new Blogs();
		blog.setBlogsId(18);
		blog.setTitle("titleOne");
		blog.setCreatedDate(createdDate);
		blog.setDescription("123dddddddddd");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blog.setUserId(103);
		
		when(blogsDAOImpl.blogExists(22)).thenReturn(true);
		when(blogsDAOImpl.getBlog(22)).thenReturn(blog);
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
	
	@Test
	public void parseRequestToGetBlogsDTOTestSuccess() throws Exception {
		
		logger.info("BlogsServiceImplTest :: parseRequestToGetBlogsDTOTestSuccess ");
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		String title="testTile";
		String description="testDescription";
		String userId="101";
		String userMailId="TestUserMailId";

		
		when(request.getParameter("title")).thenReturn(title);
		when(request.getParameter("description")).thenReturn(description);
		when(request.getParameter("userId")).thenReturn(userId);
		when(request.getParameter("userMailId")).thenReturn(userMailId);
		
		
		
		BlogsDTO actDTO=new BlogsDTO();
		actDTO.setTitle(title);
		actDTO.setDescription(description);
		actDTO.setUserId(Integer.parseInt(userId));
		actDTO.setUserMailId(userMailId);
		
		BlogsDTO expDTO=blogsServiceImpl.parseRequestToGetBlogsDTO(request);
		assertThat(actDTO).isEqualToComparingFieldByField(expDTO);
	}
	
	@Test
	public void parseRequestToStoreUploadsTestSuccess() throws Exception {
		
		logger.info("BlogsServiceImplTest :: parseRequestToStoreUploadsTestSuccess ");
		
		int blogId=6;
		String userMailId="sjbasha@nisum.com";
		
		String fileName="epfuan_17Oct2017_04_44_00_PM.pdf";
		
		String dirPath="/Users/nisum/Documents/BlogAttachments";
		
		BlogsDTO blogDTO=new BlogsDTO();
		blogDTO.setBlogsId(blogId);
		blogDTO.setUserMailId(userMailId);
		
		List<Part> parts=new ArrayList<Part>();
		
		InputStream inputStream=new FileInputStream("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22/epfuan_17Oct2017_04_44_00_PM.pdf");
		
		Part part=mock(Part.class);
		
		parts.add(part);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		when(part.getSubmittedFileName()).thenReturn(fileName);
		
		when(part.getName()).thenReturn("uploads");
		
		when(part.getInputStream()).thenReturn(inputStream);
		
		when(request.getParts()).thenReturn(parts);
		
		BlogsDTO actDTO=blogsServiceImpl.parseRequestToStoreUploads(request, dirPath, blogDTO);
		
		assertThat(actDTO).isEqualToComparingFieldByField(blogDTO);
		
		
	}
	
	@Test
	public void uploadAttachmentTestSuccess() throws Exception {
		
		logger.info("BlogsServiceImplTest :: uploadAttachmentTestSuccess ");
		
		String fileName="epfuan.pdf";
		
		String dirPath="/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/6";
		
		List<Part> parts=new ArrayList<Part>();
		
		InputStream inputStream=new FileInputStream("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22/epfuan_17Oct2017_04_44_00_PM.pdf");
		
		Part part=mock(Part.class);
		
		parts.add(part);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		when(part.getSubmittedFileName()).thenReturn(fileName);
		
		when(part.getName()).thenReturn("uploads");
		
		when(part.getInputStream()).thenReturn(inputStream);
		
		when(request.getParts()).thenReturn(parts);
		
		String expMsg=blogsServiceImpl.uploadAttachment(request, dirPath);
		
		assertEquals(dirPath,expMsg);
	}
	
	@Test
	public void getFileTestSuccess() throws Exception {
		
		logger.info("BlogsServiceImplTest :: getFileTestSuccess ");
		
		String blogsPath="/Users/nisum/Documents/BlogAttachments";
		
		ReflectionTestUtils.setField(blogsServiceImpl, "blogsAttachmentPath", blogsPath);
		
		//when(blogsServiceImpl.getBlogsAttachmentPath()).thenReturn(blogsPath);
		
		Path path=blogsServiceImpl.getFile("sjbasha@nisum.com",new Integer(6),"epfuan_23Oct2017_11_29_05_PM.pdf");
		
		assertEquals("epfuan_23Oct2017_11_29_05_PM.pdf",path.getFileName().toString());
	}
	
	@Test
	public void removeFileTestSuccess() throws Exception {
		logger.info("BlogsServiceImplTest :: removeFileTestSuccess ");
		
		String blogsPath="/Users/nisum/Documents/BlogAttachments";
		
		ReflectionTestUtils.setField(blogsServiceImpl, "blogsAttachmentPath", blogsPath);
		
		when(blogsDAOImpl.blogExists(6)).thenReturn(true);
		
		boolean expMsg=blogsServiceImpl.removeFile("sjbasha@nisum.com",new Integer(6),"epfuan_17Oct2017_04_44_00_PM_23Oct2017_11_14_48_PM.pdf");
		
		assertEquals(expMsg,true);
	}
}
