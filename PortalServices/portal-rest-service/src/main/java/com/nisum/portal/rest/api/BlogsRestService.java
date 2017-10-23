package com.nisum.portal.rest.api;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.BlogService;
import com.nisum.portal.service.dto.BlogsDTO;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.exception.BlogServiceException;
import com.nisum.portal.util.BlogsServiceUtil;


/**
 * @author nisum
 */
@Controller
@RestController
@RequestMapping(value = "/v1/Blogs")
public class BlogsRestService {
	
	private static Logger logger = LoggerFactory.getLogger(BlogsRestService.class);

	@Autowired
	BlogService blogService;
	
	@Value("${blogs.attachment.path}")
	private String blogsAttachmentPath;
	/**
	 * getAllBlogs
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public Object getAllBlogs() throws BlogServiceException {
		logger.info("BlogsRestService :: getAllBlogs");
		try {
			List<BlogsDTO> blogsDTO= blogService.getAllBlogs();
			for(BlogsDTO blogDTO : blogsDTO) {
				blogDTO.setPath("");
			}
			return blogsDTO;
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getAllBlogs Error ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
		
	}
	
	/**
	 * getBlog
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/retrieve/{id}", method = RequestMethod.GET)
	public Object getBlog(@PathVariable Integer id) throws BlogServiceException {
		logger.info("BlogsRestService :: getBlog");
		try {
			BlogsDTO blogsDTO=blogService.getBlog(id);
			blogsDTO.setPath("");
			return blogsDTO;
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getBlog ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * getFile
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/retrieve/getFile/{fileName}/{userMailId}/{blogId}", method = RequestMethod.GET)
	public Object getFile(@PathVariable String userMailId,@PathVariable Integer blogId,@PathVariable String fileName) {
		logger.info("BlogsRestService :: getFile");
		try {
			
			Path path = blogService.getFile(userMailId, blogId, fileName);
			byte[] document = Files.readAllBytes(path);
	        HttpHeaders header = new HttpHeaders();
	        header=BlogsServiceUtil.setFileTypeForHttpHeader(header,fileName);
	        header.set("Content-Disposition", "inline; filename=" +fileName);
	        header.setContentLength(document.length);

	        return new HttpEntity<byte[]>(document, header);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getFile Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}

	}
	
	
	/**
	 * updateBlog
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object updateBlog(@RequestBody BlogsDTO blogsDTO) throws BlogServiceException {
		logger.info("BlogsRestService :: updateBlog");
		try {
			return blogService.updateBlog(blogsDTO);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: updateBlog Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * removeBlog
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/remove/{userMailId}/{id}", method = RequestMethod.DELETE)
	public Object removeBlog(@PathVariable Integer id,@PathVariable String userMailId) throws BlogServiceException {
		logger.info("BlogsRestService :: removeBlog");
		try {
			blogService.removeBlog(id,userMailId);
			String resStatus="Success";
			ResponseEntity<String> response=new ResponseEntity<String>(resStatus,HttpStatus.OK);
			return response;
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: removeBlog Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * removeFile
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/remove/file/{fileName}/{userMailId}/{blogId}", method = RequestMethod.DELETE)
	public Object removeFile(@PathVariable Integer blogId,@PathVariable String userMailId,@PathVariable String fileName) throws BlogServiceException {
		logger.info("BlogsRestService :: removeFile");
		try {
			if(blogService.removeFile(userMailId, blogId, fileName)) {
				String resStatus="Success";
				ResponseEntity<String> response=new ResponseEntity<String>(resStatus,HttpStatus.OK);
				return response;
			}else {
				throw new BlogServiceException("Error while deleting file "+fileName);
			}
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: removeBlog Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * addBlog
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value="/add/addBlog", method=RequestMethod.POST)
	public @ResponseBody Object addBlog(@Context HttpServletRequest request) throws BlogServiceException {
		logger.info("BlogsRestService :: addBlog");
		try {
			
			BlogsDTO blogsDTO=blogService.parseRequestToGetBlogsDTO(request);
			
			BlogsDTO addedBlog=blogService.addBlog(blogsDTO);
			
			BlogsDTO updateBlog=blogService.parseRequestToStoreUploads(request, blogsAttachmentPath, addedBlog);
			
			BlogsDTO updatedBlog=blogService.updateBlog(updateBlog);
			
			if(updatedBlog.getPath()!=null) {
				updatedBlog.setPath("");
			}
			
			return updatedBlog;
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: addBlog Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * uploadAttachment
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	@RequestMapping(value="/add/uploadAttachment", method=RequestMethod.POST)
	public @ResponseBody Object uploadAttachment(@Context HttpServletRequest request) throws BlogServiceException {
		logger.info("BlogsRestService :: uploadAttachment");
		try {		
			String userMailId=request.getParameter("userMailId");
			String blogId=request.getParameter("blogId");
			if((userMailId!=null)&&(blogId!=null)) {
				blogService.uploadAttachment(request, blogsAttachmentPath+File.separator+userMailId+File.separator+blogId);
				return "Uploaded File(s) Successfully!!!";
			}else {
				throw new BlogServiceException("UserMailId or BlogId are null.");
			}
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: uploadAttachment Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * exceptionHandler
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(BlogServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		Errors errors = new Errors();

		errors.setErrorCode("Errors-Blogs");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.OK);
	}
}
