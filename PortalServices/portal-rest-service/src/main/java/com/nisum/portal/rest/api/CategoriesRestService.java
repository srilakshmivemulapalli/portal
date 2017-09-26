package com.nisum.portal.rest.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.CategoriesService;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.CategoryServiceException;
import com.nisum.portal.util.Constants;

/**
 * @author nisum
 */
@RestController
@RequestMapping(value = "/v1/category")
public class CategoriesRestService {

	private static Logger logger = LoggerFactory.getLogger(CategoriesRestService.class);

	@Autowired
	private CategoriesService categoriesService;

	/**
	 * categories
	 * 
	 * @return
	 * @throws CategoryServiceException
	 */
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public Object categories() throws CategoryServiceException {
		logger.info("CategoriesRestService :: categories");
		return categoriesService.getCategories();
	}

	/**
	 * @param category
	 * @return
	 * @throws CategoryServiceException
	 */
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<ServiceStatusDto> addCategory(@RequestBody CategoriesDTO category)
			throws CategoryServiceException {
		logger.info("CategoriesRestService :: addCategories");
		ServiceStatusDto servicedto = categoriesService.addCategory(category);
		if (servicedto.isStatus())
			return new ResponseEntity<ServiceStatusDto>(servicedto, HttpStatus.OK);
		else {
			logger.error("Category already Exists");
			throw new CategoryServiceException(Constants.CATEGORY_EXISTS);
		}
	}
	@RequestMapping(value="/updateCategory",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceStatusDto> updateCategories(@RequestBody CategoriesDTO categoriesDTO) throws CategoryServiceException
	{
		logger.info("CategoriesRestService :: updateCategories :: Category Details "+categoriesDTO.toString());
		try
		{
				ServiceStatusDto servicedto = categoriesService.update(categoriesDTO);
				if(servicedto.isStatus())
					return new ResponseEntity<ServiceStatusDto>(servicedto,HttpStatus.OK);
				else
				{
					throw new CategoryServiceException(Constants.CATEGORY_EXISTS);
				}
		}
		catch(Exception e)
		{
			logger.error("Unable To Update Categories with categoryId not found."+categoriesDTO.getCategoryId());
			throw new CategoryServiceException(Constants.CATEGORY_EXISTS);
		}
	}

	@RequestMapping(value = "/retrieve/{id}", method = RequestMethod.GET)
	public Object category(@PathVariable Integer id) throws CategoryServiceException {
		logger.info("CategoriesRestService :: category");
		return categoriesService.getCategory(id);
	}

	@RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<ServiceStatusDto> deletingCategories(@PathVariable("categoryId") Integer categoryId) throws CategoryServiceException {
		logger.info("CategoriesRestService :: deleteCategory");
		
		   ServiceStatusDto    servicedto = categoriesService.deleteCategories(categoryId);
		   
		    if(servicedto.isStatus())
		    {
		      	return  new ResponseEntity<ServiceStatusDto>(servicedto,HttpStatus.OK);
		    }
		    else 
		    {
		    	  System.out.println("Rest Failure");
			    	logger.error(Constants.CATEGORY_NOT_EXIST);
			    	throw new CategoryServiceException(Constants.CATEGORY_NOT_EXIST);
		    }
		
	}

	/**
	 * exceptionHandler
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(CategoryServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		Errors errors = new Errors();

		errors.setErrorCode("Error-Categories");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.OK);
	}
}