package com.nisum.portal.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.CategoriesService;
import com.nisum.portal.service.exception.CategoryServiceException;

@RestController
@RequestMapping(value = "/v1/category")
public class CategoriesRestService {
	
private static Logger logger = LoggerFactory.getLogger(CategoriesRestService.class);
	
	@Autowired
	private CategoriesService categoriesService;

	/**
	 * damagesType
	 * @return
	 * @throws InventoryReceiveException
	 */
	@RequestMapping(value="/retrieve",method=RequestMethod.GET)
	public Object categories() throws CategoryServiceException {
		 logger.info("CategoriesRestService :: categories");
		 return categoriesService.getCategories();
	}
	
	@RequestMapping(value="/retrieve/{id}",method=RequestMethod.GET)
	public Object category(@PathVariable Integer id) throws CategoryServiceException {
		 logger.info("CategoriesRestService :: category");
		 return categoriesService.getCategory(id);
	}

}
