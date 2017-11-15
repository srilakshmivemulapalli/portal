package com.nisum.portal.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.notNull;
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
import org.springframework.web.multipart.MultipartFile;

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
	public void removeBlogTest() throws Exception {
		logger.info("BlogsServiceImplTest :: removeBlogSuccess");
		Integer id=18;
		//Integer idFail=10;
		String dirPath="/Users/nisum/Documents/BlogAttachments";
		when(blogsDAOImpl.blogExists(id)).thenReturn(true);
		//when(blogsDAOImpl.blogExists(idFail)).thenReturn(false);
		//when(BlogsServiceUtil.removeBlogAttachments("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/18")).thenReturn(true);
		//when(blosgServiceUtil.removeBlogAttachments("null/sjbasha@nisum.com/18")).thenReturn(true);
		
		ReflectionTestUtils.setField(blogsServiceImpl, "blogsAttachmentPath", dirPath);
		blogsServiceImpl.removeBlog(id, "sjbasha@nisum.com");
		//blogsServiceImpl.removeBlog(idFail, "sjbasha@nisum.com");
	}
	
	@Test(expected=BlogServiceException.class)
	public void removeBlogFailure() throws Exception{
		logger.info("BlogsServiceImplTest :: removeBlogFailure ");
		//when(blogsDAOImpl.blogExists(10)).thenReturn(true);
		when(blogsDAOImpl.blogExists(11)).thenReturn(false);
		//blogsServiceImpl.removeBlog(10, "sjbasha@nisum.com");
		blogsServiceImpl.removeBlog(11, "sjbasha@nisum.com");
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
		blog.setBlogsId(22);
		blog.setTitle("titleOne");
		blog.setCreatedDate(createdDate);
		blog.setDescription("123dddddddddd");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blog.setUserMailId("sjbasha@nisum.com");
		blog.setUserId(103);
		
		when(blogsDAOImpl.blogExists(22)).thenReturn(true);
		
		when(blogsDAOImpl.updateBlog(any(Blogs.class))).thenReturn(blog);
		
		//when(blogsDAOImpl.updateBlog((Blogs)notNull())).thenReturn(blog);
		
		
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
		when(request.getParameter("emailId")).thenReturn(userMailId);
		
		
		
		BlogsDTO actDTO=new BlogsDTO();
		actDTO.setTitle(title);
		actDTO.setDescription(description);
		actDTO.setUserId(Integer.parseInt(userId));
		actDTO.setUserMailId(userMailId);
		
		BlogsDTO expDTO=blogsServiceImpl.parseRequestToGetBlogsDTO(request);
		assertThat(actDTO).isEqualToComparingFieldByField(expDTO);
	}
	
	@Test
	public void parseRequestToGetBlogsDTOForUpdateSuccess() throws Exception {
		
		logger.info("BlogsServiceImplTest :: parseRequestToGetBlogsDTOForUpdateSuccess ");
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		String title="testTile";
		String blogId="0";
		String description="testDescription";

		
		when(request.getParameter("blogId")).thenReturn(blogId);
		when(request.getParameter("title")).thenReturn(title);
		when(request.getParameter("description")).thenReturn(description);
				
		
		BlogsDTO actDTO=new BlogsDTO();
		actDTO.setTitle(title);
		actDTO.setDescription(description);
		
		BlogsDTO expDTO=blogsServiceImpl.parseRequestToGetBlogsDTOForUpdate(request);
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
		
		InputStream inputStream=new FileInputStream("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22/epfuan_17Oct2017_04_44_00_PM.pdf");
		
		MultipartFile request=mock(MultipartFile.class);
		
		MultipartFile[] mFileArr=new MultipartFile[1];
		mFileArr[0]=request;
		
		
		when(mFileArr[0].getOriginalFilename()).thenReturn(fileName);
		
		when(mFileArr[0].getName()).thenReturn("uploads");
		
		when(mFileArr[0].getInputStream()).thenReturn(inputStream);
		
		
		BlogsDTO actDTO=blogsServiceImpl.parseRequestToStoreUploads(mFileArr, dirPath, blogDTO);
		
		assertThat(actDTO).isEqualToComparingFieldByField(blogDTO);
		
		
	}
	
	@Test
	public void uploadAttachmentTestSuccess() throws Exception {
		
		logger.info("BlogsServiceImplTest :: uploadAttachmentTestSuccess ");
		
		String fileName="epfuan.pdf";
		
		String dirPath="/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/6";
		
		List<Part> parts=new ArrayList<Part>();
		
		InputStream inputStream=new FileInputStream("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22/epfuan_17Oct2017_04_44_00_PM.pdf");
		
		MultipartFile request=mock(MultipartFile.class);
		
		MultipartFile[] mFileArr=new MultipartFile[1];
		mFileArr[0]=request;
		
		when(mFileArr[0].getOriginalFilename()).thenReturn(fileName);
		
		when(mFileArr[0].getName()).thenReturn("uploads");
		
		when(mFileArr[0].getInputStream()).thenReturn(inputStream);
		
		String expMsg=blogsServiceImpl.uploadAttachment(mFileArr, dirPath);
		
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
	
	@Test(expected=BlogServiceException.class)
	public void getFileTestInvalidFailure() throws Exception {
		
		logger.info("BlogsServiceImplTest :: getFileTestFailure ");
		
		String blogsPath="/Users/nisum/Documents/BlogAttachments";
		
		ReflectionTestUtils.setField(blogsServiceImpl, "blogsAttachmentPath", blogsPath);
		
		//when(blogsServiceImpl.getBlogsAttachmentPath()).thenReturn(blogsPath);
		
		
		Path path=blogsServiceImpl.getFile(null,new Integer(6),"epfuan_23Oct2017_11_29_05_PM.pdf");
	}
	
	@Test(expected=BlogServiceException.class)
	public void getFileTestFileFailure() throws Exception {
		
		logger.info("BlogsServiceImplTest :: getFileTestFailure ");
		
		String blogsPath="/Users/nisum/Documents/BlogAttachments";
		
		ReflectionTestUtils.setField(blogsServiceImpl, "blogsAttachmentPath", blogsPath);
		
		//when(blogsServiceImpl.getBlogsAttachmentPath()).thenReturn(blogsPath);
		
		Path path=blogsServiceImpl.getFile("sjbasha@nisum.com",new Integer(6),"epfan_23Oct2017_11_29_05_PM.pdf");
	}

	
	@Test
	public void removeFileTestSuccess() throws Exception {
		logger.info("BlogsServiceImplTest :: removeFileTestSuccess ");
		
		String blogsPath="/Users/nisum/Documents/BlogAttachments";
		
		ReflectionTestUtils.setField(blogsServiceImpl, "blogsAttachmentPath", blogsPath);
		
		when(blogsDAOImpl.blogExists(6)).thenReturn(true);
		
		boolean expMsg=blogsServiceImpl.removeFile("sjbasha@nisum.com",new Integer(6),"epfuan_23Oct2017_11_41_21_PM.pdf");
		
		assertEquals(expMsg,true);
	}
	
	@Test
	public void removeFileTestFailure() throws Exception {
		logger.info("BlogsServiceImplTest :: removeFileTestSuccess ");
		
		String blogsPath="/Users/nisum/Documents/BlogAttachments";
		
		ReflectionTestUtils.setField(blogsServiceImpl, "blogsAttachmentPath", blogsPath);
		
		when(blogsDAOImpl.blogExists(6)).thenReturn(true);
		
		boolean expMsg=blogsServiceImpl.removeFile("sjbasha@nisum.com",new Integer(6),"epfuan_23Oct2017_11_38_43_PM.pdf");
		
		assertEquals(expMsg,false);
	}
	
	@Test
	public void getAllBlogsByEmailIdTestSuccess() throws Exception{
		logger.info("BlogsServiceImplTest :: getAllBlogsByEmailIdTestSuccess ");
		
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
		
		when(blogsDAOImpl.getAllBlogsByUserMailId("sjbasha@nisum.com")).thenReturn(blogs);
		List<BlogsDTO> expMsg=blogsServiceImpl.getAllBlogsByEmailId("sjbasha@nisum.com");
		BlogsDTO expObj=expMsg.get(0);
		BlogsDTO actObj=blogsDTO.get(0);
		assertThat(actObj).isEqualToComparingFieldByField(expObj);
	}
	
	@Test
	public void getAllBlogsPainationByMailIdSuccess() {
		logger.info("BlogsServiceImplTest :: getAllBlogsPainationByMailIdSuccess ");
		
		List<BlogsDTO> blogsDTO=new ArrayList<BlogsDTO>();
		List<Blogs> blogs=new ArrayList<Blogs>();
		//List<String> listValue=new ArrayList<String>();
		//listValue.add("epfuan_17Oct2017_04_44_00_PM.pdf");
		//listValue.add("hdfcAuthImg_17Oct2017_04_44_00_PM.jpg");
		
		Integer id=new Integer(22);
		long millis=1508238840000L;
		Timestamp createdDate=new Timestamp(millis);
		
		BlogsDTO blogDTO=new BlogsDTO();
		blogDTO.setBlogsId(id);
		blogDTO.setCreatedDate(createdDate);
		blogDTO.setDescription("123dddddddddd");
		blogDTO.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blogDTO.setUserId(103);
		//blogDTO.setFileNames(listValue);
		blogsDTO.add(blogDTO);
		
		
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("123dddddddddd");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blog.setUserId(103);
		blogs.add(blog);
		
		when(blogsDAOImpl.getAllBlogsPagination(3, 5)).thenReturn(blogs);
		List<BlogsDTO> expMsg=blogsServiceImpl.getAllBlogsPaination(3, 5);
		BlogsDTO expObj=expMsg.get(0);
		BlogsDTO actObj=blogsDTO.get(0);
		assertThat(actObj).isEqualToComparingFieldByField(expObj);
	}
	
	@Test
	public void getAllBlogsPainationSuccess() {
		logger.info("BlogsServiceImplTest :: getAllBlogsPainationSuccess ");
		
		List<BlogsDTO> blogsDTO=new ArrayList<BlogsDTO>();
		List<Blogs> blogs=new ArrayList<Blogs>();
		//List<String> listValue=new ArrayList<String>();
		//listValue.add("epfuan_17Oct2017_04_44_00_PM.pdf");
		//listValue.add("hdfcAuthImg_17Oct2017_04_44_00_PM.jpg");
		
		Integer id=new Integer(22);
		long millis=1508238840000L;
		Timestamp createdDate=new Timestamp(millis);
		
		BlogsDTO blogDTO=new BlogsDTO();
		blogDTO.setBlogsId(id);
		blogDTO.setCreatedDate(createdDate);
		blogDTO.setDescription("123dddddddddd");
		blogDTO.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blogDTO.setUserId(103);
		//blogDTO.setFileNames(listValue);
		blogsDTO.add(blogDTO);
		
		
		Blogs blog=new Blogs();
		blog.setBlogsId(id);
		blog.setCreatedDate(createdDate);
		blog.setDescription("123dddddddddd");
		blog.setPath("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22");
		blog.setUserId(103);
		blogs.add(blog);
		
		when(blogsDAOImpl.getAllBlogsPaginationByMailId("sjbasha@nisum.com",3, 5)).thenReturn(blogs);
		List<BlogsDTO> expMsg=blogsServiceImpl.getAllBlogsPainationByMailId("sjbasha@nisum.com", 3, 5);
		BlogsDTO expObj=expMsg.get(0);
		BlogsDTO actObj=blogsDTO.get(0);
		assertThat(actObj).isEqualToComparingFieldByField(expObj);
	}
	
	@Test
	public void getAllBlogsCountSuccess() {
		logger.info("BlogsServiceImplTest :: getAllBlogsCountSuccess ");
		
		Long ln=10L;
		when(blogsDAOImpl.getAllBlogsCount()).thenReturn(ln);
		Long expVal=blogsServiceImpl.getAllBlogsCount();
		assertEquals(expVal,ln);
	}
}
