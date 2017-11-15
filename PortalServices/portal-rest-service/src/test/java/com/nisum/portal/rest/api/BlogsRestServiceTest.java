package com.nisum.portal.rest.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;
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
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

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
		BlogsDTO blogsDTO=new BlogsDTO();
		List<BlogsDTO> expMsg=new ArrayList<BlogsDTO>();
		expMsg.add(blogsDTO);
		when(mainController.getAllBlogs()).thenReturn(expMsg);
		//List<BlogsDTO> actMsg=(List<BlogsDTO>) mainController.getAllBlogs();
		ResponseEntity<List<BlogsDTO>> actMsg=(ResponseEntity<List<BlogsDTO>>) mainController.getAllBlogs();
		assertEquals(expMsg,actMsg.getBody());
	}
	
	@Test
	public void getAllBlogsFailureTest() throws Exception {
		logger.info("BlogsRestServiceTest :: getAllBlogsFailureTest");
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
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
		
		BlogsDTO actMesg=((ResponseEntity<BlogsDTO>)mainController.getBlog(id)).getBody();
		
		assertEquals(expMesg,actMesg);
		
	}
	
	@Test
	public void getBlogTestFailure() throws Exception{
		logger.info("BlogsRestServiceTest :: getBlogTestFailure");
		Integer id=new Integer(1);
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
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
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
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
		expMesg.setTitle("titlePrev");
		expMesg.setCreatedDate(createdDate);
		expMesg.setDescription("aaaaaa");
		expMesg.setPath("bbbbbbb");
		expMesg.setUserId(101);
		
		BlogsDTO updateExpMesg=new BlogsDTO();
		updateExpMesg.setBlogsId(id);
		updateExpMesg.setTitle("titleUpdate");
		updateExpMesg.setCreatedDate(createdDate);
		updateExpMesg.setDescription("aaaaaa");
		updateExpMesg.setPath("bbbbbbb");
		updateExpMesg.setUserId(101);
		
		when(blogService.updateBlog(expMesg)).thenReturn(updateExpMesg);
		
		when(blogService.getBlog(id)).thenReturn(updateExpMesg);
		
		BlogsDTO actMsg=((ResponseEntity<BlogsDTO>)mainController.updateBlog(expMesg)).getBody();
		
		assertThat(actMsg).isEqualToComparingFieldByField(updateExpMesg);
	}
	
	@Test
	public void updateBlogTestInvalidDataSuccess()throws Exception {
		logger.info("BlogsRestServiceTest :: updateBlogTestSuccess");
		
		Integer id=new Integer(1);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		
		BlogsDTO expMesg=new BlogsDTO();
		expMesg.setBlogsId(id);
		expMesg.setTitle("titlePrev");
		expMesg.setCreatedDate(createdDate);
		expMesg.setDescription("aaaaaa");
		expMesg.setPath("bbbbbbb");
		expMesg.setUserId(101);
		
		BlogsDTO updateExpMesg=new BlogsDTO();
		updateExpMesg.setBlogsId(id);
		updateExpMesg.setTitle("titleUpdate");
		updateExpMesg.setCreatedDate(createdDate);
		updateExpMesg.setDescription("aaaaaa");
		updateExpMesg.setPath("bbbbbbb");
		updateExpMesg.setUserId(101);
		
		String resStatus="No modification found to update the Blog.";
		JSONObject jsonObj=new JSONObject();
		jsonObj.append("result", resStatus);
		
		when(blogService.updateBlog(expMesg)).thenReturn(updateExpMesg);
		
		when(blogService.getBlog(id)).thenReturn(expMesg);
		
		String actMsg=((ResponseEntity<String>)mainController.updateBlog(expMesg)).getBody();
		
		assertEquals(actMsg,jsonObj.toString());
	}
	
	@Test
	public void updateBlogTestFailure() throws Exception {
		logger.info("BlogsRestServiceTest :: updateBlogTestFailure");
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
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
		
		String resStatus="Blog Removed Successfully.";
		JSONObject jsonObj=new JSONObject();
		jsonObj.append("result", resStatus);
		ResponseEntity<String> resEntity= new ResponseEntity<String>(jsonObj.toString(), HttpStatus.OK);
		
		doNothing().when(blogService).removeBlog(id,userMailId);
		
		ResponseEntity<String> responseEntity=(ResponseEntity<String>)mainController.removeBlog(id,userMailId);
		
		assertEquals(responseEntity.getBody().toString(),resEntity.getBody().toString());
	}
	
	
	
	@Test
	public void removeBlogTestFailure() throws Exception {
		logger.info("BlogsRestServiceTest :: removeBlogTestFailure");
		
		Integer id=new Integer(1);
		String userMailId="sjbasha@nisum.com";
		
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
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
		String resStatus="File Removed Successfully.";
		JSONObject jsonObj=new JSONObject();
		jsonObj.append("result", resStatus);
		ResponseEntity<String> resEntity= new ResponseEntity<String>(jsonObj.toString(), HttpStatus.OK);
		
		when(blogService.removeFile(userMailId, blogId, fileName)).thenReturn(true);
		
		ResponseEntity<String> responseEntity=(ResponseEntity<String>)mainController.removeFile(blogId, userMailId, fileName);
		
		assertEquals(responseEntity.getBody().toString(),resEntity.getBody().toString());
		
	}
	
	@Test
	public void removeFileTestFailure() throws Exception {
		
		logger.info("BlogsRestServiceTest :: removeFileTestFailure ");
		
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
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
		
		String fileName="epfuan.pdf";
		
		InputStream inputStream=new FileInputStream("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22/epfuan_17Oct2017_04_44_00_PM.pdf");
		
		MultipartFile fileRequest=mock(MultipartFile.class);
		
		MultipartFile[] mFileArr=new MultipartFile[1];
		mFileArr[0]=fileRequest;
		
		when(mFileArr[0].getOriginalFilename()).thenReturn(fileName);
		
		when(mFileArr[0].getName()).thenReturn("uploads");
		
		when(mFileArr[0].getInputStream()).thenReturn(inputStream);
		
		when(blogService.parseRequestToGetBlogsDTO(request)).thenReturn(expMesg);
		
		when(blogService.addBlog(expMesg)).thenReturn(expMesg);
		
		when(blogService.parseRequestToStoreUploads(mFileArr,null,expMesg)).thenReturn(expMesg);
		
		when(blogService.updateBlog(expMesg)).thenReturn(expMesg);
		
		BlogsDTO actMesg=((ResponseEntity<BlogsDTO>) mainController.addBlog(mFileArr,request)).getBody();
		
		assertThat(actMesg).isEqualToComparingFieldByField(expMesg);
	}
	
	@Test
	public void addBlogTestFailure() throws Exception {
		
		logger.info("BlogsRestServiceTest :: addBlogTestFailure ");
		
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
		
		String fileName="epfuan.pdf";
		
		InputStream inputStream=new FileInputStream("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22/epfuan_17Oct2017_04_44_00_PM.pdf");
		
		MultipartFile fileRequest=mock(MultipartFile.class);
		
		MultipartFile[] mFileArr=new MultipartFile[1];
		mFileArr[0]=fileRequest;
		
		when(mFileArr[0].getOriginalFilename()).thenReturn(fileName);
		
		when(mFileArr[0].getName()).thenReturn("uploads");
		
		when(mFileArr[0].getInputStream()).thenReturn(inputStream);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		when(blogService.parseRequestToGetBlogsDTO(request)).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.addBlog(mFileArr,request);
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
		
	}
	
	@Test
	public void uploadAttachmentTestSuccess() throws Exception {
		
		logger.info("BlogsRestServiceTest :: uploadAttachmentTestSuccess ");
		
		String blogsPath="/Users/nisum/Documents/BlogAttachments";
		
		ReflectionTestUtils.setField(mainController, "blogsAttachmentPath", blogsPath);
		
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
		
		//ResponseEntity<String> respEntity=new ResponseEntity(actMsg,HttpStatus.OK);
		
		String fileName="epfuan.pdf";
		
		String dirPath="/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/6";
		
		List<Part> parts=new ArrayList<Part>();
		
		InputStream inputStream=new FileInputStream("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22/epfuan_17Oct2017_04_44_00_PM.pdf");
		
		MultipartFile fileRequest=mock(MultipartFile.class);
		
		MultipartFile[] mFileArr=new MultipartFile[1];
		mFileArr[0]=fileRequest;
		
		when(mFileArr[0].getOriginalFilename()).thenReturn(fileName);
		
		when(mFileArr[0].getName()).thenReturn("uploads");
		
		when(mFileArr[0].getInputStream()).thenReturn(inputStream);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		when(request.getParameter("emailId")).thenReturn("sjbasha@nisum.com");
		
		when(request.getParameter("blogId")).thenReturn("6");
		
		
		when(blogService.getBlog(6)).thenReturn(expMesg);
		
		when(blogService.uploadAttachment(mFileArr, dirPath)).thenReturn(dirPath);
		
		//String expMsg=(String) mainController.uploadAttachment(mFileArr,request);
		ResponseEntity<String> respEntityexpMsg=(ResponseEntity<String>) mainController.uploadAttachment(mFileArr,request);
		
		assertEquals(respEntityexpMsg.getBody(),actMsg);
		
	}
	
	@Test
	public void uploadAttachmentTestFailure() throws BlogServiceException {
		
		logger.info("BlogsRestServiceTest :: uploadAttachmentTestFailure ");
		
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		when(request.getParameter("userMailId")).thenReturn(null);
		
		
		ResponseEntity<Errors> expEntity= (ResponseEntity<Errors>) mainController.uploadAttachment(null,request);
		
		assertEquals(expEntity.getStatusCode(),responseEntity.getStatusCode());
		assertEquals(expEntity.getBody().getErrorCode(),responseEntity.getBody().getErrorCode());
		
	}
	
	@Test
	public void getAllBlogsCountTestSuccess() throws BlogServiceException, JSONException{
		logger.info("BlogsRestServiceTest :: getAllBlogsCountTestSuccess ");
		Long blogsCount=10L;
		JSONObject jsonObj=new JSONObject();
		jsonObj.append("count", blogsCount);
		ResponseEntity<String> resEntity=new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		
		when(blogService.getAllBlogsCount()).thenReturn(blogsCount);
		
		String actMsg=((ResponseEntity<String>)mainController.getAllBlogsCount()).getBody();
		assertEquals(resEntity.getBody(),actMsg);
	}
	
	@Test
	public void getAllBlogsCountTestFailure() throws JSONException, BlogServiceException {
		logger.info("BlogsRestServiceTest :: getAllBlogsCountTestFailure ");
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
		when(blogService.getAllBlogsCount()).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.getAllBlogsCount();
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void getAllBlogsByUserMailIdSuccess() throws BlogServiceException{
		
		logger.info("BlogsRestServiceTest :: getAllBlogsByUserMailIdSuccess");
		BlogsDTO blogsDTO=new BlogsDTO();
		List<BlogsDTO> expMsg=new ArrayList<BlogsDTO>();
		expMsg.add(blogsDTO);
		when(blogService.getAllBlogsByEmailId("sjbasha@nisum.com")).thenReturn(expMsg);
		//List<BlogsDTO> actMsg=(List<BlogsDTO>) mainController.getAllBlogs();
		ResponseEntity<List<BlogsDTO>> actMsg=(ResponseEntity<List<BlogsDTO>>) mainController.getAllBlogsByUserMailId("sjbasha@nisum.com");
		assertEquals(expMsg,actMsg.getBody());
	}
	
	@Test
	public void getAllBlogsByUserMailIdFailure() throws Exception {
		logger.info("BlogsRestServiceTest :: getAllBlogsFailureTest");
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
		when(blogService.getAllBlogsByEmailId("sjbasha@nisum.com")).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.getAllBlogsByUserMailId("sjbasha@nisum.com");
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void updateBlogAndAttachmentsTestSuccess() throws Exception {
		logger.info("BlogsRestServiceTest :: updateBlogAndAttachmentsTestSuccess");
		
		String blogsPath="/Users/nisum/Documents/BlogAttachments";
		
		ReflectionTestUtils.setField(mainController, "blogsAttachmentPath", blogsPath);
		
		Integer id=new Integer(1);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		
		BlogsDTO expMesg=new BlogsDTO();
		expMesg.setBlogsId(id);
		expMesg.setTitle("titlePrev");
		expMesg.setCreatedDate(createdDate);
		expMesg.setDescription("aaaaaa");
		expMesg.setPath("bbbbbbb");
		expMesg.setUserId(101);
		
		BlogsDTO updateExpMesg=new BlogsDTO();
		updateExpMesg.setBlogsId(id);
		updateExpMesg.setTitle("titleUpdate");
		updateExpMesg.setCreatedDate(createdDate);
		updateExpMesg.setDescription("aaaaaa");
		updateExpMesg.setPath("bbbbbbb");
		updateExpMesg.setUserId(101);
		
		MultipartFile fileRequest=mock(MultipartFile.class);
		
		String fileName="epfuan.pdf";
		
		InputStream inputStream=new FileInputStream("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22/epfuan_17Oct2017_04_44_00_PM.pdf");
		
		MultipartFile[] mFileArr=new MultipartFile[1];
		mFileArr[0]=fileRequest;
		
		when(mFileArr[0].getOriginalFilename()).thenReturn(fileName);
		
		when(mFileArr[0].getName()).thenReturn("uploads");
		
		when(mFileArr[0].getInputStream()).thenReturn(inputStream);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		when(blogService.parseRequestToGetBlogsDTOForUpdate(request)).thenReturn(expMesg);
		when(blogService.getBlog(id)).thenReturn(updateExpMesg);
		when(blogService.updateBlog(expMesg)).thenReturn(updateExpMesg);
		when(blogService.parseRequestToStoreUploads(mFileArr, blogsPath, expMesg)).thenReturn(expMesg);
		
		BlogsDTO actMsg=((ResponseEntity<BlogsDTO>)mainController.updateBlogAndAttachments(mFileArr, request)).getBody();
		
		assertThat(actMsg).isEqualToComparingFieldByField(updateExpMesg);
		
	}
	
	@Test
	public void updateBlogAndAttachmentsInvalidDataTestFailure() throws Exception {
		logger.info("BlogsRestServiceTest :: updateBlogAndAttachmentsInvalidDataTestFailure");
		
		String blogsPath="/Users/nisum/Documents/BlogAttachments";
		
		ReflectionTestUtils.setField(mainController, "blogsAttachmentPath", blogsPath);
		
		Integer id=new Integer(1);
		long millis=1507111208000L;
		Timestamp createdDate=new Timestamp(millis);
		
		BlogsDTO expMesg=new BlogsDTO();
		expMesg.setBlogsId(id);
		expMesg.setTitle("titlePrev");
		expMesg.setCreatedDate(createdDate);
		expMesg.setDescription("aaaaaa");
		expMesg.setPath("bbbbbbb");
		expMesg.setUserId(101);
		
		JSONObject jsonObj=new JSONObject();
		jsonObj.append("result", "File(s) uploaded successfully.");
		ResponseEntity<String> resEntity=new ResponseEntity<String>(jsonObj.toString(),HttpStatus.BAD_REQUEST);
		
		MultipartFile fileRequest=mock(MultipartFile.class);
		
		String fileName="epfuan.pdf";
		
		InputStream inputStream=new FileInputStream("/Users/nisum/Documents/BlogAttachments/sjbasha@nisum.com/22/epfuan_17Oct2017_04_44_00_PM.pdf");
		
		MultipartFile[] mFileArr=new MultipartFile[1];
		mFileArr[0]=fileRequest;
		
		when(mFileArr[0].getOriginalFilename()).thenReturn(fileName);
		
		when(mFileArr[0].getName()).thenReturn("uploads");
		
		when(mFileArr[0].getInputStream()).thenReturn(inputStream);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		
		when(blogService.parseRequestToGetBlogsDTOForUpdate(request)).thenReturn(expMesg);
		when(blogService.getBlog(id)).thenReturn(expMesg);
		when(blogService.updateBlog(expMesg)).thenReturn(expMesg);
		when(blogService.parseRequestToStoreUploads(mFileArr, blogsPath, expMesg)).thenReturn(expMesg);
		
		String actMsg=((ResponseEntity<String>)mainController.updateBlogAndAttachments(mFileArr, request)).getBody();
		
		assertEquals(actMsg,resEntity.getBody());
		
	}
	
	@Test
	public void updateBlogAndAttachmentsTestFailure() throws Exception{
		logger.info("BlogsRestServiceTest :: updateBlogAndAttachmentsTestFailure");
		
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		HttpServletRequest request=mock(HttpServletRequest.class);
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
		when(blogService.parseRequestToGetBlogsDTOForUpdate(request)).thenThrow(Exception.class);
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.updateBlogAndAttachments(null, request);
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
		
	}
	
	@Test
	public void getAllBlogsPaginationTestSuccess() throws Exception{
		logger.info("BlogsRestServiceTest :: getAllBlogsPaginationTestSuccess");
		BlogsDTO blogsDTO=new BlogsDTO();
		Integer pageNum=2;
		Integer pageSize=5;
		List<BlogsDTO> expMsg=new ArrayList<BlogsDTO>();
		expMsg.add(blogsDTO);
		when(blogService.getAllBlogsPaination(pageNum, pageSize)).thenReturn(expMsg);
		//List<BlogsDTO> actMsg=(List<BlogsDTO>) mainController.getAllBlogs();
		ResponseEntity<List<BlogsDTO>> actMsg=(ResponseEntity<List<BlogsDTO>>) mainController.getAllBlogsPagination(pageNum, pageSize);
		assertEquals(expMsg,actMsg.getBody());
	}
	
	@Test
	public void getAllBlogsPaginationTestFailure() throws Exception {
		logger.info("BlogsRestServiceTest :: getAllBlogsPaginationTestFailure");
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		Integer pageNum=2;
		Integer pageSize=5;
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
		when(blogService.getAllBlogsPaination(pageNum, pageSize)).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.getAllBlogsPagination(pageNum, pageSize);
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void getAllBlogsPaginationByMailIdTestSuccess() throws Exception{
		logger.info("BlogsRestServiceTest :: getAllBlogsPaginationTestSuccess");
		BlogsDTO blogsDTO=new BlogsDTO();
		Integer pageNum=2;
		Integer pageSize=5;
		List<BlogsDTO> expMsg=new ArrayList<BlogsDTO>();
		expMsg.add(blogsDTO);
		when(blogService.getAllBlogsPainationByMailId("sjbasha@nisum.com", pageNum, pageSize)).thenReturn(expMsg);
		//List<BlogsDTO> actMsg=(List<BlogsDTO>) mainController.getAllBlogs();
		ResponseEntity<List<BlogsDTO>> actMsg=(ResponseEntity<List<BlogsDTO>>) mainController.getAllBlogsPaginationByMailId("sjbasha@nisum.com", pageNum, pageSize);
		assertEquals(expMsg,actMsg.getBody());
	}
	
	@Test
	public void getAllBlogsPaginationByMailIdTestFailure() throws Exception {
		logger.info("BlogsRestServiceTest :: getAllBlogsPaginationTestFailure");
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		Integer pageNum=2;
		Integer pageSize=5;
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
		when(blogService.getAllBlogsPainationByMailId("sjbasha@nisum.com", pageNum, pageSize)).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.getAllBlogsPaginationByMailId("sjbasha@nisum.com", pageNum, pageSize);
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void getAllBlogsCountByUserMailIDTestSuccess() throws Exception{
		logger.info("BlogsRestServiceTest :: getAllBlogsCountByUserMailIDTestSuccess");
		
		Long blogsCount=10L;
		JSONObject jsonObj=new JSONObject();
		jsonObj.append("count", blogsCount);
		ResponseEntity<String> resEntity=new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		
		when(blogService.getAllBlogsCountByMailId("sjbasha@nisum.com")).thenReturn(blogsCount);
		
		String actMsg=((ResponseEntity<String>)mainController.getAllBlogsCountByUserMailID("sjbasha@nisum.com")).getBody();
		assertEquals(resEntity.getBody(),actMsg);
	}
	
	@Test
	public void getAllBlogsCountByUserMailIDTestFailure() throws JSONException, BlogServiceException {
		logger.info("BlogsRestServiceTest :: getAllBlogsCountByUserMailIDTestFailure ");
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Blogs");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		
		when(blogService.getAllBlogsCountByMailId("sjbasha@nisum.com")).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.getAllBlogsCountByUserMailID("sjbasha@nisum.com");
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
}
