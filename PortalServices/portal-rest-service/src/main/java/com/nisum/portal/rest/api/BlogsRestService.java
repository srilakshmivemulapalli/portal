package com.nisum.portal.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	 * categories
	 * 
	 * @return
	 * @throws CategoryServiceException
	 */
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public Object categories() throws BlogServiceException {
		logger.info("BlogsRestService :: Blogs");
		try {
		return blogService.getAllBlogs();
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: Blogs ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Categories");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
		
	}
	
	
	

}
