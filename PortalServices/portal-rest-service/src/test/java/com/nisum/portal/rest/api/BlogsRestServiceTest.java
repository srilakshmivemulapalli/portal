package com.nisum.portal.rest.api;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.service.api.BlogService;
import com.nisum.portal.service.dto.BlogsDTO;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.exception.BlogServiceException;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BlogsRestServiceTest {
	
	@InjectMocks
	BlogsRestService mainController;

	@Mock
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
	public void getAllBlogsFailureTest() throws BlogServiceException {
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
	public void getBlogTestSuccess() throws BlogServiceException{
		logger.info("BlogsRestServiceTest :: getBlogTestSuccess");
		
		Integer id=new Integer(1);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		BlogsDTO expMesg=new BlogsDTO();
		expMesg.setBlogsId(id);
		expMesg.setCreatedDate(createdDate);
		expMesg.setCategoryId(101);
		expMesg.setDescription("aaaaaa");
		expMesg.setPath("bbbbbbb");
		expMesg.setUserId(101);
		
		when(blogService.getBlog(id)).thenReturn(expMesg);
		
		BlogsDTO actMesg=(BlogsDTO)mainController.getBlog(id);
		
		assertEquals(expMesg,actMesg);
		
	}
	
	@Test
	public void getBlogTestFailure() throws BlogServiceException{
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

}
