package com.nisum.portal.rest.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.service.api.BlogService;
import com.nisum.portal.service.dto.BlogsDTO;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.exception.BlogServiceException;
import com.nisum.portal.util.BlogsServiceUtil;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BlogsRestServiceTest {
	
	@InjectMocks
	BlogsRestService mainController;

	private static Logger logger = LoggerFactory.getLogger(BlogsRestServiceTest.class);

	@Mock
	BlogService blogService;
	
	@Test
	public void getAllBlogsSuccessTest() throws BlogServiceException {
		logger.info("BlogsRestServiceTest :: getAllBlogsSuccessTest");
		List<BlogsDTO> expMsg=new ArrayList<BlogsDTO>();
		when(mainController.getAllBlogs()).thenReturn(expMsg);
		List<BlogsDTO> actMsg=(List<BlogsDTO>) mainController.getAllBlogs();
		assertEquals(expMsg,actMsg);
	}
	
	@Test
	public void getAllBlogsFailureTest() throws Exception {
		logger.info("BlogsRestServiceTest :: getAllBlogsFailureTest");
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		
		when(blogService.getAllBlogs()).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.getAllBlogs();
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void getBlogTestSuccess() throws Exception{
		logger.info("BlogsRestServiceTest :: getBlogTestSuccess");
		
		Integer id=new Integer(1);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		BlogsDTO expMesg=new BlogsDTO();
		expMesg.setBlogsId(id);
		expMesg.setCreatedDate(createdDate);
		expMesg.setDescription("aaaaaa");
		expMesg.setPath("bbbbbbb");
		expMesg.setUserId(101);
		
		when(blogService.getBlog(id)).thenReturn(expMesg);
		
		BlogsDTO actMesg=(BlogsDTO)mainController.getBlog(id);
		
		assertEquals(expMesg,actMesg);
		
	}
	
	@Test
	public void getBlogTestFailure() throws Exception{
		logger.info("BlogsRestServiceTest :: getBlogTestFailure");
		Integer id=new Integer(1);
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		
		when(blogService.getBlog(id)).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.getBlog(id);
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void getFileTestSuccess() throws Exception {
		logger.info("BlogsRestServiceTest :: getFileTestSuccess");
		
		
		String fileStr="/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/6/epfuan_17Oct2017_04_44_00_PM_23Oct2017_10_08_15_PM.pdf";
		
		File file=new File(fileStr);
		
		Path path=file.toPath();
		
		byte[] document = Files.readAllBytes(path);
		
		HttpHeaders header = new HttpHeaders();
	    header=BlogsServiceUtil.setFileTypeForHttpHeader(header,"epfuan_17Oct2017_04_44_00_PM_23Oct2017_10_08_15_PM.pdf");
	    header.set("Content-Disposition", "inline; filename=" +"epfuan_17Oct2017_04_44_00_PM_23Oct2017_10_08_15_PM.pdf");
	    header.setContentLength(document.length);
		
		when(blogService.getFile("sjbasha@nisum.com", 6, "epfuan_17Oct2017_04_44_00_PM_23Oct2017_10_08_15_PM.pdf")).thenReturn(path);
		
		HttpEntity<byte[]> obj=(HttpEntity<byte[]>) mainController.getFile("sjbasha@nisum.com", 6, "epfuan_17Oct2017_04_44_00_PM_23Oct2017_10_08_15_PM.pdf");
		
		HttpHeaders expHeader=obj.getHeaders();
		byte[] expDocument=obj.getBody();
		
		assertEquals(expHeader,header);
	}
	
	@Test
	public void getFileTestFailure() throws Exception {
		logger.info("BlogsRestServiceTest :: getFileTestFailure");
		
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		
		//when(blogService.getFile(null, null, "epfuan_17Oct2017_04_44_00_PM_23Oct2017_10_08_15_PM.pdf")).thenReturn(null);
		
		ResponseEntity<Errors> obj=(ResponseEntity<Errors>) mainController.getFile(null, null, null);
		
		assertEquals(responseEntity.getStatusCode(),obj.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),obj.getBody().getErrorCode());
	}
	
	@Test
	public void updateBlogTestSuccess()throws Exception {
		logger.info("BlogsRestServiceTest :: updateBlogTestSuccess");
		
		Integer id=new Integer(1);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		BlogsDTO expMesg=new BlogsDTO();
		expMesg.setBlogsId(id);
		expMesg.setCreatedDate(createdDate);
		expMesg.setDescription("aaaaaa");
		expMesg.setPath("bbbbbbb");
		expMesg.setUserId(101);
		
		when(blogService.updateBlog(expMesg)).thenReturn(expMesg);
		
		when(blogService.getBlog(id)).thenReturn(expMesg);
		
		BlogsDTO actMsg=(BlogsDTO)mainController.updateBlog(expMesg);
		
		assertThat(actMsg).isEqualToComparingFieldByField(expMesg);
	}
	
	@Test
	public void updateBlogTestFailure() throws Exception {
		logger.info("BlogsRestServiceTest :: updateBlogTestFailure");
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		Integer id=new Integer(1);
		BlogsDTO expMesg=new BlogsDTO();
		expMesg.setBlogsId(id);
		
		when(blogService.updateBlog(expMesg)).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.updateBlog(expMesg);
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void removeBlogTestSuccess() throws Exception {
		logger.info("BlogsRestServiceTest :: removeBlogTestSuccess");
		
		Integer id=new Integer(1);
		String userMailId="sjbasha@nisum.com";
		
		doNothing().when(blogService).removeBlog(id,userMailId);
		
		ResponseEntity<String> responseEntity=(ResponseEntity<String>)mainController.removeBlog(id,userMailId);
		
		assertEquals(responseEntity.getBody().toString(),"Success");
	}
	
	@Test
	public void removeBlogTestFailure() throws Exception {
		logger.info("BlogsRestServiceTest :: removeBlogTestFailure");
		
		Integer id=new Integer(1);
		String userMailId="sjbasha@nisum.com";
		
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		
		doThrow(new BlogServiceException("No Blog/UserMailId found with "+id+"/"+userMailId)).when(blogService).removeBlog(id,userMailId);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.removeBlog(id, userMailId);
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void removeFileTestSuccess() throws Exception {
		logger.info("BlogsRestServiceTest :: removeFileTestSuccess ");
		
		Integer blogId=new Integer(1);
		String userMailId="sjbasha@nisum.com";
		String fileName="tempFile";
		
		when(blogService.removeFile(userMailId, blogId, fileName)).thenReturn(true);
		
		ResponseEntity<String> responseEntity=(ResponseEntity<String>)mainController.removeFile(blogId, userMailId, fileName);
		
		assertEquals(responseEntity.getBody().toString(),"Success");
		
	}
	
	@Test
	public void removeFileTestFailure() throws Exception {
		
		logger.info("BlogsRestServiceTest :: removeFileTestFailure ");
		
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		
		Integer blogId=new Integer(1);
		String userMailId="sjbasha@nisum.com";
		String fileName="tempFile";
		
		when(blogService.removeFile(userMailId, blogId, fileName)).thenReturn(false);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.removeFile(blogId, userMailId, fileName);
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void addBlogTestSuccess() throws Exception {
		
		logger.info("BlogsRestServiceTest :: addBlogTestSuccess ");
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		Integer id=new Integer(1);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		BlogsDTO expMesg=new BlogsDTO();
		expMesg.setBlogsId(id);
		expMesg.setCreatedDate(createdDate);
		expMesg.setDescription("aaaaaa");
		expMesg.setPath("bbbbbbb");
		expMesg.setUserId(101);
		
		when(blogService.parseRequestToGetBlogsDTO(request)).thenReturn(expMesg);
		
		when(blogService.addBlog(expMesg)).thenReturn(expMesg);
		
		when(blogService.parseRequestToStoreUploads(request,null,expMesg)).thenReturn(expMesg);
		
		when(blogService.updateBlog(expMesg)).thenReturn(expMesg);
		
		//BlogsDTO actMesg=(BlogsDTO) mainController.addBlog(request);
		
		//assertThat(actMesg).isEqualToComparingFieldByField(expMesg);
	}
	
	@Test
	public void addBlogTestFailure() throws Exception {
		
		logger.info("BlogsRestServiceTest :: addBlogTestFailure ");
		
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		when(blogService.parseRequestToGetBlogsDTO(request)).thenThrow(Exception.class);
		
		//ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.addBlog(request);
		
		//assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		//assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
		
	}
	
	@Test
	public void uploadAttachmentTestSuccess() throws Exception {
		
		logger.info("BlogsRestServiceTest :: uploadAttachmentTestSuccess ");
		
		Integer id=new Integer(6);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		BlogsDTO expMesg=new BlogsDTO();
		expMesg.setBlogsId(id);
		expMesg.setCreatedDate(createdDate);
		expMesg.setDescription("aaaaaa");
		expMesg.setPath("bbbbbbb");
		expMesg.setUserMailId("sjbasha@nisum.com");
		expMesg.setUserId(101);
		
		String actMsg="Uploaded File(s) Successfully!!!";
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		when(request.getParameter("userMailId")).thenReturn("sjbasha@nisum.com");
		
		when(request.getParameter("blogId")).thenReturn("6");
		
		when(blogService.validateHttpRequestUploads(request)).thenReturn(true);
		
		when(blogService.getBlog(6)).thenReturn(expMesg);
		
		String expMsg=(String) mainController.uploadAttachment(request);
		
		assertEquals(expMsg,actMsg);
		
	}
	
	@Test
	public void uploadAttachmentTestFailure() throws BlogServiceException {
		
		logger.info("BlogsRestServiceTest :: uploadAttachmentTestFailure ");
		
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		when(request.getParameter("userMailId")).thenReturn(null);
		
		
		ResponseEntity<Errors> expEntity= (ResponseEntity<Errors>) mainController.uploadAttachment(request);
		
		assertEquals(expEntity.getStatusCode(),responseEntity.getStatusCode());
		assertEquals(expEntity.getBody().getErrorCode(),responseEntity.getBody().getErrorCode());
		
	}

}
