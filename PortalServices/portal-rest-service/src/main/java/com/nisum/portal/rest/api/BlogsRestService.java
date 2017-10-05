package com.nisum.portal.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.BlogService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.exception.BlogServiceException;
import com.nisum.portal.service.exception.CategoryServiceException;


/**
 * @author nisum
 */
@RestController
@RequestMapping(value = "/v1/Blogs")
public class BlogsRestService {
	
	private static Logger logger = LoggerFactory.getLogger(BlogsRestService.class);

	@Autowired
	BlogService blogService;
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
			return blogService.getAllBlogs();
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
		logger.info("CategoriesRestService :: getBlog");
		try {
			return blogService.getBlog(id);
		}
		catch(Exception e) {
			logger.error("BlogServiceException :: getBlog ");
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
